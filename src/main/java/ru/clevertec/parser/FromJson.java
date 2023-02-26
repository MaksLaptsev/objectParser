package ru.clevertec.parser;

import java.lang.reflect.Type;

public class FromJson {

    public <T> T fromJsonToObject(String s, Class<T> tClass){
        return fromJson(s,tClass);
    }

    private <T> T fromJson(String json, Type type){
        return (T) new Object();
    }
}
