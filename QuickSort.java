import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Timer;

/**
 * Created by Dany on 01.05.14.
 */
public class QuickSort {
    private static int[][] arrays;
    private static int[] array;

    public static void main(String[] args){
//        int a [] = concatenateTwoArrays(getRandomArray(6), getRandomArray(5));

//        int [] a = bubbleSort(new int[]{5, 2, 3, 1, 4});
//        int [] a = bubbleSort(getRandomArray(100));
//        printArray(a);

//        printArray(concatenateNArraysRec(getSortedArrays(5, 3)));

        testSort();
    }

    private static void testSort(){
        array = getRandomArray(50000);
        int hot = 3;
        for (int i = 0; i < hot; i++) testQuick();
        for (int i = 0; i < hot; i++) testBubble();

        testBubble();
        testQuick();
    }

    private static void testQuick(){
        start();
        Arrays.sort(Arrays.copyOf(array, array.length));
        stop();
        sout("quick");
    }

    private static void testBubble(){
        start();
        bubbleSort(Arrays.copyOf(array, array.length));
        stop();
        sout("bubble");
    }

    private static void testConcatenate(){
        arrays = getSortedArrays(5000, 10);

        testFor();
        testFor();
        testFor();

        testRecursive();
        testRecursive();
        testRecursive();

        testFor();
        testRecursive();

    }

    private static void testRecursive(){
        start();
        concatenateNArraysRec(arrays);
        stop();
        sout("Recursive");
    }

    private static void testFor(){
        start();
        concatenateNArrays(arrays);
        stop();
        sout("for");
    }

    private static void printArray(int a[]){
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }

    private static int getRandom(){
        return (int) (100 * Math.random());
    }

    private static int [] getRandomArray(int length){
        int [] array = new int [length];
        for (int i = 0; i < length; i++) {
            array[i] = getRandom();
        }

        return array;
    }

    public static int[] bubbleSort(int[] array){
        if (array.length <= 1) {
            return array;
        }

        while(!isSorted(array)){
            for (int i = 1; i < array.length; i++) {
                if (array[i] < array[i-1]){
                    int a = array[i];
                    array[i] = array[i-1];
                    array[i-1] = a;
                }
            }
        }

        return array;
    }

    private static boolean isSorted(int[] array) {
        boolean isSorted = true;
        if (array.length <= 1) {
            return true;
        }

        for (int i = 1; i < array.length; i++) {
            isSorted &= array[i] >= array[i-1];
        }

        return isSorted;
    }

    public static int[] quickSort(int array []){
        if (array.length <= 1) {
            return array;
        }

        int e = array[0];
        int min[] = new int[array.length];
        int max[] = new int[array.length];

        int imin = 0;
        int imax = 0;

        for (int a : array){
            if (e > a){
                min[imin] = a;
                imin++;
            } else {
                max[imax] = a;
                imax++;
            }

//            if (array.length)
        }

        return concatenateTwoArrays(quickSort(Arrays.copyOf(min, imin)), quickSort(Arrays.copyOf(max, imax)));

    }

    private static int[] concatenateTwoArrays(int[] first, int[] second) {
        int length = first.length + second.length;
        int result [] = new int [length];
        System.arraycopy(first, 0, result, 0, first.length);
        System.arraycopy(second, 0, result, first.length, second.length);

        return result;
    }

    private static int [] concatenateNArrays(int [] ... a){
        if (a.length < 2) {
            return a[1];
        } else {
            int [] result = concatenateTwoArrays(a[0], a[1]);
            for (int i = 2; i < a.length; i++){
                result = concatenateTwoArrays(result, a [i]);
            }
            return result;
        }
    }

    private static int [] concatenateNArraysRec(int [] ... a){
        return concatenateNArraysRec(-1, new int [0], a);
    }

    private static int [] concatenateNArraysRec(int number, int result [], int [] ... a){
        if (a.length < 2 || ++number >= a.length) return result;
        return concatenateNArraysRec(number, concatenateTwoArrays(result, a[number]), a);
    }

    static long start;
    public static void start(){
        start = System.currentTimeMillis();
    }

    public static void stop(){
        sout(System.currentTimeMillis() - start);
    }

    public static void sout(Object s){
        System.out.println(String.valueOf(s));
    }

    public static int [][] getSortedArrays(int arraysQuantity, int arraysLength){
        int arrays [][] = new int[arraysQuantity][];
        for (int j = 0; j < arraysQuantity; j++) {
            arrays[j] = getSortedArray(arraysLength);
        }
        return arrays;
    }


    public static int [] getSortedArray(int length){
        int result [] = new int [length];
        for (int i = 0; i < length; i++){
            result[i] = i;
        }
        return result;
    }

    private void testSpeed(String s, Object o)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException{
        start();
        this.getClass().getDeclaredMethod(s).invoke(o);
        stop();
    }

}
