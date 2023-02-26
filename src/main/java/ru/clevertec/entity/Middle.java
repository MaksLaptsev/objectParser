package ru.clevertec.entity;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Middle {
    private int id;
    private String name;
    private String surName;
    private int age;
    private boolean likeApple;
    private String[][] strings;
    private Integer[][] integers;
    private Boolean[][] booleans;
    private Character[] characters;
    private Double[] doubles;
    private Float[] floats;
    private byte[] bytes;
    private short[] shorts;

    private List<Simple> simples;

    private Map<String,Integer> integerMap;
    private Map<Double,Integer> doubleMaps;

    private Map<String, Simple> simpleMap;

    public Middle() {
    }

    public Middle(int id, String name, String surName, int age, boolean likeApple, String[][] strings,
                  Integer[][] integers, Boolean[][] booleans, Character[] characters, Double[] doubles,
                  Float[] floats, byte[] bytes, short[] shorts, List<Simple> simples, Map<String, Integer> integerMap,
                  Map<Double,Integer> doubleMaps,Map<String, Simple> simpleMap) {
        this.id = id;
        this.name = name;
        this.surName = surName;
        this.age = age;
        this.likeApple = likeApple;
        this.strings = strings;
        this.integers = integers;
        this.booleans = booleans;
        this.characters = characters;
        this.doubles = doubles;
        this.floats = floats;
        this.bytes = bytes;
        this.shorts = shorts;
        this.simples = simples;
        this.integerMap = integerMap;
        this.doubleMaps = doubleMaps;
        this.simpleMap = simpleMap;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isLikeApple() {
        return likeApple;
    }

    public void setLikeApple(boolean likeApple) {
        this.likeApple = likeApple;
    }

    public String[][] getStrings() {
        return strings;
    }

    public void setStrings(String[][] strings) {
        this.strings = strings;
    }

    public Integer[][] getIntegers() {
        return integers;
    }

    public void setIntegers(Integer[][] integers) {
        this.integers = integers;
    }

    public Boolean[][] getBooleans() {
        return booleans;
    }

    public void setBooleans(Boolean[][] booleans) {
        this.booleans = booleans;
    }

    public Character[] getCharacters() {
        return characters;
    }

    public void setCharacters(Character[] characters) {
        this.characters = characters;
    }

    public Double[] getDoubles() {
        return doubles;
    }

    public void setDoubles(Double[] doubles) {
        this.doubles = doubles;
    }

    public Float[] getFloats() {
        return floats;
    }

    public void setFloats(Float[] floats) {
        this.floats = floats;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public short[] getShorts() {
        return shorts;
    }

    public void setShorts(short[] shorts) {
        this.shorts = shorts;
    }

    public List<Simple> getSimples() {
        return simples;
    }

    public Map<String, Integer> getIntegerMap() {
        return integerMap;
    }

    public void setIntegerMap(Map<String, Integer> integerMap) {
        this.integerMap = integerMap;
    }

    public void setSimples(List<Simple> simples) {
        this.simples = simples;
    }

    public Map<Double, Integer> getDoubleMaps() {
        return doubleMaps;
    }

    public void setDoubleMaps(Map<Double, Integer> doubleMaps) {
        this.doubleMaps = doubleMaps;
    }

    public Map<String, Simple> getSimpleMap() {
        return simpleMap;
    }

    public void setSimpleMap(Map<String, Simple> simpleMap) {
        this.simpleMap = simpleMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Middle middle = (Middle) o;
        return id == middle.id && age == middle.age && likeApple == middle.likeApple && Objects.equals(name, middle.name) && Objects.equals(surName, middle.surName) && Arrays.equals(strings, middle.strings) && Arrays.equals(integers, middle.integers) && Arrays.equals(booleans, middle.booleans) && Arrays.equals(characters, middle.characters) && Arrays.equals(doubles, middle.doubles) && Arrays.equals(floats, middle.floats) && Arrays.equals(bytes, middle.bytes) && Arrays.equals(shorts, middle.shorts);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, name, surName, age, likeApple);
        result = 31 * result + Arrays.deepHashCode(strings);
        result = 31 * result + Arrays.deepHashCode(integers);
        result = 31 * result + Arrays.deepHashCode(booleans);
        result = 31 * result + Arrays.hashCode(characters);
        result = 31 * result + Arrays.hashCode(doubles);
        result = 31 * result + Arrays.hashCode(floats);
        result = 31 * result + Arrays.hashCode(bytes);
        result = 31 * result + Arrays.hashCode(shorts);
        return result;
    }
}
