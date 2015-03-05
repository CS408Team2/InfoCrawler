class SearchResult {
    private String result_string;
    private String[] result_array;
    private long time_used;
    private int count;
    
    public SearchResult(){
        result_string="";
        result_array={};
        time_used=0;
        count=0;
    }
    
    public SearchResult(String a,String[]b,int c,int d){
        result_string=a;
        result_array=b;
        time_used=c;
        count=d;
        
    }
    public String getrs(){
        return result_string;
    }
    public String [] getra(){
        return result_array;
    }
    
    public int gettu(){
        return time_used;
    }
    
    public int getcount(){
        return count;
    }
    
    public setrs(String a){
        result_string=a;
        
    }
    public setra(String[] a){
        
        result_array=a;
    }
    public settime(int a){
        time_used=a;
    }
    public setcount(int a){
        count=a;
    }
}
