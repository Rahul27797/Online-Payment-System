package com.ops;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Userlogin
 */
@WebServlet("/Userlogin")
public class Userlogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Userlogin() {
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

		String pass=req.getParameter("upass");
	
           User u = new User();
			 try {
				 u=Userdao.user(name,pass);
			} catch (Exception e) {
				// TODO Auto-generated catch block
			System.out.println(e);
			}
			 
			HttpSession session=req.getSession();
			session.setAttribute("username", name);
			session.setAttribute("useremail", pass);
			session.setAttribute("mybalance", u.getBalance());
			
			
		    out.println("<body>");
			out.println("<div><a href='Addpayee_servlet?email="+u.getEmail()+"'>  AddPayee  </a>&nbsp;&nbsp;&nbsp;<a href='View_payeeservlet?email="+u.getEmail()+"'>   ViewPayee  </a>&nbsp;&nbsp;&nbsp;<a href='User_transaction?email="+u.getEmail()+"'>  View Transaction  </a>&nbsp;&nbsp;&nbsp;<a href='Update_user_servlet?id="+u.getAccount()+"'>  MyProfile  </a>&nbsp;&nbsp;&nbsp;<a href='Home.html'>Log Out</a></div><br>");
			out.println("<h4> Welcome "+u.getName()+"</h4><br><br>");
			out.println("<h4>Your Acoount no  "+u.getAccount()+"</h4><br><br>");
			out.println("<h4>Your Balance is  "+u.getBalance()+"</h4>");
			out.println("</body>");
			
		
		
	}

}
