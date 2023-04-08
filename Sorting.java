/******************************************************************************
 *  Compilation:  javac Sorting.java
 *  Execution:    java Sorting input.txt AlgorithmUsed
 *  Dependencies: StdOut.java In.java Stopwatch.java
 *  Data files:   http://algs4.cs.princeton.edu/14analysis/1Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/2Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/4Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/8Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/16Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/32Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/1Mints.txt
 *
 *  A program to play with various sorting algorithms. 
 *
 *
 *  Example run:
 *  % java Sorting 2Kints.txt  2
 *
 ******************************************************************************/
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Stack;

public class Sorting {
    final static String NETID = "rbrenna2,nayalew";


 /**
     * 
     * Sorts the numbers present in the file based on the algorithm provided.
     * 0 = Arrays.sort() (Java Default)
     * 1 = Bubble Sort
     * 2 = Selection Sort 
     * 3 = Insertion Sort 
     * 4 = Mergesort
     * 5 = Quicksort
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args)  { 
        In in = new In(args[0]);
        
		  // Storing file input in an array
        int[] a = in.readAllInts();

        // TODO: Generate 3 other arrays, b, c, d where
        // b contains sorted integers from a (You can use Java Arrays.sort() method)
        // c contains all integers stored in reverse order 
        // (you can have your own O(n) solution to get c from b
        // d contains almost sorted array 
        //(You can copy b to a and then perform (0.1 * d.length)  many swaps to acheive this.
        int[] b = a.clone();
        Arrays.sort(b);
        int[] c = new int[b.length];
        Stack<Integer> stack = new Stack<>();
        for (int i : b) {
            stack.add(i);
        }
        for (int i = 0; i < c.length; i++) {
            c[i] = stack.pop();
        }
        int[] d = b.clone();
        int numberOfSwaps = (int)((float)(d.length)*0.1);
        for (int k = 0; k < numberOfSwaps; k++) {
            int i = StdRandom.uniform(0, d.length);
            int j = StdRandom.uniform(0, d.length);
            swap(d, i, j);
        }

        //TODO: 
        // Read the second argument and based on input select the sorting algorithm
        //  * 0 = Arrays.sort() (Java Default)
        //  * 1 = Bubble Sort
        //  * 2 = Selection Sort 
        //  * 3 = Insertion Sort 
        //  * 4 = Mergesort
        //  * 5 = Quicksort
        //  Perform sorting on a,b,c,d. Pring runtime for each case along with timestamp and record those. 
        // For runtime and priting, you can use the same code from Lab 4 as follows:

        /*
         // TODO: For each array, a, b, c, d:  
        Stopwatch timer = new Stopwatch();
        // TODO: Perform Sorting and store the result in an  array

        double time = timer.elapsedTimeMillis();
        
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
          //TODO: Replace with your own netid
        String netID = "tbiswas2";
          //TODO: Replace with the algorithm used 
        String algorithmUsed = "insertion sort";
          //TODO: Replace with the  array used 
        String arrayUsed = "a";
          StdOut.printf("%s %s %8.1f   %s  %s  %s\n", algorithmUsed, arrayUsed, time, timeStamp, netID, args[0]);
         */

        testSort(a, "a", NETID, args[0], args[1]);
        testSort(b, "b", NETID, args[0], args[1]);
        testSort(c, "c", NETID, args[0], args[1]);
        testSort(d, "d", NETID, args[0], args[1]);

