package lambdasinaction._02stream.collect;

import static java.util.stream.Collectors.*;
import static lambdasinaction._02stream.collect.Dish.menu;

public class _03Reducing {

    public static void main(String ... args) {
        System.out.println("Total calories in menu: " + calculateTotalCalories());
        System.out.println("Total calories in menu: " + calculateTotalCaloriesWithMethodReference());
        System.out.println("Total calories in menu: " + calculateTotalCaloriesWithoutCollectors());
        System.out.println("Total calories in menu: " + calculateTotalCaloriesUsingSum());
    }

    //reducing 사용한 칼로리의 합
    private static int calculateTotalCalories() {
        return menu.stream().collect(reducing(0, Dish::getCalories, (i, j) -> i + j));
    }
    //reducing 인자로 method 참조를 사용한 칼로리의 합
    private static int calculateTotalCaloriesWithMethodReference() {
        return menu.stream().collect(reducing(0, Dish::getCalories, Integer::sum));
    }
    //map - reduce 사용
    private static int calculateTotalCaloriesWithoutCollectors() {
        return menu.stream().map(Dish::getCalories)
                .reduce(Integer::sum) //Optional<Dish>
                .get();
    }
    //mapToInt 사용
    private static int calculateTotalCaloriesUsingSum() {

        return menu.stream()
                .mapToInt(Dish::getCalories) //IntStream
                .sum();
    }

}