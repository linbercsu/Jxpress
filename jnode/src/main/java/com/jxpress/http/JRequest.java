package com.jxpress.http;

import java.io.InputStream;

public class JRequest {

    public static final String GET = "get";
    public static final String POST = "post";
    public static final String PUT = "put";
    public static final String DELETE = "delete";
    public static final String PATCH = "patch";

    private InputStream inputStream;

    public JRequest(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public String method() {
        throw new RuntimeException();
    }
    public String path() {
        throw new RuntimeException();
    }
}
