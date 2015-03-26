public class Search {
    public SearchResult search(SearchSetting s){
        int i = 0;
        String target_url;
        String target_file="";
        //System.out.println(s.BaseURL);
        //System.out.println(s.start_keyword);
        //System.out.println(s.end_keyword);
        //System.out.println(s.mode);
        String[] result = new String[10000];
        GetURLContent in = new GetURLContent();
        Searchbywords search = new Searchbywords();
        SearchResult search_result = new SearchResult();
        RegularExpressionSearch r_search = new RegularExpressionSearch();
        if(s.get_mode() == 1){
            //R
            i = s.index;
                target_url = replace_url_incremnet(i,s.BaseURL);
                try{
                    target_file = in.open_url_file(target_url);
                    //System.out.println(target_file);
                }catch( Exception e){
                    e.printStackTrace();
                }
                if(s.method==1){
                    search_result = search.searchbywords(target_file,s.start_keyword,s.end_keyword);
                }else if(s.method==2){
                    //System.out.println(s.start_keyword+" "+s.end_keyword+" "+i);
                    search_result = r_search.RegularExpressionSearch(target_file,s.start_keyword,s.end_keyword);
            }
        }else if(s.get_mode() == 2){
            //P
            target_url = s.BaseURL;
            try{
                target_file = in.open_url_file(target_url);
            }catch( Exception e){
                e.printStackTrace();
            }
            if(s.method==1){
                search_result = search.searchbywords(target_file,s.start_keyword,s.end_keyword);
            }else if(s.method==2){
                search_result = r_search.RegularExpressionSearch(target_file,s.start_keyword,s.end_keyword);
            }
        }else{
            System.out.println("Mode Bug");
        }
        //System.out.println(search_result.result_string);
        return search_result;
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