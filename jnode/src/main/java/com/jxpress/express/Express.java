package com.jxpress.express;

import com.jxpress.http.Request;
import com.jxpress.http.Response;

public class Express extends Router {

    @Override
    public void call(Request request, Response response) {
        System.out.println("express: call");
        path = "/";

        super.call(request, response);
    }
}
