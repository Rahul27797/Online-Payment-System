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
 * Servlet implementation class Edit_user_servlet
 */
@WebServlet("/Edit_user_servlet")
public class Edit_user_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Edit_user_servlet() {
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
		 HttpSession session=req.getSession();
			String username=(String)session.getAttribute("username");
			String useremail=(String)session.getAttribute("useremail");
			
		if(status>0)
		{
			out.println("<h1>successfully update</h1>");
			out.println("<form action='Userlogin'>");
			out.println("<input type='hidden' name='uname' value='"+username+"'>");
			out.println("<input type='hidden' name='upass' value='"+useremail+"'>");
			out.println("<input type='submit' value='Your Profile'>");
			out.println("</form></body>");
		}
		else
		{
			out.println("Unsuccessfully update");
			RequestDispatcher rd=req.getRequestDispatcher("Update_user_servlet");
			rd.include(req, res);
			
			
		}
		
		
		
		
	}

}
