import java.sql.*;
public class Data{
    
    
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    private String DB_URL = "jdbc:mysql://localhost/STUDENTS";
    private String USER = "username";
    private String PASS = "password";
  
  
    public void set(String a,String b,String c){
        DB_URL=a;
        USER=b;
        PASS=c;
    }
  
  
  public void start(){

   
         Connection conn = null;
         try{
         //STEP 2: Register JDBC driver
              Class.forName("com.mysql.jdbc.Driver");
      
         //STEP 3: Open a connection
              conn = DriverManager.getConnection(DB_URL, USER, PASS);
          }catch(SQLException se){
                 se.printStackTrace();
          }catch(Exception e){
                e.printStackTrace();
          }finally{
          try{
            if(conn!=null)
              conn.close();
             }catch(SQLException se){
                  se.printStackTrace();
             }//end finally try
          }//end try
    }//end of Start method
   
   

}
