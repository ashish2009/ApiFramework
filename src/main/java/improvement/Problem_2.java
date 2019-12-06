package improvement;

import java.util.Arrays;
import java.util.HashSet;

public class Problem_2 {

    public static int[] removeDuplicates(int[] array){
        int n = array.length;
        for(int i=0;i<n;i++){
            for(int j = i+1;j<n;j++){
                if(array[i] == array[j]){
                    array[j] = array[n-1];
                    n--;
                    j--;
                }
            }
        }
        return Arrays.copyOf(array,n);

    }

    public static Object[] removeDuplicate_1(int[] array){
        HashSet<Integer> set = new HashSet<>();
        for(int i: array){
            set.add(i);
        }
        return  set.toArray();
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(removeDuplicate_1(new int[] {4, 3, 2, 4, 9, 2})));

        System.out.println(Arrays.toString(removeDuplicate_1(new int[] {1, 2, 1, 2, 1, 2})));

        System.out.println(Arrays.toString(removeDuplicate_1(new int[] {15, 21, 11, 21, 51, 21, 11})));

        System.out.println(Arrays.toString(removeDuplicate_1(new int[] {7, 3, 21, 7, 34, 18, 3, 21})));

    }
}
