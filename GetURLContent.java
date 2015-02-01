import java.net.*;
import java.io.*;

public class GetURLContent {
    public static String open_url_file(String target) throws Exception,EmptyFileException{
        URL oracle = new URL(target);
        BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));
        String inputLine;
        String return_result = "";
        while ((inputLine = in.readLine()) != null){
            return_result = return_result+inputLine;
        }
        if(return_result==""){
            throw new EmptyFileException("EmptyFileException");
        }
        return return_result;
    }
}