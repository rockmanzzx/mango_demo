package org.example.core.http;

public class HttpResult {
    private int code;
    private String msg;
    private Object data;

    private HttpResult(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static HttpResult success(Object data) {
        return new HttpResult(HttpStatus.SUCCESS.getCode(), HttpStatus.SUCCESS.getMessage(), data);
    }

    public static HttpResult success(Object data, String msg) {
        return new HttpResult(HttpStatus.SUCCESS.getCode(), msg, data);
    }

    public static HttpResult error(HttpStatus status) {
        return new HttpResult(status.getCode(), status.getMessage(), null);
    }

    public static HttpResult error(HttpStatus status, String message) {
        return new HttpResult(status.getCode(), message, null);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
