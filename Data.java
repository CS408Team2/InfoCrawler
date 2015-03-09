import java.sql.*;
public class Data{
    
    
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    private String DB_URL = "jdbc:mysql://localhost/STUDENTS";
    private String USER = "username";
    private String PASS = "password";
  
  
  
  public Connection start(){

   
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
          }
          
          
          return conn;
    }//end of Start method
    
    public void store(String [] res){
        Connection conn=start();
        System.out.println("Inserting records into the table");
        stmt = conn.createStatement();
        try{
            for (int i=0;i<res.length;i++){
           
                String sql = "INSERT INTO Results VALUES('"+res[i]+"')";
                stmt.executeUpdate(sql);
            
            }
        }
        catch(SQLException se){
            se.printStackTrace;
        } catch(Exception e){
            e.printStackTrace();
        }
        
    }
   
   

}
