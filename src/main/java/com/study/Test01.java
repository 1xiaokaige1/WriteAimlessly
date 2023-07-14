package com.study;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: zk
 * Date: 2023/6/25
 * Time: 10:45
 */
public class Test01 {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(5);
        ListNode l2 = new ListNode(5);
        ListNode listNode = testAdd(l1, l2);
        System.out.println("listNode = " + listNode);
    }

    private static ListNode testAdd(ListNode l1, ListNode l2) {
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        convertToBinary(l1, sb);
        convertToBinary(l2, sb2);
        String s = sb.toString();
        String s2 = sb2.toString();
        int length = Math.min(s.length(), s2.length());
        int constantNumber = 0;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            int modifyNumber;
            int number1 = s.charAt(i) - 48;
            int number2 = s2.charAt(i) - 48;
            int sum = number1 + number2 + constantNumber;
            if (sum >= 10) {
                modifyNumber = sum % 10;
                constantNumber = 1;
            } else {
                modifyNumber = sum;
                constantNumber = 0;
            }
            list.add(modifyNumber);
        }
        if (s.length() == s2.length() && constantNumber == 1) {
            list.add(constantNumber);
        }
        if (s.length() > length) {
            computeRemainNumber(s, list, length, constantNumber);
        } else {
            computeRemainNumber(s2, list, length, constantNumber);
        }
        List<ListNode> listNodes = list.stream().map(ListNode::new).collect(Collectors.toList());
        for (int i = 0; i < listNodes.size() - 1; i++) {
            listNodes.get(i).next = listNodes.get(i + 1);
        }
        return listNodes.get(0);
    }

    private static void computeRemainNumber(String s2, List<Integer> list, int length, int constantNumber) {
        if (constantNumber == 1) {
            int constantTmp = 0;
            int remainNumber;
            for (int i = length; i < s2.length(); i++) {
                int tmp = s2.charAt(i) - 48 + constantTmp + constantNumber;
                if (tmp >= 10) {
                    constantTmp = 1;
                    remainNumber = tmp % 10;
                } else {
                    constantTmp = 0;
                    remainNumber = tmp;
                }
                constantNumber = 0;
                list.add(remainNumber);
            }
            if (constantTmp == 1) {
                list.add(constantTmp);
            }
        } else {
            for (int i = length; i < s2.length(); i++) {
                list.add(s2.charAt(i) - 48);
            }
        }
    }

    private static String convertToBinary(ListNode n, StringBuilder sb) {
        sb.append(Long.toString(n.val));
        if (null != n.next) {
            convertToBinary(n.next, sb);
        }
        return sb.toString();
    }

}


class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
