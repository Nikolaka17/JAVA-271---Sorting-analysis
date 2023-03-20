import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * Class that contains many different types of sorting algorithms
 */
public abstract class Sorts {

    /**
     * Method to test if an array is sorted
     * @param a The integer array to test
     * @return A boolean representing if the array is sorted
     */
    public static boolean isSorted(int[] a){
        for(int i = 0; i < a.length - 1; i++){
            if(a[i] > a[i+1]){
                return false;
            }
        }
        return true;
    }

    /**
     * Method to test if an list is sorted
     * @param a The integer based list to be tested
     * @return A boolean representing if the list is sorted
     */
    public static boolean isSorted(List<Integer> a){
        for(int i = 0; i < a.size() - 1; i++){
            if(a.get(i) > a.get(i)){
                return false;
            }
        }
        return true;
    }

    /**
     * Method to swap two elements in an array
     * @param a The integer array to swap elements in
     * @param i The index of the first element to swap
     * @param j The index of the second element to swap
     */
    public static void swap(int[] a, int i, int j){
        if(a[i] != a[j]){
            a[i] ^= a[j];
            a[j] ^= a[i];
            a[i] ^= a[j];
        }
    }
    
    /**
     * Bubble sort algorithm. Works by pushing the largest elements towards the back of the array
     * Worst: O(n^2)
     * Average: O(n^2)
     * Best: O(n)
     * @param a The integer array to sort
     */
    public static void bubbleSort(int[] a){
        boolean swapped = true;
        int pass = 1;
        while(swapped){
            swapped = false;
            for(int i = 0; i < a.length - pass; i++){
                if(a[i] > a[i+1]){
                    swap(a, i, i+1);
                    swapped = true;
                }
            }
            pass++;
        }
    }
    
    /**
     * Selection sort algorithm. Works by finding the smallest elements and placing them in the front of the array
     * Worst: O(n^2)
     * Average: O(n^2)
     * Best: O(n^2)
     * @param a The integer array to sort
     */
    public static void selectionSort(int[] a){
        int min = 0;
        for(int i = 0; i < a.length - 1; i++){
            min = i;
            for(int j = i + 1; j < a.length; j++){
                if(a[j] < a[min]){
                    min = j;
                }
            }
            if(i != min){
                swap(a, i, min);
            }
        }
    }

    /**
     * A two sided selection sort. Works by finding the highest and lowest value in the array and placing them in the front and back of the array respectively
     * Worst: O(n^2)
     * Average: O(n^2)
     * Best: ?
     * @param a The integer array to sort
     */
    public static void doubleSelectionSort(int[] a){
        for(int i = 0, j = a.length - 1; i < j; i++, j--){
            int min = i;
            int max = i;
            for(int k = i; k <= j; k++){
                min = (a[k] < a[min])?k:min;
                max = (a[k] > a[max])?k:max;
            }
            int maxStore = a[max];
            swap(a, i, min);
            if(a[min] == maxStore){
                swap(a, j, min);
            }else{
                swap(a, j, max);
            }
        }
    }
    
    /**
     * An insertion sort algorithm. Works by looking at each value one by one and moves them into the correct position
     * Worst: O(n^2)
     * Average: O(n^2)
     * Best: O(n)
     * @param a The integer array to sort
     */
    public static void insertionSort(int[] a){
        int cur = 0;
        int index = 0;
        for(int i = 1; i < a.length; i++){
            index = i - 1;
            cur = a[i];
            while(index >= 0 && a[index] > cur){
                a[index+1] = a[index];
                index--;
            }
            a[index+1] = cur;
        }
    }

    /**
     * A binary insertion sort algorithm. Works by using a binary search to find the location for an element to be inserted
     * Worst: O(n^2)
     * Average: ?
     * Best: ?
     * @param a The integer array to be sorted
     */
    public static void binaryInsertionSort(int[] a){
        for(int i = 1; i < a.length; i++){
            int x = a[i];
            int j = Math.abs(Arrays.binarySearch(a, 0, i, x) + 1);
            System.arraycopy(a, j, a, j+1, i-j);
            a[j] = x;
        }
    }
    
    /**
     * A quick sort algorithm. Works by sorting an array into two sides based off a pivot value and recursively calling each side
     * Worst: O(n^2)
     * Average: O(nlogn)
     * Best: O(nlogn)
     * @param a The integer array to be sorted
     */
    public static void quickSort(int[] a){
        quickSort(a, 0, a.length - 1);
    }

