package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCTemplate {
	
   public static final Connection getConn() throws Exception {
      Connection conn = null;
      
      String driver = "oracle.jdbc.driver.OracleDriver";
      Class.forName(driver);
      String url = "jdbc:oracle:thin:@localhost:1521:xe";
      String id = "C##TEAM03";
      String pwd = "1234";
      conn = DriverManager.getConnection(url, id, pwd);
      
      return conn;
   }
}