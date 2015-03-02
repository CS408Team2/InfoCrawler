class HTMLElement {
    String element_start;
    String element_end;
    String[] html_element = new String[]{"DOCTYPE","a","abbr","acronym","abbr","address","applet","object","area","article","aside","audio","b","base","basefont","bdi","bdo","big","blockquote","body","br","button","canvas","caption","center","cite","code","col","colgroupgt","colgroup","datalist","dd","del","details","dfn","dialog","dir","ul","div","dl","dt","em","embed","fieldset","figcaption","figure","figure","font","footer","form","frame","frameset","h1","h2","h3","h4","h5","h6","head","header","hgroup","hr","html","i","iframe","img","input","ins","kbd","keygen","label","input","legend","fieldset","li","link","main","map","mark","menu","menuitem","meta","meter","nav","noframes","noscript","object","ol","optgroup","option","output","p","param","pre","progress","q","rp","rt","ruby","s","samp","script","section","select","small","source","span","strike","del","strong","style","sub","summary","sup","table","tbody","td","textarea","tfoot","th","thead","time","title","tr","track","tt","u","ul","var","video","wbr"};
    
    public boolean html_element_check(String s){
        int i =0 ;
        while(i<html_element.length){
            if(s.equals(html_element[i])){
                return true;
            }
            i++;
        }
        return false;
    }
    public void html_element_keyword_set(String s){
        int i =0 ;
        int index = 0;
        while(i<html_element.length){
            if(s.equals(html_element[i])){
                index = i;
            }
            i++;
        }
        element_start = "<" + html_element[index];
        element_end = "<" + "/" + html_element[index] + ">";
        //System.out.println(element_end);
    }
}