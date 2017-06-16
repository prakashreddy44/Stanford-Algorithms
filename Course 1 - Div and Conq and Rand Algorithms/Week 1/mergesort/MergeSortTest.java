package mergesort;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class MergeSortTest {

    private Integer[] numbers;
    private int[] std;
    private final static int SIZE = 10000000;
    private final static int MAX = 10000000;

    @Before
    public void setUp() throws Exception {
        numbers = new Integer[SIZE];
        std = new int[SIZE];
        Random generator = new Random();
        for (int i = 0; i < numbers.length; i++) {
            int num = generator.nextInt(MAX);
            numbers[i] = num;
            std[i] = num;
        }
        Arrays.sort(std);
    }
    
    @Test
    public void testMergeSort() {
        long startTime = System.currentTimeMillis();
        MergeSort.sort(numbers);
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("MergeSort " + elapsedTime);

        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i - 1] > numbers[i]) {
                fail("Array not sorted in ascending order");
            }
        }
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] != std[i]) {
                fail("Array elements different");
            }
        }
        assertTrue(true);
    }

    @Test
    public void testStandardSort() {
        long startTime = System.currentTimeMillis();
        Arrays.sort(numbers);
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("Standard Java sort " + elapsedTime);

        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i - 1] > numbers[i]) {
                fail("Array not sorted in ascending order");
            }
        }
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] != std[i]) {
                fail("Array elements different");
            }
        }
        assertTrue(true);
    }
}