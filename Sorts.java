public abstract class Sorts {
    
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
}
