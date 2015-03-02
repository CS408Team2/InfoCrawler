/*
This class use reglar expression to search string between 2 key words.
If anything prevent search or any errors happen durning search, a NULL will be returned.
If number of keyword1 and keyword2 does not match, the middle keyword will be discard.
If keyword2 is in front of keyword1, this function will return empty string in that specific location in string array
*/

import java.net.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class RegularExpressionSearch {
    long start = System.nanoTime();
    public int array_length;
    /*public static void main(String [] args){
        String[] test = new String[3];
        test = RegularExpressionSearch("<td>One</td>asd asd  asd<td>two</td>asq,.<tj> <td>tjree</td>", "<td>", "</td>");
        for(int i = 0; i < 3; i++){
            System.out.println(test[i]);
        }
        //RegularExpressionSearch("<td>One</td>asd asd  asd <td>two</td>asq <td>tjree</td> ", "<td>", "</td>");
        //RegularExpressionSearch("qqa swwda a qq sdads ww", "qq", "ww");
    }*/
    
    public SearchResult RegularExpressionSearch(String input, String key1, String key2){
        
        // If input only contain exautly key1 + key 2, return ""
        
        String Key1AndKey2 = key1 + key2;
        Pattern pKey1AndKey2Check = Pattern.compile(Key1AndKey2);
        Matcher mKey1AndKey2Check = pKey1AndKey2Check.matcher(input);
        
        if(mKey1AndKey2Check.matches()){
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
            countKey2++;
        }
        
        // If input does not contain one or both of key words, return
        
        if(countKey1 == 0 || countKey2 == 0){
            return null;
        }
        
        // Get min number of countKey1 and countKey2
        
        int min = getMin(countKey1, countKey2);
        String[] result = new String[min];
        array_length = min;
        Pattern pattern = Pattern.compile(key1 + "(.*?)" + key2);
        Matcher matcher = pattern.matcher(input);
        
        int count = 0;
        while (matcher.find()) {
            if(count < min){
                result[count] = matcher.group(1);
                count++;
            }
        }
        int i;
        for(i=0;i<min;i++){
            System.out.println(result[i]);
        }
        
        String temp = "";
        int k = 0;
        for(k = 0; k < count; k++){
            temp = temp + result[k] + "\n";
        }
        
        long elapsedTime = System.nanoTime() - start;
        
        SearchResult theResult = new SearchResult();
        theResult.count = count;
        theResult.result_array = result;
        theResult.result_string = temp;
        theResult.time_used = elapsedTime;
        
        return theResult;
    }
    
    // Get minimum number of 2 integer
    
    public static int getMin(int num1, int num2){
        if(num1 <= num2){
            return num1;
        }else{
            return num2;
        }
    }
}