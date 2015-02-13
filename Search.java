public class Search {
    public String search(SearchSetting s){
        int i = 0;
        String target_url;
        String target_file="";
        String[] result = new String[10000];
        GetURLContent in = new GetURLContent();
        Searchbywords search = new Searchbywords();
        RegularExpressionSearch r_search = new RegularExpressionSearch();
        if(s.get_mode() == 1){
            //R
            for(i=s.increment_from;i<s.increment_to+1;i++){
                target_url = replace_url_incremnet(i,s.BaseURL);
                try{
                    target_file = in.open_url_file(target_url);
                }catch( Exception e){
                    e.printStackTrace();
                }
                if(s.method==1){
                    result = search.searchbywords(target_file,s.start_keyword,s.end_keyword);
                }else if(s.method==2){
                    result = r_search.RegularExpressionSearch(target_file,s.start_keyword,s.end_keyword);
                }
                while(i<r_search.array_length||result[i]!=null){
                    System.out.println(result[i]);
                    i++;
                }
            }
        }else if(s.get_mode() == 2){
            //P
            target_url = s.BaseURL;
            while(true){
                try{
                    target_file = in.open_url_file(target_url);
                }catch( Exception e){
                    e.printStackTrace();
                }
                if(s.method==1){
                    result = search.searchbywords(target_file,s.start_keyword,s.end_keyword);
                }else if(s.method==2){
                    result = r_search.RegularExpressionSearch(target_file,s.start_keyword,s.end_keyword);
                }
                i = 0;
                while(i<r_search.array_length||result[i]!=null){
                    System.out.println(r_search.array_length);
                    System.out.println(i);
                    System.out.println(result[i]);
                    i++;
                }
                try {
                    Thread.sleep(s.time_interval*1000);
                    //1000 milliseconds is one second.
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
        }else{
            System.out.println("Mode Bug");
        }
        return "";
    }
    public String replace_url_incremnet(int r,String url){
        String BaseURL = url;
        if(url.contains("XXX")){
            String replace = String.valueOf(r);
            int t = BaseURL.indexOf("XXX");
            BaseURL = BaseURL.substring(0,t)+replace+BaseURL.substring(t+3);
            return BaseURL;
        }else{
            System.out.println("URL replace Bug");
            return "";
        }
    }
}