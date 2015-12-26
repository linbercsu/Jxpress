package com.jxpress.http;

import java.io.InputStream;

public class Request {

    private InputStream inputStream;

    public Request(InputStream inputStream) {
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
