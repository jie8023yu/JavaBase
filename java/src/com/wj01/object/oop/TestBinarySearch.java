package com.wj01.object.oop;

import java.util.Arrays;

/**
 * 二分法查找
 */
public class TestBinarySearch {

    public static void main(String[] args) {
        int[] a = {20,12,30,67,89,16,30};
        Arrays.sort(a);
        System.out.println(Arrays.toString(a));
        binarySearch(a,30);

    }

    public static void binarySearch(int[] arr,int search) {

        //二分法查找
        int low = 0;
        int high = arr.length - 1;
        while(low <= high) {
            int mid = (low + high) / 2;

            if(search == arr[mid]){
                System.out.println("找到了：" + mid);
                break;
            }

            if(search > arr[mid]) {
                low = mid + 1;
            }

            if(search < arr[mid]) {
                high = mid - 1;
            }
        }
        System.out.println("没找到");
    }
}
