package ru.clevertec;

import com.google.gson.Gson;
import ru.clevertec.entity.Middle;
import ru.clevertec.entity.Nested;
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
        Nested nested_1 = Nested.builder()
                .id(123)
                .age(999)
                .name("GOD")
                .lastName("GODDEST")
                .longList(Arrays.asList(1L,2L,3L)).booleanList(Arrays.asList(true,false))
                .characterList(Arrays.asList('w','y','S'))
                .floatList(Arrays.asList(32.3F,11F,5F))
                .stringList(Arrays.asList("first","second"))
                .build();

        Nested nested_2 = Nested.builder()
                .id(1323)
                .age(33)
                .name("Who")
                .lastName("Is")
                .longList(Arrays.asList(13L,26L,83L)).booleanList(Arrays.asList(true,false))
                .characterList(Arrays.asList('b','k','S'))
                .floatList(Arrays.asList(2.3F,0.1F,5F))
                .stringList(Arrays.asList("third","second"))
                .build();

        Simple simpleOne = Simple.builder()
                .id(1)
                .name("Alex")
                .surName("D-Grey")
                .age(45)
                .likeApple(true)
                .list(list)
                .integers(new int[]{1,2,3,4,5})
                .nested(nested_1)
                .build();


        Simple simpleTwo = Simple.builder()
                .id(1)
                .name("Fedor")
                .surName("F-White")
                .age(22)
                .likeApple(false)
                .list(list)
                .integers(new int[]{11,24,37,49,5})
                .nested(nested_2)
                .build();

        Middle middle = Middle.builder()
                .id(2)
                .name("Vasya")
                .surName(null)
                .age(35)
                .likeApple(false)
                .strings(new String[][]{{"dasf","qwe","qqq"},{"qwe","rty"}})
                .integers(new int[][]{{1,2,3,4},{1,2,3}})
                .booleans(new Boolean[][]{{true,false,false},{false,true,false}})
                .bytes(new byte[]{1,2,9})
                .shorts(new short[]{123,15})
                .simples(Arrays.asList(simpleOne,simpleTwo))
                .simpleMap(Map.of("First",simpleOne,"Second",simpleTwo))
                .doubleMaps(Map.of(1.5,2,2.6,3))
                .integerMap(Map.of("str1",1,"str2",2))
                .build();

        Top top = Top.builder()
                .id(5)
                .name("Georg")
                .surName("Jesus")
                .age(777)
                .likeApple(true)
                .simple(simpleOne)
                .middle(middle)
                .build();


        System.out.println("Next string -> made by custom parser\n-------------------------------");
        System.out.println(new ObjParser().toJson(middle));
        System.out.println("\nNext string -> made by Gson parser\n-------------------------------");
        System.out.println(new Gson().toJson(middle));
    }
}
