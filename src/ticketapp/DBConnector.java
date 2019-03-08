

package ticketapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class  DBConnector {
             public Connection con;
             
             String DB_driver="com.mysql.jdbc.Driver";
             String DB_conn = "jdbc:mysql://localhost:8080/passengers";
             String DB_user = "root";
             String DB_password = "";
    public Connection getConnection() throws SQLException{
         try{

           Class.forName(DB_driver);
             }catch(ClassNotFoundException e){
             try{
            con = DriverManager.getConnection(DB_conn ,DB_user,DB_password); 
            return con;
           }catch(SQLException ex){
      }
       
    }
          return con;      
  }
}

