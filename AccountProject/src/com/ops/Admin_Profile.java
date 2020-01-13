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
 * Servlet implementation class Admin_Profile
 */
@WebServlet("/Admin_Profile")
public class Admin_Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin_Profile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		     PrintWriter out=res.getWriter();
		     res.setContentType("text/html");
		     out.println("<h3>Update Your Profile</h3>");
		     HttpSession admin=req.getSession();
			 String email=(String)admin.getAttribute("adname");
			 String pass=(String)admin.getAttribute("adpass");
		     
		    User u=new User();
		    
		    try {
				u=Userdao.user(email, pass);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		    out.println("<body><br><h3>Your Account No-"+u.getAccount()+"</h3><br><h3>Your Balance-"+u.getBalance()+"</h3><form action='Edit_admin_servlet'>");
			out.println("<br><input type='hidden' name='acctno' value='"+u.getAccount()+"'>");
			out.println("<br><br>Name : <input type='text' name='name' value='"+u.getName()+"'>");
			out.println("<br><br>Email : <input type='text' name='email' value='"+u.getEmail()+"'>");
			out.println("<br><br>Birth Date : <input type='text' name='bdate' value='"+u.getBdate()+"'>");
			out.println("<br><br>Password : <input type='text' name='pass' value='"+u.getPassword()+"'>");
			out.println("<br><input type='hidden' name='balance' value='"+u.getBalance()+"'>");
			out.println("<br><input type='submit' value='Save'>");
			out.println("</form></body>");
		
	}

}