          // Write the resultant array to a file (Each time you run a program 4 output files should be generated. (one for each a,b,c, and d)
        writeTextFile(a, "a");
        writeTextFile(b, "b");
        writeTextFile(c, "c");
        writeTextFile(d, "d");
    }

    public static void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    /** Bubble sort implementation code from lecture notes
     * of Professor Eustrat Zhupa.
     * This code has been slightly modified.
     */
    public static void bubbleSort(int[] A) {
        for (int i=0; i < A.length-1; i++)
            for (int j = A.length -1; j > i; j--)
                if (A[j] < A[j-1])
                    swap(A, j, j -1);
    }

    /** Insertion sort implementation code from lecture notes
     * of Professor Eustrat Zhupa.
     * This code has been slightly modified.
     */
    public static void insertionSort(int[] A) {
        for (int i=1; i<A.length; i++) // Insert i’th item
            for (int j=i; (j>0) && (A[j] < A[j-1]); j--)
                swap(A, j, j-1);
    }

    /** Selection sort implementation code from lecture notes
     * of Professor Eustrat Zhupa.
     * This code has been slightly modified.
     */
    public static void selectionSort(int[] A) {
        for (int i=0; i<A.length-1; i++) { // Get i-th item
            int lowindex = i; // Remember it
            for (int j=A.length-1; j>i; j--) // Find min
                if (A[j] < A[lowindex])
                    lowindex = j; // Put it in place
            swap(A, i, lowindex);
        }
    }

    /** Merge sort implementation code from lecture notes
     * of Professor Eustrat Zhupa, who in turn found this source
     * code from Clifford A. Shaffer (original citation included).
     * This code has been slightly modified.
     */
    /** Source code example for "A Practical Introduction to Data
     Structures and Algorithm Analysis, 3rd Edition (Java)"
     by Clifford A. Shaffer
     Copyright 2008-2011 by Clifford A. Shaffer
     */
    public static void mergeSort(int[] A) {
        int[] temp = new int[A.length];
        mergesort(A, temp, 0, A.length-1);
    }
    private static void mergesort(int[] A, int[] temp, int l, int r) {
        int mid = (l+r)/2;                // Select midpoint
        if (l == r) return;               // List has one element
        mergesort(A, temp, l, mid);   // Mergesort first half
        mergesort(A, temp, mid+1, r); // Mergesort second half
        for (int i=l; i<=r; i++)          // Copy subarray to temp
            temp[i] = A[i];
        // Do the merge operation back to A
        int i1 = l; int i2 = mid + 1;
        for (int curr=l; curr<=r; curr++) {
            if (i1 == mid+1)              // Left sublist exhausted
                A[curr] = temp[i2++];
            else if (i2 > r)              // Right sublist exhausted
                A[curr] = temp[i1++];
            else if (temp[i1] < temp[i2]) // Get smaller
                A[curr] = temp[i1++];
            else A[curr] = temp[i2++];
        }
    }

    /** Quick sort implementation code from lecture notes
     * of Professor Eustrat Zhupa, who in turn found this source
     * code from Clifford A. Shaffer (original citation included).
     * This code has been slightly modified.
     */
    /** Source code example for "A Practical Introduction to Data
     Structures and Algorithm Analysis, 3rd Edition (Java)"
     by Clifford A. Shaffer
     Copyright 2008-2011 by Clifford A. Shaffer
     */
    public static void quickSort(int[] A) {
        qsort(A,0,A.length-1);
    }
    private static void qsort(int[] A, int i, int j) {      // Quicksort
        int pivotindex = findpivot(A, i, j); // Pick a pivot
        swap(A, pivotindex, j);       // Stick pivot at end
        // k will be the first position in the right subarray
        int k = partition(A, i-1, j, A[j]);
        swap(A, k, j);              // Put pivot in place
        if ((k-i) > 1) qsort(A, i, k-1);   // Sort left partition
        if ((j-k) > 1) qsort(A, k+1, j);   // Sort right partition
    }
    private static int partition(int[] A, int l, int r, int pivot) {
        do {                 // Move bounds inward until they meet
            while (A[++l] < pivot);
            while ((r!=0) && (A[--r] > pivot));
            swap(A, l, r);       // Swap out-of-place values
        } while (l < r);              // Stop when they cross
        swap(A, l, r);         // Reverse last, wasted swap
        return l;      // Return first position in right partition
    }
    private static int findpivot(int[] A, int i, int j) { return (i+j)/2; }

    public static void defaultSort(int[] A) {
        Arrays.sort(A);
    }

    public static void sortArray(int[] A, String algorithm) {
        switch (algorithm) {
            case "0" -> defaultSort(A);
            case "1" -> bubbleSort(A);
            case "2" -> selectionSort(A);
            case "3" -> insertionSort(A);
            case "4" -> mergeSort(A);
            case "5" -> quickSort(A);
        }
    }

    public static String getAlgorithmName(String algorithm) {
        switch (algorithm) {
            case "1":
                return "Bubble Sort";
            case "2":
                return "Selection Sort";
            case "3":
                return "Insertion Sort";
            case "4":
                return "Merge Sort";
            case "5":
                return "Quick Sort";
            default:
                return "Default Sort";
        }
    }

    public static void testSort(int[] A, String arrayUsed, String netID, String filename, String algorithm) {
        Stopwatch timer = new Stopwatch();
        sortArray(A, algorithm);
        double time = timer.elapsedTimeMillis();
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        String algorithmUsed = getAlgorithmName(algorithm);
        StdOut.printf("%s %s %8.1f   %s  %s  %s\n", algorithmUsed, arrayUsed, time, timeStamp, netID, filename);
    }

    public static void writeTextFile(int[] A, String arrayUsed) {
        FileWriter fw;
        BufferedWriter bw;
        try {
            fw = new FileWriter(arrayUsed + ".txt");
            bw = new BufferedWriter(fw);
            for (int i : A) {
                fw.append(Integer.toString(i)).append("\n");
            }
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
} 


