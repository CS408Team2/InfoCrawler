class SearchSetting {
    public String BaseURL;
    public String start_keyword;
    public String end_keyword;
    public HTMLElement html_keyword;
    public int mode = 0;
    public int method = 0;
    public int increment_from = 0;
    public int increment_to = 0;
    public int increment_times = 0;
    public int time_interval = 0;
    public boolean notification_select = false;
    public boolean html_element_select = false;
    public String notification_email;
    public String job_name;
    
    public String get_BaseURL() {
        return BaseURL;
    }
    public String get_start_keyword() {
        return start_keyword;
    }
    public String get_ned_keyword() {
        return end_keyword;
    }
    public int get_mode() {
        return mode;
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
    public boolean get_notification_select() {
        return notification_select;
    }
    public String get_notification_email() {
        return notification_email;
    }
    public String get_job_name() {
        return job_name;
    }
}