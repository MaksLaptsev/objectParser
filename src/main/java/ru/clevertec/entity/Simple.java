package ru.clevertec.entity;

import java.util.List;
import java.util.Objects;

public class Simple {
    private int id;
    private String name;
    private String surName;
    private int age;
    private boolean likeApple;
    private List<Integer> list;

    public Simple() {
    }

    public Simple(int id, String name, String surName, int age, boolean likeApple, List<Integer> list) {
        this.id = id;
        this.name = name;
        this.surName = surName;
        this.age = age;
        this.likeApple = likeApple;
        this.list = list;
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

    public List<Integer> getList() {
        return list;
    }

    public void setList(List<Integer> list) {
        this.list = list;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Simple simple = (Simple) o;
        return id == simple.id && age == simple.age && likeApple == simple.likeApple && Objects.equals(name, simple.name) && Objects.equals(surName, simple.surName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surName, age, likeApple);
    }

    @Override
    public String toString() {
        return "Simple{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surName='" + surName + '\'' +
                ", age=" + age +
                ", likeApple=" + likeApple +
                '}';
    }
}