    /**
     * A dual pivot quick sort algorithm. Follows the same principal as the regular quick sort but uses two pivot values and splits lists into thirds instead
     * Worst: O(n^2)
     * Average: ?
     * Best: ?
     * @param a The integer array to be sorted
     */
    public static void dualPivotQuickSort(int[] a){
        dualPivotQuickSort(a, 0, a.length - 1);
    }
    
    /**
     * Recursive method for quick sort. Performs a quick sort on the range of [low, high]
     * @param a The integer array to be sorted
     * @param low The index for the left side of the range
     * @param high The index for the right side of the range
     */
    public static void quickSort(int[] a, int low, int high){
        int pivot = partition(a, low, high);
        if(low < pivot){
            quickSort(a, low, pivot - 1);
        }
        if(pivot < high){
            quickSort(a, pivot + 1, high);
        }
    }

    /**
     * Recursive method for dual pivot quick sort. Performs a dual pivot quick sort on the range of [low, high]
     * @param a The integer array to be sorted
     * @param low The index for the left side of the range
     * @param high The index for the right side of the range
     */
    public static void dualPivotQuickSort(int[] a, int low, int high){
        if(low < high){
            int[] pivot = dualPartition(a, low, high);
            dualPivotQuickSort(a, low, pivot[0] - 1);
            dualPivotQuickSort(a, pivot[0] + 1, pivot[1] - 1);
            dualPivotQuickSort(a, pivot[1] + 1, high);
        }
    }
    
    /**
     * Utility method for quick sort. Organizes an array based off of a pivot. Pivot is selected as the first element in the range
     * @param a The integer array to partition
     * @param low The index for the left side of the range
     * @param high The index for the right side of the range
     * @return The index of the location of the pivot value
     */
    private static int partition(int[] a, int low, int high){
        int pivot = a[low];
        while(low < high){
            while(a[high] > pivot && low < high){
                high--;
            }
            if(low != high){
                a[low] = a[high];
                low++;
            }
            while(a[low] < pivot && low < high){
                low++;
            }
            if(low != high){
                a[high] = a[low];
                high--;
            }
        }
        a[low] = pivot;
        return low;
    }

    /**
     * Utility method for dual pivot quick sort. Organizes an array based off of two pivot values. Pivots are selected to be the first and last values of the array
     * @param a The integer array to partition
     * @param low The index for the left side of the range
     * @param high The index for the right side of the range
     * @return An array containing the locations of both pivot values
     */
    private static int[] dualPartition(int[] a, int low, int high){
        if(a[low] > a[high]){
            swap(a, low, high);
        }
        int j = low + 1;
        int g = high - 1;
        int k = low + 1;
        int p = a[low];
        int q = a[high];
        while(k <= g){
            if(a[k] < p){
                swap(a, k, j);
                j++;
            }else if(a[k] >= q){
                while(a[g] > q && k < g){
                    g--;
                }
                swap(a, k, g);
                g--;
                if(a[k] < p){
                    swap(a, k, j);
                    j++;
                }
            }
            k++;
        }
        j--;
        g++;
        swap(a, low, j);
        swap(a, high, g);
        return new int[]{j, g};
    }

    /**
     * A heap sort algorithm. Works by using a binary heap to sift low values down
     * Worst: O(nlogn)
     * Average: O(nlogn)
     * Best: O(nlogn)
     * @param a The integer array to sort
     */
    public static void heapSort(int[] a){
        for(int i = a.length / 2 - 1; i >= 0; i--){
            heapify(a, a.length, i);
        }

        for(int i = a.length - 1; i > 0; i--){
            swap(a, 0, i);
            heapify(a, i, 0);
        }
    }

    /**
     * A utility method to create a binary heap of an array
     * @param a The array to make a heap out of
     * @param n The size of the heap
     * @param i The root node
     */
    private static void heapify(int[] a, int n, int i){
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if(l < n && a[l] > a[largest]){
            largest = l;
        }

        if(r < n && a[r] > a[largest]){
            largest = r;
        }

        if(largest != i){
            swap(a, i, largest);

            heapify(a, n, largest);
        }
    }

