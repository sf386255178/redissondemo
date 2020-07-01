package com.bds.redissondemo.utils;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

/**
 * @Author :Kevin Ding;
 * @TIME :2020/7/1;
 * @TODO :排列组合算法;
 * 输入一个字符串,按字典序打印出该字符串中字符的所有排列。例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
 *思路：
 * 1.把字符串分成两部分，一部分是字符串的第一个字符，另一部分是第一个字符以后的所有字符。递归求另一部分字符的排列。
 * 2.拿第一个字符和它后面的字符逐个交换。注：交换完还要换回去
 */
public class Solution {
    public ArrayList<String> permutation(String str){
        ArrayList<String> res = new ArrayList<String>();
        if(str == null || str.length() <= 0)
            return res;
        HashSet<String> set = new HashSet<String>(); //结果去重
        dfs(set, str.toCharArray(), 0);
        res.addAll(set);
        Collections.sort(res);
        return res;
    }

    public void dfs(HashSet<String> set, char [] str, int k){
        if(k == str.length){  //得到结果
            set.add(new String(str));
            return ;
        }
        for(int i = 0; i < str.length; i ++){
            swap(i, k, str);
            dfs(set, str, k + 1);
            swap(i, k, str);  //回溯
        }
    }

    public void swap(int i, int j, char [] str){
        if(i != j){
            char temp = str[i];
            str[i] = str[j];
            str[j] = temp;
        }
    }


    /**
     * 计算n的阶乘
     *
     * @param n
     * @return 返回 n!
     */
    private static long factorial(int n) {
        long sum = 1;
        while (n > 0) {
            sum = sum * n--;
        }
        return sum;
    }

    /**
     *2.计算排列A(n,m)个数
     * @param m
     * @param n
     * @return 返回A(n, m)的排列个数
     */
    private static long arrangement(int m, int n) {
        return m <= n ? factorial(n) / factorial(n - m) : 0;
    }


    /**
     * 3计算组合C(n, m)
     *
     * @param m
     * @param n
     * @return 返回C(n, m)的组合个数
     */
    private static long combination(int m, int n) {
        return m < n ? factorial(n) / (factorial(n - m) * factorial(m)) : 0;
    }


    /**
     * 4展示排列情况
     * @param dataList
     * @param n
     */
    public static void arrangementSelect(String[] dataList, int n) {
        System.out.println(String.format("A(%d, %d) = %d ", dataList.length, n, arrangement(n, dataList.length)));
        arrangementSelectRes(dataList, new String[n], 0);
    }

    private static void arrangementSelectRes(String[] dataList, String[] resultList, int resultIndex) {
        int resultLen = resultList.length;
        if (resultIndex >= resultLen) { // 全部选择完时，输出排列结果
            System.out.println(Arrays.asList(resultList));
            return;
        }
        // 递归选择下一个
        for (int i = 0; i < dataList.length; i++) {
            // 判断待选项是否存在于排列结果中
            boolean exists = false;
            for (int j = 0; j < resultIndex; j++) {
                if (dataList[i].equals(resultList[j])) {
                    exists = true;
                    break;
                }
            }
            if (!exists) { // 排列结果不存在该项，才可选择
                resultList[resultIndex] = dataList[i];
                arrangementSelectRes(dataList, resultList, resultIndex + 1);
            }
        }
    }

    /**
     * 5展示组合情况
     * @param dataList
     * @param n
     */
    public static void combinationSelect(String[] dataList, int n) {
        System.out.println(String.format("C(%d, %d) = %d", dataList.length, n, combination(n, dataList.length)));
        combinationSelect(dataList, 0, new String[n], 0);
    }
    private static void combinationSelect(String[] dataList, int dataIndex, String[] resultList, int resultIndex) {
        int resultLen = resultList.length;
        int resultCount = resultIndex + 1;
        if (resultCount > resultLen) { // 全部选择完时，输出组合结果
            System.out.println(Arrays.asList(resultList));
            return;
        }
        // 递归选择下一个
        for (int i = dataIndex; i < dataList.length + resultCount - resultLen; i++) {
            resultList[resultIndex] = dataList[i];
            combinationSelect(dataList, i + 1, resultList, resultIndex + 1);
        }
    }






    public static void main(String[] args) {
        System.out.println(new Solution().permutation("abc"));
        // 计算n的阶乘
        System.out.println(arrangement(3, 6));
        System.out.println(combination(3, 6));
        String[] a = {"a", "b", "c","e"};
        arrangementSelect(a, 3);
        combinationSelect(a,3);
    }
}
