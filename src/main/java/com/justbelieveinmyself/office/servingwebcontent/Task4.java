package com.justbelieveinmyself.office.servingwebcontent;

import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.*;

public class Task4 {
//    public static void main(String[] args) {
////        Scanner scanner = new Scanner(System.in);
////        int n = scanner.nextInt(); // sum
////        int m = scanner.nextInt(); // num of banknotes
////        int[] banknotes = new int[m];
////        for (int i = 0; i < m; i++) {
////            banknotes[i] = scanner.nextInt();
////        }
////        List<Integer> res = new ArrayList<>();
////
////        while()
//
//
//    }
public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int m = scanner.nextInt();

    Map<Integer, Integer> denominations = new HashMap<>();
    for (int i = 0; i < m; i++) {
        int denomination = scanner.nextInt();
        denominations.put(denomination, 2);
    }

    List<Integer> stolenDenominations = findStolenDenominations(n, denominations);

    if (stolenDenominations == null) {
        System.out.println(-1);
    } else {
        System.out.println(stolenDenominations.size());
        for (int denomination : stolenDenominations) {
            System.out.print(denomination + " ");
        }
    }
}

    private static List<Integer> findStolenDenominations(int targetSum, Map<Integer, Integer> denominations) {
        List<Integer> stolenDenominations = new ArrayList<>();

        for (int denomination : denominations.keySet()) {
            int count = denominations.get(denomination);

            if (denomination <= targetSum && count > 0) {
                denominations.put(denomination, count - 1);
                int remainingSum = targetSum - denomination;

                if (remainingSum == 0) {
                    stolenDenominations.add(denomination);
                    return stolenDenominations;
                } else {
                    List<Integer> result = findStolenDenominations(remainingSum, denominations);

                    if (result != null) {
                        stolenDenominations.add(denomination);
                        stolenDenominations.addAll(result);
                        return stolenDenominations;
                    }
                }

                denominations.put(denomination, count);
            }
        }

        return null;
    }
}
/*
5 2
1 2
out:
3
1 2 2
------
7 2
1 2
out:
-1
 */