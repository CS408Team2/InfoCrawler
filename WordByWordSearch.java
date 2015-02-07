//written by YIJIE WU
// Return the string between 2 string kewords

import org.apache.commons.lang.StringUtils;

public class WordByWordSearch {
    public static String[] WordByWordSearch(String input_string, String keyword1, String keyword2){

        String[] result_string = StringUtils.substringsBetween(input_string, keyword1, keyword2);
    
    //testing purpose
	
	//	for (int i=0;i<result_string.length;i++) {
	//		System.out.println("value:" + result_string[i]); 
	//	}

        return result_string;
    }
    
}
