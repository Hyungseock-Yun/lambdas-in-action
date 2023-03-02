package lambdasinaction._02stream.collect;

import java.util.*;
import java.util.function.Function;

import static java.util.stream.Collectors.*;
import static lambdasinaction._02stream.collect.Dish.menu;

public class _04GroupingDishes {

    enum CaloricLevel { DIET, NORMAL, FAT };

    public static void main(String ... args) {
        System.out.println("Dishes grouped by type: " + groupDishesByType());
        System.out.println("Dishes grouped by caloric level: " + groupDishesByCaloricLevel());
        System.out.println("Dishes grouped by type and caloric level: " + groupDishedByTypeAndCaloricLevel());
        System.out.println("Count dishes in groups: " + countDishesInGroups());
        System.out.println("Most caloric dishes by type: " + mostCaloricDishesByType());
        System.out.println("Most caloric dishes by type: " + mostCaloricDishesByTypeWithoutOptionals());
        System.out.println("Sum calories by type: " + sumCaloriesByType());
        System.out.println("Caloric levels by type: " + caloricLevelsByType());
    }

    //1. type별 그룹핑
    private static Map<Dish.Type, List<Dish>> groupDishesByType() {
        return menu.stream()
                .collect(groupingBy(getDishTypeFunction()));
    }

    private static Function<Dish, Dish.Type> getDishTypeFunction() {
        return Dish::getType;
    }

    //2. 칼로리별 그룹핑
    private static Map<CaloricLevel, List<Dish>> groupDishesByCaloricLevel() {
        return menu.stream()
                .collect(groupingBy(getDishCaloricLevelFunction()));
    }

    private static Function<Dish, CaloricLevel> getDishCaloricLevelFunction() {
        return dish -> {
            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
            else return CaloricLevel.FAT;
        };
    }

    //3. type별로 그룹핑 후에 다시 칼로리별로 그룹핑
    private static Map<Dish.Type, Map<CaloricLevel, List<Dish>>> groupDishedByTypeAndCaloricLevel() {
        return menu.stream()
                .collect(groupingBy(getDishTypeFunction(), groupingBy(getDishCaloricLevelFunction())));
    }
    //4. type별 갯수 카운팅
    private static Map<Dish.Type, Long> countDishesInGroups() {
        return menu.stream()
                .collect(groupingBy(getDishTypeFunction(),counting()));
    }
    //5. type별 그룹에서 가장 칼로리가 높은 Dish 찾기
    private static Map<Dish.Type, Optional<Dish>> mostCaloricDishesByType() {
        return menu.stream()
                .collect(groupingBy(getDishTypeFunction(), maxBy(getDishComparator())));
    }

    private static Comparator<Dish> getDishComparator() {
        return Comparator.comparingInt(Dish::getCalories);
    }

    //5.1 type별 그룹에서 가장 칼로리가 높은 Dish 찾기 - collectingAndThen() 사용
    //public static <T,A,R,RR> Collector<T,A,RR> collectingAndThen(Collector<T,A,R> downstream, Function<R,RR> finisher)
    //Adapts a Collector to perform an additional finishing transformation.
    private static Map<Dish.Type, Dish> mostCaloricDishesByTypeWithoutOptionals() {
        return menu.stream()
                .collect(groupingBy(getDishTypeFunction(),
                                    collectingAndThen(maxBy(getDishComparator()),Optional::get))); //optional -> optional.get()
    }

    //6. type별로 그룹핑하여 칼로리의 합계 내기
    private static Map<Dish.Type, Integer> sumCaloriesByType() {
        return menu.stream()
                .collect(groupingBy(getDishTypeFunction(),summingInt(Dish::getCalories)));
    }

    private static Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType() {
        return menu.stream().collect(
                groupingBy(getDishTypeFunction(),
                        mapping(getDishCaloricLevelFunction(),
                        toSet() )));
    }
}
