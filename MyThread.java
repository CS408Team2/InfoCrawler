public class MyThread extends Thread {
    SearchSetting set;
    int job_no = 0;
    public MyThread(SearchSetting s, int i) {
        // store parameter for later user
        set = s;
        job_no = i;
    }
    public void run() {
        Search s = new Search();
        s.search(set);
    }
    
}