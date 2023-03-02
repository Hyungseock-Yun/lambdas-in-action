package lambdasinaction._02stream.basic2;

import lambdasinaction._02stream.basic1.Dish;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import static lambdasinaction._02stream.basic1.Dish.menu;

public class _05Reducing {

    public static void main(String... args) {

        //reduce - a + b 연산
        List<Integer> numbers = Arrays.asList(3, 4, 5, 1, 2);
        //reduce() 메서드 구현
        Integer sum = numbers.stream()
                .reduce(0, (n1, n2) -> n1 + n2);
        System.out.println("sum = " + sum);
        //IntStream의 sum() 메서드 사용
        sum = numbers.stream().mapToInt(Integer::intValue).sum();
        System.out.println("sum2 = " + sum);

        //sum = numbers.stream().flatMapToInt(number -> IntStream.of(number)).sum();
        sum = numbers.stream().flatMapToInt(IntStream::of).sum();
        System.out.println("sum3 = " + sum);

        //reduce -  최대값
        int max = numbers.stream().reduce(0, (n1,n2) -> Integer.max(n1,n2));
        max = numbers.stream().reduce(0, Integer::max);
        System.out.println("max = " + max);

        //Stream의 min()
        int minValue = numbers.stream().min(Integer::compareTo).get();
        System.out.println("minValue = " + minValue);

        //reduce - 최소값
        Optional<Integer> optional = numbers.stream().reduce(Integer::min);
        minValue = optional.orElse(0);
        System.out.println("minValue2 = " + minValue);
        optional.ifPresent(System.out::println);

        //Dish 의  총 칼로리 합계를 구하는 여러가지 방법
        //1. reduce() 함수로 구현
        Integer totalValue = menu.stream() //Stream<Dish>
                .map(Dish::getCalories) //Stream<Integer>
                .reduce(0, (dish1, dish2) -> dish1 + dish2);
        System.out.println("totalValue = " + totalValue);
        //2. reduce() 함수에서 Integer.sum() 메서드 호출
        totalValue = menu.stream()
                .map(Dish::getCalories)
                .reduce(Integer::sum) //Optional<Integer>
                .get();
        System.out.println("totalValue2 = " + totalValue);
        //3. mapToInt()사용하여 IntStream 변환하여 sum() 호출
        totalValue = menu.stream()
                .mapToInt(Dish::getCalories) //IntStream
                .sum();
        System.out.println("totalValue3 = " + totalValue);
        //4. Collectors의 summingInt() 호출
        totalValue = menu.stream()
                        .collect(Collectors.summingInt(Dish::getCalories));
        System.out.println("totalValue4 = " + totalValue);

        IntSummaryStatistics statistics = menu.stream()
                .collect(Collectors.summarizingInt(Dish::getCalories));
        System.out.println("statistics = " + statistics);
    }
}
