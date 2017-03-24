package main.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import main.util.DBConnManager;

/**
 * 2017.03.20
 * �α��� ��û ���� Ŭ����
 * @author KWONYW
 */
@WebServlet("/request_login.do")
public class RequestLogin extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	// TODO : �⺻ �ڹ� �ΰ� �� �ܺ� ���̺귯��
	private Logger logger = Logger.getLogger("RequestLogin");
	
	private String RETURN_MSG = "";
	private String ERROR_MSG = "";
	
	private Connection conn = null;
	private PreparedStatement pst = null;
	private ResultSet rstset = null;
	private String sql = null;
	


	/**
	 * ���� ������
	 * @see HttpServlet#HttpServlet()
	 */
	public RequestLogin() {
		super();

		// �׽�Ʈ
		logger.log(Level.OFF, "RequestLogin ������");

		// TODO : DB connection �� WAS �⵿��
		conn = DBConnManager.getInstance().getConnection();
	}

	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * �α��� ��û ó��
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		logger.log(Level.OFF, "doPost()");
		
		
		// ��û ���� ���� ���ڵ� ��� ����
		request.setCharacterEncoding("UTF-8");
		// ���� ���� ��ü�� ������ ���� Ÿ�԰� ���� �� ����
		response.setContentType("text/plain; charset=utf-8");
		
		// character ���� ��� ��Ʈ�� ����
		PrintWriter toClient = response.getWriter(); // PrintWriter Ŭ������ �⺻ �ڷ����̳� ��ü�� ���� ���� Ŭ����

		// �׽�Ʈ
		System.out.println("affiliate : " + request.getParameter("affiliate"));
		System.out.println("id : " + request.getParameter("id"));
		System.out.println("password : " + request.getParameter("password"));
		
		String affiliate = request.getParameter("affiliate");
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		JSONObject jObj = new JSONObject();
		
		/****************************************** DB access ********************************************/
		try {
			
			// TODO : ACCESS �α�..?
			// TODO : 3ȸ �ʰ� �α��� ���н� �α��� ���� (Ư�� �ð� ���� ����) �� �̰� �׳� pass �ϴ°� ���� ��...
			
			java.util.concurrent.Future<?> a = null;
			
			// 1. ���̵� ��� ���� Ȯ��
			/* ���̵� ��� ���� ��ȸ */
			sql = "SELECT IF(COUNT(USER_ID) > 0, 'Y', 'N') AS USER_EXIST_YN FROM USERS WHERE AFFILIATE_CD = ? AND USER_ID = ?";
			pst = conn.prepareStatement(sql);
			
			// ���� �Ķ���� ����
			pst.setString(1, affiliate);
			pst.setString(2, id);
			
			// ���� ����
			rstset = pst.executeQuery();
			
			if (rstset.next() && "Y".equals(rstset.getString("USER_EXIST_YN"))) {
				
				// 2. ���̵� �ش��ϴ� ��й�ȣ Ȯ��
				/* �ش� ���̵� ���� ��й�ȣ ��ġ ���� ��ȸ */
				sql = "SELECT IF(COUNT(USER_ID) > 0, 'Y', 'N') AS PWD_MATCH_YN"
					+ " FROM USERS"
					+ " WHERE"
					+ " AFFILIATE_CD = ?"
				    + " AND USER_ID = ?"
				    + " AND USER_PW = PASSWORD(?)";
				pst = conn.prepareStatement(sql);
				
				// ���� �Ķ���� ����
				pst.setString(1, affiliate);
				pst.setString(2, id);
				pst.setString(3, password);
				
				// ���� ����
				rstset = pst.executeQuery();
				
				if (rstset.next() && "Y".equals(rstset.getString("PWD_MATCH_YN"))) {
					RETURN_MSG = "true";
					jObj.put("RETURN_MSG", RETURN_MSG);
				} else {
					RETURN_MSG = "��й�ȣ�� ��ġ���� �ʽ��ϴ�.";
					jObj.put("RETURN_MSG", RETURN_MSG);
				}
				
			} else {
				RETURN_MSG = "��ġ�ϴ� ID�� �����ϴ�.";
				jObj.put("RETURN_MSG", RETURN_MSG);
			}
			
		} catch (SQLException sqle) {
			System.out.println(sqle.toString());
			
			ERROR_MSG = sqle.toString();
			
			JSONObject errorJObj = new JSONObject();
			errorJObj.put("ERROR_MSG", ERROR_MSG);
			
			toClient.println(errorJObj.toJSONString());
			toClient.flush();
			toClient.close();
			
			return;
			
		} finally {
			if (rstset != null) {
				try {
					rstset.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		/***********************************************************************************************/
		
		toClient.println(jObj.toJSONString()); // �׳� text. �׷��� Ŭ���̾�Ʈ�� �޴� �����ʹ� ��� String
		toClient.flush();
		toClient.close();
		
	}

}
