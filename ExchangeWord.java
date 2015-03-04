/*
This calss change one keyword to another keyword. If a keyword is supposed to remove, an empty string should be provided as newkeyword
 */

import java.net.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ReplaceKeyword {
    public int array_length;
    
    public static void main(String [] args){
        SearchResult theReturn = new SearchResult();
        theReturn.result_string = "aqwqwqwqkldajdlkasjdaaaks";
        String[] result = new String[0];
        theReturn.result_array = result;
        theReturn.count = 0;
        ReplaceKeywordWithinString(theReturn, "w", " ");
    }
    
    public ReplaceKeywordWithinString(SearchResult input, String oldKeyword, String newKeyword){
        int i;
        for(i = 0; i < input.count; i++){
            input.result_array[i].replaceAll(oldKeyword, newKeyword);
        }
        input.result_string.replaceAll(oldKeyword, newKeyword);
        System.out.println(input.result_string);
    }
}