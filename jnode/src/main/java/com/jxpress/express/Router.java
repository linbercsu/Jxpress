package com.jxpress.express;

import com.jxpress.http.Response;

import java.util.ArrayList;
import java.util.List;

public class Router implements com.jxpress.http.Function {

    private List<RouterPoint> subRouters = new ArrayList<>();
    protected String path;

    public void get(String path, com.jxpress.http.Function function) {

    }

    public void use(com.jxpress.http.Function function) {

    }

    @Override
    public void call(com.jxpress.http.Request request, Response response) {
        String wholePath = request.path();

        int index = wholePath.indexOf(path);

        String subPath = wholePath.substring(index + path.length());

        for (RouterPoint routerPoint : subRouters) {
            if (match(routerPoint, request.method(), subPath)) {
                com.jxpress.http.Function function = routerPoint.function;
                if (function instanceof Router) {
                    ((Router)function).path = path + InterceptPath(subPath, routerPoint.path);
                }

                function.call(request, response);

                if (response.completed()) {
                    break;
                }
            }
        }
    }

    private static boolean match(RouterPoint point, String method, String path) {
        throw new RuntimeException();
    }

    private static String InterceptPath(String path, String patten) {
        throw new RuntimeException();
    }

    static class RouterPoint {
        private final String method;
        private final String path;
        private final com.jxpress.http.Function function;

        public RouterPoint(String method, String path, com.jxpress.http.Function function) {
            this.method = method;
            this.path = path;
            this.function = function;
        }
    }
}
