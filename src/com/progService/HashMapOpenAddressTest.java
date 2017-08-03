package com.progService;


import java.util.Random;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HashMapOpenAddressTest {

    private HashMapOpenAddress hashMapOpenAddress;
    private static Random random;

    @BeforeAll
    public static void beforeClass() {
        random = new Random();
    }

    @BeforeEach
    public void setUp() {
        hashMapOpenAddress = new HashMapOpenAddress();
    }

    //testing Hash Map with initial capacity 16 by artifisially putting 10000 elements
    // multiple of 16, so collision situation should be achieved every consecutive putting.
    // However, due to Open Address method everything fine!
    @Test
    public void putAndGetTestCollision() {
        int numOfElements = 10000;
        long someValue = 1000;
        for (int i = 0; i < 16 * numOfElements; i += 16) {

            hashMapOpenAddress.put(i, someValue += 1234);
        }
        someValue = 1000;
        for (int i = 0; i < 16 * numOfElements; i += 16) {
            assertEquals(someValue += 1234, hashMapOpenAddress.get(i));
        }
    }

    // testing Hash Map with initial capacity 16 and 1500 element by putting and getting 1000 elements
    @Test
    public void putAndGetTestCapacity() {
        int numOfElements = 1000;
        HashMapOpenAddress hashMapOpenAdressCapacity = new HashMapOpenAddress(1500);
        long randomValue;
        for (int i = 0; i < numOfElements; i++) {
            randomValue = random.nextLong();
            hashMapOpenAddress.put(i, randomValue);
            hashMapOpenAdressCapacity.put(i, randomValue);
        }
        for (int i = 0; i < numOfElements; i++) {
            assertEquals(hashMapOpenAddress.get(i), hashMapOpenAdressCapacity.get(i));
        }
    }

    // testing Hash Map with loadFactor 0.5; 0.75; 0.9 by putting and getting 1000 elements
    @Test
    public void putAndGetTestLoadFactor() {
        int numOfElements = 1000;
        HashMapOpenAddress hashMapOpenAdressCapacity = new HashMapOpenAddress(1500); // loadFactor 0.75
        HashMapOpenAddress hashMapOpenAddressLoadFactor = new HashMapOpenAddress(1500, 0.5);
        HashMapOpenAddress hashMapOpenAddressLoadFactor2 = new HashMapOpenAddress(1500, 0.9);
        long randomValue;
        for (int i = 0; i < numOfElements; i++) {
            randomValue = random.nextLong();
            hashMapOpenAdressCapacity.put(i, randomValue);
            hashMapOpenAddressLoadFactor.put(i, randomValue);
            hashMapOpenAddressLoadFactor2.put(i, randomValue);
        }
        for (int i = 0; i < numOfElements; i++) {
            assertEquals(hashMapOpenAddressLoadFactor.get(i), hashMapOpenAdressCapacity.get(i));
            assertEquals(hashMapOpenAddressLoadFactor2.get(i), hashMapOpenAdressCapacity.get(i));
        }
    }

    //testing the size of Hash Map after putting 10000 elements
    @Test
    public void sizeSuccessTest() {
        int numOfElements = 10000;
        for (int i = 0; i < numOfElements; i++) {
            hashMapOpenAddress.put(random.nextInt(), random.nextLong());
        }
        int res = hashMapOpenAddress.size();
        assertEquals(numOfElements, res);
    }
}
