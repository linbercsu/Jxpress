package com.jxpress.http;

import java.io.OutputStream;

/**
 * Created by lin on 12/26/15.
 */
public class Response {

    private OutputStream outputStream;

    public Response(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public boolean completed() {
        throw new RuntimeException();
    }
}
