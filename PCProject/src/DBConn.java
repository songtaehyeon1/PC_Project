import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConn {

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception e) {
			System.out.println("����̺� �ε� ����" + e.getMessage());
		}
	}
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public DBConn() {
	}
	
//	����
	public void getConn() {
		try {
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "pcproject", "1234");
		}catch(Exception e) {
			System.out.println("DB ����" + e.getMessage());
		}
	}

//	�ݱ�
	public void getClose() {
		try {
			if(rs != null)rs.close();
			if(pstmt != null)pstmt.close();
			if(conn != null)conn.close();
		}catch(Exception e) {
			System.out.println("DB�ݱ� ����" + e.getMessage());
		}
	}
	
}
