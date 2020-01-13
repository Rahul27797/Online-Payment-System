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
 * Servlet implementation class Payee_servlet
 */
@WebServlet("/Payee_servlet")
public class Payee_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Payee_servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out=res.getWriter();
		res.setContentType("text/html");
		String uemail=req.getParameter("uemail");
		String uaccount=req.getParameter("uacctno");
		String myemail=req.getParameter("myemail");
		
		HttpSession session=req.getSession();
		String username=(String)session.getAttribute("username");
		String useremail=(String)session.getAttribute("useremail");
		
		int status=0;
		
		try {
			 status=Userdao.insertpayee( myemail,uemail);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(status>0)
		{
			
			out.println("<body><h3>sucefully ADD to payee</h3>");
			out.println("<form action='Userlogin'>");
			out.println("<input type='hidden' name='uname' value='"+username+"'>");
			out.println("<input type='hidden' name='upass' value='"+useremail+"'>");
			out.println("<input type='submit' value='Your Profile'>");
			out.println("</form></body>");
			
		}
		else
		{
			out.println("Unsucefully ADD to payee");
			RequestDispatcher rd=req.getRequestDispatcher("Addpayee_servlet");
			rd.include(req, res);
			
		}
		
		
	}

}
