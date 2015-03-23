import java.io.*;
import java.net.*;
public class save_file_test  {
    public static void main(String[] args) {
        try{
            String filename = "/Users/Curtis/Desktop/";
            File file = new File(filename);
            if (!file.exists()) {
                file.createNewFile();
            }
            String test = "Hello";
            BufferedWriter output = new BufferedWriter(new FileWriter(file));
            output.write(test);
            output.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}