package com.ops;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Admin_servlet
 */
@WebServlet("/Admin_servlet")
public class Admin_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin_servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out=res.getWriter();
		res.setContentType("text/html");
		HttpSession admin=req.getSession();
		String email=(String)admin.getAttribute("adname");
		String pass=(String)admin.getAttribute("adpass");
		
		
		 User u = new User();
		 try {
			 u=Userdao.user(email,pass);
		} catch (Exception e) {
			// TODO Auto-generated catch block
		System.out.println(e);
		}
		
		
		
		out.println("<h1>Hiii Admin</h1>");
		out.println("<a href='View_user_servlet'>View User</a>&nbsp;&nbsp;&nbsp;<a href='Admin_transaction_view'>View Transaction</a>&nbsp;&nbsp;&nbsp;<a href='User_disable_account_view_servlet'>Disable Account</a>&nbsp;&nbsp;&nbsp;<a href='Admin_Profile'>My Profile</a>&nbsp;&nbsp;&nbsp;<a href='Home.html'>Log Out</a>");
		out.println("<h3>Welcome   "+u.getName()+"</h3>");
		out.println("<h3>Your Account No   "+u.getAccount()+"</h3>");
		out.println("<h3>Your Balance    "+u.getBalance()+"</h3>");
		
	}

}
