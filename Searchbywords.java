//Written by YIJIE WU
//Given a string as input, find the string between 2 keywords



import java.util.*;
import java.io.*;

public class Searchbywords{

 String[]  searchbywords(String input,String key1,String key2){
        
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
            //System.out.println("indeix is"+i);
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
        if(i==-1&&l==-1){
         String aa[1]=input;
         return aa;
        }
        
        if(i!=-1&&l==-1){
         String aa[1]="";
         return aa;
        }
        if(i==-1&&l!=-1){
         String aa[1]="";
         return aa;
        }
        
        int size=0;
        if(j>k)
        size=k;
        else
        size=j;
        
        for(int m=0;m<size;m++){
            //System.out.println("kkkk"+ key1index[m]);
            //System.out.println("aaaa"+ (key1length-1));
            results[m]=input.substring(key1index[m]+key1length,key2index[m]);
        }
        
        
        return results;
    }
}
