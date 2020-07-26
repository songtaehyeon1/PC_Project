
public class AllDAO extends DBConn{

	String dbid = null, dbpw = null, dbname = null;
	int dbrest = 0, dbmoney = 0;

	public AllDAO() {}
	
//	로그인 체크
	public boolean loginSelect(String id, String pw) {
		try {
			getConn();
			String query = "select id, pw from member where id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dbid = rs.getString(1);
				dbpw = rs.getString(2);
			}
			if(id.equals(dbid) && pw.equals(dbpw)) {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getClose();
		}
		return false;
	}
	
//	아이디 중복체크
	public boolean idcheckSelect(String id) {
		try {
			getConn();
			String query = "select id from member where id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dbid = rs.getString(1);
			}
			if(id.equals(dbid)) {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getClose();
		}
		return false;
	}
	
//	관리자 - 사용자 등록
	public void memberInsert(JoinVO vo) {
		try {
			getConn();
			String query = "insert into member values(?, ?, ?, ?, ?, ?, ?, ?, 0, 0)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getId());
			pstmt.setString(3, vo.getPw());
			pstmt.setString(4, vo.getHumannum());
			pstmt.setInt(5, vo.getBirth());
			pstmt.setString(6, vo.getGender());
			pstmt.setString(7, vo.getTel());
			pstmt.setString(8, vo.getEmail());
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getClose();
		}
	}
	
//	사용자 - 정보
	public String[] userSelect() {
		String user[] = new String[2];
		try {
			getConn();
			String query = "select * from member where id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, Login.id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				user[0] = rs.getString("id");
				user[1] = rs.getString("rest_time");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getClose();
		}
		return user;
	}
	
	public void userUpdate(ExitInfoVO infovo) {
		try {
			getConn();
			String query = "update member set rest_time = ?, money_temp = ? where id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, infovo.getResttime());
			pstmt.setInt(2, infovo.getMoneytemp());
			pstmt.setString(3, infovo.getId());
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getClose();
		}
	}
	
	public boolean infoSelect(String id) {
		try {
			getConn();
			String query = "select name, id, rest_time, money_temp from member where id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dbname = rs.getString("name");
				dbid = rs.getString("id");
				dbrest = rs.getInt("rest_time");
				dbmoney = rs.getInt("money_temp");
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getClose();
		}
		
		return false;
	}
	
	public void moneyUpdate(int money, int rest) {
		try {
			getConn();
			String query = "update member set money_temp = ?, rest_time = ? where id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, dbmoney - money);
			pstmt.setInt(2, dbrest + rest * 3600);
			pstmt.setString(3, dbid);
			pstmt.executeUpdate();
			dbmoney = dbmoney - money;
			dbrest = dbrest + rest * 3600;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getClose();
		}
	}
	
	public void seatInfo(String userid, UserVO uservo) {
		try {
			getConn();
			String query = "select * from member where id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				uservo.setName(rs.getString("name"));
				uservo.setId(rs.getString("id"));
				uservo.setRest(rs.getInt("rest_time"));
				uservo.setMoney(rs.getInt("money_temp"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getClose();
		}
	}
	
	public void exitvo(ExitInfoVO uservo) {
		try {
			getConn();
			String query = "select * from member where id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, Login.id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				uservo.setName(rs.getString("name"));
				uservo.setId(rs.getString("id"));
				uservo.setPw(rs.getString("pw"));
				uservo.setHumannum(rs.getString("human_num"));
				uservo.setBirth(rs.getInt("birth"));
				uservo.setGender(rs.getString("gender"));
				uservo.setTel(rs.getString("tel"));
				uservo.setEmail(rs.getString("email"));
				uservo.setResttime(rs.getInt("rest_time"));
				uservo.setMoneytemp(rs.getInt("money_temp"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getClose();
		}
	}

}
