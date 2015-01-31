import java.net.*;
import java.io.*;

public class GetURLContent {
    public static void main(String[] args) {
        System.out.println("Hello, World");
        try{
            open_url_file("http://lemonbear.tk/test.html");
        }catch(Exception e){
            System.out.println("MalformedURLException");
        }
    }
    public static void open_url_file(String target) throws Exception{
        URL oracle = new URL(target);
        BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null){
            System.out.println(inputLine);
        }
    }
}