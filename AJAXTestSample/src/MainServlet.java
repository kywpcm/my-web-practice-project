

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

		System.out.println("Servlet service �Լ�");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("GET send Servlet");

		//��û ������ ���� ���ڼ� ����
		request.setCharacterEncoding("euc-kr");
		//Ŭ���̾�Ʈ�κ��� ���۵� ������ ����
		String num = request.getParameter("inputNum");

		// ���� ���
		String rsltNum = String.valueOf(Integer.parseInt(num) * Integer.parseInt(num));

		//���� ���� �ѱ��Է��� ó���ϱ����� �ڵ��̴�.
		//Ŭ���̾�Ʈ�� ������ ���� ����Ÿ�԰� ���ڼ� ����
		response.setContentType("text/html; charset=euc-kr");
		//Ŭ���̾�Ʈ�� ���������� ����ϱ� ���� ��Ʈ�� ��ü ������
		PrintWriter pw = response.getWriter();

//		pw.println("<html><head><title>���� ù��° ������</title></head>");
//		pw.println("<body>");           
//		pw.println("<font size='5pt'>");
//		//�Ʒ��Ͱ��� �ѱ��� �Է��ϸ� Ŭ���̾�Ʈ�� ������������ ���ڰ�
//		//������ ���´�.            
//		pw.println("������ : " + rsltNum);
//		pw.println("</font>");
//		pw.println("</body>");
//		pw.println("<html>");

		pw.println("������ : " + rsltNum);

		pw.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("POST send Servlet");
		
//		doGet(request, response);

		//��û ������ ���� ���ڼ� ����
		request.setCharacterEncoding("euc-kr");
		//Ŭ���̾�Ʈ�κ��� ���۵� ������ ����
		String num = request.getParameter("inputNum");

		// ���� ���
		String rsltNum = String.valueOf(Integer.parseInt(num) * Integer.parseInt(num));

		//���� ���� �ѱ��Է��� ó���ϱ����� �ڵ��̴�.
		//Ŭ���̾�Ʈ�� ������ ���� ����Ÿ�԰� ���ڼ� ����
		response.setContentType("text/plain; charset=euc-kr");
		//Ŭ���̾�Ʈ�� ���������� ����ϱ� ���� ��Ʈ�� ��ü ������
		PrintWriter pw = response.getWriter();

		pw.println("<html><head><title>���� ù��° ������</title></head>");
		pw.println("<body>");
		pw.println("<font size='5pt'>");
		//�Ʒ��Ͱ��� �ѱ��� �Է��ϸ� Ŭ���̾�Ʈ�� ������������ ���ڰ�
		//������ ���´�.
		pw.println("������ : " + rsltNum);
		pw.println("</font>");
		pw.println("</body>");
		pw.println("<html>");

//		pw.println("������ : " + rsltNum);

		pw.close();
	}

}
