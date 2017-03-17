package main.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 2017.03.14
 * DB 커넥션 관리 클래스
 * @author KWONYW
 */
public class DBConnManager {

	private static Logger logger = Logger.getLogger("DBConnManager");
	
	// 상수 정의
	private final String DB_URL =
			"jdbc:mysql://211.109.150.113:13306/for_new_workers?autoReconnect=true"; // 데이터베이스 이름을 포함한 전체 DB 경로  
	private final String DB_USER = "kywpcm";
	private final String DB_PASSWORD = "y98122514w";

	private Connection conn = null;

	// static으로 드라이버 로드..
	static {
		try{
			Class.forName("com.mysql.jdbc.Driver");
		} catch(ClassNotFoundException cnfe) {
			System.out.println("mysql jdbc 드라이버 로드 중 문제 발생 : " + cnfe.toString());
			System.out.flush();
		}
	}

	// 싱글톤 패턴..
	private static DBConnManager instance;
	public static DBConnManager getInstance() {
		if (instance == null) {
	        logger.log(Level.INFO, "DBConnManager instance null !!!");
			instance = new DBConnManager();
		}
		return instance;
	}

	// 생성자
	private DBConnManager() {
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Connection 객체 가져오기
	public Connection getConnection() {
		return this.conn;
	}

}
