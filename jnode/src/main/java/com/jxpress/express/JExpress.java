package com.jxpress.express;

import com.jxpress.http.JRequest;
import com.jxpress.http.JResponse;

public class JExpress extends JRouter {

    public JExpress() {
        path = "/";
    }

    @Override
    public void call(JRequest request, JResponse response) {
        System.out.println("express: call");

        super.call(request, response);
    }
}
