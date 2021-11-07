package com.z.test.Utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.z.test.bean.myBook;

import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;

public class JsonUtil {
    public static myBook getBookBean(String json){
        Gson gson=new Gson();
        return gson.fromJson(json,myBook.class);
    }
}
