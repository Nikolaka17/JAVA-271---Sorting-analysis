import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public abstract class Sorts {

    public static boolean isSorted(int[] a){
        for(int i = 0; i < a.length - 1; i++){
            if(a[i] > a[i+1]){
                return false;
            }
        }
        return true;
    }

    public static boolean isSorted(List<Integer> a){
        for(int i = 0; i < a.size() - 1; i++){
            if(a.get(i) > a.get(i)){
                return false;
            }
        }
        return true;
    }

    public static int binarySearch(int[] a, int item, int low, int high){
        while(low <= high){
            int mid = low + (high - low) / 2;
            if(item == a[mid]){
                return mid;
            }else if(item > a[mid]){
                low = mid + 1;
            }else{
                high = mid - 1;
            }
        }
        return low;
    }

    public static void swap(int[] a, int i, int j){
        if(a[i] != a[j]){
            a[i] ^= a[j];
            a[j] ^= a[i];
            a[i] ^= a[j];
        }
    }
    
    //O(n^2)
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
    
    //O(n^2)
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
    
    //O(n^2)
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

    public static void binaryInsertionSort(int[] a){
        for(int i = 1; i < a.length; i++){
            int x = a[i];
            int j = Math.abs(Arrays.binarySearch(a, 0, i, x) + 1);
            System.arraycopy(a, j, a, j+1, i-j);
            a[j] = x;
        }
    }
    
    //O(nlgn)
    public static void quickSort(int[] a){
        quickSort(a, 0, a.length - 1);
    }

    public static void dualPivotQuickSort(int[] a){
        dualPivotQuickSort(a, 0, a.length - 1);
    }
    
    public static void quickSort(int[] a, int low, int high){
        int pivot = partition(a, low, high);
        if(low < pivot){
            quickSort(a, low, pivot - 1);
        }
        if(pivot < high){
            quickSort(a, pivot + 1, high);
        }
    }

    public static void dualPivotQuickSort(int[] a, int low, int high){
        if(low < high){
            int[] pivot = dualPartition(a, low, high);
            dualPivotQuickSort(a, low, pivot[0] - 1);
            dualPivotQuickSort(a, pivot[0] + 1, pivot[1] - 1);
            dualPivotQuickSort(a, pivot[1] + 1, high);
        }
    }
    
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

    //O(nlgn)
    public static void heapSort(int[] a){
        int n = a.length;

        for(int i = n / 2 - 1; i >= 0; i--){
            heapify(a, n, i);
        }

        for(int i = n - 1; i > 0; i--){
            swap(a, 0, i);
            heapify(a, i, 0);
        }
    }

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

    //O(S)
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

    //O(nlgn)
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

    //O(n^2)
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

    //O(n^2)
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

    //O(n^2)
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

    //O(n^4/3)
    public static void shellSort(int[] a){
        shellSort(a, new int[]{701, 301, 132, 57, 23, 10, 4, 1});
    }

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

    //O(n * n!)
    public static void bogoSort(int[] a){
        while(!isSorted(a)){
            List<Integer> temp = Arrays.stream(a).boxed().collect(Collectors.toList());
            Collections.shuffle(temp);
            for(int i = 0; i < temp.size(); i++){
                a[i] = temp.get(i);
            }
        }
    }

    //O(n + r)
    public static void bucketSort(int[] a){
        bucketSort(a, a.length);
    }

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

    public static void optimizedBucketSort(int[] a){
        optimizedBucketSort(a, a.length);
    }

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

    //O(n^2)
    public static void exchangeSort(int[] a){
        for(int i = 0; i < a.length - 1; i++){
            for(int j = i + 1; j < a.length; j++){
                if(a[i] > a[j]){
                    swap(a, i, j);
                }
            }
        }
    }

    //O(n^2)
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

    //O(n + r)
    public static void countingSort(int[] a){
        int max = 0;
        for(int i: a){
            if(i > max){
                max = i;
            }
        }
        countingSort(a, max);
    }

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

    //O(n^2)
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

    private static void flip(int[] a, int k){
        int left = 0;
        while(left < k){
            swap(a, k, left);
            k--;
            left++;
        }
    }

    //O(m * n)
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

    //O(nlgn)
    /*public static void treeSort(int[] a){
        BinaryTree<Integer> tree = new BinaryTree();
        for(int i: a){
            tree.add(new Integer(i));
        }
        Node<Integer>[] sorted = tree.inorder();
        for(int i = 0; i < sorted.length; i++){
            a[i] = Arrays.asList(sorted).get(i).getData().intValue();
        }
    }*/

    //O(n + 2^k)
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

    //O(n^2)
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

    //O(n^(lg3/lg1.5))
    public static void stoogeSort(int[] a){
        stoogeSort(a, 0, a.length - 1);
    }

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
}
