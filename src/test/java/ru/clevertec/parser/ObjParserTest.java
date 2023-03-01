package ru.clevertec;

import com.google.gson.Gson;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.clevertec.entity.Middle;
import ru.clevertec.entity.Nested;
import ru.clevertec.entity.Simple;
import ru.clevertec.entity.Top;
import ru.clevertec.parser.ObjParser;
import ru.clevertec.util.MiddleTestBuilder;
import ru.clevertec.util.NestedTestBuilder;
import ru.clevertec.util.TopTestBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class ObjParserTest {
    private Gson gson;
    private ObjParser objParser;
    @BeforeEach
    void setUp() {
        gson = new Gson();
        objParser = new ObjParser();
    }

    @AfterEach
    void tearDown() {
    }



    @ParameterizedTest
    @MethodSource("argsForToJson")
    void toJson(Object o) throws IllegalAccessException, IOException, ClassNotFoundException {
        Assertions.assertThat(objParser.toJson(o)).isEqualTo(gson.toJson(o));
    }




    static Stream<Arguments> argsForToJson(){
        return Stream.of(
                Arguments.of(
                        NestedTestBuilder.builder()
                                .id(15)
                                .age(13)
                                .name("Nested")
                                .lastName("Last")
                                .longList(Stream.of(13L,52L,37L).collect(Collectors.toList()))
                                .booleanList(Stream.of(true,false).collect(Collectors.toList()))
                                .characterList(Stream.of('f','o','S').collect(Collectors.toList()))
                                .floatList(Stream.of(325.3F,11.2F,5F).collect(Collectors.toList()))
                                .stringList(Stream.of("first","second").collect(Collectors.toList()))
                                .build()
                ),
                Arguments.of(
                        MiddleTestBuilder.staticBuild()
                ),
                Arguments.of(
                        TopTestBuilder.staticBuild()
                )
        );

    }
}