    /**
     * A bead sort algorithm. Works by following abdacus arithmetic. Only usable on integers greater than zero
     * Worst: O(S) Where s is the sum of all values in the array
     * Average: O(S) Where s is the sum of all values in the array
     * Best: O(n)
     * @param a The integer array to be sorted
     */
    public static void beadSort(int[] a){
        int max = a[0];
        for(int i = 0; i < a.length; i++){
            if(max < a[i]){
                max = a[i];
            }
        }

        int[][] beads = new int[a.length][max];

        for(int i = 0; i < a.length; i++){
            for(int j = 0; j < a[i]; j++){
                beads[i][j] = 1;
            }
        }

        for(int j = 0; j < max; j++){
            int sum = 0;
            for(int i = 0; i < a.length; i++){
                sum += beads[i][j];
                beads[i][j] = 0;
            }

            for(int i = a.length - 1; i >= a.length - sum; i--){
                a[i] = j + 1;
            }
        }
    }

    /**
     * A patience sort algorithm. Works by creating piles of decending elements and picking top level elements in order
     * Worst: O(nlogn)
     * Average: O(nlogn)
     * Best: O(n)
     * @param a The integer array to sort
     */
    public static void patienceSort(int[] a){
        ArrayList<ArrayList<Integer>> piles = new ArrayList<ArrayList<Integer>>();

        for(int i = 0; i < a.length; i++){
            if(piles.isEmpty()){
                ArrayList<Integer> temp = new ArrayList<Integer>();
                temp.add(i);
                piles.add(temp);
            }else{
                boolean flag = true;
                for(int j = 0; j < piles.size() && flag; j++){
                    if(a[i] < piles.get(j).get(piles.get(j).size() - 1)){
                        piles.get(j).add(a[i]);
                        flag = false;
                    }
                }

                if(flag){
                    ArrayList<Integer> temp = new ArrayList<Integer>();
                    temp.add(a[i]);
                    piles.add(temp);
                }
            }
        }

        ArrayList<Integer> sorted = mergePiles(piles);
        for(int i = 0; i < sorted.size(); i++){
            a[i] = sorted.get(i);
        }
    }

    /**
     * Utility method to select items in order from an arraylist of arraylists
     * @param piles The piles to merge into a single pile
     * @return An arraylist containing all the values of the piles in sorted order
     */
    private static ArrayList<Integer> mergePiles(ArrayList<ArrayList<Integer>> piles){
        ArrayList<Integer> result = new ArrayList<Integer>();
        boolean flag = true;

        while(flag){
            int min = Integer.MAX_VALUE;
            int index = -1;

            for(int i = 0; i < piles.size(); i++){
                if(!piles.get(i).isEmpty() && min > piles.get(i).get(piles.get(i).size() - 1)){
                    min = piles.get(i).get(piles.get(i).size() - 1);
                    index = i;
                }
            }

            flag = index != -1;
            if(flag){
                result.add(min);
                piles.get(index).remove(piles.get(index).size() - 1);
                if(piles.get(index).isEmpty()){
                    piles.remove(index);
                }
            }
        }
        
        return result;
    }

    /**
     * A cocktail shaker sort algorithm. Works by performing the bubble sort in both directions each iteration
     * Worst: O(n^2)
     * Average: O(n^2)
     * Best: O(n)
     * @param a The integer array to sort
     */
    public static void cocktailShakerSort(int[] a){
        boolean swapped = true;

        while(swapped){
            swapped = false;
            for(int i = 0; i < a.length - 1; i++){
                if(a[i] > a[i+1]){
                    swap(a, i, i+1);
                    swapped = true;
                }
            }
            if(swapped){
                swapped = false;
                for(int i = a.length - 2; i >= 0; i--){
                    if(a[i] > a[i+1]){
                        swap(a, i, i+1);
                        swapped = true; 
                    }
                }
            }
        }
    }

    /**
     * A gnome sort algorithm. Works by swapping small elements forward one at a time
     * Worst: O(n^2)
     * Average: O(n^2)
     * Best: O(n)
     * @param a The integer array to sort
     */
    public static void gnomeSort(int[] a){
        int pos = 0;
        while(pos < a.length){
            if(pos == 0 || a[pos] > a[pos - 1]){
                pos++;
            }else{
                swap(a, pos, pos-1);
                pos--;
            }
        }
    }

