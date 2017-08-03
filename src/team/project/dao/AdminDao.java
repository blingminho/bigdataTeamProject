package team.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import team.project.dto.Admin;
import team.project.util.DBUtil;

public class AdminDao {
	// 싱글턴패턴
	private static AdminDao dao = new AdminDao();

	private AdminDao() {
	}

	public static AdminDao getInstance() {
		return dao;
	}

	// admin 테이블에 입력
	// admin테이블에 오늘 날짜, 시간 , 토탈 및 오늘의 펫,가입자,방문자,게시글 을 insert시킴
	// Schedule에서 주기적으로 실행함
	/**
	 * 가입자 테이블에서 오늘 가입자수 가져옴
	 * @param simple_today : 오늘날짜 2017-12-12
	 * @param simple_time : 현재 시간 12:12:12
	 * @param admin : admin객체 dto
	 * @return result : insert결과 1 이나오면 행입력된거임
	 */
	public int insertAdmin(String simple_today, String simple_time, Admin admin) throws SQLException {
		System.out.println("3. AdminDao insert()");
		Connection con = DBUtil.getConnection();
		String sql = "insert into admintest (date,time,posttotalcount,posttodaycount,visittotalcount,visittodaycount,"
				+ "puppytotalcount,puppytodaycount,regtotalcount,regtodaycount)" + " values (?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, simple_today);
		pstmt.setString(2, simple_time);
		pstmt.setInt(3, admin.getPostTotalCount());
		pstmt.setInt(4, admin.getPostTodayCount());
		pstmt.setInt(5, admin.getVisitTotalCount());
		pstmt.setInt(6, admin.getVisitTodayCount());
		pstmt.setInt(7, admin.getPuppyTotalCount());
		pstmt.setInt(8, admin.getPuppyTodayCount());
		pstmt.setInt(9, admin.getRegTotalCount());
		pstmt.setInt(10, admin.getRegTodayCount());
		int result = 0;
		result = pstmt.executeUpdate();
		System.out.println("관리자 db에 넣은 insert값 : " + result);
		return result;
	}

	// admin 테이블에서 출력 //미완 //필요한가..? 의문..
	// admin테이블에서 날짜에 맞는 토탈 및 오늘의 펫,가입자,방문자,게시글 을 select하여 가져옴
	// 별 필요 없을듯해서 미완
	public String selectAdmin() throws SQLException {
		System.out.println("3. AdminDao insert()");
		Connection con = DBUtil.getConnection();
		String sql = "select * from admintest";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = null;
		String resultdate = null;
		rs = pstmt.executeQuery();
		if (rs.next()) {
			resultdate = rs.getString("date");
			DBUtil.close(con, pstmt, rs);
			return resultdate;
		}
		return null;
	}

