public class SortingAlgorithms {
    public static void swap(Integer[] A, int i, int j) {
        Integer temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    /** Bubble sort implementation code from lecture notes
     * of Professor Eustrat Zhupa.
     * This code has been slightly modified.
     */
    public static void bubbleSort(Integer[] A) {
        for (int i=0; i < A.length-1; i++)
            for (int j = A.length -1; j > i; j--)
                if ((A[j].compareTo(A[j-1]) < 0))
                    swap(A, j, j -1);
    }

    /** Insertion sort implementation code from lecture notes
     * of Professor Eustrat Zhupa.
     * This code has been slightly modified.
     */
    public static void insertionSort(Integer[] A) {
        for (int i=1; i<A.length; i++) // Insert i’th item
            for (int j=i; (j>0) && (A[j].compareTo(A[j-1])<0); j--)
                swap(A, j, j-1);
    }

    /** Selection sort implementation code from lecture notes
     * of Professor Eustrat Zhupa.
     * This code has been slightly modified.
     */
    public static void selectionSort(Integer[] A) {
        for (int i=0; i<A.length-1; i++) { // Get i-th item
            int lowindex = i; // Remember it
            for (int j=A.length-1; j>i; j--) // Find min
                if (A[j].compareTo(A[lowindex]) < 0)
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
    public static void mergeSort(Integer[] A) {
        Integer[] temp = new Integer[A.length];
        mergesort(A, temp, 0, A.length-1);
    }
    public static void mergesort(Integer[] A, Integer[] temp, int l, int r) {
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
            else if (temp[i1].compareTo(temp[i2])<0) // Get smaller
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
    public static void quickSort(Integer[] A) {
        qsort(A,0,A.length-1);
    }
    public static void qsort(Integer[] A, int i, int j) {      // Quicksort
        int pivotindex = findpivot(A, i, j); // Pick a pivot
        swap(A, pivotindex, j);       // Stick pivot at end
        // k will be the first position in the right subarray
        int k = partition(A, i-1, j, A[j]);
        swap(A, k, j);              // Put pivot in place
        if ((k-i) > 1) qsort(A, i, k-1);   // Sort left partition
        if ((j-k) > 1) qsort(A, k+1, j);   // Sort right partition
    }
    public static int partition(Integer[] A, int l, int r, Integer pivot) {
        do {                 // Move bounds inward until they meet
            while (A[++l].compareTo(pivot)<0);
            while ((r!=0) && (A[--r].compareTo(pivot)>0));
            swap(A, l, r);       // Swap out-of-place values
        } while (l < r);              // Stop when they cross
        swap(A, l, r);         // Reverse last, wasted swap
        return l;      // Return first position in right partition
    }
    public static int findpivot(Integer[] A, int i, int j) { return (i+j)/2; }

    // Main method for testing
    public static void main(String[] args) {
        Integer[] arr = {3,2,5,1,4};
        quickSort(arr);
        for (Integer i : arr) {
            System.out.println(i);
        }
    }
}
