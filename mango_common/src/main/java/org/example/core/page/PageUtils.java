package org.example.core.page;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import java.lang.reflect.Method;

public class PageUtils {

    public static PageResult getPageResult(PageRequest pageRequest, Object mapper, String queryMethodName, Object... args) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);

        try {
            Method method = mapper.getClass().getMethod(queryMethodName, getParameterTypes(args));
            Object result = method.invoke(mapper, args);

            if (result instanceof Page) {
                Page page = (Page) result;
                return new PageResult(page.getPageNum(), page.getPageSize(), page.getTotal(), page.getPages(), page.getResult());
            }

            return new PageResult(pageNum, pageSize, 0, 0, null);
        } catch (Exception e) {
            throw new RuntimeException("分页查询异常", e);
        }
    }

    private static Class<?>[] getParameterTypes(Object[] args) {
        return java.util.Arrays.stream(args)
                .map(Object::getClass)
                .toArray(Class<?>[]::new);
    }
}
