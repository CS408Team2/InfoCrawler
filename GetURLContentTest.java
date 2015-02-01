import java.net.*;
import java.io.*;

public class GetURLContentTest {
    public static void main(String[] args) {
        String result;
        if(args.length==0){
            System.out.println("Please enter URL \nCommand:java GetURLContentTest URL");
        }else{
            GetURLContent content = new GetURLContent();
            try{
                result = content.open_url_file(args[0]);
                System.out.println(result);
            }catch( Exception e){
                e.printStackTrace();
            }
        }
    }
}