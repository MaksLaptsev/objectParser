package ru.clevertec.entity;

import lombok.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Simple {
    private int id;
    private String name;
    private String surName;
    private int age;
    private boolean likeApple;
    private List<Integer> list;
    private int[] integers;
    private Nested nested;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Simple simple = (Simple) o;
        return id == simple.id && age == simple.age && likeApple == simple.likeApple && Objects.equals(name, simple.name) && Objects.equals(surName, simple.surName) && Objects.equals(list, simple.list) && Arrays.equals(integers, simple.integers) && Objects.equals(nested, simple.nested);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, name, surName, age, likeApple, list, nested);
        result = 31 * result + Arrays.hashCode(integers);
        return result;
    }
}
