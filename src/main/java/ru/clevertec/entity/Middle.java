package ru.clevertec.entity;

import lombok.*;
import java.util.List;
import java.util.Map;
@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Middle {
    private int id;
    private String name;
    private String surName;
    private int age;
    private boolean likeApple;
    private String[][] strings;
    private int[][] integers;
    private Boolean[][] booleans;
    private char[] characters;
    private double[] doubles;
    private float[] floats;
    private byte[] bytes;
    private short[] shorts;
    private List<Simple> simples;
    private Map<String,Integer> integerMap;
    private Map<Double,Integer> doubleMaps;
    private Map<String, Simple> simpleMap;

}
