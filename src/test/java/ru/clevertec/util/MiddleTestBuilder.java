package ru.clevertec.util;


import lombok.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MiddleTestBuilder implements TestBuilder<MiddleTestBuilder>{
    private int id;
    private String name;
    private String surName;
    private int age;
    private boolean likeApple;
    private String[][][] strings;
    private int[][][] integers;
    private Boolean[][] booleans;
    private char[] characters;
    private double[] doubles;
    private float[] floats;
    private byte[] bytes;
    private short[] shorts;
    private List<SimpleTestBuilder> simples;
    private Map<String,Integer> integerMap;
    private Map<Double,Integer> doubleMaps;
    private Map<String, SimpleTestBuilder> simpleMap;

    @Override
    public MiddleTestBuilder build() {
        return MiddleTestBuilder.builder()
                .id(2)
                .name("Vasya")
                .surName("NotNull")
                .age(35)
                .likeApple(false)
                .strings(new String[][][]{{{"dasf","qwe","qqq"},{"hard"}},{{"qwe","rty"},{"123","4567"}},{{"fsdfs","asda"},{"hgfh"}}})
                .integers(new int[][][]{{{1,2,3,},{4}},{{1,2,3}},{{1,2}}})
                .booleans(new Boolean[][]{{true,false,false},{false,true,false}})
                .bytes(new byte[]{1,2,9})
                .shorts(new short[]{123,15})
                .simples(Arrays.asList(SimpleTestBuilder.staticBuild(),SimpleTestBuilder.staticBuild()))
                .simpleMap(Map.of("First",SimpleTestBuilder.staticBuild(),"Second",SimpleTestBuilder.staticBuild()))
                .doubleMaps(Map.of(1.5,2,2.6,3))
                .integerMap(Map.of("str1",1,"str2",2))
                .build();
    }

    public static MiddleTestBuilder staticBuild() {
        return MiddleTestBuilder.builder()
                .id(2)
                .name("Vasya")
                .surName("NotNull")
                .age(35)
                .likeApple(false)
                .strings(new String[][][]{{{"dasf","qwe","qqq"},{"hard"}},{{"qwe","rty"},{"123","4567"}},{{"fsdfs","asda"},{"hgfh"}}})
                .integers(new int[][][]{{{1,2,3,},{4}},{{1,2,3}},{{1,2}}})
                .booleans(new Boolean[][]{{true,false,false},{false,true,false}})
                .bytes(new byte[]{1,2,9})
                .shorts(new short[]{123,15})
                .simples(Arrays.asList(SimpleTestBuilder.staticBuild(),SimpleTestBuilder.staticBuild()))
                .simpleMap(Map.of("First",SimpleTestBuilder.staticBuild(),"Second",SimpleTestBuilder.staticBuild()))
                .doubleMaps(Map.of(1.5,2,2.6,3))
                .integerMap(Map.of("str1",1,"str2",2))
                .build();
    }


}
