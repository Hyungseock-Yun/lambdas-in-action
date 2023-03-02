package lambdasinaction._02stream.basic2;

import lambdasinaction._02stream.basic1.*;

import java.lang.reflect.Array;
import java.util.*;
import static java.util.stream.Collectors.toList;
import static lambdasinaction._02stream.basic1.Dish.menu;

public class _03Mapping {

    public static void main(String...args){

        //1. map - Dish의 name 목록만



        // map
        List<String> words = Arrays.asList("Hello", "World");
        List<Integer> wordLengths = words.stream()
                                         .map(String::length)
                                         .collect(toList());
        System.out.println(wordLengths);

        //2. map - 중복된 문자 제거한 word 리스트
        //<R> Stream<R> map(Function<? super T, ? extends R> mapper)
        words.stream() //Stream<String>
                .map(word -> word.split("")) //Stream<String[]>
                .distinct()
                .forEach(System.out::println);

        //3.flatMap - 중복된 문자 제거가 word 리스트
        //<R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper)
        words.stream()
                .map(word -> word.split("")) //Stream<String[]>
                .flatMap(arr -> Arrays.stream(arr)) //Stream<String>
                .distinct()
                .forEach(System.out::println);

        words.stream()
                .flatMap(word -> Arrays.stream(word.split("")))
                .distinct()
                .forEach(System.out::print);

        // flatMap
        List<Integer> numbers1 = Arrays.asList(1,2,3,4,5);
        List<Integer> numbers2 = Arrays.asList(6,7,8);
        List<int[]> pairs =
                        numbers1.stream()
                                .flatMap((Integer i) -> numbers2.stream()
                                                       .map((Integer j) -> new int[]{i, j})
                                 )
                                .filter(pair -> (pair[0] + pair[1]) % 3 == 0)
                                .collect(toList());
        pairs.forEach(pair -> System.out.println("(" + pair[0] + ", " + pair[1] + ")"));
    }
}
