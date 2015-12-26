package com.example;

import com.jxpress.express.Express;
import com.jxpress.http.Function;
import com.jxpress.http.Server;
import com.jxpress.http.Request;
import com.jxpress.http.Response;

public class MyClass {
    public static void main(String[] args) {

        Express express = new Express();
        Server server = Server.createServer(express);

        express.use(new Function() {
            @Override
            public void call(Request request, Response response) {
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
