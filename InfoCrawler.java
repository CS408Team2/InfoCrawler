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
        input_string = input_command();
        if(input_string.equals("-r")){
            mode = 1;
        }else if(input_string.equals("-p")){
            mode = 2;
        }else{
            System.out.println("\n=====Please enter -p or -r=====\n-r Repeat Mode\n-p Periodic Mode");
        }
        return mode;
    }
    public static void main(String[] args) {
        System.out.println("Welcome to InfoCrawler\n-h For help\n-q Exit the program\n======Please Select Mode======\n-r Repeat Mode\n-p Periodic Mode");
        Console console = System.console();
        String URL_file;
        String input_string;
        String BaseURL;
        GetURLContent in = new GetURLContent();
            int mode = 0;
            while (mode==0){
                mode = mode_selection();
            }
            if(mode==1){
                //Repeat Mode
                System.out.println("\n======Please enter the URL======");
                input_string = input_command();
                input_string = http_check(input_string);
                try{
                    URL_file = in.open_url_file(input_string);
                    System.out.println("\n======Input URL again using 'XXX' to replace increment variable======");
                    input_string = input_command();
                    input_string = http_check(input_string);
                    BaseURL = input_string;
                    
                }catch(Exception e){
                    e.printStackTrace();
                }
                
            }else if(mode==2){
                //Periodic Mode
                System.out.println("\n======Please enter the URL=====");
                input_string = input_command();
                input_string = http_check(input_string);
                try{
                    URL_file = in.open_url_file(input_string);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
    }
}