import java.util.Scanner;
import java.text.DecimalFormat;
import java.io.File;
import java.io.FileNotFoundException;

public class Timing {
    
    private static final int SIZE = 10000;
    private static final String TENK = "small.txt";
    private static final String FORTYK = "med.txt";
    private static final String THREESIXTYK = "large.txt";
    private static final String FOURMIL = "xlarge.txt";
    private static final String TWOSIXTEENMIL = "huge.txt";
    public static void main(String[] args){
        Scanner fileReader = null;
        DecimalFormat timerFormat = new DecimalFormat("0.000000#");
        int[] arr = new int[SIZE];

        try{
            fileReader = new Scanner(new File(TENK));
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
            System.exit(0);
        }
        
        for(int i = 0; i < SIZE; i++){
            arr[i] = fileReader.nextInt();
        }

        long start = System.nanoTime();
        Sorts.bubbleSort(arr);
        long end = System.nanoTime();

        if(isSorted(arr)){
            System.out.println(timerFormat.format(end - start));
        }else{
            System.out.println("Sort didnt work");
        }
    }

    private static boolean isSorted(int[] a){
        for(int i = 0; i < a.length; i++){
            if(a[i] > a[i+1]){
                return false;
            }
        }
        return true;
    }
}
