import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConn {

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception e) {
			System.out.println("드라이브 로딩 에러" + e.getMessage());
		}
	}
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public DBConn() {
	}
	
//	연결
	public void getConn() {
		try {
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "pcproject", "1234");
		}catch(Exception e) {
			System.out.println("DB 에러" + e.getMessage());
		}
	}

//	닫기
	public void getClose() {
		try {
			if(rs != null)rs.close();
			if(pstmt != null)pstmt.close();
			if(conn != null)conn.close();
		}catch(Exception e) {
			System.out.println("DB닫기 에러" + e.getMessage());
		}
	}
	
}
