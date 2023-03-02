package lambdasinaction._02stream.basic2;
import lambdasinaction._02stream.basic1.*;

import java.util.*;

import static lambdasinaction._02stream.basic1.Dish.menu;

public class _04Finding {

    public static void main(String...args){
        if(isVegetarianFriendlyMenu()){
            System.out.println("Vegetarian friendly");
        }

        System.out.println(isHealthyMenu());
        System.out.println(isHealthyMenu2());
        
        Optional<Dish> dish = findVegetarianDish();
        dish.ifPresent(d -> System.out.println(d.getName()));
    }

    //1. anyMatch
    private static boolean isVegetarianFriendlyMenu(){
        return menu.stream()
                .anyMatch(Dish::isVegetarian);
    }
    //2.allMatch
    private static boolean isHealthyMenu(){
        return menu.stream()
                .allMatch(dish -> dish.getCalories() <= 800);
    }

    //3. noneMatch
    private static boolean isHealthyMenu2(){
        return menu.stream()
                .noneMatch(dish -> dish.getCalories() > 800);
    }
    //4. findAny
    private static Optional<Dish> findVegetarianDish(){
        //return Optional.empty();
        return menu.stream()
                .filter(Dish::isVegetarian)
                .findAny();
    }
    
}
