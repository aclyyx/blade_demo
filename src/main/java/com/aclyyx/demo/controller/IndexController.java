package com.aclyyx.demo.controller;

import com.aclyyx.demo.obj.DemoObj;
import com.blade.mvc.annotation.*;
import com.blade.mvc.http.Request;
import com.blade.mvc.http.Response;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

@Path
public class IndexController {

    public static JsonConfig config;
    static {
        config = new JsonConfig();
        config.setIgnoreDefaultExcludes(true);
        config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
        config.setExcludes(new String[]{
                "h"
        });
    }

    @GetRoute("/")
    public void index(Response resp) {
        resp.text("This is a controller index.");
    }

    @PostRoute("/hello")
    public void hello(Request req, Response resp, @Param DemoObj obj) {
        System.out.println(req.bodyToString());
        System.out.println(obj.getC());
        resp.text("OK");
    }

    @PostRoute("/hello/json")
    @JSON
    public DemoObj helloJson(Request req, Response resp) {
        String strJson = req.bodyToString();
        System.out.println(strJson);
        JSONObject json = JSONObject.fromObject(strJson, config);
        System.out.println("--------------------------");
        System.out.println(json.toString());
        System.out.println("--------------------------");
        DemoObj obj = (DemoObj) JSONObject.toBean(json, DemoObj.class);
        System.out.println(obj.getC());
        return obj;
    }

    /*
        发送的Json数据：
        {
            "a" : 1,
            "b" : 2,
            "c" : "test3",
            "o" : {"a" : 9, "b" : 9, "c" : "test9"},
            "h" : "123",
            "arr" : [
                {"a" : 1, "b" : 3, "c" : "test1"},
                {"a" : 2, "b" : 2, "c" : "test2"},
                {"a" : 3, "b" : 1, "c" : "test3"}
            ]
        }
     */
}
