package com.jecyhw.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CombinationSum3 {
    public List<List<Integer>> combinationSum3(int k, int n) {
        Map<Integer, List<List<Integer>>> data = new HashMap<Integer, List<List<Integer>>>();
        for (int i = 1; i < 10; i++) {
            if (i > n) {
                break;
            }

            Map<Integer, List<List<Integer>>> map = new HashMap<Integer, List<List<Integer>>>();
            for (Map.Entry<Integer, List<List<Integer>>> entry : data.entrySet()) {
                int sumIt = entry.getKey() + i;
                if (sumIt > n) {
                    break;
                }
                for (List<Integer> nums : entry.getValue()) {
                    if (nums.size() >= k || (sumIt == n && nums.size() + 1 < k)) {
                        continue;
                    }

                    List<Integer> newNums = new ArrayList<Integer>(nums);
                    newNums.add(i);

                    List<List<Integer>> lists = get(map, sumIt);
                    lists.add(newNums);
                }
            }

            for (Map.Entry<Integer, List<List<Integer>>> entry : map.entrySet()) {
                List<List<Integer>> lists = get(data, entry.getKey());
                lists.addAll(entry.getValue());
            }

            if (i == n &&  k > 1) {
                continue;
            }

            List<Integer> nums = new ArrayList<Integer>();
            nums.add(i);

            List<List<Integer>> lists = get(data, i);
            lists.add(nums);
        }
        return data.getOrDefault(n, new ArrayList<List<Integer>>());
    }

    private List<List<Integer>> get(Map<Integer, List<List<Integer>>> map, int key) {
        List<List<Integer>> lists = map.get(key);
        if (lists == null) {
            lists = new ArrayList<List<Integer>>();
            map.put(key, lists);
        }
        return lists;
    }

    public static void main(String[] args) {
        System.out.println(new CombinationSum3().combinationSum3(3, 9));
    }
}
