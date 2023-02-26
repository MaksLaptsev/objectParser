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
import ru.clevertec.entity.Simple;
import ru.clevertec.entity.Top;
import ru.clevertec.parser.ObjParser;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
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

    @Test
    void toJson() throws IOException, ClassNotFoundException, IllegalAccessException {
        Assertions.assertThat(objParser.toJson(null)).isEqualTo(gson.toJson(null));
    }

    @ParameterizedTest
    @MethodSource("argsForToJson")
    void toJson(Object o) throws IllegalAccessException, IOException, ClassNotFoundException {
        Assertions.assertThat(objParser.toJson(o)).isEqualTo(gson.toJson(o));
    }

    static Stream<Arguments> argsForToJson(){
        return Stream.of(
                Arguments.of(
                        new Simple(1,"Filche","D-Grey",12,true, Arrays.asList(1,2,3,4))
                ),
                Arguments.of(
                        new Middle(2,"Henry","D-Blue",35,false,
                                new String[][]{{"dasf","qwe","qqq"},{"hello","world"}},
                                new Integer[][]{{1,2,3,4},{5,6,8}},new Boolean[][]{{true,false,false},{false,false}},
                                new Character[]{'s','z','v'}, new Double[]{22.1,33.5,10.9}, new Float[]{1.5f,5.0f,9.9f},
                                new byte[]{1,2,9}, new short[]{123,15},
                                Arrays.asList(
                                        new Simple(1,null,"D-Grey",12,true, Arrays.asList(1,2,3,4)),
                                        new Simple(2,"Alex","Robert",112,false, Arrays.asList(4,122,3333,84))
                                ),
                                Map.of("str1",1,"str2",2,"str3",3),
                                Map.of(1.5,2,2.6,3,4.8,5),
                                Map.of("FirstSimple",new Simple(11,"First","D-Grey",124,true, Arrays.asList(1,2,3,4)),
                                        "SecondSimple",new Simple(55,null,"D",19,true, Arrays.asList(55,62,3,4))))
                ),
                Arguments.of(
                        new Top(5,"Georg","Jesus",999,true,new Simple(1,null,
                                "D-Grey",12,true,Arrays.asList(1,2,3,4)),
                                new Middle(2,"Henry","D-Blue",35,false,
                                        new String[][]{{"dasf","qwe","qqq"},{"hello","world"}},
                                        new Integer[][]{{1,2,3,4},{5,6,8}},new Boolean[][]{{true,false,false},{false,false}},
                                        new Character[]{'s','z','v'},
                                        new Double[]{22.1,33.5,10.9}, new Float[]{1.5f,5.0f,9.9f},
                                        new byte[]{1,2,9}, new short[]{123,15},
                                        Arrays.asList(
                                            new Simple(10,"Natali","Helicopter",23,true, Arrays.asList(11,288,3,4)),
                                            new Simple(6,"Fedor","Robert",112,false, Arrays.asList(4,122,3333,84))
                                        ),
                                        Map.of("str1",1,"str2",2,"str3",3),
                                        Map.of(1.5,2,2.6,3,4.8,5),
                                        Map.of("FirstSimple",new Simple(11,"First","D-Grey",124,true, Arrays.asList(1,2,3,4)),
                                                "SecondSimple",new Simple(55,null,"D",19,true, Arrays.asList(55,62,3,4))))
                                )
                            )
                        );
    }
}