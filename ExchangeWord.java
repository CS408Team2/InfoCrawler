/*
This calss change one keyword to another keyword. If a keyword is supposed to remove, an empty string should be provided as newkeyword
 */

import java.net.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class RegularExpressionSearch {
    public int array_length;
    
    public RegularExpressionSearch(SearchResult input, String oldKeyword, String newKeyword){
        int i;
        for(i = 0; i < input.count; i++){
            input.result_array[i].replaceAll(oldKeyword, newKeyword);
        }
        input.result_string.replaceAll(oldKeyword, newKeyword);
    }
}