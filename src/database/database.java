
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author User
 */
public class database {
       Connection con = null;
//       String host="sql10.freesqldatabase.com";
//       String dbname="sql10438068";
//       String user="sql10438068";
//       String pass="rXPEWtFrs6";
       String host="localhost";
       String dbname="db_tfc_ecole";
       String user="root";
       String pass="";
    public Connection getcon (){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://"+host+":3306/"+dbname,user,pass);
//                        con = DriverManager.getConnection("jdbc:mysql://localhost/db_tfc_stage","root","");
//
            System.out.println("connection reusssi");
        }catch(ClassNotFoundException | SQLException e){
        
         System.out.println("connection echouee "+e.getMessage());
        }
        return con;
    }
}
