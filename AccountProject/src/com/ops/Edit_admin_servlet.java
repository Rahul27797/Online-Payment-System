package com.ops;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Edit_admin_servlet
 */
@WebServlet("/Edit_admin_servlet")
public class Edit_admin_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Edit_admin_servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out=res.getWriter();
		res.setContentType("text/html");
		int acctno=Integer.parseInt(req.getParameter("acctno"));
		String name=req.getParameter("name");
		String email=req.getParameter("email");
		String bdate=req.getParameter("bdate");
		String pass=req.getParameter("pass");
		String balance=req.getParameter("balance");
		User u=new User();
		u.setAccount(acctno);
		u.setName(name);
		u.setEmail(email);
		u.setBdate(bdate);
		u.setPassword(pass);
		u.setBalance(balance);
		
		int status=0;
		try {
			status=Userdao. updateuserinfo(u);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 HttpSession admin=req.getSession();
		 String ademail=(String)admin.getAttribute("adname");
		 String adpass=(String)admin.getAttribute("adpass");
		
			
		if(status>0)
		{
			out.println("<h1>successfully update</h1>");
			out.println("<form action='Admin_servlet'>");
			out.println("<input type='hidden' name='uname' value='"+ademail+"'>");
			out.println("<input type='hidden' name='upass' value='"+adpass+"'>");
			out.println("<input type='submit' value='Your Profile'>");
			out.println("</form></body>");
		}
		else
		{
			out.println("Unsuccessfully update");
			RequestDispatcher rd=req.getRequestDispatcher("Admin_Profile");
			rd.include(req, res);
			
			
		}
		
		
	}

}
