package ru.clevertec.entity;

import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Top {
    private int id;
    private String name;
    private String surName;
    private int age;
    private boolean likeApple;
    private Simple simple;
    private Middle middle;

}
