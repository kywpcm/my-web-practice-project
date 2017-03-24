package main.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
 * 2017.03.14
 * 계열사 조회 서블릿 클래스
 * @author KWONYW
 */
@WebServlet(name = "search_affiliate.do", urlPatterns = { "/search_affiliate.do" })
public class SearchAffiliate extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	// TODO : 기본 자바 로거 → 외부 라이브러리
	private Logger logger = Logger.getLogger("SearchAffiliate");
	
	private String RETURN_MSG = "";
	private String ERROR_MSG = "";
	
	private Connection conn = null; // 싱글톤 패턴으로 객체를 가져오기 때문에 이 객체는 모두 같은 곳을 참조한다.
	private Statement stmt = null;
	private ResultSet rstset = null;
	
	
	
    /**
     * 서블릿 생성자
     * @see HttpServlet#HttpServlet()
     */
    public SearchAffiliate() {
        super();
        
        // 테스트
        logger.log(Level.OFF, "SearchAffiliate 생성자");
        
        // TODO : DB connection → WAS 기동시
        conn = DBConnManager.getInstance().getConnection();
    }

    // 생성자 호출 뒤 바로 호출
  	// 서블릿 일생에 한 번만 호출
  	@Override
  	public void init() throws ServletException {
  		super.init();
  		System.out.println("init()");
  	}

  	// 서블릿이 죽기 전, 한 번만 호출되며 가지고 있는 자원을 없앤다.
  	// 서블릿이 일정 시간이 지나면, reloading을 시작하면서 호출된다.
  	@Override
  	public void destroy() {
  		super.destroy();
  		System.out.println("destroy()");
  	}
  	
  	
    
	/**
	 * 계열사 조회 요청 처리
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.log(Level.OFF, "doGet()");
		
		
		// 요청 쪽의 문자 인코딩 방식 설정
		request.setCharacterEncoding("UTF-8");
		// 보낼 응답 객체의 컨텐츠 마임 타입과 문자 셋 설정
		response.setContentType("text/plain;charset=utf-8"); // 보내는 데이터는 그냥 text라는 것..
		
		// character 단위 출력 스트림 생성
		PrintWriter toClient = response.getWriter(); // PrintWriter 클래스는 기본 자료형이나 객체를 쓰기 위한 클래스
		
		JSONArray jArray = new JSONArray();
		
		/****************************************** DB access ********************************************/
		try {
			// 테스트
			System.out.println("conn.isClosed() : " + conn.isClosed());
//			System.out.println("conn.isValid() : " + conn.isValid(30));
			
			// TODO : createStatement vs. prepareStatement
			stmt = conn.createStatement();
			rstset = stmt.executeQuery("SELECT CD, NM FROM AFFILIATE"); // ResultSet 객체 리턴
			
			while (rstset.next()) {
				System.out.println(rstset.getString("CD"));
				System.out.println(rstset.getString("NM"));
				
				JSONObject jObj = new JSONObject();
				jObj.put("CD", rstset.getString("CD"));
				jObj.put("NM", rstset.getString("NM"));
				
				jArray.add(jObj);
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
			
			// 테스트
//			this.doGet(request, response);
			
		} finally {
			if (rstset != null) {
				try {
					rstset.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		/***********************************************************************************************/
		
		toClient.println(jArray.toJSONString()); // 그냥 text. 그래서 클라이언트가 받는 데이터는 모두 String
		toClient.flush();
		toClient.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
