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
}
