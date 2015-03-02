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
    }
}