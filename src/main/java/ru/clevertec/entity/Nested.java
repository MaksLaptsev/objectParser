package ru.clevertec.entity;

import lombok.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Nested {
    private int id;
    private int age;
    private String name;
    private String lastName;
    private List<Long> longList;
    private List<String> stringList;
    private List<Boolean> booleanList;
    private List<Character> characterList;
    private List<Float> floatList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nested nested = (Nested) o;
        return id == nested.id && age == nested.age && Objects.equals(name, nested.name) && Objects.equals(lastName, nested.lastName) && Objects.equals(longList, nested.longList) && Objects.equals(stringList, nested.stringList) && Objects.equals(booleanList, nested.booleanList) && Objects.equals(characterList, nested.characterList) && Objects.equals(floatList, nested.floatList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, age, name, lastName, longList, stringList, booleanList, characterList, floatList);
    }

}
