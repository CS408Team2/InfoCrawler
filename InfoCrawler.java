import java.net.*;
import java.io.*;

public class InfoCrawler {
    public static String input_command(){
        Console console = System.console();
        System.out.print("Command:");
        String input_string = console.readLine();
        regular_command_check(input_string);
        return input_string;
    }
    public static String input_URL(){
        Console console = System.console();
        System.out.print("URL:");
        String input_string = console.readLine();
        regular_command_check(input_string);
        input_string = http_check(input_string);
        return input_string;
    }
    
    public static String input_keyword(){
        Console console = System.console();
        System.out.print("keyword:");
        String input_string = console.readLine();
        regular_command_check(input_string);
        input_string = http_check(input_string);
        return input_string;
    }
    public static String notification_selection(){
        Console console = System.console();
        System.out.print("Command:");
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
    public static String http_check(String s){
        String return_string;
        if(s.length()<7){
            return_string = s;
            return return_string;
        }
        if(s.substring(0,7).equals("http://")==false){
            return_string = "http://"+s;
        }else{
            return_string = s;
        }
        return return_string;
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
        System.out.println("\n======Please Select Mode======\n-r Repeat Mode\n-p Periodic Mode");
        input_string = input_command();
        if(input_string.equals("-r")){
            mode = 1;
        }else if(input_string.equals("-p")){
            mode = 2;
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
        input_string = input_command();
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
        while (mode==0){
            mode = mode_selection();
        }
        if(mode==1){
            //Repeat Mode
            System.out.println("\n======Input URL again using 'XXX' to replace increment variable======");
            BaseURL = input_URL();
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
        }
        else if(mode==2){
            //Periodic Mode
            System.out.println("\n======Please enter the URL=====");
            BaseURL = input_URL();
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
        }
        int keyword_set = 0;
        while(keyword_set==0){
            System.out.println("\n=====Please Enter Starting keyword and Ending keyword=====");
            System.out.println("Input Start Keyword:");
            start_keyword = input_keyword();
            System.out.println("Input End Keyword:");
            end_keyword = input_keyword();
            keyword_set = 1;
        }
        while(method==0){
            method = method_selection();
        }
        int notification_set = 0;
        boolean notification_selection = false;
        System.out.println("\n=====E-mail Notifications=====");
        while(notification_set==0){
            System.out.println("Do you want to receive e-mail Notifications?(y/n)");
            input_string = notification_selection();
            if(input_string.equals("y")){
                notification_selection = true;
                notification_set = 1;
            }else if(input_string.equals("n")){
                notification_selection = false;
                notification_set = 1;
            }else{
                System.out.println("Yes/No? (y/n) input y or n only");
            }
        }
        
        
        set.mode = mode;
        set.increment_to = increment_to;
        set.increment_from = increment_from;
        set.increment_times = increment_from - increment_to;
        set.time_interval = time_interval;
        set.BaseURL = BaseURL;
        set.start_keyword = start_keyword;
        set.end_keyword = end_keyword;
        set.notification_select = notification_selection;
        return set;
    }
    public static void main(String[] args) {
        System.out.println("=====Welcome to InfoCrawler=====\n-h For help\n-q Exit");
        SearchSetting set = setup();
        System.out.println(set.BaseURL);
    }
}