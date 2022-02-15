package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StringApplication {

    public static void main(String[] args) {

        ArrayList<String> array1 = new ArrayList<>();
        array1.add("Hello");
        array1.add("World");
        array1.add("!!");

        ArrayList<String> array2 = new ArrayList<String>() {
            {
                add("Hello");
                add("World");
                add("!!");
            }
        };

        ArrayList<String> array3 = new ArrayList<>(
                List.of("Hello", "World", "!!")
        );

        joinString(array1);

    }

    public static String joinString(ArrayList<String> array) {
        String output1 = array.stream().collect(Collectors.joining(", "));
        String output2 = array.stream().collect(Collectors.joining(" ", "(", ")"));

        System.out.println(output1);
        System.out.println(output2);
        return "";
    }

}

