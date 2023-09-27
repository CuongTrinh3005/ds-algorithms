// Online Java Compiler
// Use this editor to write, compile and run your Java code online

class ArrayUtilities {
    public static int[] insetAt(int[] a, int pos, int num) {
        int[] newArr = new int[a.length + 1];


        for (int index = 0; index < a.length; index++) {
            newArr[index] = a[index];
        }

        for (int index = newArr.length - 1; index >= pos; index--) {
            newArr[index] = newArr[index - 1];
        }

        newArr[pos - 1] = num;

        return newArr;
    }

    public static int[] deleteAt(int[] arr, int pos){
        int newSize = arr.length - 1;
        for(int index = pos; index < arr.length; index++)
            arr[index-1] = arr[index];

        int[] newArr = new int[newSize];
        for(int index=0; index < newSize; index++)
            newArr[index] = arr[index];

        return newArr;
    }

    public static void printItems(int[] arr) {
        for (int i : arr) System.out.print(i);

        System.out.println();
    }
}

public class Main {
    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 2, 8};
        int positionToInsert = 3, valueToInsert = 7, positionToDelete = 4;

        System.out.println("Before inserting:");
        ArrayUtilities.printItems(arr);

        int[] new_arr = ArrayUtilities.insetAt(arr, positionToInsert, valueToInsert);
        System.out.println("\nAfter inserting:");
        ArrayUtilities.printItems(new_arr);

        int[] deletedArr = ArrayUtilities.deleteAt(new_arr, positionToDelete);
        System.out.println("\nAfter deleting:");
        ArrayUtilities.printItems(deletedArr);
    }
}