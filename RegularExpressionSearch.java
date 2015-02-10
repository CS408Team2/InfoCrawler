import java.net.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegularExpressionSearch {
    
    
    public static void main(String [] args){
        RegularExpressionSearch("wwasdasuu", "ww", "uu");
    }
    
    
    public static String[] RegularExpressionSearch(String input, String key1, String key2){
        
        // If input only contain exautly key1 + key 2, return ""
        
        String Key1AndKey2 = key1 + key2;
        Pattern pKey1AndKey2Check = Pattern.compile(Key1AndKey2);
        Matcher mKey1AndKey2Check = pKey1AndKey2Check.matcher(input);
        
        if(mKey1AndKey2Check.matches()){
            System.out.println("If input only contain exautly key1 + key 2, return ");
            return null;
        }
        
        // Get how many times key1 and key2 does input string contain
        
        String regexKey1 = key1;
        String regexKey2 = key2;
        
        Pattern pKey1 = Pattern.compile(regexKey1);
        Matcher mKey1 = pKey1.matcher(input);
        
        Pattern pKey2 = Pattern.compile(regexKey2);
        Matcher mKey2 = pKey2.matcher(input);
        
        int countKey1 = 0;
        int countKey2 = 0;
        
        while(mKey1.find()) {
            countKey1++;
        }
        while(mKey2.find()){
            System.out.println("1");
            countKey2++;
        }
        
        // If input does not contain any of key words, return
        
        if(countKey1 == 0 || countKey2 == 0){
            System.out.println("If input does not contain any of key words, return");
            return null;
        }
        
        System.out.println("" + countKey1);
        System.out.println("" + countKey2);

        // Get min number of countKey1 and countKey2
        
        int min = getMin(countKey1, countKey2);
        
        //
        
        //String result[] = new String[min];
        
        String[] result = new String[min];
        
        
        
        
        //test regex ------------
        
        
        
        
        
        
        
        
        
        
        
        
        
        return result;
    }
    
    public static int getMin(int num1, int num2){
        if(num1 <= num2){
            return num1;
        }else{
            return num2;
        }
    }
}