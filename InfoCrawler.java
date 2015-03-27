import java.net.*;
import java.io.*;
import java.lang.*;
import java.awt.event.KeyEvent;

public class InfoCrawler  {
    public static boolean mode_flag = false;
    public static int public_mode = 0;
    public static int input_concurrency(){
        int return_result = 0;
        Console console = System.console();
        System.out.println("\n=====Add new Job=====");
        int choice_set = 0;
        while(choice_set==0){
            System.out.println("Do you want to add more job?(y/n)");
            System.out.print("Command:");
            String input_string = console.readLine();
            regular_command_check(input_string);
            if(input_string.equals("n")){
                choice_set = 1;
                return 0;
            }else if(input_string.equals("y")){
                choice_set = 1;
                System.out.println("How many more?(Max:4 Currently:1)");
                int set = 0;
                while(set==0){
                    try{
                        System.out.print("Command:");
                        input_string = console.readLine();
                        return_result = Integer.parseInt(input_string);
                        if(return_result>3){
                            System.out.println("Only support max 4 at the same time");
                        }else if(return_result<0){
                            System.out.println("Invalid");
                        }else{
                            set = 1;
                        }
                    }catch(Exception e){
                        System.out.println("Please input a Integer");
                    }

                }
            }else{
                System.out.print("Choose y or n");
            }
        }
        return return_result;
    }
    public static String input_command(String word){
        Console console = System.console();
        System.out.print(word);
        String input_string = console.readLine();
        regular_command_check(input_string);
        return input_string;
    }
    public static void regular_command_check(String s){
        if(s.equals("-q")){
            System.exit(0);
        }else if(s.equals("-h")){
            System.out.println("Help:");
        }
        return;
    }
    public static int mode_selection(){
        Console console = System.console();
        int mode = 0;
        // int mode
        // Repeat Mode 1
        // Periodic Mode 2
        String input_string;
        String start_keyword;
        String end_keyword;
        System.out.println("\n======Please Select Mode======\n-r Repeat Mode\n-p Periodic Mode\n-s Single Page");
        input_string = input_command("Command:");
        if(input_string.equals("-r")){
            mode = 1;
        }else if(input_string.equals("-p")){
            mode = 2;
        }else if(input_string.equals("-s")){
            mode = 3;
        }else{
            System.out.println("Error:Please select from available mode");
        }
        return mode;
    }
    
    public static int method_selection(){
        Console console = System.console();
        int method = 0;
        String input_string;
        System.out.println("\n======Please Select Available Method======\n-w Word by Word\n-r Regular Expression");
        input_string = input_command("Command:");
        if(input_string.equals("-w")){
            method = 1;
        }else if(input_string.equals("-r")){
            method = 2;
        }else{
            System.out.println("Error:Please select from available method");
        }
        return method;
    }
    
