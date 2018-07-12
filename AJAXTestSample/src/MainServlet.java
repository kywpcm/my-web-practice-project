

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/send_alias.do")
public class MainServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MainServlet() {
		super();
	}

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		super.service(arg0, arg1);

		System.out.println("Servlet service 함수");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("GET send Servlet");

		//요청 정보에 대한 문자셋 설정
		request.setCharacterEncoding("euc-kr");
		//클라이언트로부터 전송된 데이터 얻어옴
		String num = request.getParameter("inputNum");

		// 제곱 계산
		String rsltNum = String.valueOf(Integer.parseInt(num) * Integer.parseInt(num));

		//다음 줄은 한글입력을 처리하기위한 코드이다.
		//클라이언트의 응답할 때의 문서타입과 문자셋 설정
		response.setContentType("text/html; charset=euc-kr");
		//클라이언트의 웹브라우저로 출력하기 위한 스트림 객체 얻어오기
		PrintWriter pw = response.getWriter();

//		pw.println("<html><head><title>나의 첫번째 페이지</title></head>");
//		pw.println("<body>");           
//		pw.println("<font size='5pt'>");
//		//아래와같이 한글을 입력하면 클라이언트의 웹브라우져에서 글자가
//		//깨져서 나온다.            
//		pw.println("제곱값 : " + rsltNum);
//		pw.println("</font>");
//		pw.println("</body>");
//		pw.println("<html>");

		pw.println("제곱값 : " + rsltNum);

		pw.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("POST send Servlet");
		
//		doGet(request, response);

		//요청 정보에 대한 문자셋 설정
		request.setCharacterEncoding("euc-kr");
		//클라이언트로부터 전송된 데이터 얻어옴
		String num = request.getParameter("inputNum");

		// 제곱 계산
		String rsltNum = String.valueOf(Integer.parseInt(num) * Integer.parseInt(num));

		//다음 줄은 한글입력을 처리하기위한 코드이다.
		//클라이언트의 응답할 때의 문서타입과 문자셋 설정
		response.setContentType("text/plain; charset=euc-kr");
		//클라이언트의 웹브라우저로 출력하기 위한 스트림 객체 얻어오기
		PrintWriter pw = response.getWriter();

		pw.println("<html><head><title>나의 첫번째 페이지</title></head>");
		pw.println("<body>");
		pw.println("<font size='5pt'>");
		//아래와같이 한글을 입력하면 클라이언트의 웹브라우져에서 글자가
		//깨져서 나온다.
		pw.println("제곱값 : " + rsltNum);
		pw.println("</font>");
		pw.println("</body>");
		pw.println("<html>");

//		pw.println("제곱값 : " + rsltNum);

		pw.close();
	}

}
