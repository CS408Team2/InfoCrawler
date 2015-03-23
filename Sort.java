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
        result[1] = "ba";
        result[2] = "c2";
        result[3] = "bbsss";
        result[4] = "11";
        theReturn.result_array = result;
        
        theReturn.count = 5;

        Sort(theReturn, 7, 2);
    }
    
    /* 
     method = 1 : sort by integer
     method = 2 : sort by character
     method = 3 : sort by first character
     method = 4 : sort by content length
     method = 5 : sort by substring
     method = 6 : eliminate content that only has number
     method = 7 : eliminate content that contain number
     method = 8 : eliminate content that only has character
     method = 9 : eliminate content that contain character
     
     sub is any integer if method 5 is not used or substring number if method 5 is used
     If an invalid sub number is provide, sort will fail
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
                if(k != input.count - 1){
                    temp = temp + input.result_array[k];
                }else{
                    temp = temp + input.result_array[k] + "\n";
                }
            }
            input.result_string = temp;
        }
        else if(method == 4){
            int num = input.count;
            Arrays.sort(input.result_array, new LengthComparator());
            int i;
            String temp = "";
            int k = 0;
            for(k = 0; k < input.count; k++){
                if(k != input.count - 1){
                    temp = temp + input.result_array[k];
                }else{
                    temp = temp + input.result_array[k] + "\n";
                }
            }
            input.result_string = temp;

        }
        else if(method == 5){
            glSub = sub;
            
            boolean toGo = true;
            
            if(sub < 0){
                toGo = false;
            }
            int num = input.count;
            int j;
            for(j = 0; j < num; j++){
                String temp;
                temp = input.result_array[j];
                if(temp.length() < sub + 1){
                    toGo = false;
                }
            }
             System.out.println(toGo);
            if(toGo){
                Arrays.sort(input.result_array, new SubstringComparator());
                int i;
                for(i = 0; i < input.count; i++){
                    System.out.println(input.result_array[i]);
                }
                String temp = "";
                int k = 0;
                for(k = 0; k < input.count; k++){
                    if(k != input.count - 1){
                        temp = temp + input.result_array[k];
                    }else{
                        temp = temp + input.result_array[k] + "\n";
                    }
                }
                input.result_string = temp;
            }
        }
        else if(method == 6){
            int i;
            int num = input.count;
            for(i = 0; i < input.count; i++){
                if(isInteger(input.result_array[i])){
                    num--;
                }
            }
            String[] result = new String[num];
            for(i = 0; i < input.count; i++){
                if(!isInteger(input.result_array[i])){
                    result[i] = input.result_array[i];
                }
            }
            input.result_array = result;
            input.count = num;
            String temp = "";
            int k = 0;
            for(k = 0; k < input.count; k++){
                if(k != input.count - 1){
                    temp = temp + input.result_array[k];
                }else{
                    temp = temp + input.result_array[k] + "\n";
                }
            }
            input.result_string = temp;
        }
        else if(method == 7){
            int i;
            for(i = 0; i < input.count; i++){
                if(!containInteger(input.result_array[i])){
                    System.out.println("1");
                }
            }
        }
        
    }
    
    public static boolean isInteger(String str) {

        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;

        for (i = 0; i < length; i++) {
            char c = str.charAt(i);
            if (c <= '/' || c >= ':') {
                return false;
            }
        }
        return true;
    }
    
    public static boolean containInteger(String str) {
        
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        
        for (i = 0; i < length; i++) {
            char c = str.charAt(i);
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
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
        //System.out.println(sub);
        String substr1 = str1.substring(sub);
        String substr2 = str2.substring(sub);
        
        return substr1.compareTo(substr2);
    }
}
