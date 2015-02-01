import java.net.*;
import java.io.*;

public class InfoCrawler {
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
        System.out.print("Command:");
        input_string = console.readLine();
        regular_command_check(input_string);
        if(input_string.equals("-r")){
            mode = 1;
        }else if(input_string.equals("-p")){
            mode = 2;
        }else{
            System.out.println("Please enter -p or -r\n-r Repeat Mode\n-p Periodic Mode");
        }
        return mode;
    }
    public static void main(String[] args) {
        System.out.println("welcome to InfoCrawler\n-h For help\n-q Exit the program\n======Please Select Mode======\n-r Repeat Mode\n-p Periodic Mode");
        while(true){
            int mode = 0;
            while (mode==0){
                mode = mode_selection();
            }
        }
    }
}