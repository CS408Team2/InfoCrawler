Written by YIJIE WU
Given a string as input, find the string between 2 keywords



import java.util.*;
import java.io.*;



String[] results searchbywords(String input,String key1,String key2){

    //may need to chage the size of the array
    int []key1index=new int[1000];
    int []key2index=new int[1000];
    String[] results=new String[1000];
    
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

    
   while(l=input.indexOf(key2,l)>-1){
      //System.out.println("indeix is"+l);
      key2index[k]=l;
      l++
      k++
      
    }
    
  int numofkeypairs=key1index.length;

   for(int m=0;m<numofkeypairs;m++){
      results[m]=input.substring(key1index[m],key2index[m]);
   }

return results;
}
