package main.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 2017.03.14
 * DB Ŀ�ؼ� ���� Ŭ����
 * @author KWONYW
 */
public class DBConnManager {

	private static Logger logger = Logger.getLogger("DBConnManager");
	
	// ��� ����
	private final String DB_URL =
			"jdbc:mysql://211.109.150.113:13306/for_new_workers?autoReconnect=true"; // �����ͺ��̽� �̸��� ������ ��ü DB ���  
	private final String DB_USER = "kywpcm";
	private final String DB_PASSWORD = "y98122514w";

	private Connection conn = null;

	// static���� ����̹� �ε�..
	static {
		try{
			Class.forName("com.mysql.jdbc.Driver");
		} catch(ClassNotFoundException cnfe) {
			System.out.println("mysql jdbc ����̹� �ε� �� ���� �߻� : " + cnfe.toString());
			System.out.flush();
		}
	}

	// �̱��� ����..
	private static DBConnManager instance;
	public static DBConnManager getInstance() {
		if (instance == null) {
	        logger.log(Level.INFO, "DBConnManager instance null !!!");
			instance = new DBConnManager();
		}
		return instance;
	}

	// ������
	private DBConnManager() {
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Connection ��ü ��������
	public Connection getConnection() {
		return this.conn;
	}

}
