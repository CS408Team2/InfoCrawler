public class SearchTest{
    public static String pass[] = new String[10];
    public static String fail[] = new String[10];
    public static int count_pass=0;
    public static int count_fail=0;
    public static SearchSetting testcase = new SearchSetting();
    public static Search search_func = new Search();
    public static SearchResult search_result = new SearchResult();
    public static GetURLContent content = new GetURLContent();
    public static void testing(SearchSetting testcase,int i,String description,String match_key){
        String testcase_num = String.valueOf(i);
        System.out.println("=====TestCase"+testcase_num+"====");
        System.out.println("TestCase"+testcase_num+": "+description);
        try{
            search_result = search_func.search(testcase);
            if(search_result.result_string.equals(match_key)){
                System.out.println("TestCase"+testcase_num+": Pass\n");
                pass[count_pass] = "TestCase"+testcase_num;
                count_pass++;
            }else{
                System.out.println("TestCase"+testcase_num+": Fail Wrong Result\n");
                fail[count_fail] = "TestCase"+testcase_num;
                count_fail++;
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("TestCase"+testcase_num+": Fail! Exception\n");
            fail[count_fail] = "TestCase"+testcase_num;
            count_fail++;
        }
    }
    public static void main(String[] args) {
        
        //BoilerLink Test Case
        String match_key = "&quot;A Cause for Paws&quot; (ACP)\nA Cultural Connection promoting Leadership opportunities and Academic achievement for International and Multicultural students (ACCLAIM)\nAAE Graduate Women’s Gathering (AAE GWG)\nAcacia\nAcademy of Student Pharmacists (APhA-ASP)\nAccounting Association (PAA)\nACM SIGGRAPH\nAcoustical Society of America, Purdue Chapter (ASA)\nActive Minds at Purdue University\nActuarial Club (PAC)\nView Recommended Organizations\nManage Your Interests";
        testcase.BaseURL = "https://boilerlink.purdue.edu/Organizations?SearchType=None&SelectedCategoryId=0&CurrentPage=XXX";
        testcase.start_keyword = "target=\"_self\">";
        testcase.end_keyword = "</a>";
        testcase.mode = 1;//r
        testcase.method = 1;//1 word by word, 2 reg
        testcase.increment_from = 1;
        testcase.increment_to = 1;
        testcase.index = 1;
        String match_key2 = "";

        
        //Test Case 1 Basic Search WordByWord
        //BoilerLink
        testing(testcase,1,"Boilerlink -r WordByWordSearch",match_key);
        //System.out.println(search_result.result_string);
        //System.out.println(match_key);

        
        // Test Case 2 Two Empty String
        testcase.start_keyword = "";
        testcase.end_keyword = "";
        testcase.BaseURL = "http://ticketvault.cu.cc/test1.html";
        try{
            match_key2 = content.open_url_file(testcase.BaseURL);
        }catch(Exception e){
            
        }
        testcase.BaseURL = "http://ticketvault.cu.cc/testXXX.html";
        testing(testcase,2,"Key1 and Key 2 Empty WordByWordSearch",match_key2);
        //System.out.println(search_result.result_string);

        
        
        // Test Case 3 Key1 is Empty
        testcase.BaseURL = "https://boilerlink.purdue.edu/Organizations?SearchType=None&SelectedCategoryId=0&CurrentPage=XXX";
        testcase.start_keyword = "";
        testcase.end_keyword = "</a>";
        testing(testcase,3,"Key1 Empty WordByWordSearch","");
        //System.out.println(search_result.result_string);

        
        // Test Case 4 Key2 is Empty
        testcase.start_keyword = "<a";
        testcase.end_keyword = "";
        testing(testcase,4,"Key2 Empty WordByWordSearch","");
        //System.out.println(search_result.result_string);

        
        //Test Case 5 Basic Search Reg Search
        //BoilerLink
        testcase.method = 2;
        testcase.start_keyword = "target=\"_self\">";
        testcase.end_keyword = "</a>";
        testing(testcase,5,"Boilerlink -r RegSearch",match_key);
        //System.out.println(search_result.result_string);

        
        // Test Case 6 Two Empty String
        testcase.start_keyword = "";
        testcase.end_keyword = "";
        testcase.BaseURL = "http://ticketvault.cu.cc/test1.html";
        try{
            match_key2 = content.open_url_file(testcase.BaseURL);
        }catch(Exception e){
            
        }
        testcase.BaseURL = "http://ticketvault.cu.cc/testXXX.html";
        testing(testcase,6,"Key1 and Key 2 Empty RegSearch",match_key2);
        //System.out.println(search_result.result_string);
        //System.out.println(match_key2);

        
        
        // Test Case 7 Key1 is Empty
        testcase.BaseURL = "https://boilerlink.purdue.edu/Organizations?SearchType=None&SelectedCategoryId=0&CurrentPage=XXX";
        testcase.start_keyword = "";
        testcase.end_keyword = "</a>";
        testing(testcase,7,"Key1 Empty RegSearch","");
        //System.out.println(search_result.result_string);

        
        // Test Case 8 Key2 is Empty
        testcase.start_keyword = "<a";
        testcase.end_keyword = "";
        testing(testcase,8,"Key2 Empty RegSearch","");
        //System.out.println(search_result.result_string);


        // Test Case 9 Test HTML Element R
        testcase.start_keyword = "<h3";
        testcase.end_keyword = "</h3>";
        testing(testcase,9,"HTML elemnt RegSearch", " id=\"aria-label-sideColumn\" class=\"sideHeading\" style=\"border:0;float:left;\">Browse Orgs");
        //System.out.println(search_result.result_string);
        
        // Test Case 10 Test HTML Element W
        testcase.method = 1;
        testcase.start_keyword = "<h3";
        testcase.end_keyword = "</h3>";
        testing(testcase,10,"HTML elemnt WordByWordSearch", " id=\"aria-label-sideColumn\" class=\"sideHeading\" style=\"border:0;float:left;\">Browse Orgs");
        //System.out.println(search_result.result_string);

        //Test Case 11 Concurrency Test W
        
        
        
        
        System.out.println("=====Passed Test Case=====");
        int i;
        for(i=0;i<count_pass;i++){
            System.out.println(pass[i]);
        }
        System.out.println("");
        System.out.println("=====Failed Test Case=====");
        if(count_fail==0){
            System.out.println("None");
        }else{
            for(i=0;i<count_fail;i++){
                System.out.println(fail[i]);
            }
        }
    }
}