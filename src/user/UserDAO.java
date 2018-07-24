package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class UserDAO {

	private Connection conn; 		 // Database�� �����ϰ� ���ִ� ��ü
	private PreparedStatement pstmt; // 
	private ResultSet rs; 			 // � ������ ���� �� �ִ� ��ü
	
	public UserDAO() { // ���� mysql�� �����ϰ� �ϴ� ��
		try {
			String dbURL = "jdbc:mysql://localhost:3307";
			String dbID = "root";
			String dbPassword = "est2678s";
			Class.forName("com.mysql.jdbc.Driver"); // mysql�� �����Ҽ� �ֵ��� �ϴ� �ϳ��� ���̺귯��
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword ); // conn ��ü �ȿ� ������ ������ ���� �ȴ�.
		}catch(Exception e) {
			e.printStackTrace(); 	// ���� ����ϴ� ��
		}
	}
	
	public int login(String userID,String userPassword) {
		String SQL = "SELECT userPassword FROM USER WHERE UserID = ?"; // SQL �������� �����ϱ� �����̴�.
		// �ϳ��� ������ �̸� �غ��ϰ� ?�� �ش��ϴ� ������ user ID�� ���� ���̴�. 
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if(rs.getString(1).equals(userPassword)) { // ������ �õ��� ���� password�� ���ٸ� 
					return 1; // �α��� ����
				}
				else {
					return 0; // ��й�ȣ ����ġ
				}
			}
			return -1; // �����ϴ� ���̵� ����
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return -2; // �����ͺ��̽� ����
	}
	
	// ȸ������ �õ��� �ϴ� ���̴�. 
	public int join(User user) { // user Ŭ������ �̿��ؼ� ���� �� �ִ� �ν��Ͻ�
		String SQL = "INSERT INTO USER VALUES (?, ?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1,  user.getUserID());
			pstmt.setString(2,  user.getUserPassword());
			pstmt.setString(3,  user.getUserName());
			pstmt.setString(4,  user.getUserGender());
			pstmt.setString(5,  user.getUserEmail());
			return pstmt.executeUpdate(); // 0 �̻��� ���� ��ȯ�ȴ�. 
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1; // �����ͺ��̽� ����
	}
	
	
}
