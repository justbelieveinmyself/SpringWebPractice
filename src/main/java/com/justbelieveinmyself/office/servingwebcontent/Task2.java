package com.justbelieveinmyself.office.servingwebcontent;

import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        String needed = "sheriff";
        Scanner scanner = new Scanner(System.in);
        StringBuilder stringBuilder =  new StringBuilder(scanner.nextLine());
        boolean flag = true;
        int contains = 0;
        while(flag) {
            for (int i = 0; i < needed.length(); i++) {
                char currentChar = needed.charAt(i);
                String current = String.valueOf(currentChar);
                int index = stringBuilder.indexOf(current, 0);
                if(index == -1){
                    flag = false;
                    break;
                }
                stringBuilder.deleteCharAt(index);
                if(i == needed.length() -1) {
                    contains++;
                }
            }
        }
        System.out.println(contains);
    }
}
/*
fheriherffazfszkisrrs
out: 2
---------
rifftratatashe
out: 1
---------
thegorillaiswatching
out: 0
 */