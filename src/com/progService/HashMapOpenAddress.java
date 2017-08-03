package com.progService;

import java.util.Arrays;



public class HashMapOpenAddress {
    int capacity;
    int initialCapacity;
    double loadFactor;
    int EMPTY = Integer.MIN_VALUE;

    int[] keys;
    long [] values;


    public HashMapOpenAddress() {
        capacity = 16; // default in Java
        loadFactor = 0.75; // default in Java
        initialCapacity = capacity;
        keys = new int[capacity];
        values = new long[capacity];
        Arrays.fill(keys, EMPTY);
    }

    public HashMapOpenAddress(int initialCapacity) {
        if (initialCapacity <= 0){
            throw new IllegalArgumentException("Initial capacity is negative: " + initialCapacity);
        } else {
            capacity = initialCapacity;
            this.initialCapacity = initialCapacity;
        }
        loadFactor = 0.75;
        keys = new int[capacity];
        values = new long[capacity];
        Arrays.fill(keys, EMPTY);
    }

    public HashMapOpenAddress(int initialCapacity, double loadFactor) {
        this(initialCapacity);
        // Unlike Separate chaining method, Open Address method does not allow to have Load Factor greater than 1
        if (loadFactor > 1) {
            throw new IllegalArgumentException("Load factor greater than 1: " + loadFactor);
        } else{
            this.loadFactor = loadFactor;
        }
    }

    // Hash Code (appropriate for int Key)
    int hash(int x) {
        return (x >> 15) ^ x;
    }

    // Position in HashMap
    int index(int hash) {
        return Math.abs(hash) % initialCapacity;
    }

    // Put <K,V> into HashMap
    void put(int x, long y) {
        // Control the size of HashMap according to loadFactor
        if ((double)size() / capacity >= loadFactor) {
            increaseSize();
        }
        //Put Pair<K,V>
        for (int i = index(hash(x)); ; i++) {
            if (i == capacity) {
                i = 0;
            }
            if (keys[i] == EMPTY){
                keys[i] = x;
            }
            if (keys[i] == x) {
                values[i] = y;
                return;
            }
        }
    }

    // Get Value taking into account Key
    long get(int x) {
        for (int i = index(hash(x)); ; i++) {
            if (i == capacity) {
                i = 0;
            }
            if (keys[i] == EMPTY) {
                throw new RuntimeException("No such key!");
            }
            if (keys[i] == x) {
                return values[i];
            }
        }
    }

    // Number of the allocated Pairs in HashMap
    int size() {
        int count = 0;
        for (int key : keys) {
            if (key != EMPTY) {
                count++;
            }
        }
        return count;
    }

    // Increase Array if load factor reach its value
    //to avoid ArrayIndexOutOfBoundsException
    private void increaseSize() {
        capacity *= 2;
        int[] tempKey = keys;
        long[] tempValues = values;
        keys = new int[capacity];
        Arrays.fill(keys, EMPTY);
        values = new long[capacity];
        for (int j = 0; j < capacity / 2; j++) {
            keys[j] = tempKey[j];
            values[j] = tempValues[j];
        }
    }
}