	/**
	 * 가입자 테이블에서 오늘 가입자수 가져옴
	 * @param simple_today : 오늘 날짜
	 * @return result : 오늘 가입자수
	 */
	public int regPrint(String simple_today) throws SQLException {
		System.out.println("3. AdminDao regPrint()");
		Connection con = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		String sql = "select count(regDt) from user where regDt=?";
		ResultSet rs = null;
		int result = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, simple_today);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt("count(regDt)");
				DBUtil.close(con, pstmt, rs);
				return result;
			} else
				DBUtil.close(con, pstmt, rs);
			return 0; //
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 * 가입자 테이블에서 총 가입자수 가져옴
	 * 
	 * @return result : 총 가입자수
	 */
	public int regAllPrint() throws SQLException {
		System.out.println("3. AdminDao regAllPrint()");
		Connection con = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		String sql = "select count(regDt) from user";
		ResultSet rs = null;
		int result = 0;
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt("count(regDt)");
				DBUtil.close(con, pstmt, rs);
				return result;
			} else
				DBUtil.close(con, pstmt, rs);
			return 0; //
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	
	/**
	 * 총 방문자수를 증가시킨다.
	 * @param simple_today : 현재 날짜
	 * @param timetoday : 현재날자 및 시간
	 * @return result : 데이터 insert결과
	 */
	// 매개변수로 현재 날짜와 날짜시간을 받아서 visit테이블에 insert 시킬때 같이 포함하여 입력함
	// 서버의 시간이 지금과 달라서 이렇게 조치함
	public int setVisitTotalCount(String simple_today, String timetoday) throws SQLException {
		System.out.println("3. AdminDao setTotalCount()");
		Connection con = null;
		PreparedStatement pstmt = null;
		// 총 방문자수를 증가시키기 위해 테이블에 현재 날짜와 시간을 추가시킨다.
		String sql = "insert into visit (visitdate,visittime) values (?,?)";
		// 커넥션을 가져온다.
		con = DBUtil.getConnection();
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, simple_today);
		pstmt.setString(2, timetoday);
		int result = 0;
		result = pstmt.executeUpdate();
		// Connection, PreparedStatement를 닫는다.
		DBUtil.close(con, pstmt);
		pstmt = null;
		con = null;
		return result;

	}// end setTotalCount()

	/**
	 * 총 방문자수를 가져온다.
	 * 
	 * @return visitTotalCount : 총 방문자 수
	 */
	public int getVisitTotalCount() {
		System.out.println("3. AdminDao getVisitTotalCount()");
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int visitTotalCount = 0;
		// 테이블의 테이터 수를 가져온다.
		// 데이터 수 = 총 방문자 수
		String sql = "select count(visitdate) as totalCount from visit";
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			// 방문자 수를 변수에 담는다.
			if (rs.next())
				visitTotalCount = rs.getInt("totalCount");
			DBUtil.close(con, pstmt, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		pstmt = null;
		con = null;
		rs = null;
		return visitTotalCount;
	}

	/**
	 * 오늘 방문자 수를 가져온다.
	 * @param simple_today : 오늘 날짜
	 * @return visitTodayCount : 오늘 방문자
	 */
	public int getVisitTodayCount(String simple_today) {
		System.out.println("3. AdminDao getVisitTodayCount()");
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int visitTodayCount = 0;
		String sql = "select count(visitdate) as todayCount from visit where visitdate=?";
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, simple_today);
			rs = pstmt.executeQuery();

			// 방문자 수를 변수에 담는다.
			if (rs.next())
				visitTodayCount = rs.getInt("todayCount");

			DBUtil.close(con, pstmt, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pstmt = null;
		con = null;
		rs = null;
		return visitTodayCount;

	}// end getTodayCount()

	// 게시판테이블에서 게시날짜의 게시글 select
	/**
	 * 오늘 게시글 수를 가져온다.
	 * @param simple_today : 오늘 날짜
	 * @return result : 오늘 게시글수
	 */
	public int postPrint(String simple_today) throws SQLException {
		System.out.println("3. AdminDao postPrint()");
		Connection con;
		PreparedStatement pstmt;
		String sql = "select count(writeDt) from post where writeDt=?";
		ResultSet rs = null;
		int result = 0;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, simple_today);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt("count(writeDt)");
				DBUtil.close(con, pstmt, rs);
				return result;
			} else
				DBUtil.close(con, pstmt, rs);
			return 0; //
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	// 게시판테이블에서 전체 게시글 select
	/**
	 * 전체 게시글 수를 가져온다.
	 * 
	 * @return result : 전체 게시글수
	 */
	public int postAllPrint() throws SQLException {
		System.out.println("3. AdminDao postAllPrint()");
		Connection con;
		PreparedStatement pstmt;
		String sql = "select count(writeDt) from post";
		ResultSet rs = null;
		int result = 0;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt("count(writeDt)");
				DBUtil.close(con, pstmt, rs);
				return result;
			} else
				DBUtil.close(con, pstmt, rs);
			return 0; //
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}
