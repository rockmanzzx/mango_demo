package org.example.core.page;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.List;

public class MyBatisPageHelper {

    public static final String findPage = "selectAll";

    public static PageResult findPage(PageRequest pageRequest, Object mapper) {
        return findPage(pageRequest, mapper, findPage);
    }

//    public static PageResult findPage(PageRequest pageRequest, Object mapper, String queryMethodName, Object ...args) {
//        int pageNum = pageRequest.getPageNum();
//        int pageSize = pageRequest.getPageSize();
//        PageHelper.startPage(pageNum, pageSize);
//        Method method = ReflectionUtils.findMethod(mapper.getClass(), queryMethodName);
//        Object result = ReflectionUtils.invokeMethod(method, mapper, args);
//        return getPageResult(pageRequest, new PageInfo<>((List) result));
//    }

    public static PageResult findPage(PageRequest pageRequest, Object mapper, String queryMethodName, Object... args) {
        if (mapper == null) {
            throw new IllegalArgumentException("Mapper object cannot be null");
        }

        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);

        Method method = ReflectionUtils.findMethod(mapper.getClass(), queryMethodName);
        if (method == null) {
            throw new IllegalArgumentException("Method " + queryMethodName + " not found in " + mapper.getClass().getName());
        }

        try {
            Object result = ReflectionUtils.invokeMethod(method, mapper, args);
            if (result == null) {
                throw new RuntimeException("Method " + queryMethodName + " returned null");
            }
            if (!(result instanceof List)) {
                throw new RuntimeException("Method " + queryMethodName + " did not return a List");
            }
            return getPageResult(pageRequest, new PageInfo<>((List) result));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Failed to invoke method " + queryMethodName + ". Check if the arguments match the method parameters.", e);
        }
    }

    /**
     * 封装分页信息
     * @param pageRequest
     * @param pageInfo
     * @return
     */
    private static <T> PageResult getPageResult(PageRequest pageRequest, PageInfo<?> pageInfo) {
        PageResult pageResult = new PageResult();
        pageResult.setPageNum(pageInfo.getPageNum());
        pageResult.setPageSize(pageInfo.getPageSize());
        pageResult.setTotalPages((int) pageInfo.getTotal());
        pageResult.setTotalSize(pageInfo.getPages());
        pageResult.setContent((List<T>) pageInfo.getList());
        return pageResult;
    }

}
