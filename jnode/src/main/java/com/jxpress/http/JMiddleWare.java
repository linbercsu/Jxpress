package com.jxpress.http;

public interface JMiddleWare {

    void call(JRequest request, JResponse response);
}
