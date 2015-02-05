class SearchSetting {
    public String BaseURL;
    public String start_keyword;
    public String end_keyword;
    public int mode = 0;
    public int increment_from = 0;
    public int increment_to = 0;
    public int increment_times = 0;
    public int time_interval = 0;
    public boolean notification_select = false;
    public String get_BaseURL() {
        return BaseURL;
    }
    public int get_increment_from() {
        return increment_from;
    }
    public int get_increment_to() {
        return increment_to;
    }
    public int get_increment_times() {
        return increment_times;
    }
    public int get_time_interval() {
        return time_interval;
    }
}