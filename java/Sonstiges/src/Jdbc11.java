import java.sql.*;

public class Jdbc11 {
  public static void main(String args[]){
    System.out.println(
                  "Copyright 2004, R.G.Baldwin");
    try {
      Statement stmt;

      //Register the JDBC driver for MySQL.
      Class.forName("com.mysql.jdbc.Driver");

      //Define URL of database server for
      // database named mysql on the localhost
      // with the default port number 3306.
      String url =
            "jdbc:mysql://localhost:3306/mysql";

      //Get a connection to the database for a
      // user named root with a blank password.
      // This user is the default administrator
      // having full privileges to do anything.
      Connection con =
                     DriverManager.getConnection(
                                 url,"root", "");

      //Display URL and connection information
      System.out.println("URL: " + url);
      System.out.println("Connection: " + con);

      //Get a Statement object
      stmt = con.createStatement();

      //Create the new database
      stmt.executeUpdate("CREATE DATABASE JunkDB");
      //Register a new user named auser on the
      // database named JunkDB with a password
      // drowssap enabling several different
      // privileges.
      stmt.executeUpdate(
          "GRANT SELECT,INSERT,UPDATE,DELETE," +
          "CREATE,DROP " +
          "ON JunkDB.* TO 'auser'@'localhost' " +
          "IDENTIFIED BY 'drowssap';");
      con.close();
    }catch( Exception e ) {
      e.printStackTrace();
    }//end catch
  }//end main
}//end class Jdbc11