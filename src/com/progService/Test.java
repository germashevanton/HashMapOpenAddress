package com.progService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Anton on 26.07.2017.
 */
public class Test {

    Map<Integer, Long> map = new HashMap<>();

    public static void main(String[] args) {
        Map<Integer, Long> map = new HashMap<>();
        map.put(10,10L);

        map.put(10,100L);
        map.put(10,1000L);
        System.out.println(map.toString());
        System.out.println((10 >> 15)^10);
        System.out.println(10%31);

        System.out.println(0^7);

        HashMapOpenAddress openHashMap = new HashMapOpenAddress(1);
        openHashMap.put(40, 20);
        System.out.println(openHashMap.size());
        System.out.println(Arrays.toString(openHashMap.keys));
        openHashMap.put(9, 30);
        System.out.println(Arrays.toString(openHashMap.keys));
        openHashMap.put(11, 30);
        System.out.println(Arrays.toString(openHashMap.keys));
        System.out.println(openHashMap.size());
        System.out.println(openHashMap.get(40));

        System.out.println(openHashMap.get(9));
        HashMapOpenAddress hashMapOpenAdress = new HashMapOpenAddress();
        hashMapOpenAdress.put(16,20);
        System.out.println(hashMapOpenAdress.get(16));
        System.out.println(Arrays.toString(hashMapOpenAdress.keys));
        hashMapOpenAdress.put(32,20);

        System.out.println(Arrays.toString(hashMapOpenAdress.keys));

        hashMapOpenAdress = new HashMapOpenAddress();
        int numOfElements = 25;
        for (int i = 0; i < 16*numOfElements; i+=16) {
            hashMapOpenAdress.put(i, 1000L);
            System.out.println(Arrays.toString(hashMapOpenAdress.keys));
            System.out.println(hashMapOpenAdress.capacity + " " + hashMapOpenAdress.size());
        }

        HashMapOpenAddress hashMapOpenAdressCapacity = new HashMapOpenAddress(10); // loadFactor 0.75
        HashMapOpenAddress hashMapOpenAddressLoadFactor = new HashMapOpenAddress(10, 0.5);
        HashMapOpenAddress hashMapOpenAddressLoadFactor2 = new HashMapOpenAddress(10, 0.9);
        for (int i = 0; i < 20; i++) {
            Random random = new Random();
            long randomValue = random.nextLong();
            hashMapOpenAdressCapacity.put(i, randomValue);
            hashMapOpenAddressLoadFactor.put(i, randomValue);
            hashMapOpenAddressLoadFactor2.put(i, randomValue);
            System.out.println(Arrays.toString(hashMapOpenAdressCapacity.keys));
            System.out.println(Arrays.toString(hashMapOpenAddressLoadFactor.keys));
            System.out.println(Arrays.toString(hashMapOpenAddressLoadFactor2.keys));
        }
    }
}
