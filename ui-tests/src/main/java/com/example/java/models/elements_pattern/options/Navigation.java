package com.example.java.models.elements_pattern.options;

public class Navigation {


    public enum Tags {
        BROWN("Brown", "brown"),
        LARGE("Large", "large"),
        MAGIC("Magic", "magic");


        public String name;
        public String filter;

        Tags(String name, String filter) {
            this.name = name;
            this.filter = filter;
        }

    }

}
