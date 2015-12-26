package com.jxpress.http;

import java.io.OutputStream;

public class JResponse {

    private OutputStream outputStream;

    public JResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public boolean completed() {
        throw new RuntimeException();
    }
}
