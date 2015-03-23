/*
This calss sorts string array and string in SearchResult
 */

import java.net.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Arrays;
import java.util.*;

class Sort {
    public int array_length;
    
    public static int glSub = 0;
    
    public static void main(String [] args){
        SearchResult theReturn = new SearchResult();

        String[] result = new String[5];
        
        result[0] = "aa";
        result[1] = "bb";
        result[2] = "cc";
        result[3] = "bbsss";
        result[4] = "aa";
        theReturn.result_array = result;
        
        theReturn.count = 5;

        Sort(theReturn, 5, 2);
    }
    
    /* 
     method = 1 : sort by integer
     method = 2 : sort by character
     method = 3 : sort by first character
     method = 4 : sort by content length
     method = 5 : sort by substring
    */
    
    public static void Sort(SearchResult input, int method, int sub){
        if(method == 1 || method == 2 || method == 3){
            Arrays.sort(input.result_array);
            int i;
            for(i = 0; i < input.count; i++){
                System.out.println(input.result_array[i]);
            }
            String temp = "";
            int k = 0;
            for(k = 0; k < input.count; k++){
                if(k == input.count - 1){
                    temp = temp + input.result_array[k];
                }else{
                    temp = temp + input.result_array[k] + "\n";
                }
            }
            input.result_string = temp;
        }else if(method == 4){
            int num = input.count;
            Arrays.sort(input.result_array, new LengthComparator());
            int i;
            for(i = 0; i < input.count; i++){
                System.out.println(input.result_array[i]);
            }
        }else if(method == 5){
            glSub = sub;
            Arrays.sort(input.result_array, new SubstringComparator());
            int i;
            for(i = 0; i < input.count; i++){
                System.out.println(input.result_array[i]);
            }
        }
        
    }
    
    static int getSub(){
        return glSub;
    }
}

class LengthComparator implements Comparator<String> {
    public int compare(String o1, String o2) {
        if (o1.length() > o2.length()) {
            return 1;
        } else if (o1.length() < o2.length()) {
            return -1;
        } else {
            return 0;
        }
    }
}

class SubstringComparator implements Comparator<String>{
    public int compare(String str1, String str2) {
        int sub = Sort.getSub();
        String substr1 = str1.substring(sub);
        String substr2 = str2.substring(sub);
        
        return substr1.compareTo(substr2);
    }
}
