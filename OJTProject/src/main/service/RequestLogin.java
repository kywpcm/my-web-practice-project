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
 * 로그인 요청 서블릿 클래스
 * @author KWONYW
 */
@WebServlet("/request_login.do")
public class RequestLogin extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	// TODO : 기본 자바 로거 → 외부 라이브러리
	private Logger logger = Logger.getLogger("RequestLogin");
	
	private String RETURN_MSG = "";
	private String ERROR_MSG = "";
	
	private Connection conn = null;
	private PreparedStatement pst = null;
	private ResultSet rstset = null;
	private String sql = null;
	


	/**
	 * 서블릿 생성자
	 * @see HttpServlet#HttpServlet()
	 */
	public RequestLogin() {
		super();

		// 테스트
		logger.log(Level.OFF, "RequestLogin 생성자");

		// TODO : DB connection → WAS 기동시
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
	 * 로그인 요청 처리
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		logger.log(Level.OFF, "doPost()");
		
		
		// 요청 쪽의 문자 인코딩 방식 설정
		request.setCharacterEncoding("UTF-8");
		// 보낼 응답 객체의 컨텐츠 마임 타입과 문자 셋 설정
		response.setContentType("text/plain; charset=utf-8");
		
		// character 단위 출력 스트림 생성
		PrintWriter toClient = response.getWriter(); // PrintWriter 클래스는 기본 자료형이나 객체를 쓰기 위한 클래스

		// 테스트
		System.out.println("affiliate : " + request.getParameter("affiliate"));
		System.out.println("id : " + request.getParameter("id"));
		System.out.println("password : " + request.getParameter("password"));
		
		String affiliate = request.getParameter("affiliate");
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		JSONObject jObj = new JSONObject();
		
		/****************************************** DB access ********************************************/
		try {
			
			// TODO : ACCESS 로그..?
			// TODO : 3회 초과 로그인 실패시 로그인 제한 (특정 시간 이후 해제) → 이건 그냥 pass 하는게 나을 듯...
			
			java.util.concurrent.Future<?> a = null;
			
			// 1. 아이디 등록 여부 확인
			/* 아이디 등록 여부 조회 */
			sql = "SELECT IF(COUNT(USER_ID) > 0, 'Y', 'N') AS USER_EXIST_YN FROM USERS WHERE AFFILIATE_CD = ? AND USER_ID = ?";
			pst = conn.prepareStatement(sql);
			
			// 쿼리 파라미터 설정
			pst.setString(1, affiliate);
			pst.setString(2, id);
			
			// 쿼리 수행
			rstset = pst.executeQuery();
			
			if (rstset.next() && "Y".equals(rstset.getString("USER_EXIST_YN"))) {
				
				// 2. 아이디에 해당하는 비밀번호 확인
				/* 해당 아이디에 대한 비밀번호 일치 여부 조회 */
				sql = "SELECT IF(COUNT(USER_ID) > 0, 'Y', 'N') AS PWD_MATCH_YN"
					+ " FROM USERS"
					+ " WHERE"
					+ " AFFILIATE_CD = ?"
				    + " AND USER_ID = ?"
				    + " AND USER_PW = PASSWORD(?)";
				pst = conn.prepareStatement(sql);
				
				// 쿼리 파라미터 설정
				pst.setString(1, affiliate);
				pst.setString(2, id);
				pst.setString(3, password);
				
				// 쿼리 수행
				rstset = pst.executeQuery();
				
				if (rstset.next() && "Y".equals(rstset.getString("PWD_MATCH_YN"))) {
					RETURN_MSG = "true";
					jObj.put("RETURN_MSG", RETURN_MSG);
				} else {
					RETURN_MSG = "비밀번호가 일치하지 않습니다.";
					jObj.put("RETURN_MSG", RETURN_MSG);
				}
				
			} else {
				RETURN_MSG = "일치하는 ID가 없습니다.";
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
		
		toClient.println(jObj.toJSONString()); // 그냥 text. 그래서 클라이언트가 받는 데이터는 모두 String
		toClient.flush();
		toClient.close();
		
	}

}
