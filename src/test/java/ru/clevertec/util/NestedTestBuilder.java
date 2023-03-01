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
@NoArgsConstructor(staticName = "aNested")
@AllArgsConstructor
@EqualsAndHashCode
public class NestedTestBuilder implements TestBuilder<NestedTestBuilder> {
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
    public NestedTestBuilder build() {
        return NestedTestBuilder.builder()
                .id(123)
                .age(999)
                .name("GOD")
                .lastName("GODDEST")
                .longList(Stream.of(1L,2L,3L).collect(Collectors.toList()))
                .booleanList(Stream.of(true,false).collect(Collectors.toList()))
                .characterList(Stream.of('w','y','S').collect(Collectors.toList()))
                .floatList(Stream.of(32.3F,11F,5F).collect(Collectors.toList()))
                .stringList(Stream.of("first","second").collect(Collectors.toList()))
                .build();
    }

    public static NestedTestBuilder staticBuild() {
        return NestedTestBuilder.builder()
                .id(123)
                .age(999)
                .name("GOD")
                .lastName("GODDEST")
                .longList(Stream.of(1L,2L,3L).collect(Collectors.toList()))
                .booleanList(Stream.of(true,false).collect(Collectors.toList()))
                .characterList(Stream.of('w','y','S').collect(Collectors.toList()))
                .floatList(Stream.of(32.3F,11F,5F).collect(Collectors.toList()))
                .stringList(Stream.of("first","second").collect(Collectors.toList()))
                .build();
    }
}
