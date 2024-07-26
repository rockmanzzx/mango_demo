package org.example.core.page;

import java.util.HashMap;
import java.util.Map;

public class PageRequest {
    private int pageNum;
    private int pageSize;
    private Map<String, Object> params;

    public PageRequest() {
        this.pageNum = 1;
        this.pageSize = 10;
        this.params = new HashMap<>();
    }

    public PageRequest(int pageNum, int pageSize, Map<String, Object> params) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.params = params;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
