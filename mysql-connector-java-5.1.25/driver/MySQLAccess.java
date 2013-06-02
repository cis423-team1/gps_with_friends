import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class MySQLAccess {
  private Connection connect = null;
  private Statement statement = null;
  private PreparedStatement preparedStatement = null;
  private ResultSet resultSet = null;



  public void addUser(int UID, byte[] Fname, byte[] Lname, byte[] Email) throws Exception {
    try {
      // This will load the MySQL driver, each DB has its own driver
      Class.forName("com.mysql.jdbc.Driver");
      // Setup the connection with the DB
   //   connect = DriverManager.getConnection("jdbc:mysql://localhost/mydb","root,"");
     //connect = DriverManager.getConnection(  "jdbc:mysql://localhost:3306/myuser", "root", "root");

    connect = DriverManager
          .getConnection("jdbc:mysql://localhost/mydb?"
              + "user=root&password=12345");
      // Statements allow to issue SQL queries to the database

      statement = connect.createStatement();
      // Result set get the result of the SQL query
      resultSet = statement
          .executeQuery("select * from mydb.User");
      writeResultSet(resultSet);


      preparedStatement = connect
          .prepareStatement("insert into  mydb.User values (default, ?, ? , ?, ?)");
     
      preparedStatement.setString(1, UID);
      preparedStatement.setString(2, Fname);
      preparedStatement.setString(3, Lname);
      preparedStatement.setDate(4, Email);
      preparedStatement.executeUpdate();

      preparedStatement = connect
          .prepareStatement("SELECT UID , Fname, Lname, Email from mydb.User");
      resultSet = preparedStatement.executeQuery();
      writeResultSet(resultSet);

      
    } catch (Exception e) {
      throw e;
    } finally {
      close();
    }

  }
  public delete(int UID) {
    
      // Remove 
      preparedStatement = connect
      .prepareStatement("delete from mydb.User where UID= ? ; ");
      preparedStatement.setString(1, UID);
      preparedStatement.executeUpdate();
      
      resultSet = statement
      .executeQuery("select * from mydb.UID");
      writeMetaData(resultSet);



   }

  private void writeMetaData(ResultSet resultSet) throws SQLException {
    //   Now get some metadata from the database
    // Result set get the result of the SQL query
    
    System.out.println("The columns in the table are: ");
    
    System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
    for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
      System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
    }
  }


private void writeResultSet(ResultSet resultSet) throws SQLException {
    // ResultSet is initially before the first data set
    while (resultSet.next()) {
      // It is possible to get the columns via name
      // also possible to get the columns via the column number
      // which starts at 1
      // e.g. resultSet.getSTring(2);
      String uid= resultSet.getString("UID");
      String fname= resultSet.getString("Fname");
      String lname= resultSet.getString("Lname");
      String email= resultSet.getString("Email");
      System.out.println("UID: " + uid);
      System.out.println("Fname: " + fname);
      System.out.println("Lname: " + lname);
      System.out.println("Email: " + email);
    }
  }




  // You need to close the resultSet
  private void close() {
    try {
      if (resultSet != null) {
        resultSet.close();
      }

      if (statement != null) {
        statement.close();
      }

      if (connect != null) {
        connect.close();
      }
    } catch (Exception e) {

    }
  }

} 