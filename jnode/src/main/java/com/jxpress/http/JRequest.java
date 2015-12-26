package com.jxpress.http;

import java.io.InputStream;

public class JRequest {

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
