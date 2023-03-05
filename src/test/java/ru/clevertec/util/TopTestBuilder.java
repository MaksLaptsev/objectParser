package ru.clevertec.util;

import lombok.*;
@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TopTestBuilder implements TestBuilder<TopTestBuilder>{
    private int id;
    private String name;
    private String surName;
    private int age;
    private boolean likeApple;
    private SimpleTestBuilder simple;
    private MiddleTestBuilder middle;

    @Override
    public TopTestBuilder build() {
        return TopTestBuilder.builder()
                .id(5)
                .name("Georg")
                .surName("Jesus")
                .age(777)
                .likeApple(true)
                .simple(SimpleTestBuilder.staticBuild())
                .middle(MiddleTestBuilder.staticBuild())
                .build();
    }
    public static TopTestBuilder staticBuild() {
        return TopTestBuilder.builder()
                .id(5)
                .name("Georg")
                .surName("Jesus")
                .age(777)
                .likeApple(true)
                .simple(SimpleTestBuilder.staticBuild())
                .middle(MiddleTestBuilder.staticBuild())
                .build();
    }
}
