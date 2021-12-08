package com.company;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Main {

    public static void main(String[] args) throws Exception {
        Food[] breakfast = new Food[args.length];
        int count_breakfast = 0;
        boolean calories_needed = true;
        for (int i = 0; i < args.length; i++) {
            String[] parts = args[i].split("/");
            if (parts[0].equals("Cheese")) {
                breakfast[i] = new Cheese();
                count_breakfast++;
            } else if (parts[0].equals("Apple")) {
                breakfast[i] = new Apple(parts[1]);
                count_breakfast++;
            } else if (parts[0].equals("Milk")) {
                breakfast[i] = new Milk(parts[1]);
                count_breakfast++;
            }  else if (parts[0].equals("Sandwich")) {
                    breakfast[i] = new Sandwich();
                count_breakfast++;
            }
        }

        for (Food item : breakfast) {
            item.consume();
        }
        if(calories_needed){
            int calorii = 0;
            for (int i = 0; i < count_breakfast; i++) calorii += breakfast[i].calculateCalories();
            System.out.println("калорийность: " + calorii);
        }

        Food food = new Milk("5");
        System.out.println(food + ": " + countFoods(breakfast, food));
        printFoods(breakfast);
        System.out.println("Всего хорошего!");


    }

    static Integer countFoods(Food[] breakfast, Food food) {
        Integer count = 0;
        for(int i = 0; i < breakfast.length; i++) {
            if(food.equals(breakfast[i]))
            {
                count++;
            }
        }
        return count;
    }

    static void printFoods(Food[] breakfast){
        int c = 0;
        int a = 0;
        int p = 0;
        int h = 0;
        for(int i = 0; i < breakfast.length; i++) {

            if(breakfast[i] instanceof Cheese){
                c++;
            }
            else if (breakfast[i] instanceof Apple){
                a++;
            }
            else if (breakfast[i] instanceof Milk){
                p++;
            }
            else if (breakfast[i] instanceof Sandwich){
                h++;
            }
        }
        System.out.println("CЫР - " + c);
        System.out.println("ЯБЛОКО - " + a);
        System.out.println("Milk - " + p);
        System.out.println("Sandwich - " + h);
    }
}