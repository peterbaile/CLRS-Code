package Chapter4;

import java.util.Arrays;
import java.util.Random;

// 4.1 Maximum Subarray Problem
public class MaximumSubArray {
    public static void print(Object o) {
        System.out.println(o);
    }

    // Algorithm 1: O(nlgn)
    public static int[] maxCrossingSubArray(int[] A, int low, int mid, int high) {
        int leftSum = Integer.MIN_VALUE;
        int sum = 0;
        int maxLeft = mid;
        for (int i = mid; i >= low; i--) {
            sum += A[i];
            if (sum > leftSum) {
                leftSum = sum;
                maxLeft = i;
            }
        }

        int rightSum = Integer.MIN_VALUE;
        sum = 0;
        int maxRight = mid;
        for (int i = mid + 1; i <= high; i++) {
            sum += A[i];
            if (sum > rightSum) {
                rightSum = sum;
                maxRight = i;
            }
        }

        return new int[]{maxLeft, maxRight, leftSum + rightSum};
    }

    public static int[] findMaxSubArray(int[] A, int low, int high) {
        if (high == low) {
            return new int[]{low, high, A[low]};
        } else {
            int mid = (low + high) / 2;
            int[] left = findMaxSubArray(A, low, mid);
            int[] right = findMaxSubArray(A, mid + 1, high);
            int[] cross = maxCrossingSubArray(A, low, mid, high);
            if (left[2] >= right[2] && left[2] >= cross[2]) {
                return left;
            } else if (right[2] >= left[2] && right[2] >= cross[2]) {
                return right;
            } else {
                return cross;
            }
        }
    }

    // Algorithm 2: O(nlgn)
    public static int[] maxCrossingSubArrayPeter(int[] A, int leftLow, int rightHigh) {
        int leftSum = Integer.MIN_VALUE;
        int sum = 0;
        int maxLeft = leftLow;
        for (int i = leftLow; i <= rightHigh; i++) {
            sum += A[i];
            if (sum > leftSum) {
                maxLeft = i;
                leftSum = sum;
            }
        }

        int rightSum = Integer.MIN_VALUE;
        sum = 0;
        int maxRight = rightHigh;
        for (int i = rightHigh; i >= leftLow; i--) {
            sum += A[i];
            if (sum > rightSum) {
                maxRight = i;
                rightSum = sum;
            }
        }

        if (leftSum > rightSum) {
            return new int[]{leftLow, maxLeft, leftSum};
        } else {
            return new int[]{maxRight, rightHigh, rightSum};
        }
    }

    public static int[] findMaxSubArrayPeter(int[] A, int low, int high) {
        if (high == low) {
            return new int[]{low, high, A[low]};
        } else {
            int mid = (low + high) / 2;
            int[] left = findMaxSubArrayPeter(A, low, mid);
            int[] right = findMaxSubArrayPeter(A, mid + 1, high);
            int[] cross = maxCrossingSubArrayPeter(A, left[0], right[1]);
            if (left[2] >= right[2] && left[2] >= cross[2]) {
                return left;
            } else if (right[2] >= left[2] && right[2] >= cross[2]) {
                return right;
            } else {
                return cross;
            }
        }
    }

    // Algorithm 3: O(n^2)
    public static int[] findMaxSubArrayBruteForce(int[] A) {
        int sum, leftIndex = 0, rightIndex = 0, leftSum = Integer.MIN_VALUE;
        for (int i = 0; i < A.length; i++) {
            sum = 0;
            sum += A[i];
            for (int j = i + 1; j < A.length; j++) {
                sum += A[j];
                if (sum > leftSum) {
                    leftSum = sum;
                    rightIndex = j;
                }
            }
        }

        return new int[]{leftIndex, rightIndex, leftSum};
    }

    // Algorithm 4: Linear (Kadane's algorithm)
    public static int kadane(int[] A) {
        int maxSum = A[0], acc = A[0];
        for (int i = 1; i < A.length; i++) {
            acc = Math.max(A[i], acc + A[i]);
            maxSum = Math.max(maxSum, acc);
        }

        return maxSum;
    }

    public static void main(String[] args) {
        int[] array = new int[100];
        for (int i = 0; i < array.length; i++) {
            if (new Random().nextBoolean()) {
                array[i] = (int) (Math.random() * 50);
            } else {
                array[i] = -(int) (Math.random() * 50);
            }
        }

        // print(Arrays.toString(array));
        print(kadane(array));
        print(findMaxSubArray(array, 0, array.length - 1)[2]);
        print(findMaxSubArrayPeter(array, 0, array.length - 1)[2]);
        print(findMaxSubArrayBruteForce(array)[2]);
    }
}
