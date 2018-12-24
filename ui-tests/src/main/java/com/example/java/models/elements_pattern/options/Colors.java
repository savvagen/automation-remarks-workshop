package com.example.java.models.elements_pattern.options;

public class Colors {

    public enum Color {
        BROWN("brown"),
        GEEK("geek"),
        FORMAL("formal"),
        BLUE("blue"),
        SKIN("skin"),
        RED("red"),
        ACTION("action"),
        SPORT("sport"),
        BLACK("black"),
        MAGIC("magic"),
        GREEN("green");

        public String name;

        Color(String color){
            this.name = color;
        }
    }

}
