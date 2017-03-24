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
 * �迭�� ��ȸ ���� Ŭ����
 * @author KWONYW
 */
@WebServlet(name = "search_affiliate.do", urlPatterns = { "/search_affiliate.do" })
public class SearchAffiliate extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	// TODO : �⺻ �ڹ� �ΰ� �� �ܺ� ���̺귯��
	private Logger logger = Logger.getLogger("SearchAffiliate");
	
	private String RETURN_MSG = "";
	private String ERROR_MSG = "";
	
	private Connection conn = null; // �̱��� �������� ��ü�� �������� ������ �� ��ü�� ��� ���� ���� �����Ѵ�.
	private Statement stmt = null;
	private ResultSet rstset = null;
	
	
	
    /**
     * ���� ������
     * @see HttpServlet#HttpServlet()
     */
    public SearchAffiliate() {
        super();
        
        // �׽�Ʈ
        logger.log(Level.OFF, "SearchAffiliate ������");
        
        // TODO : DB connection �� WAS �⵿��
        conn = DBConnManager.getInstance().getConnection();
    }

    // ������ ȣ�� �� �ٷ� ȣ��
  	// ���� �ϻ��� �� ���� ȣ��
  	@Override
  	public void init() throws ServletException {
  		super.init();
  		System.out.println("init()");
  	}

  	// ������ �ױ� ��, �� ���� ȣ��Ǹ� ������ �ִ� �ڿ��� ���ش�.
  	// ������ ���� �ð��� ������, reloading�� �����ϸ鼭 ȣ��ȴ�.
  	@Override
  	public void destroy() {
  		super.destroy();
  		System.out.println("destroy()");
  	}
  	
  	
    
	/**
	 * �迭�� ��ȸ ��û ó��
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.log(Level.OFF, "doGet()");
		
		
		// ��û ���� ���� ���ڵ� ��� ����
		request.setCharacterEncoding("UTF-8");
		// ���� ���� ��ü�� ������ ���� Ÿ�԰� ���� �� ����
		response.setContentType("text/plain;charset=utf-8"); // ������ �����ʹ� �׳� text��� ��..
		
		// character ���� ��� ��Ʈ�� ����
		PrintWriter toClient = response.getWriter(); // PrintWriter Ŭ������ �⺻ �ڷ����̳� ��ü�� ���� ���� Ŭ����
		
		JSONArray jArray = new JSONArray();
		
		/****************************************** DB access ********************************************/
		try {
			// �׽�Ʈ
			System.out.println("conn.isClosed() : " + conn.isClosed());
//			System.out.println("conn.isValid() : " + conn.isValid(30));
			
			// TODO : createStatement vs. prepareStatement
			stmt = conn.createStatement();
			rstset = stmt.executeQuery("SELECT CD, NM FROM AFFILIATE"); // ResultSet ��ü ����
			
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
			
			// �׽�Ʈ
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
		
		toClient.println(jArray.toJSONString()); // �׳� text. �׷��� Ŭ���̾�Ʈ�� �޴� �����ʹ� ��� String
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