    /**
     * An odd even sort algorithm. Works by performing a bubble sort on odd and even values separately
     * Worst: O(n^2)
     * Average: O(n^2)
     * Best: O(n)
     * @param a The integer array to sort
     */
    public static void oddEvenSort(int[] a){
        boolean swapped = true;
        while(swapped){
            swapped = false;
            for(int i = 1; i < a.length - 1; i += 2){
                if(a[i] >= a[i+1]){
                    swap(a, i, i+1);
                    swapped = true;
                }
            }
            for(int i = 0; i < a.length - 1; i += 2){
                if(a[i] >= a[i+1]){
                    swap(a, i, i+1);
                    swapped = true;
                }
            }
        }
    }

    /**
     * A shell sort algorithm. Works by comparing far apart elements first and progressively lowering the size of the gap
     * Worst: O(n^3/2)
     * Average: O(n^4/3)
     * Best: O(nlogn)
     * @param a The integer array to sort
     */
    public static void shellSort(int[] a){
        shellSort(a, new int[]{701, 301, 132, 57, 23, 10, 4, 1});
    }

    /**
     * Shell sort with a user defined gap array
     * @param a The integer array to sort
     * @param gaps The gaps to follow
     */
    public static void shellSort(int[] a, int[] gaps){
        for(int gap: gaps){
            for(int i = gap; i < a.length; i++){
                int temp = a[i];
                int j;
                for(j = i; j >= gap && a[j-gap] > temp; j -= gap){
                    a[j] = a[j - gap];
                }
                a[j] = temp;
            }
        }
    }

    /**
     * A bogo sort algorithm. Works by shuffling the array until it is sorted
     * Worst: O(infinity)
     * Average: O(n * n!)
     * Best: O(n)
     * @param a The integer array to sort
     */
    public static void bogoSort(int[] a){
        while(!isSorted(a)){
            List<Integer> temp = Arrays.stream(a).boxed().collect(Collectors.toList());
            Collections.shuffle(temp);
            for(int i = 0; i < temp.size(); i++){
                a[i] = temp.get(i);
            }
        }
    }

    /**
     * A bucket sort algorithm. Works by creating buckets of different element ranges to sort separately
     * Worst: O(n^2 + k) Where k is the number of buckets
     * Average: O(n + k) Where k is the number of buckets
     * Best: ?
     * @param a The integer array to sort
     */
    public static void bucketSort(int[] a){
        bucketSort(a, a.length);
    }

    /**
     * A method that performs a bucket sort with a user defined number of buckets
     * @param a The integer array to sort
     * @param k The number of buckets to use
     */
    public static void bucketSort(int[] a, int k){
        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] buckets = new ArrayList[k];

        for(int i = 0; i < k; i++){
            buckets[i] = new ArrayList<Integer>();
        }

        int max = 0;
        for(int i: a){
            if(i > max){
                max = i;
            }
        }
        max++;

        for(int i = 0; i < a.length; i++){
            buckets[(int) Math.floor((double) k * a[i] / (double) max)].add(a[i]);
        }

        for(int i = 0; i < k; i++){
            Collections.sort(buckets[i]);
        }

