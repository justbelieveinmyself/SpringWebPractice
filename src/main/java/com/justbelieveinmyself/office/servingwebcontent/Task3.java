package com.justbelieveinmyself.office.servingwebcontent;

import java.util.*;

public class Task3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // count of cards
        int[] input = new int[n];
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            input[i] = scanner.nextInt();
        }
        for (int i = 0; i < n; i++) {
            result[i] = scanner.nextInt();
        }
        int firstMismatch = -1;
        int lastMismatch = -1;

        for (int i = 0; i < n; i++) {
            if (input[i] != result[i]) {
                firstMismatch = i;
                break;
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            if (input[i] != result[i]) {
                lastMismatch = i;
                break;
            }
        }

        if (firstMismatch == -1 && lastMismatch == -1) {
            System.out.println("yes");
        } else {
            sortArray(input, firstMismatch, lastMismatch);
            if (Arrays.equals(input, result)) {
                System.out.println("yes");
            } else {
                System.out.println("no");
            }
        }
    }

    private static void sortArray(int[] array, int start, int end) {
        int[] temp = new int[end - start + 1];
        for (int i = start; i <= end; i++) {
            temp[i - start] = array[i];
        }
        Arrays.sort(temp);
        for (int i = start; i <= end; i++) {
            array[i] = temp[i - start];
        }
    }
}
/*
7
3 4 5 3 2 3 1
4 5 3 3 2 3 1
out: No
----------
7
6 7 5 3 2 1 4
1 2 3 4 5 6 7
out: Yes
----------
3
5 2 3
3 2 5
out: No
----------
9
25 14 17 20 100 40 56 56 53
25 14 17 20 100 40 53 56 56
out: Yes

 */