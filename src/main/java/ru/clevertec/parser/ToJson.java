package ru.clevertec.parser;

import org.apache.commons.lang3.ArrayUtils;
import ru.clevertec.ObjSimpleWriterAndReader.ObjWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ToJson {
    private final ObjWriter writer;
    private boolean withoutNullField;

    public ToJson() {
        this.writer = new ObjWriter();
        this.withoutNullField = true;
    }
    //принимаем объект + проверяем на array/list/anotherObject
    public String toJson(Object object, Type typeObject) throws IllegalAccessException, IOException, ClassNotFoundException {
        Field[] fields = object.getClass().getDeclaredFields();
        writer.beginObject();
        Iterator<Field> iterator = Arrays.stream(fields).iterator();
        while (iterator.hasNext()){
            Field f = iterator.next();
            f.setAccessible(true);
            if(f.get(object) == null && iterator.hasNext() && withoutNullField){continue;}
            writer.writeFieldName(f.getName());
            writer.separator();
            if (isAnotherObject(f,f.get(object))){
                writer.write(new ObjParser().toJson(f.get(object)));
                if (iterator.hasNext())writer.comma();
                continue;
            }else if (checkIsSimpleArray(f,f.get(object))){
                writeArray(f,f.get(object));
            } else if (f.get(object) instanceof Map<?,?>) {
                writer.beginObject();
                writeMap(f.get(object));
                writer.endObject();
            } else writer.writeValueField(f.get(object)==null?"null":f.get(object).toString(),checkStringField(f));
            if (iterator.hasNext()){writer.comma();}
            f.setAccessible(false);
        }
        writer.endObject();
        return writer.getResult();
    }
    private void toJson(Object o) throws IOException, ClassNotFoundException, IllegalAccessException {
        toJson(o,o.getClass());
    }
    //проверка является ли объект list или array
    private boolean checkIsSimpleArray(Field f, Object o){
        return f.getType().isArray() || o instanceof List<?>;
    }
    //запись в строку объекта array/list
    private void writeArray(Field f,Object object) throws IOException, ClassNotFoundException, IllegalAccessException {
        writer.beginArray();
        int size = object.getClass().getSimpleName().split("\\[").length-1;//проверка на вложенный массив типа Integer[] or Integer [][]
        checkInArray(size,f,object);//проверка на вложенный массив типа Integer[] or Integer [][]
        if(object instanceof Integer[] array){
            writeArray(array,false);
        } else if (object instanceof int[] ar) {
            Integer[] array = ArrayUtils.toObject(ar);
            writeArray(array,false);
        } else if (object instanceof String[] array) {
            writeArray(array,true);
        } else if (object instanceof Character[] array) {
            writeArray(array,true);
        } else if (object instanceof char[] arr) {
            Character[] array = ArrayUtils.toObject(arr);
            writeArray(array,true);
        } else if (object instanceof Boolean[] array) {
            writeArray(array,false);
        } else if (object instanceof Short[] array) {
            writeArray(array,false);
        } else if (object instanceof short[] ar) {
            Short[] array = ArrayUtils.toObject(ar);
            writeArray(array,false);
        } else if (object instanceof Byte[] array) {
            writeArray(array,false);
        } else if (object instanceof byte[] array) {
            Byte[] ar = ArrayUtils.toObject(array);
            writeArray(ar,false);
        } else if (object instanceof Float[] || object instanceof Double[]) {
            Object[] array = (Object[]) object;
            writeArray(array,false);
        } else if (object instanceof float[] arr) {
            Float[] array = ArrayUtils.toObject(arr);
            writeArray(array,false);
        } else if (object instanceof double[] arr) {
            Double[] array = ArrayUtils.toObject(arr);
            writeArray(array,false);
        } else if (object instanceof List<?>){
            List<?> array = (List<?>) object;
            writeArray(array,false,f);
        }
        writer.afterArray();
    }
    //записываем в строку массив
    private void writeArray(Object[] array,boolean b) throws IOException {
        Iterator<?> iterator = Arrays.stream(array).iterator();
        while (iterator.hasNext()){
            Object d = iterator.next();
            writer.writeValueField(d.toString(),b);
            if (iterator.hasNext())writer.comma();
        }
    }
    //записываем в строку List
    private void writeArray(List<?> array,boolean b,Field f) throws IOException, IllegalAccessException, ClassNotFoundException {
        Iterator<?> iterator = array.iterator();
        while (iterator.hasNext()){
            Object d = iterator.next();
            if (isNotStandartJavaObj(d)){
                writer.write(new ObjParser().toJson(d));
            } else if (d.getClass().equals(String.class) || d.getClass().equals(Character.class) || d.getClass().equals(char.class)) {
                writer.writeValueField(d.toString(),true);
            } else {
                writer.writeValueField(d.toString(),b);
            }
            if (iterator.hasNext())writer.comma();
        }
    }
    private void writeMap(Object o) throws IOException, ClassNotFoundException, IllegalAccessException {
        Map<?,?> map = (Map<?,?>) o;
        Iterator<?> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<?,?> entry = (Map.Entry<?, ?>) iterator.next();
            if(isNotStandartJavaObj(entry.getKey())){
                toJson(entry.getKey());
            }else {writer.writeValueField(String.valueOf(entry.getKey()),true);}
            writer.separator();
            if(isNotStandartJavaObj(entry.getValue())){
                toJson(entry.getValue());
            }else {writer.writeValueField(String.valueOf(entry.getValue()),checkStringField(entry.getValue()));}
            if (iterator.hasNext()){writer.comma();}
        }

    }
    //является ли поле строкой
    private boolean checkStringField(Field f){
        return f.getType().equals(String.class);
    }
    private boolean checkStringField(Object o){
        return o.getClass().equals(String.class);
    }

    //проверка, является ли объект не стандартным объектом
    private boolean isAnotherObject(Field f, Object o){
        return !(f.getType().getTypeName().split("\\.")[0].equals("java") || f.getType().equals(int.class) ||
                f.getType().equals(boolean.class) || f.getType().equals(char.class) || f.getType().equals(byte.class) ||
                f.getType().equals(short.class) || f.getType().equals(double.class) || f.getType().equals(float.class) ||
                f.getType().equals(long.class) || checkIsSimpleArray(f, o));
    }
    //тоже самое, что и предыдущее,но для содержимого List
    private boolean isNotStandartJavaObj(Object o){
        return !o.getClass().getTypeName().split("\\.")[0].equals("java");
    }
    //разбор и запись массивов типа Integer[][]
    private void checkInArray(int number,Field f, Object object) throws IOException, ClassNotFoundException, IllegalAccessException {
        if (number > 1) {
            int leg = Array.getLength(object);
            for (int i = 0; i < leg; i++) {
                try {
                    writeArray(f, Array.get(object,i));
                }
                catch (IndexOutOfBoundsException ignored){}
                if (i < leg-1) {
                    writer.comma();
                }
            }
        }
    }
    public void setWithoutNullField(boolean b){
        this.withoutNullField = b;
    }
    public boolean getWithoutNullField(){
        return withoutNullField;
    }

}
