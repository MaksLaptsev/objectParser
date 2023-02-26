package ru.clevertec;

import com.google.gson.Gson;
import ru.clevertec.entity.Middle;
import ru.clevertec.entity.Simple;
import ru.clevertec.entity.Top;
import ru.clevertec.parser.ObjParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IllegalAccessException, IOException, ClassNotFoundException {
        List<Integer> list = new ArrayList<>();
        list.add(1); list.add(2); list.add(3); list.add(4);
        Simple simpleOne = new Simple(1,"Alex","D-Grey",45,true, list);
        Simple simpleTwo = new Simple(6,"Vasyas","D-Grey",45,true, list);
        Middle middle2 = new Middle(2,"Vasya",null,35,false,new String[][]{{"dasf","qwe","qqq"},{"qwe","rty"}},
                new Integer[][]{{1,2,3,4},{1,2,3}},new Boolean[][]{{true,false,false},{false,true,false}},new Character[]{'s','z','v'},
                new Double[]{22.1,33.5,10.9}, new Float[]{1.5f,5.0f,9.9f},new byte[]{1,2,9}, new short[]{123,15},
                Arrays.asList(simpleOne,simpleTwo),
                Map.of("str1",1,"str2",2,"str3",3),
                Map.of(1.5,2,2.6,3,4.8,5),
                Map.of("First",new Simple(1,"Alex","D-Grey",45,true, list),"Second",
                        new Simple(6,"Vasyas","D-Grey",45,true, list)
                        )
                );
        Top top = new Top(5,"Georg","Jesus",999,true,simpleOne,middle2);

        System.out.println("Next string -> made by custom parser\n-------------------------------");
        System.out.println(new ObjParser().toJson(top));
        System.out.println("\nNext string -> made by Gson parser\n-------------------------------");
        System.out.println(new Gson().toJson(top));





    }
}
