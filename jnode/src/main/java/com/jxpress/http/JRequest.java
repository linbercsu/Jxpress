package com.jxpress.http;

import java.io.InputStream;
import java.io.PushbackInputStream;

public class JRequest {

    public static final String GET = "get";
    public static final String POST = "post";
    public static final String PUT = "put";
    public static final String DELETE = "delete";
    public static final String PATCH = "patch";

    private InputStream bodyStream;


    public JRequest(InputStream inputStream) {
        parseStream(inputStream);
    }

    public InputStream getBodyStream() {
        return bodyStream;
    }

    public void setBodyStream(InputStream bodyStream) {
        this.bodyStream = bodyStream;
    }

    public String method() {
        throw new RuntimeException();
    }
    public String path() {
        throw new RuntimeException();
    }

    private void parseStream(InputStream inputStream) {
        PushbackInputStream stream = new PushbackInputStream(inputStream);
        bodyStream = stream;
    }

}
