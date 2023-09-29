public class Sorting {
    static int[] getArr(){
        return new int[]{35, 10, 31, 11, 26, 5, 40, 2};
    }

    static String display(int[] arr){
        StringBuilder result = new StringBuilder();
        for (int j : arr) result.append(j).append(" ");

        return result.toString();
    }

    static int[] bubbleSort(int[] arr){
        for(int i=0; i< arr.length; i++){
            for(int j=i+1; j< arr.length; j++){
                if(arr[i] > arr[j]){
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

    static int[] bubbleSortOpt(int[] arr){
        boolean swapped = true;
        // swapped is false means elements already sorted
        while (swapped){
            swapped = false;
            for(int i=1; i< arr.length; i++){
                if(arr[i] < arr[i-1]){
                    int temp = arr[i];
                    arr[i] = arr[i-1];
                    arr[i-1] = temp;
                    swapped = true;
                }
            }
        }
        return arr;
    }

    static int[] selectionSort(int[] arr){
        int minVal, minPos;
        for(int i=0; i<arr.length-1; i++){
            minVal = arr[i];
            minPos = i;
            for(int j = i+1; j < arr.length; j++){
                if(arr[j] < minVal){
                    minVal = arr[j];
                    minPos = j;
                }
            }
            // swap unsorted element with sorted part
            int temp = arr[minPos];
            arr[minPos] = arr[i];
            arr[i] = temp;
        }
        return arr;
    }

    static int[] insertionSort(int[] arr){
        for(int i=1; i<arr.length; i++){
            int temp = arr[i], j;
            for(j=i-1; j >= 0 && temp < arr[j]; j--){
                arr[j+1] = arr[j];
            }
            arr[j+1] = temp;
        }
        return arr;
    }

    static int[] quickSortRecursive(int[] arr, int start, int end){
        int leftIndex = start, rightIndex = end;
        int pivotItem = arr[(start + end) / 2];

        do{
            while (arr[leftIndex] < pivotItem) leftIndex++;
            while (arr[rightIndex] > pivotItem) rightIndex--;
            if(leftIndex <= rightIndex){
                // swap items at leftIndex and rightIndex position
                int temp = arr[rightIndex];
                arr[rightIndex] = arr[leftIndex];
                arr[leftIndex] = temp;
                leftIndex++; rightIndex--;
            }
        }while (leftIndex <= rightIndex);

        if(rightIndex > start){
            // Continue process on new sub array [start, rightIndex]
            quickSortRecursive(arr, start, rightIndex);
        }
        if(leftIndex < end){
            // Continue process on new sub array [leftIndex, end]
            quickSortRecursive(arr, leftIndex, end);
        }
        return arr;
    }

    static void heapify(int[] arr, int size, int rootIndex){
        int largestIndex = rootIndex;
        int leftNodeIndex = 2 * rootIndex + 1;
        int rightNodeIndex = leftNodeIndex + 1;

        if(leftNodeIndex < size && arr[leftNodeIndex] > arr[largestIndex]){
            largestIndex = leftNodeIndex;
        }
        if(rightNodeIndex < size && arr[rightNodeIndex] > arr[largestIndex]){
            largestIndex = rightNodeIndex;
        }
        if(arr[rootIndex] < arr[largestIndex]){
            // swap largest value to become root node
            int temp = arr[rootIndex];
            arr[rootIndex] = arr[largestIndex];
            arr[largestIndex] = temp;

            heapify(arr, size, largestIndex);
        }
    }

    static int[] heapSort(int[] arr, int size){
        for(int i = size/2 - 1; i >= 0; i--){
            // Because only nodes from 0 to n/2-1 index have children
            // so we initialize heap on these nodes.
            heapify(arr, size, i);
        }

        for(int i = size - 1; i >= 0; i--){
            // swap to remove root node with last node
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0);
        }

        return arr;
    }

    static int[] mergeSort(int[] arr, int start, int end){
        if(start < end){
            int mid = (start + end) / 2;
            mergeSort(arr, 0, mid);
            mergeSort(arr, mid + 1, end);
            merge(arr, start, mid, end);
        }
        return arr;
    }

    private static void merge(int[] arr, int start, int mid, int end) {
        int leftArrSize = mid - start + 1;
        int rightArrSize = end - mid;
        int[] leftArr = new int[leftArrSize];
        int[] rightArr = new int[rightArrSize];

        // Copy data into 2 sub arrays
        for(int i=0; i<leftArrSize; i++)
            leftArr[i] = arr[start + i];
        for(int i=0; i<rightArrSize; i++)
            rightArr[i] = arr[mid + 1 + i];

        // Init indexes to control left, right and merged arrays
        int leftArrIndex = 0, rightArrIndex = 0, mergeArrIndex = start;
        while (leftArrIndex < leftArrSize && rightArrIndex < rightArrSize){
            if(leftArr[leftArrIndex] < rightArr[rightArrIndex]){
                arr[mergeArrIndex] = leftArr[leftArrIndex];
                leftArrIndex++;
            }
            else{
                arr[mergeArrIndex] = rightArr[rightArrIndex];
                rightArrIndex++;
            }
            mergeArrIndex++;
        }

        // Copy remaining items of left arr if any
        while (leftArrIndex < leftArrSize){
            arr[mergeArrIndex] = leftArr[leftArrIndex];
            leftArrIndex++;
            mergeArrIndex++;
        }
        // Copy remaining items of right arr if any
        while (rightArrIndex < rightArrSize){
            arr[mergeArrIndex] = rightArr[rightArrIndex];
            rightArrIndex++;
            mergeArrIndex++;
        }
    }

    public static int binarySearch(int[] arr, int n, int value){
        int left = 0, right = n - 1, mid;
        while(left <= right){
            mid = (left + right) / 2;
            if(value == arr[mid])   return mid;
            else if (value < arr[mid]) {
                // new scope will be left -> mid-1
                right = mid - 1;
            }
            else left = mid + 1;
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] bubbleSortedArr = bubbleSort(getArr());
        System.out.println("Bubble sorting result: " + display(bubbleSortedArr));

        int[] bubbleSortedOptArr = bubbleSortOpt(getArr());
        System.out.println("Bubble sorting optimize result: " + display(bubbleSortedOptArr));

        int[] selectionSortedResult = selectionSort(getArr());
        System.out.println("Selection sort result: " + display(selectionSortedResult));

        int[] insertionSortedResult = insertionSort(getArr());
        System.out.println("Insertion sort result: " + display(insertionSortedResult));

        int[] arr = getArr();
        int[] quickSortedRecursionResult = quickSortRecursive(arr, 0, arr.length - 1);
        System.out.println("Quick sort recursion result: " + display(quickSortedRecursionResult));

        arr = getArr();
        int[] heapSortedResult = heapSort(arr, arr.length);
        System.out.println("Heap sort recursion result: " + display(heapSortedResult));

        arr = getArr();
        int[] mergeSortedResult = mergeSort(arr, 0, arr.length - 1);
        System.out.println("Merge sort recursion result: " + display(mergeSortedResult));

        int key = 40;
        System.out.println(String.format("Position of %s is: ", key) + binarySearch(arr, arr.length, key));
    }
}
