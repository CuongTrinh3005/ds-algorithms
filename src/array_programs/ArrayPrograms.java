package array_programs;

import java.util.*;

public class ArrayPrograms {
    public static int[] getArr() {
        return new int[]{4, 2, 7, 9, 4, 3, 7, 2, 8};
    }

    public static void printOut(int[] arr){
        String result = "";
        for (int i : arr) result += i + " ";
        System.out.println(result);
    }

    public static void countFrequent(int[] arr){
        int [] fr = new int [arr.length];
        int visited = -1;
        for(int i = 0; i < arr.length; i++){
            int count = 1;
            for(int j = i+1; j < arr.length; j++){
                if(arr[i] == arr[j]){
                    count++;
                    //To avoid counting same element again
                    fr[j] = visited;
                }
            }
            if(fr[i] != visited)
                fr[i] = count;
        }

        //Displays the frequency of each element present in array
        System.out.println("---------------------------------------");
        System.out.println(" Element | Frequency");
        System.out.println("---------------------------------------");
        for(int i = 0; i < fr.length; i++){
            if(fr[i] != visited)
                System.out.println("    " + arr[i] + "    |    " + fr[i]);
        }
        System.out.println("----------------------------------------");
    }

    public static void countOccurenceWithHashmap(int[] arr){
        Map<Integer, Integer> frequencymap = new HashMap<>();
        for (int j : arr) {
            if (frequencymap.containsKey(j)) {
                frequencymap.put(j, frequencymap.get(j) + 1);
            } else frequencymap.put(j, 1);
        }
        for (Map.Entry<Integer, Integer> integerIntegerEntry : frequencymap.entrySet()) {
            System.out.println(((Map.Entry<?, ?>) integerIntegerEntry).getKey() + " appears " 
                    + ((Map.Entry<?, ?>) integerIntegerEntry).getValue() + " time(s)");
        }
    }

    public static int[] leftRotate(int[] arr, int noTime){
        int size = arr.length;
        for(int time = 1; time <= noTime; time++){
            int temp = arr[0];
            for(int i=1; i<size; i++)
                arr[i-1] = arr[i];

            arr[size-1] = temp;
        }

        return arr;
    }

    public static int[] reverseArr(int[] arr){
        for(int index = 0; index < arr.length/2; index++){
            int temp = arr[index];
            arr[index] = arr[arr.length - 1 - index];
            arr[arr.length - 1 - index] = temp;
        }
        return arr;
    }

    public static int[] removeDuplicates(int[] arr){
        Arrays.sort(arr);
        int[] uniqueElements = new int[arr.length];
        int j=0;
        for(int i = 0; i < arr.length - 1; i++){
            if(arr[i] != arr[i+1]){
                uniqueElements[j++] = arr[i];
            }
        }
        // for loop not iterate last element so we have this line
        uniqueElements[j] = arr[arr.length-1];
        return uniqueElements;
    }

    public static void main(String[] args) {
        countFrequent(getArr());
        countOccurenceWithHashmap(getArr());

        int times = 3;
        System.out.println("Rotate left after " + times + " times:");
        printOut(leftRotate(getArr(), getArr().length / 2));

        System.out.println("Before reversing: ");
        int[] testReverseArr = getArr();
        printOut(testReverseArr);
        int[] reversedArr = reverseArr(testReverseArr);
        System.out.println("After reversing: ");
        printOut(reversedArr);

        System.out.println("Unique elements: ");
        printOut(removeDuplicates(getArr()));
    }
}
