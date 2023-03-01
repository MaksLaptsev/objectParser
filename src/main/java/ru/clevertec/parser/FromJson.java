package ru.clevertec.parser;
import org.apache.commons.lang3.ArrayUtils;
import ru.clevertec.ObjSimpleWriterAndReader.ObjReader;
import ru.clevertec.entity.Middle;
import ru.clevertec.entity.Nested;
import ru.clevertec.entity.Simple;

import java.lang.reflect.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FromJson {

    public FromJson() {
    }

    public Object jSonToObject(String jSon, Class<?> clazz) throws NoSuchFieldException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {

        return createObject(new ObjReader(jSon),clazz);
    }

    private Object createObject(ObjReader reader, Class<?> clazz) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException, ClassNotFoundException, NoSuchMethodException {
        Map<String,String> stringMap = map(clazz,reader);
        Object object = clazz.getDeclaredConstructor().newInstance();

        Field[] fields = object.getClass().getDeclaredFields();
        Iterator<Field> iterator = Arrays.stream(fields).iterator();
        while (iterator.hasNext()){
            Field field = iterator.next();
            field.setAccessible(true);
            object = checkTypeFieldSetAndReturn(reader,field, object, stringMap.get(field.getName()));
        }
        return object;
    }

    private Map<String,String> map (Class<?> clazz, ObjReader reader){
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            if (f.getType().isArray()) {
                reader.writeToMapKeyValue(f.getName(), reader.resultMatcherArrayField(f.getName()));
            } else reader.writeToMapKeyValue(f.getName(), reader.resultMatcherFieldValue(f.getName()));
        }
        return reader.getMapKeyValue();
    }

    private Object checkTypeFieldSetAndReturn(ObjReader reader, Field field,Object o,String valueToSet) throws IllegalAccessException, NoSuchFieldException, ClassNotFoundException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        if(field.getType().equals(int.class)||field.getType().equals(Integer.class)){
            field.setInt(o,Integer.parseInt(valueToSet));
            return o;
        } else if (field.getType().equals(double.class)||field.getType().equals(Double.class)) {
            field.setDouble(o,Double.parseDouble(valueToSet));
            return o;
        } else if (field.getType().equals(float.class)||field.getType().equals(Float.class)) {
            field.setFloat(o,Float.parseFloat(valueToSet));
            return o;
        } else if (field.getType().equals(byte.class)||field.getType().equals(Byte.class)) {
            field.setByte(o,Byte.parseByte(valueToSet));
            return o;
        } else if (field.getType().equals(short.class)||field.getType().equals(Short.class)) {
            field.setShort(o,Short.parseShort(valueToSet));
            return o;
        }else if (field.getType().equals(String.class)){
            field.set(o,valueToSet.substring(1,valueToSet.length()-1));
            return o;
        } else if (field.getType().equals(boolean.class)||field.getType().equals(Boolean.class)) {
            field.setBoolean(o,Boolean.parseBoolean(valueToSet));
            return o;
        } else if (field.getType().equals(char.class)||field.getType().equals(Character.class)) {
            field.setChar(o,valueToSet.charAt(0));
            return o;
        } else if (field.getType().equals(long.class)||field.getType().equals(Long.class)) {
            field.setLong(o,Long.parseLong(valueToSet));
            return o;
        } else if (field.getType().isArray()) {
            return parseArray(reader,field,o,valueToSet);
        } else if (field.getType().equals(List.class)) {
            return parseList(reader,field,o,valueToSet);
        } else if (field.getType().equals(Map.class)) {
            return o;
        } else if (!field.getGenericType().getTypeName().contains("java.lang")) {
            field.set(o, jSonToObject(valueToSet,field.getType()));
            return o;
        } else return o;
    }

    private Object parseList(ObjReader reader, Field field,Object o, String valueToSet) throws IllegalAccessException {
        ParameterizedType type = (ParameterizedType) field.getGenericType();
        Class<?> clazz = (Class<?>) type.getActualTypeArguments()[0];

         if (clazz.equals(Integer.class)){
            field.set(o,Arrays.stream(reader.resultMatcherArrayField(field.getName()).split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList()));
            return o;
        } else if (clazz.equals(Long.class)){
            field.set(o,Arrays.stream(reader.resultMatcherArrayField(field.getName()).split(","))
                    .map(Long::parseLong)
                    .collect(Collectors.toList()));
            return o;
        } else if (clazz.equals(Double.class)){
            field.set(o,Arrays.stream(reader.resultMatcherArrayField(field.getName()).split(","))
                    .map(Double::parseDouble)
                    .collect(Collectors.toList()));
            return o;
        } else if (clazz.equals(Float.class)){
            field.set(o,Arrays.stream(reader.resultMatcherArrayField(field.getName()).split(","))
                    .map(Float::parseFloat)
                    .collect(Collectors.toList()));
            return o;
        } else if (clazz.equals(Character.class)){
            field.set(o, reader.replaceSymbols(valueToSet).replaceAll("\"", "").replaceAll(",", "").chars()
                    .mapToObj(x->(char) x)
                    .collect(Collectors.toList()));
            return o;
        } else if (clazz.equals(Boolean.class)){
            field.set(o,Arrays.stream(reader.resultMatcherArrayField(field.getName()).split(","))
                    .map(Boolean::parseBoolean)
                    .collect(Collectors.toList()));
            return o;
        } else if (clazz.equals(String.class)) {
             field.set(o, Arrays.stream(reader.replaceSymbols(valueToSet).replaceAll("\"","").split(","))
                     .collect(Collectors.toList()));
         } else if (!clazz.toString().contains("java.lang")) {
             return o;
         }return o;
    }
    private Object parseArray(ObjReader reader, Field field, Object o,String valueToSet) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        int size = reader.resultMatcherArrayField(field.getName()).split(",").length;
        String numeric = reader.resultMatcherArrayField(field.getName());
        if(field.getType().equals(int[].class)){
            field.set(o, Arrays.stream(numeric.split(","))
                    .mapToInt(Integer::parseInt)
                    .toArray());
            return o;
        } else if (field.getType().equals(String[].class)) {
            field.set(o,reader.replaceSymbols(valueToSet).replaceAll("\"","").split(","));
        } else if (field.getType().equals(char[].class)) {
            field.set(o,reader.replaceSymbols(valueToSet).replaceAll("\"","").replaceAll(",","").toCharArray());
            return o;
        } else if (field.getType().equals(Boolean[].class)) {
            field.set(o, Arrays.stream(
                            numeric.split(","))
                    .map(Boolean::parseBoolean)
                    .toArray());
            return o;
        } else if (field.getType().equals(short[].class)) {
            field.set(o, ArrayUtils.toPrimitive(Arrays.stream(
                            numeric.split(","))
                    .map(Short::parseShort)
                    .toList().toArray(new Short[size-1])));
            return o;
        } else if (field.getType().equals(byte[].class)) {
            field.set(o, ArrayUtils.toPrimitive(Arrays.stream(
                            numeric.split(","))
                    .map(Byte::parseByte)
                    .toList().toArray(new Byte[size-1])));
            return o;
        } else if (field.getType().equals(float[].class)) {
            field.set(o, ArrayUtils.toPrimitive(Arrays.stream(numeric.split(",")).map(Float::parseFloat).toList().toArray(new Float[size-1])));
            return o;
        } else if (field.getType().equals(double[].class)) {
            field.set(o, Arrays.stream(numeric.split(",")).mapToDouble(Double::valueOf).toArray());
            return o;
        }return o;
    }
}

