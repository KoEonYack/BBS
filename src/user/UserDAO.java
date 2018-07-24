package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class UserDAO {

	private Connection conn; 		 // Database에 접근하게 해주는 객체
	private PreparedStatement pstmt; // 
	private ResultSet rs; 			 // 어떤 정보를 담을 수 있는 객체
	
	public UserDAO() { // 실제 mysql에 접근하게 하는 것
		try {
			String dbURL = "jdbc:mysql://localhost:3307";
			String dbID = "root";
			String dbPassword = "est2678s";
			Class.forName("com.mysql.jdbc.Driver"); // mysql에 접속할수 있도록 하는 하나의 라이브러리
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword ); // conn 객체 안에 접속한 정보가 담기게 된다.
		}catch(Exception e) {
			e.printStackTrace(); 	// 오류 출력하는 것
		}
	}
	
	public int login(String userID,String userPassword) {
		String SQL = "SELECT userPassword FROM USER WHERE UserID = ?"; // SQL 인젝셜을 방지하기 위함이다.
		// 하나의 문장을 미리 준비하고 ?에 해당하는 내용을 user ID에 넣은 것이다. 
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if(rs.getString(1).equals(userPassword)) { // 접속을 시도한 유저 password와 같다면 
					return 1; // 로그인 성공
				}
				else {
					return 0; // 비밀번호 불일치
				}
			}
			return -1; // 존재하는 아이디가 없다
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return -2; // 데이터베이스 오류
	}
	
	// 회원가입 시도를 하는 것이다. 
	public int join(User user) { // user 클래스를 이용해서 만들 수 있는 인스턴스
		String SQL = "INSERT INTO USER VALUES (?, ?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1,  user.getUserID());
			pstmt.setString(2,  user.getUserPassword());
			pstmt.setString(3,  user.getUserName());
			pstmt.setString(4,  user.getUserGender());
			pstmt.setString(5,  user.getUserEmail());
			return pstmt.executeUpdate(); // 0 이상의 수가 반환된다. 
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1; // 데이터베이스 오류
	}
	
	
}
