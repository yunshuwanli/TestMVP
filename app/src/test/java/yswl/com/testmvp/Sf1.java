package yswl.com.testmvp;

/**
 * Created by kangpAdministrator on 2017/5/10 0010.
 * Emial kangpeng@yunhetong.net
 */

/**
 * 二分法
 */
public class Sf1 {

    public static void main(String[] args) {
        int[] arr = {12, 23, 34, 37, 53, 79, 100, 154, 299};
        int data = 12;
        int index = funBinSerach(arr, data);
        System.out.println("index :"+index);
    }

    /**
     * 二分法时间复杂度
     * 数学推导
     * n/2 n/2^2 ...n/2^K   K就是循环的次数
     * n/2^k >=1  ==>令 n/2^k = 1 <==> 2^K = n;
     *
     */
    private static int funBinSerach(int[] arr, int data) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (data == arr[mid]) {
                return mid;
            } else if (data < arr[mid]) {
                high = mid - 1;
            } else if (data > arr[mid]) {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 排序
     *
     */

}
