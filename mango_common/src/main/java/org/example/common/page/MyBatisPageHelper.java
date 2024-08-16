package org.example.common.page;

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

//    public static PageResult findPage(PageRequest pageRequest, Object mapper, String queryMethodName, Object... args) {
//        if (mapper == null) {
//            throw new IllegalArgumentException("Mapper object cannot be null");
//        }
//
//        int pageNum = pageRequest.getPageNum();
//        int pageSize = pageRequest.getPageSize();
//        PageHelper.startPage(pageNum, pageSize);
//
//        Method method = ReflectionUtils.findMethod(mapper.getClass(), queryMethodName);
//        if (method == null) {
//            throw new IllegalArgumentException("Method " + queryMethodName + " not found in " + mapper.getClass().getName());
//        }
//
//        try {
//            Object result = ReflectionUtils.invokeMethod(method, mapper, args);
//            if (result == null) {
//                throw new RuntimeException("Method " + queryMethodName + " returned null");
//            }
//            if (!(result instanceof List)) {
//                throw new RuntimeException("Method " + queryMethodName + " did not return a List");
//            }
//            return getPageResult(pageRequest, new PageInfo<>((List) result));
//        } catch (IllegalArgumentException e) {
//            throw new RuntimeException("Failed to invoke method " + queryMethodName + ". Check if the arguments match the method parameters.", e);
//        }
//    }

//    public static PageResult findPage(PageRequest pageRequest, Object mapper, String queryMethodName, Object... args) {
//        int pageNum = pageRequest.getPageNum();
//        int pageSize = pageRequest.getPageSize();
//        PageHelper.startPage(pageNum, pageSize);
//        Object result = ReflectionUtils.invoke(mapper, queryMethodName, args);
//        return getPageResult(pageRequest, new PageInfo<>((List) result));
//    }

    public static PageResult findPage(PageRequest pageRequest, Object mapper, String queryMethodName, Object... args) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        Object result = invoke(mapper, queryMethodName, args);
        return getPageResult(pageRequest, new PageInfo<>((List) result));
    }

    private static Object invoke(Object target, String methodName, Object... args) {
        Method method = ReflectionUtils.findMethod(target.getClass(), methodName, getParameterTypes(args));
        if (method == null) {
            throw new IllegalArgumentException("Method not found: " + methodName);
        }
        return ReflectionUtils.invokeMethod(method, target, args);
    }

    private static Class<?>[] getParameterTypes(Object[] args) {
        Class<?>[] parameterTypes = new Class<?>[args.length];
        for (int i = 0; i < args.length; i++) {
            parameterTypes[i] = args[i].getClass();
        }
        return parameterTypes;
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
        pageResult.setTotalPages((int) pageInfo.getPages());
        pageResult.setTotalSize(pageInfo.getTotal());
        pageResult.setContent(pageInfo.getList());
        return pageResult;
    }

}
