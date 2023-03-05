package ru.clevertec.parser;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.clevertec.entity.Nested;
import ru.clevertec.entity.Simple;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FromJsonTest {
    private FromJson fromJson;
    private String jSonSimple;
    private Simple simple;
    @BeforeEach
    void setUp() {
        fromJson = new FromJson();
        jSonSimple = "{\"id\":1,\"name\":\"Alex\",\"surName\":\"D-Grey\",\"age\":45,\"likeApple\":true,\"list\":[1,2,3,4],\"integers\":[1,2,3,4,5],\"nested\":{\"id\":123,\"age\":999,\"name\":\"GOD\",\"lastName\":\"GODDEST\",\"longList\":[1,2,3],\"stringList\":[first,second],\"booleanList\":[true,false],\"characterList\":[w,y,S],\"floatList\":[32.3,11.0,5.0]}}";
        Nested nested_1 = Nested.builder()
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

        List<Integer> list = new ArrayList<>();
        list.add(1); list.add(2); list.add(3); list.add(4);
        simple = Simple.builder()
                .id(1)
                .name("Alex")
                .surName("D-Grey")
                .age(45)
                .likeApple(true)
                .list(list)
                .integers(new int[]{1,2,3,4,5})
                .nested(nested_1)
                .build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void jSonToObject() throws NoSuchFieldException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        Assertions.assertThat((Simple)fromJson.jSonToObject(jSonSimple,Simple.class)).isEqualTo(simple);
    }
}