    public static SearchSetting setup(){
        int url_set = 0;
        SearchSetting set = new SearchSetting();
        Console console = System.console();
        String URL_file;
        String input_string;
        String start_keyword="";
        String end_keyword="";
        String BaseURL = "";//Base URL with increment variable in -r mode
        //Repeat Mode Increment Range
        int increment_from = 0;
        int increment_to = 0;
        int i;
        //Periodic Mode
        int time_interval = 0;
        GetURLContent in = new GetURLContent();
        int mode = 0;
        int method = 0;
        HTMLElement h = new HTMLElement();
        mode = public_mode;
        while (mode==0&&mode_flag==false){
            mode = mode_selection();
        }
        if(mode==1){
            //Repeat Mode
            System.out.println("\n======Input URL using 'XXX' to replace increment variable======");
            System.out.println("Also add 'http://' or 'https://' in front");
            while(url_set==0){
                BaseURL = input_command("URL:");
                set.BaseURL = BaseURL;
                if(BaseURL.contains("XXX")){
                    int t = BaseURL.indexOf("XXX");
                    System.out.println("\n======Input Increment Range======");
                    int range_set = 0;
                    while(range_set==0){
                        try{
                            System.out.print("From:");
                            input_string = console.readLine();
                            increment_from = Integer.parseInt(input_string);
                            System.out.print("To:");
                            input_string = console.readLine();
                            increment_to = Integer.parseInt(input_string);
                            range_set=1;
                        }catch(Exception e){
                            System.out.println("Please input a Integer");
                        }
                    }
                    String replace = String.valueOf(increment_from);
                    BaseURL = BaseURL.substring(0,t)+replace+BaseURL.substring(t+3);
                    //System.out.println(BaseURL);
                    try{
                        in.open_url_file(BaseURL);
                        url_set = 1;
                    }catch(Exception e){
                        System.out.println("Invalid URL!");
                    }
                }else{
                    System.out.println("Using 'XXX' to replace increment variable");
                }
            }
        }
        else if(mode==2){
            //Periodic Mode
            System.out.println("\n======Please enter the URL=====");
            System.out.println("Also add 'http://' or 'https://' in front");
            while(url_set==0){
                try{
                    BaseURL = input_command("URL:");
                    in.open_url_file(BaseURL);
                    url_set = 1;
                }catch(Exception e){
                    System.out.println("Invalid URL!");
                }
            }
            set.BaseURL = BaseURL;
            System.out.println("\n======Input Time Interval======");
            int interval_time_set = 0;
            while(interval_time_set == 0){
                try{
                    System.out.print("Seconds:");
                    input_string = console.readLine();
                    time_interval = Integer.parseInt(input_string);
                    interval_time_set = 1;
                }catch(Exception e){
                    System.out.println("Please input a Integer");
                }
            }
        }else if(mode == 3){
            System.out.println("\n======Please enter the URL=====");
            System.out.println("Also add 'http://' or 'https://' in front");
            while(url_set==0){
                try{
                    BaseURL = input_command("URL:");
                    in.open_url_file(BaseURL);
                    url_set = 1;
                }catch(Exception e){
                    System.out.println("Invalid URL!");
                }
            }
            set.BaseURL = BaseURL;
        }
        int keyword_set = 0;
        int html_element_set = 0;
        boolean html_element_select = false;
        while(keyword_set==0){
            System.out.println("\n=====Please Enter Starting keyword and Ending keyword=====");
            html_element_set = 0;
            while(html_element_set==0){
                System.out.println("Do you want to search HTML element?(y/n)");
                input_string = input_command("Command:");
                if(input_string.equals("y")){
                    html_element_select = true;
                    html_element_set = 1;
                }else if(input_string.equals("n")){
                    html_element_select = false;
                    html_element_set = 1;
                }else{
                    System.out.println("Yes/No? (y/n) input y or n only");
                }
            }
            if(html_element_select==true){
                set.html_element_select = html_element_select;
                int html_element_select_set = 0;
                while(html_element_select_set==0){
                    System.out.println("\nInput HTML Element without '<>'");
                    input_string = input_command("HTML Element:");
                    if(h.html_element_check(input_string)==true){
                        html_element_select_set = 1;
                        h.html_element_keyword_set(input_string);
                    }else{
                        System.out.print(input_string);
                        System.out.println(" is not a HTML Element");
                    }
                }
                
            }else{
                System.out.println("Input Start Keyword:");
                start_keyword = input_command("Keyword:");
                System.out.println("Input End Keyword:");
                end_keyword = input_command("Keyword:");
            }
            keyword_set = 1;
        }
        while(method==0){
            method = method_selection();
        }
        int notification_set = 0;
        String notification_email="";
        boolean notification_selection = false;
        System.out.println("\n=====E-mail Notifications=====");
        while(notification_set==0){
            System.out.println("Do you want to receive e-mail Notifications?(y/n)");
            input_string = input_command("Command:");
            if(input_string.equals("y")){
                notification_selection = true;
                notification_set = 1;
                System.out.println("Input your email address");
                notification_email = input_command("Email:");
            }else if(input_string.equals("n")){
                notification_selection = false;
                notification_set = 1;
            }else{
                System.out.println("Yes/No? (y/n) input y or n only");
            }
        }
        int save_set = 0;
        boolean save_select = false;
        String file_name = "";
        if(mode == 1){
            while(save_set==0){
                System.out.println("Do you want to save the result into file?(y/n)");
                input_string = input_command("Command:");
                if(input_string.equals("y")){
                    save_select = true;
                    save_set = 1;
                    System.out.println("");
                    System.out.println("Enter your file name. Provide path if you want to store it elsewhere");
                    file_name = input_command("Name:");
                }else if(input_string.equals("n")){
                    save_select = false;
                    save_set = 1;
                }else{
                    System.out.println("Yes/No? (y/n) input y or n only");
                }
            }
        }
        String job_name = "";
        System.out.println("\nName your job(Can be empty)");
        System.out.print("Name:");
        job_name = console.readLine();
        set.save_select = save_select;
        set.file_name = file_name;
        set.mode = mode;
        set.increment_to = increment_to;
        set.increment_from = increment_from;
        set.increment_times = increment_to - increment_from;
        set.time_interval = time_interval;
        set.start_keyword = start_keyword;
        set.end_keyword = end_keyword;
        set.notification_select = notification_selection;
        set.notification_email = notification_email;
        set.job_name = job_name;
        set.method = method;
        set.html_keyword = h;
        set.index = set.increment_from;
        if(html_element_select == true){
            set.start_keyword = h.element_start;
            set.end_keyword = h.element_end;
        }
        return set;
    }
    public static SearchSetting setup_argv(String[] args){
        SearchSetting set = new SearchSetting();
        boolean mode_set = false;
        boolean url_set = false;
        boolean range_set = false;
        boolean interval_set = false;
        boolean keyword_set = false;
        int i;
        for(i=0;i<args.length;i++){
            if(args[i].equals("-r")){
                set.mode = 1;
                mode_set = true;
            }else if(args[i].equals("-p")){
                set.mode = 2;
                mode_set = true;
            }else if(args[i].equals("-url")){
                i++;
                set.BaseURL = args[i];
                url_set = true;
            }else if(args[i].equals("-Range")){
                range_set = true;
                i++;
                set.increment_from = Integer.parseInt(args[i]);
                i++;
                set.increment_to = Integer.parseInt(args[i]);
            }else if(args[i].equals("-Interval")){
                interval_set = true;
                i++;
                set.time_interval = Integer.parseInt(args[i]);
            }else if(args[i].equals("-Keyword")){
                keyword_set = true;
                i++;
                set.start_keyword = args[i];
                i++;
                set.end_keyword = args[i];
            }
            MyThread myThread = new MyThread(set,0);
            try{
                myThread.start();
            }catch(Exception e){
                System.out.println("Exception Caught, Please Enter valid settings");
                e.printStackTrace();
            }
        }
        
        return set;
    }
    public static void main(String[] args) {
        SearchSetting[] set =  new SearchSetting[4];
        if(args.length==0){
            System.out.println("=====Welcome to InfoCrawler=====\n-h For help\n-q Exit");
            set[0] = setup();
            int concurrency = 0;
            concurrency = input_concurrency();
            public_mode = set[0].mode;
            int i;
            for(i=0;i<concurrency;i++){
                mode_flag=true;
                System.out.print("\n======Job No.");
                System.out.print(i+2);
                System.out.print(" Set up======");
                set[i+1] = setup();
            }
            MyThread[] myThread = new MyThread[4];
            for(i=0;i<concurrency+1;i++){
                myThread[i] = new MyThread(set[i],i);
                myThread[i].start();
            }
        }else{
            set[0] = setup_argv(args);
        }
    }
}