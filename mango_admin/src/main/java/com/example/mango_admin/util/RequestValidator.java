package com.example.mango_admin.util;

import org.example.common.page.PageRequest;

public class RequestValidator {
    public static void validatePageRequest(PageRequest pageRequest, String... requiredParams) {
        if (pageRequest == null || pageRequest.getParams() == null) {
            throw new IllegalArgumentException("Invalid PageRequest or parameters");
        }
        for (String param : requiredParams) {
            if (pageRequest.getParams().get(param) == null) {
                throw new IllegalArgumentException("Parameter '" + param + "' is required");
            }
        }
    }
}
