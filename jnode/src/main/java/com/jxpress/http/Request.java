package com.jxpress.http;

import java.io.InputStream;

/**
 * Created by lin on 12/26/15.
 */
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
