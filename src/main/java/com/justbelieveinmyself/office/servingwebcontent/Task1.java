package com.justbelieveinmyself.office.servingwebcontent;

import java.util.Scanner;

public class Task1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); //num of revolvers
        int s = scanner.nextInt(); //dollars
        int price = 0;
        for (int i = 0; i < n; i++) {
            int nextPrice = scanner.nextInt();
            if(nextPrice > price && nextPrice <= s){
                price = nextPrice;
            }
        }
        System.out.println(price);

    }
}
/*
5 13
3 10 300 15 3
out: 10
----------
3 4
5 12 10
out: 0
 */