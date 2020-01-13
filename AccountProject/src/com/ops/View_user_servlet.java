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
 * Servlet implementation class View_user_servlet
 */
@WebServlet("/View_user_servlet")
public class View_user_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public View_user_servlet() {
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
			al=Userdao.alluserdetails();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 out.println("<center><form action='Admin_servlet'>");
		 out.println("<input type='hidden' name='uname' value='"+ademail+"'>");
		 out.println("<input type='hidden' name='upass' value='"+adpass+"'>");
		out.println("<table border='1'>");
		out.println("<tr><th>Account No</th><th>User Name</th><th>User Email</th><th>User Bdate</th><th>User Password</th><th>User Balance</th><th>Role id</th><th>Active status</th><th>View Payee</th><th>View Transaction</th><th>Disable</th><th>Enable</th><th>Set Admin</th><th>Remove Admin</th></tr>");
		for(User u:al)
		{
			out.println("<tr><td>"+u.getAccount()+"</td><td>"+u.getName()+"</td><td>"+u.getEmail()+"</td><td>"+u.getBdate()+"</td><td>"+u.getPassword()+"</td><td>"+u.getBalance()+"</td><td>"+u.getRid()+"</td><td>"+u.getActive()+"</td><td><a href='Admin_user_payee_servlet?email="+u.getEmail()+"'>View payee</a></td><td><a href='Admin_user_transaction_servlet?email="+u.getEmail()+"'>View Transaction</a></td><td><a href='User_disable_servlet?email="+u.getEmail()+"'>Disable</a></td><td><a href='User_enable_servlet?email="+u.getEmail()+"'>Enable</a></td><td><a href='set_admin_servle?email="+u.getEmail()+"'>Set Admin</a></td><td><a href='remove_admin_servlet?email="+u.getEmail()+"'>Remove Admin</a></td></tr>");
		}
		out.println("</table><br><br><input type='submit' value='Your Profile'>");
		out.println("</form></center></body>");
		
		
	}

}
