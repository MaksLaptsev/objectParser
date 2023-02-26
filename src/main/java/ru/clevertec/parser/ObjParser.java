package ru.clevertec.parser;

import java.io.IOException;
import java.lang.reflect.Type;

public class ObjParser {

    private final ToJson toJsonObj;

    public ObjParser() {
        toJsonObj = new ToJson();
    }

    public String toJson(Object object) throws IllegalAccessException, IOException, ClassNotFoundException {
        if(object == null){
            return "null";
        }else{
            return toJson(object, object.getClass());
        }
    }
    private String toJson(Object object, Type typeObject) throws IllegalAccessException, IOException, ClassNotFoundException {
        return toJsonObj.toJson(object,typeObject);
    }





}
