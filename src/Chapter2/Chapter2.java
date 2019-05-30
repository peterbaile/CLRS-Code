package Chapter2;

import java.util.Arrays;


public class Chapter2 {
    //Exercise 2.1-4
    public static int[] binaryAddition(int[] arr1, int[] arr2) {
        int length = arr1.length;
        int[] arr3 = new int[length + 1];
        int addition = 0;

        for (int i = length - 1; i >= 0; i--) {
            int c = arr1[i] + arr2[i] + addition;

            arr3[i + 1] = c % 2;
            addition = c / 2;

            if (i == 0) {
                arr3[i] = addition;
            }
        }

        return arr3;
    }

    //Exercise 2.2-2
    public static void selectionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int index = i;
            int smallest = array[i];

            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < smallest) {
                    smallest = array[j];
                    index = j;
                }
            }

            array[index] = array[i];
            array[i] = smallest;
        }
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{3, 2, 1};
        selectionSort(arr1);
        System.out.println(Arrays.toString(arr1));
    }
}
