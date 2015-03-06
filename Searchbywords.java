//Written by YIJIE WU
//Given a string as input, find the string between 2 keywords



import java.util.*;
import java.io.*;

public class Searchbywords{
        



	SearchResult searchbywords(String input,String key1,String key2){
		
	long start = System.nanoTime();
        int key1length=key1.length();
        //may need to chage the size of the array
        int []key1index=new int[input.length()];
        int []key2index=new int[input.length()];
        String[] results=new String[input.length()];
        
        int i=0;
        int l=0;
        int j=0;
        int k=0;
        
        while((i=input.indexOf(key1,i))>-1){
          //  System.out.println("indeix is"+i);
            key1index[j]=i;
            i++;
            j++;
        }
        
        
        while((l=input.indexOf(key2,l))>-1){
            //System.out.println("indeix is"+l);
            key2index[k]=l;
            l++;
            k++;
            
        }

         
        if(j==0&&k==0){
            String aa[]=new String[1];
            aa[0]=input;
            long elapsedTime = System.nanoTime() - start;
            
            SearchResult sr = new SearchResult();
            sr.result_string = input;
            sr.result_array = aa;
            sr.time_used = elapsedTime;
            sr.count = 1;
            return sr;
        }
        
        if(j!=0&&k==0){
         String aa[]=new String[1];
         aa[0]="";
         long elapsedTime = System.nanoTime() - start;
            SearchResult sr = new SearchResult();
            sr.result_string = input;
            sr.result_array = aa;
            sr.time_used = elapsedTime;
            sr.count = 0;
         return sr;
        }
        if(j!=0&&k==0){
         String aa[]=new String[1];
         aa[0]="";
            long elapsedTime = System.nanoTime() - start;

            SearchResult sr = new SearchResult();
            sr.result_string = input;
            sr.result_array = aa;
            sr.time_used = elapsedTime;
            sr.count = 0;
         return sr;
        }
        

//System.out.println("j is "+j);
//System.out.println("k is "+k);
		int idx1=0;
		int idx2=0;

        int size=0;
        if(j>k)
        size=k;
        else
        size=j;
        
        for(int m=0;m<size;m++){
           // System.out.println("kkkk"+ key1index[m]);
           // System.out.println("aaaa"+ (key1length-1));
			while(key1index[idx1]+key1length>key2index[idx2]){
           		//results[m]=input.substring(key1index[m]+key1length,key2index[m]);
				idx2++;
			}
            results[m]=input.substring(key1index[idx1]+key1length,key2index[idx2]);
			idx1++;
			//System.out.println(results[m]);
        }
        
        String whole="";
        for(i=0;i<results.length;i++){
            whole=whole+results[i];
        }
       
         long elapsedTime = System.nanoTime() - start;
        SearchResult sr = new SearchResult();
        sr.result_string = whole;
        sr.result_array = results;
        sr.time_used = elapsedTime;
        sr.count = results.length;
        return sr;
    }
}
