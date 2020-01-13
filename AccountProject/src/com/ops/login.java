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
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		String name=req.getParameter("uname");
		String pass=req.getParameter("upass");
		
	     HttpSession admin=req.getSession();
	     admin.setAttribute("adname", name);
	     admin.setAttribute("adpass", pass);
	     
	     
		
		PrintWriter out=res.getWriter();
		User u=new User();
		u.setEmail(name);
		u.setPassword(pass);
	    String status="";
		try {
		  status=Userdao.confirm(u);
		  
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		if(status.equals("Disable"))
		{
			out.println("<h3>Your Account is Disable</h3>");
			RequestDispatcher rd=req.getRequestDispatcher("loginpage.html");
			rd.include(req, res);
			
		}
		else if(status.equals("admin"))
		{
            out.println("successfull Login");
            RequestDispatcher rd=req.getRequestDispatcher("Admin_servlet");
			rd.include(req, res);
            
			
		}
		else if(status.equals("user"))
		{
			out.println("successfull login");
			RequestDispatcher rd=req.getRequestDispatcher("Userlogin");
			rd.include(req, res);
		}
		else
		{
			out.println("Unsuccessfull login");
			RequestDispatcher rd=req.getRequestDispatcher("loginpage.html");
			rd.include(req, res);
		}
		
		
	}

}
