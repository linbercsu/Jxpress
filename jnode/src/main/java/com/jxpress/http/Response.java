package com.jxpress.http;

import java.io.OutputStream;

public class Response {

    private OutputStream outputStream;

    public Response(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public boolean completed() {
        throw new RuntimeException();
    }
}
