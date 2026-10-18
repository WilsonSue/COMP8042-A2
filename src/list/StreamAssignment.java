package src.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamAssignment {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 5, 6, 4, 6, 2, 8, 6));

        // Remove Evens
        List<Integer> noEvens = list.stream()
                .filter(n -> n % 2 != 0) // same mod logic
                .collect(Collectors.toList());
        System.out.println("Remove Evens: " + noEvens);

        // Remove Odds
        List<Integer> noOdds = list.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("Remove Odds: " + noOdds);

        // Remove All Instances of 6
        List<Integer> noSixes = list.stream()
                .filter(n -> n != 6)
                .collect(Collectors.toList());
        System.out.println("Remove 6s: " + noSixes);

        // myFilter usage
        int sum = myFilter(list, 6);
        System.out.println("\nResult of myFilter (sum of non-even, non-6 numbers): " + sum);
    }

    public static int myFilter(List<Integer> list, int elementToFilter) {
        return list.stream()
                .filter(n -> n % 2 != 0)         // Filters out evens (keeps odds)
                .filter(n -> n != elementToFilter) // Removes all instances of given number
                .reduce(0, Integer::sum);        // Sums the result
    }
}