import java.util.concurrent.*;
public class MyThread extends Thread {
    SearchSetting set;
    int job_no = 0;
    public MyThread(SearchSetting s, int i) {
        // store parameter for later user
        set = s;
        job_no = i;
    }
    public void run() {
        Search s = new Search();
        SearchResult sr = new SearchResult();
        if(set.mode == 2){
            while(true){
                sr = s.search(set);
                if(sr.result_string!=null){
                    System.out.println(sr.result_string);
                }else{
                    System.out.println("Null");
                }
                try {
                    Thread.sleep(set.time_interval*1000);
                    //1000 milliseconds is one second.
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        else if(set.mode == 1){
            sr = s.search(set);
            if(sr.result_string!=null){
                System.out.println(sr.result_string);
            }else{
                System.out.println("Null");
            }
        }
    }
    
}