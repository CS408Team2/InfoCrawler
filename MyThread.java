import java.util.concurrent.*;
import java.io.*;
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
        //System.out.println(set.increment_times);
        int count = 0;
        if(set.increment_times!=0){
            count = set.increment_times+1;
        }else{
            count = 1;
        }
        SearchResult sr[] = new SearchResult[count];
        SearchResult sr_s = new SearchResult();

        if(set.mode == 2){
            while(true){
                sr[0] = s.search(set);
                System.out.println(sr[0].result_string);
                try {
                    Thread.sleep(set.time_interval*1000);
                    //1000 milliseconds is one second.
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        else if(set.mode == 1){
            int i;
            int k = 0;
            for(i=set.increment_from;i<set.increment_to+1;i++){
                sr[k] = s.search(set);
                set.index++;
                k++;
            }
            count = k;
        }else if(set.mode == 3){
            sr_s = s.search(set);
        }
        if(set.mode == 1){
            int i;
            for(i=0;i<count;i++){
                //long threadId = Thread.currentThread().getId();
                System.out.println(sr[i].result_string);
            }
        }else if(set.mode == 3){
            System.out.println(sr_s.result_string);
        }
        //Filter
        ReplaceKeyword filter = new ReplaceKeyword();
        System.out.println();
        InfoCrawler info = new InfoCrawler();
        int filter_set = 0;
        String input_string = "";
        String remove_keyword;
        String replace_keyword;
        while(filter_set==0){
            System.out.println("Do you want to use filter?(y/n)");
            input_string = info.input_command("Command:");
            if(input_string.equals("y")){
                System.out.println("Enter Remove Keyword");
                remove_keyword = info.input_command("Keyword:");
                System.out.println("Enter Replace Keyword(Can be empty)");
                replace_keyword = info.input_command("Keyword:");
                if(set.mode == 1){
                    int i;
                    for(i=0;i<count;i++){
                        //long threadId = Thread.currentThread().getId();
                        filter.ReplaceKeywordWithinString(sr[i],remove_keyword,replace_keyword);
                    }
                }else if(set.mode == 3){
                    filter.ReplaceKeywordWithinString(sr_s,remove_keyword,replace_keyword);
                }
                if(set.mode == 1){
                    int i;
                    for(i=0;i<count;i++){
                        //long threadId = Thread.currentThread().getId();
                        System.out.println(sr[i].result_string);
                    }
                }else if(set.mode == 3){
                    System.out.println(sr_s.result_string);
                }
            }else if(input_string.equals("n")){
                filter_set = 1;
            }
            else{
                System.out.println("Yes/No? (y/n) input y or n only");
            }
            
        }
        int i;
        if(set.save_select == true){
            try{
                File file = new File(set.file_name);
                BufferedWriter output = new BufferedWriter(new FileWriter(file));
                for(i=0;i<count;i++){
                    //long threadId = Thread.currentThread().getId();
                    output.write(sr[i].result_string);
                    output.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        
    }
    
}