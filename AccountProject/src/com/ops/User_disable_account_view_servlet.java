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
 * Servlet implementation class User_disable_account_view_servlet
 */
@WebServlet("/User_disable_account_view_servlet")
public class User_disable_account_view_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public User_disable_account_view_servlet() {
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
		 String ademail=(String)admin.getAttribute("adname");
		 String adpass=(String)admin.getAttribute("adpass");
		
		ArrayList<User> al=new ArrayList<>();
		try {
			al=Userdao.disableuserdetails();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.println("<center><form action='Admin_servlet'>");
		 out.println("<input type='hidden' name='uname' value='"+ademail+"'>");
		 out.println("<input type='hidden' name='upass' value='"+adpass+"'>");
		out.println("<h1>User Disable Accounts</h1>");
		out.println("<table border='1'><tr><th>Account No</th><th>User Name</th><th>User Email</th><th>User Balance</th><th>Enable</th></tr>");
		for(User u:al)
		{
		out.println("<tr><td>"+u.getAccount()+"</td><td>"+u.getName()+"</td><td>"+u.getEmail()+"</td><td>"+u.getBalance()+"</td><td><a href='User_enable_servlet1?email="+u.getEmail()+"'>Enable</a></td></tr>");
		}
		out.println("</table><br><br><input type='submit' value='Your Profile'>");
		out.println("</form></center>");
		
		
	}

}
