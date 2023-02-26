package ru.clevertec.entity;

public class Top {
    private int id;
    private String name;
    private String surName;
    private int age;
    private boolean likeApple;
    private Simple simple;
    private Middle middle;


    public Top() {
    }

    public Top(int id, String name, String surName, int age, boolean likeApple, Simple simple, Middle middle) {
        this.id = id;
        this.name = name;
        this.surName = surName;
        this.age = age;
        this.likeApple = likeApple;
        this.simple = simple;
        this.middle = middle;
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

    public Simple getSimple() {
        return simple;
    }

    public void setSimple(Simple simple) {
        this.simple = simple;
    }

    public Middle getMiddle() {
        return middle;
    }

    public void setMiddle(Middle middle) {
        this.middle = middle;
    }
}
