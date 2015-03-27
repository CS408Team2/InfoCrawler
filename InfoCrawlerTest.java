import java.net.*;
import java.io.*;
import java.lang.*;
import java.awt.event.KeyEvent;

public class InfoCrawlerTest  {
    public static SearchSetting set =  new SearchSetting();
    /*
     set.BaseURL = String
     set.mode = Int 1 Repeat 2 Perodic
     set.increment_to = Int
     set.increment_from = Int
     set.increment_times = increment_to - increment_from;
     set.time_interval = Int
     set.start_keyword = String
     set.end_keyword = String
     set.method = Int 1 WordByWord 2 Reg
     set.index = set.increment_from;
     //Save into file
     set.save_select = true or false
     set.file_name = String
     
     */
    public static void main(String[] args) {
        MyThread myThread = new MyThread(set,0);
        try{
            myThread.start();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}