package com.ipinyou.other.features;

import org.junit.Test;

import java.util.HashMap;

public class MapDemo {

    @Test
    public void compute_test() {
        var hashMap = new HashMap<String, String>();
        hashMap.put("a", "1");
        hashMap.put("b", "2");
        hashMap.put("c", "3");

        hashMap.compute("a", (s, s2) ->{
            System.out.println(s);
            if (s2 == null) {
                s2 = "4";
            } else {
                s2 = "111";
            }
            return s2;
        });

        System.out.println(hashMap);
    }

    @Test
    public void computeIfAbsent_test() {
        var hashMap = new HashMap<String, String>();
        hashMap.put("a", "1");
        hashMap.put("b", "2");
        hashMap.put("c", "3");

        hashMap.computeIfAbsent("a", s -> {
            System.out.println(s);
            return "111";
        });

        System.out.println(hashMap);
    }

    @Test
    public void computeIfPresent_test() {
        var hashMap = new HashMap<String, String>();
        hashMap.put("a", "1");
        hashMap.put("b", "2");
        hashMap.put("c", "3");

        hashMap.computeIfPresent("d", (s, s2) ->{
            System.out.println(s);
            s2 = "111";
            return s2;
        });

        System.out.println(hashMap);
    }

}
