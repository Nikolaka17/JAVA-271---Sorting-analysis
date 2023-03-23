import java.util.Scanner;
import java.text.DecimalFormat;
import java.io.File;
import java.io.FileNotFoundException;

public class Timing {
    
    private static final int SIZE = 4320000;
    private static final String PATH = "data.txt";

    public static void main(String[] args){
        Scanner fileReader = null;
        DecimalFormat timerFormat = new DecimalFormat("0.000000#");
        int[] arr = new int[SIZE];

        try{
            fileReader = new Scanner(new File(PATH));
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
            System.exit(0);
        }

        for(int i = 0; i < arr.length; i++){
            arr[i] = fileReader.nextInt();
        }

        long start = System.nanoTime();
        Sorts.patienceSort(arr);
        long end = System.nanoTime();

        if(isSorted(arr)){
            System.out.println(timerFormat.format((end - start) / Math.pow(10, 9)));
        }else{
            System.out.println("Sort didnt work");
        }
    }

    private static boolean isSorted(int[] a){
        for(int i = 0; i < a.length - 1; i++){
            if(a[i] > a[i+1]){
                return false;
            }
        }
        return true;
    }
}
