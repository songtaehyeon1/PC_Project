package member;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO extends DBConn{

	public MemberDAO() {
	}

	public static MemberDAO getInstance() {
		return new MemberDAO();
	}
	
//	등록
	public int memberInsert(MemberVO vo) {
		int cnt = 0;
		try {
			getConn();
			String query = "insert into member(name, id, pw, human_num, birth, gender, tel, email)"
					+ " values(?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getId());
			pstmt.setString(3, vo.getPw());
			pstmt.setString(4, vo.getHumannum());
			pstmt.setInt(5, vo.getBirth());
			pstmt.setString(6, vo.getGender());
			pstmt.setString(7, vo.getTel());
			pstmt.setString(8, vo.getEmail());
			cnt = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getClose();
		}
		return cnt;
	}
	
//	수정
	public int updateRecord(MemberVO vo) {
		int cnt = 0;
		try {
			getConn();
			String query = "update member set name = ?, pw = ?, human_num = ?, birth = ?, "
					+ "gender = ?, tel = ?, email = ?, rest_time = ?, money_temp = ? where id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPw());
			pstmt.setString(3, vo.getHumannum());
			pstmt.setInt(4, vo.getBirth());
			pstmt.setString(5, vo.getGender());
			pstmt.setString(6, vo.getTel());
			pstmt.setString(7, vo.getEmail());
			pstmt.setInt(8, vo.getResttime());
			pstmt.setInt(9, vo.getMoneytemp());
			pstmt.setString(10, vo.getId());
			cnt = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getClose();
		}
		return cnt;
	}
	
//	삭제
	public int deleteRecord(String no) {
		int cnt = 0;
		try {
			getConn();
			String query = "delete from member where id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, no);
			cnt = pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("삭제 실패");
		}finally {
			getClose();
		}
		return cnt;
	}
	
//	선택
	public List<MemberVO> selectAllRecord() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		try {
//			DB연결
			getConn();
			
			String query = "select name, id, pw, human_num, birth, gender, tel, "
					+ "email, rest_time, money_temp from member order by name";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setName(rs.getString(1));
				vo.setId(rs.getString(2));
				vo.setPw(rs.getString(3));
				vo.setHumannum(rs.getString(4));
				vo.setBirth(rs.getInt(5));
				vo.setGender(rs.getString(6));
				vo.setTel(rs.getString(7));
				vo.setEmail(rs.getString(8));
				vo.setResttime(rs.getInt(9));
				vo.setMoneytemp(rs.getInt(10));
				list.add(vo);
			}
		}catch(Exception e) {
			System.out.println();
		}finally {
			getClose();
		}
		return list;
	}
}
