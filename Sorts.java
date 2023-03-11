import java.util.ArrayList;

public abstract class Sorts {
    
    //O(n^2)
    public static void bubbleSort(int[] a){
        boolean swapped = true;
        int pass = 1;
        while(swapped){
            swapped = false;
            for(int i = 0; i < a.length - pass; i++){
                if(a[i] > a[i+1]){
                    a[i] ^= a[i+1];
                    a[i+1] ^= a[i];
                    a[i] ^= a[i+1];
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
            min = 1;
            for(int j = i + 1; j < a.length; j++){
                if(a[j] < a[min]){
                    min = j;
                }
            }
            if(i != min){
                a[i] ^= a[min];
                a[min] ^= a[i];
                a[i] ^= a[min];
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
    
    //O(nlgn)
    public static void quickSort(int[] a){
        quickSort(a, 0, a.length - 1);
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

    //O(nlgn)
    public static void heapSort(int[] a){
        int n = a.length;

        for(int i = n / 2 - 1; i >= 0; i--){
            heapify(a, n, i);
        }

        for(int i = n - 1; i > 0; i--){
            a[0] ^= a[i];
            a[i] ^= a[0];
            a[0] ^= a[i];
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
            a[i] ^= a[largest];
            a[largest] ^= a[i];
            a[i] ^= a[largest];

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
}
