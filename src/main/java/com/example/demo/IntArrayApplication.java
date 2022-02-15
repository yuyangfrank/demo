package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class IntArrayApplication {

    public static void main(String[] args) {

        ArrayList<Integer> array = new ArrayList<>(
                List.of(112, 110, 332, 1, 2, 3, 5, 6, 7, 77, 99, 365)
        );

        array.forEach(i -> System.out.println(i));
        System.out.println(String.format("The length of the list is %d", array.size()));

        int min = minInArray(array);
        System.out.println(String.format("min value in aray is %d", min));

        int max = maxInArray(array);
        System.out.println(String.format("max value in array is %d", max));

        List<String> list = getEven(array).stream().map(Object::toString).collect(Collectors.toList());
        String value = String.join(", ", list);
        System.out.println(String.format("even values in array are %s", value));
    }

    public static int minInArray(ArrayList<Integer> array) {
        int min = Integer.MAX_VALUE;
        for (int i : array) {
            if (i < min) min = i;
        }

        return min;
    }

    public static int maxInArray(ArrayList<Integer> array) {
        int max = Integer.MIN_VALUE;
        for (int i : array) {
            if (i > max) max = i;
        }

        return max;
    }

    public static List<Integer> getEven(ArrayList<Integer> array) {
        return array.stream().filter(i -> i % 2 == 0).collect(Collectors.toList());
    }

    public static void bubbleSortArray(ArrayList<Integer> array) {


    }


}

