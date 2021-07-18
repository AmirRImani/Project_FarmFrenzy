package view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Database {
    public static void post(String var1,String var2) throws Exception{
        try{
            Connection con = getConnection();
            PreparedStatement posted=con.prepareStatement("INSERT INTO farmfenzy3 ( first,last) VALUE ('"+var1+"','"+var2+"')");
            posted.executeUpdate();}
        catch (Exception e){
            System.out.println(e);
        }
        finally {
            System.out.println("Insert Completed.");
        }

    }
    public static void createTable() throws Exception{
        try{
            Connection con=getConnection();
            PreparedStatement create= con.prepareStatement ("CREATE TABLE IF NOT EXSISTS tablename(id int NOT NULL AUTO_INCREMENT, first varchar(255),last varchar(255)) ");
            create.executeUpdate();
        }catch (Exception e){
            System.out.println(e);
        }
        finally {
            System.out.println("Function complete.");
        }
    }
    public static Connection getConnection() throws Exception{
        try{
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost/farmfenzy3";
            String username = "farmfenzy3";
            String password = "farmfenzy3";
            Class.forName(driver);

            Connection conn = DriverManager.getConnection(url,username,password);
            System.out.println("Connected");
            return conn;
        } catch(Exception e){System.out.println(e);}


        return null;
    }
}
