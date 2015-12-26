package com.example;

import com.jxpress.express.JExpress;
import com.jxpress.http.JMiddleWare;
import com.jxpress.http.JServer;
import com.jxpress.http.JRequest;
import com.jxpress.http.JResponse;

public class MyClass {
    public static void main(String[] args) {

        JExpress express = new JExpress();
        JServer server = JServer.createServer(express);

        express.use(new JMiddleWare() {
            @Override
            public void call(JRequest request, JResponse response) {
                System.out.println("request got.");
            }
        });

        try {
            server.listen(8007);
        }catch (Exception e) {

        }

        Looper.loop();

    }
}
