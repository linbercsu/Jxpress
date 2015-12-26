package com.jxpress.express;

import com.jxpress.http.JRequest;
import com.jxpress.http.JMiddleWare;
import com.jxpress.http.JResponse;

import java.util.ArrayList;
import java.util.List;

public class JRouter implements JMiddleWare {

    private List<RouterPoint> subRouters = new ArrayList<>();
    protected String path;

    public void get(String path, JMiddleWare middleWare) {

    }

    public void use(JMiddleWare middleWare) {

    }

    @Override
    public void call(JRequest request, JResponse response) {
        String wholePath = request.path();

        int index = wholePath.indexOf(path);

        String subPath = wholePath.substring(index + path.length());

        for (RouterPoint routerPoint : subRouters) {
            if (match(routerPoint, request.method(), subPath)) {
                JMiddleWare middleWare = routerPoint.middleWare;
                if (middleWare instanceof JRouter) {
                    ((JRouter) middleWare).path = path + InterceptPath(subPath, routerPoint.path);
                }

                middleWare.call(request, response);

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
        private final JMiddleWare middleWare;

        public RouterPoint(String method, String path, JMiddleWare middleWare) {
            this.method = method;
            this.path = path;
            this.middleWare = middleWare;
        }
    }
}