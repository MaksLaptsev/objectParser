package ru.clevertec.util;

import lombok.*;
import ru.clevertec.entity.Nested;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SimpleTestBuilder implements TestBuilder<SimpleTestBuilder> {

    private int id;
    private String name;
    private String surName;
    private int age;
    private boolean likeApple;
    private List<Integer> list;
    private int[] integers;
    private NestedTestBuilder nested;

    @Override
    public SimpleTestBuilder build() {
        return SimpleTestBuilder.builder()
                .id(1)
                .name("Alex")
                .surName("D-Grey")
                .age(45)
                .likeApple(true)
                .list(list)
                .nested(null)
                .integers(new int[]{1,2,3,4,5})
                .build();
    }
    public static SimpleTestBuilder staticBuild() {
        return SimpleTestBuilder.builder()
                .id(1)
                .name("Alex")
                .surName("D-Grey")
                .age(45)
                .likeApple(true)
                .list(Stream.of(1,2,3).collect(Collectors.toList()))
                .nested(NestedTestBuilder.staticBuild())
                .integers(new int[]{1,2,3,4,5})
                .build();
    }
}