        int index = 0;
        for(int i = 0; i < k; i++){
            for(int j = 0; j < buckets[i].size(); j++){
                a[index] = buckets[i].get(j);
                index++;
            }
        }
    }

    /**
     * An optimized bucket sort algorithm. Works by separating elements into buckets before placing them back in array and using an insertion sort
     * Worst: ?
     * Average: ?
     * Best: ?
     * @param a The integer array to sort
     */
    public static void optimizedBucketSort(int[] a){
        optimizedBucketSort(a, a.length);
    }

    /**
     * A method that performs an optimized bucket sort for a user defined amount of buckets
     * @param a The integer array to sort
     * @param k The number of buckets to use
     */
    public static void optimizedBucketSort(int[] a, int k){
        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] buckets = new ArrayList[k];

        for(int i = 0; i < k; i++){
            buckets[i] = new ArrayList<Integer>();
        }

        int max = 0;
        for(int i: a){
            if(i > max){
                max = i;
            }
        }
        max++;

        for(int i = 0; i < a.length; i++){
            buckets[(int) Math.floor((double) k * a[i] / (double) max)].add(a[i]);
        }

        int index = 0;
        for(int i = 0; i < k; i++){
            for(int j = 0; j < buckets[i].size(); j++){
                a[index] = buckets[i].get(j);
                index++;
            }
        }

        insertionSort(a);
    }

    /**
     * An exchange sort algorithm. Variation of bubble sort that test each position against every other element
     * Worst: ?
     * Average: O(n^2)
     * Best: ?
     * @param a The integer array to sort
     */
    public static void exchangeSort(int[] a){
        for(int i = 0; i < a.length - 1; i++){
            for(int j = i + 1; j < a.length; j++){
                if(a[i] > a[j]){
                    swap(a, i, j);
                }
            }
        }
    }

    /**
     * A comb sort algorithm. Variation of bubble sort where a gap is used to separate compared values
     * Worst: O(n^2)
     * Average: O(n^2)
     * Best: O(nlogn)
     * @param a The integer array to sort
     */
    public static void combSort(int[] a){
        int gap = a.length;
        double shrink = 1.3;
        boolean swapped = true;
        while(swapped){
            gap = (int) Math.floor(gap / shrink);
            if(gap <= 1){
                gap = 1;
                swapped = false;
            }
            for(int i = 0; i + gap < a.length; i++){
                if(a[i] > a[i+gap]){
                    swap(a, i, i+gap);
                    swapped = true;
                }
            }
        }
    }

    /**
     * A counting sort algorithm. Works by performing math on key values.
     * Worst: O(n + k)
     * Average: O(n + k)
     * Best: ?
     * @param a The integer array to sort
     */
    public static void countingSort(int[] a){
        int max = 0;
        for(int i: a){
            if(i > max){
                max = i;
            }
        }
        countingSort(a, max);
    }

    /**
     * Utility method to perform counting sort on maximum value of the array
     * @param a The integer array to sort
     * @param k The maximum key value
     */
    private static void countingSort(int[] a, int k){
        int[] count = new int[k + 1];
        int[] output = new int[a.length];

        for(int i = 0; i < a.length; i++){
            count[a[i]]++;
        }

        for(int i = 1; i < k + 1; i++){
            count[i] += count[i - 1];
        }

        for(int i = a.length - 1; i <= 0; i--){
            count[a[i]]--;
            output[count[a[i]]] = a[i];
        }

        for(int i = 0; i < a.length; i++){
            a[i] = output[i];
        }
    }

    /**
     * A pancake sort algorithm. Uses the pancake stack problem to sort an array
     * @param a The integer array to sort
     */
    public static void pancakeSort(int[] a){
        for(int i = a.length; i > 1; i--){
            int max = 0;
            for(int j = 1; j < i; j++){
                if(a[j] > a[max]){
                    max = j;
                }
            }
            if(max != i - 1){
                flip(a, max);
                flip(a, i - 1);
            }
        }
    }

    /**
     * A utility method to flip a stack of numbers from 0 to k
     * @param a The integer array to flip
     * @param k The index of the final position of the stack
     */
    private static void flip(int[] a, int k){
        int left = 0;
        while(left < k){
            swap(a, k, left);
            k--;
            left++;
        }
    }

    /**
     * A bingo sort algorithm. Works by moving all equivalent elements to the front from lowest value to highest
     * Worst: O(m * n) Where m is the number of distinct elements
     * Average: O(m * n) Where m is the number of distinct elements
     * Best: O(n * m^2) Where m is the number of distinct elements
     * @param a The integer array to sort
     */
    public static void bingoSort(int[] a){
        int bingo = a[0];
        int next = a[0];
        for(int i = 0; i < a.length; i++){
            bingo = Math.min(bingo, a[i]);
            next = Math.max(next, a[i]);
        }
        int largest = next;
        int nextPos = 0;
        while(bingo < next){
            int start = nextPos;
            for(int i = start; i < a.length; i++){
                if(a[i] == bingo){
                    swap(a, i, nextPos);
                    nextPos++;
                }else if(a[i] < next){
                    next = a[i];
                }
            }
            bingo = next;
            next = largest;
        }
    }

    /**
     * A pigeonhole sort algorithm. Works by creating an array corresponding to the key value sof the original array
     * Worst: O(n + 2^k)
     * Average: O(n + 2^k)
     * Best: ?
     * @param a The integer array to sort
     */
    public static void pigeonholeSort(int[] a){
        int min = a[0];
        int max = a[0];
        for(int i = 1; i < a.length; i++){
            min = Math.min(min, a[i]);
            max = Math.max(max, a[i]);
        }
        int range = max - min + 1;
        int[] holes = new int[range];
        for(int i = 0; i < a.length; i++){
            holes[a[i]-min]++;
        }
        int index = 0;
        for(int i = 0; i < range; i++){
            while(holes[i]-->0){
                a[index++] = i + min;
            }
        }
    }

    /**
     * A cycle sort algorithm. Works by cycling elements into their correct positions
     * Worst: O(n^2)
     * Average: O(n^2)
     * Best: O(n^2)
     * @param a The integer array to sort
     */
    public static void cycleSort(int[] a){
        for(int i = 0; i <= a.length - 2; i++){
            int item = a[i];
            int pos = i;
            for(int j = i + 1; j < a.length; j++){
                if(a[j] < item){
                    pos++;
                }
            }
            if(pos != i){
                while(item == a[pos]){
                    pos++;
                }
                if(pos != i){
                    if(item != a[pos]){
                        item ^= a[pos];
                        a[pos] ^= item;
                        item ^= a[pos];
                    }
                }
                while(pos != i){
                    pos = i;
                    for(int j = i + 1; j < a.length; j++){
                        if(a[j] < item){
                            pos++;
                        }
                    }
                    while(item == a[pos]){
                        pos++;
                    }
                    if(item != a[pos]){
                        item ^= a[pos];
                        a[pos] ^= item;
                        item ^= a[pos];
                    }
                }
            }
        }
    }

    /**
     * A stooge sort algorithm. Works by swapping first and last values if they are incorrect positions then recusively sorting the first two thirds of the list, the second two thirds of the list, and the first two thirds of the list a second time
     * Worst: O(n^(log3/log1.5))
     * Average: O(n^(log3/log1.5))
     * Best: O(n^(log3/log1.5))
     * @param a The integer array to sort
     */
    public static void stoogeSort(int[] a){
        stoogeSort(a, 0, a.length - 1);
    }

    /**
     * A method that performs the stooge sort over a given interval of indexes
     * @param a The integer array to sort
     * @param l The index of the left side of the interval, inclusive
     * @param h The index of the right side of the interval, inclusive
     */
    public static void stoogeSort(int[] a, int l, int h){
        if(l >= h){
            return;
        }
        if(a[l] > a[h]){
            swap(a, l, h);
        }
        if(h - l + 1 > 2){
            int pivot = (h - l + 1) / 3;
            stoogeSort(a, l, h - pivot);
            stoogeSort(a, l + pivot, h);
            stoogeSort(a, l, h - pivot);
        }
    }
    
    /**
     * A tree sort algorithm. Works by creating and performing an inorder traversal on a binary tree based off the array
     * Worst: ?
     * Average: O(nlogn)
     * Best: ?
     * @param a The integer array to sort
     */
    public static void treeSort(int[] a){
        BinaryTree<Integer> tree = new BinaryTree<Integer>();
        for(int i: a){
            tree.add(new Integer(i));
        }
        Node<Integer>[] sorted = tree.inorder();
        for(int i = 0; i < sorted.length; i++){
            a[i] = Arrays.asList(sorted).get(i).getData().intValue();
        }
    }
    
    /**
     * A merge sort algorithm. Works by splitting lists into sublists to sort recursively
     * Worst: O(nlogn)
     * Average: O(nlogn)
     * Best: O(nlogn)
     * @param a The integer array to sort
     */
    public static void mergeSort(int[] a){
        int[] b = a.clone();
        order(b, a, 0, a.length-1);
    }
    
    /**
     * Helper method to perform recursion on split arrays for merge sort
     * @param source The array to copy from
     * @param dest The array to copy to
     * @param l The index of the left side of the subgroup
     * @param h The index of the right side of the subgroup
     */
    private static void order(int[] source, int[] dest, int l, int h){
        if(l != h){
            int mid = (l + h) / 2;
            order(dest, source, l, mid);
            order(dest, source, mid + 1, h);
            merge(source, dest, l, mid, h);
        }
    }
    
    /**
     * Helper method to merge two sublists
     * @param source The array to copy from
     * @param dest The array to copy to
     * @param l The index of the left side of the first subgroup
     * @param mid The index of the right side of the first subgroup
     * @param h The index of the right side of the second subgroup
     */
    private static void merge(int[] source, int[] dest, int l, int mid, int h){
        int i = l;
        int j = mid + 1;
        int index = l;
        do{
            if(source[i] < source[j]){
                dest[index] = source[i++];
            }else{
                dest[index] = source[j++];
            }
            index++;
        }while(i <= mid && j <= h);
        
        if(i > mid){
            do{
                dest[index++] = source[j++];
            }while(j <= h);
        }else{
            do{
                dest[index++] = source[i++];
            }while(i <= mid);
        }
    }
}
