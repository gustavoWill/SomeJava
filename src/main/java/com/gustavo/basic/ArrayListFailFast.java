package com.gustavo.basic;

import java.util.ArrayList;
import java.util.List;

public class ArrayListFailFast {
    public static void main(String[] args) {
        List<String> masterList = new ArrayList();
        masterList.add("one");
        masterList.add("two");
        masterList.add("third");
        masterList.add("four");

        for (String s : masterList) {
            if(s.equals("two")){
                masterList.remove(s);
            }
        }
        System.out.println(masterList);
    }
}
