package com.company;


public class Milk extends Food {
    private String fill;

    public boolean equals(Object obj) {
        if (!(obj instanceof Milk)) {
            return false;
        } else if (this.fill != null && ((Milk)obj).fill != null) {
            return !this.fill.equals(((Milk)obj).fill) ? false : super.equals(obj);
        } else {
            return false;
        }
    }

    public String getFilling() {
        return this.fill;
    }

    public void setFilling(String fill) {
        this.fill = fill;
    }

    public Milk(String fill) {
        super("Moloko");
        this.fill = fill;
    }

    public void consume() {
        System.out.println(this + " выпито");
    }

    public String toString() {
        String var10000 = super.toString();
        return var10000 + " с жирностью '" + this.fill.toUpperCase() + "'";
    }
    @Override
    public int calculateCalories() {
        int calories = 0;
        if(fill.equals("5")) {
            calories+= 100;
        } else if(fill.equals("2.5")){
            calories+=70;
        }else if(fill.equals("1.5")) {
            calories += 50;
        }
        return calories;
    }
}
