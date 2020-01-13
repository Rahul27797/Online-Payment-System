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

/**
 * Servlet implementation class Registration
 */
@WebServlet("/new")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out=res.getWriter();
		res.setContentType("text/html");
		String name=req.getParameter("uname");
		String email=req.getParameter("uemail");
		String bdate=req.getParameter("ubdate");
		String password=req.getParameter("upass");
		String cpassword=req.getParameter("ucpass");
		String amount=req.getParameter("uamount");
		System.out.println(name);
		User u=new User();
		u.setName(name);
		u.setEmail(email);
		u.setBdate(bdate);
		u.setPassword(password);
		u.setBalance(amount);
		int status=0;
		if(cpassword.equals(password))
		{
			try {
				 status=Userdao.insert(u);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(status>0)
			{
				out.println("Successfully Register");
				RequestDispatcher rd=req.getRequestDispatcher("loginpage.html");
				rd.include(req, res);
				
			}
			else
			{
				out.println("Unsuccessfull To Register");
				RequestDispatcher rd=req.getRequestDispatcher("Newlogin.html");
				rd.include(req, res);
			}
		}
		else
		{
		
			RequestDispatcher rd=req.getRequestDispatcher("Newlogin.html");
			rd.include(req, res);
		}
		
		
				
		
	}

}
