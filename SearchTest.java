public class SearchTest{
    public static void main(String[] args) {
        SearchSetting testcase = new SearchSetting();
        testcase.BaseURL = "https://boilerlink.purdue.edu/Organizations?SearchType=None&SelectedCategoryId=0&CurrentPage=XXX";
        testcase.start_keyword = "target=\"_self\">";
        testcase.end_keyword = "</a>";
        testcase.mode = 1;
        //r
        testcase.method = 1;
        //1 word by word, 2 reg
        testcase.increment_from = 0;
        testcase.increment_to = 1;
        System.out.println("TestCase1:Boilerlink -w");
        Search search_func = new Search();
        try{
            search_func.search(testcase);
            System.out.println("TestCase1:Pass");
        }catch(Exception e){
            System.out.println("TestCase1:Fail");
        }
        
        testcase.method = 2;
        System.out.println("TestCase2:Boilerlink -r");
        try{
            search_func.search(testcase);
            System.out.println("TestCase2:Pass");
        }catch(Exception e){
            System.out.println("TestCase2:Fail");
        }
        
        // Test cast3
        System.out.println("TestCase3:key1 and key 2 empty");
        testcase.start_keyword = "";
        testcase.end_keyword = "";
        try{
            search_func.search(testcase);
            System.out.println("TestCase3:Pass");
        }catch(Exception e){
            System.out.println("TestCase3:Fail");
        }
        
        // Test case 4, testcast not working
        System.out.println("TestCase4:key1 is empty");
        testcase.start_keyword = "";
        testcase.end_keyword = "</a>";
        try{
            if(search_func.search(testcase) == null){
                System.out.println("TestCase4:Pass");
            }else{
                System.out.println("TestCase4:Fail");
            }
        }catch(Exception e){
            System.out.println("TestCase4:Fail : excepition");
        }
    }
}