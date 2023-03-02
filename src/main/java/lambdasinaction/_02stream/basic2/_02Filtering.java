package lambdasinaction._02stream.basic2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lambdasinaction._02stream.basic1.Dish;

public class _02Filtering {

    public static void main(String...args){

        // 1. Filtering with predicate ( isVegeterian() )
        List<Dish> vegeList =
                Dish.menu.stream()//Stream<Dish>
                        .filter(Dish::isVegetarian) //Stream<Dish>
                        .collect(Collectors.toList()); //List<Dish>
        vegeList.forEach(System.out::println);

        // 2. Filtering unique elements
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(num -> num >= 2)
                .distinct()
                .forEach(System.out::println);

        //3. Truncating 3 stream ( d.getCalories() > 300 )
        List<Dish> dishesLimit3 =
                Dish.menu.stream()
                        .filter(dish -> dish.getCalories() >= 300)
                        //.filter(Dish::getCalories >= 300)
                        .limit(3)
                        .collect(Collectors.toList());
        dishesLimit3.forEach(System.out::println);

        //4. Skipping elements
        List<Dish> dishesSkip2 =
                Dish.menu.stream()
                        .skip(2)
                        .collect(Collectors.toList());
        dishesSkip2.forEach(System.out::println);

    }
}
