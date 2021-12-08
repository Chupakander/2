package com.company;


public class Apple extends Food {

    private String size;

    @Override
    public void consume() {
        System.out.println(this + " съедено");
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Apple(String size){
        super("Яблоко");
        this.size= size;
    }

    @Override
    public String toString() {
        return super.toString() + " размера '" + size.toUpperCase() + "'";
    }


    @Override
    public int calculateCalories() {
        int calories = 0;
        if(size.equals("большое")) {
            calories+= 30;
        } else if(size.equals("небольшое")){
            calories+=10;
        }else{
            calories+=5;
        }
        return calories;
    }
}