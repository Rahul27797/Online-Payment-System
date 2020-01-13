package com.ops;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Update_user_servlet
 */
@WebServlet("/Update_user_servlet")
public class Update_user_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update_user_servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out=res.getWriter();
		res.setContentType("text/html");
		int a=Integer.parseInt(req.getParameter("id"));
		User u=new User();
		try {
			u=Userdao.userdetails1(a);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.println("<body><br><h3>Your Account No-"+u.getAccount()+"</h3><br><h3>Your Balance-"+u.getBalance()+"</h3><form action='Edit_user_servlet'>");
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
