package com.ops;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Admin_user_payee_servlet
 */
@WebServlet("/Admin_user_payee_servlet")
public class Admin_user_payee_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin_user_payee_servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out=res.getWriter();
		res.setContentType("text/html");
		
		String uemail=req.getParameter("email");
		
		ArrayList<User> al=new ArrayList<>();
		try {
			al=Userdao.payeelist(uemail);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpSession admin=req.getSession();
		 String ademail=(String)admin.getAttribute("adname");
		 String adpass=(String)admin.getAttribute("adpass");
		
		out.println("<form action='Admin_servlet'><table border='1'><tr><th>Name</th><th>Account No</th></tr>");
		for(User u:al)
		{
		    out.println("<tr><td>"+u.getName()+"</td><td>"+u.getAccount()+"</td>");
		}
		out.println("</table><input type='hidden' name='uname' value='"+ademail+"'>");
		out.println("<input type='hidden' name='upass' value='"+adpass+"'>");
		out.println("<br><input type='submit' value='Your Profile'>");
		out.println("</table></form></body>");
		
		
		
	}

}
