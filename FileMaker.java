import java.io.FileWriter;
import java.util.Random;

public class FileMaker {
    
    private final static int SIZE = 10000;
    private final static String PATH = "C:\\Users\\nikol\\Code\\Math-271\\data.txt";

    public static void main(String[] args){
        try{
            FileWriter writer = new FileWriter(PATH, false);
            Random rng = new Random();

            writer.write("");
            for(int i = 0; i < SIZE; i++){
                writer.write((rng.nextInt(SIZE) + 1) + " ");
            }

            writer.close();
        }catch(Exception e){
            System.out.println("An error occurred");
        }
    }
}
