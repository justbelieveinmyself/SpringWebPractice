package com.justbelieveinmyself.office.servingwebcontent;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // sum
        int m = scanner.nextInt(); // num of banknotes
        Map<Integer, Integer> banknotes = new HashMap<>();
        for (int i = 0; i < m; i++) {
            int cur = scanner.nextInt();
            banknotes.put(cur, 2);
        }
        List<Integer> stolen = findCombination(n, banknotes);
        if(stolen == null) System.out.println(-1);
        else{
            System.out.println(stolen.size());
            for (int i : stolen) {
                System.out.print(i + " ");
            }
        }
    }

    private static List<Integer> findCombination(int targetSum, Map<Integer, Integer> banknotes) {
        List<Integer> result = new ArrayList<>();
        for (Integer banknote : banknotes.keySet()) {
            int count = banknotes.get(banknote);
            if(banknote <= targetSum && count > 0){
                banknotes.put(banknote, count - 1);
                int remainingSum = targetSum - banknote;
                if(remainingSum == 0){
                    result.add(banknote);
                    return result;
                }else{
                    List<Integer> res = findCombination(remainingSum, banknotes);

                    if(res != null){
                        result.add(banknote);
                        result.addAll(res);
                        return result;
                    }
                }
                banknotes.put(banknote, count);
            }
        }
        return null;
    }
}
