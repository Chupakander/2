package com.company;

public class Sandwich extends Food {

    public Sandwich(){
        super ("Sandwich");
    }

    public void consume(){
        System.out.println(this + " съеден");
    }
    @Override
    public int calculateCalories() {
        return 400;
    }
}