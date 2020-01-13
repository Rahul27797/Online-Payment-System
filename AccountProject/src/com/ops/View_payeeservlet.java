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
 * Servlet implementation class View_payeeservlet
 */
@WebServlet("/View_payeeservlet")
public class View_payeeservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public View_payeeservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out=res.getWriter();
		res.setContentType("text/html");
		String myemail=req.getParameter("email");
		
		
	    ArrayList<User> al=new ArrayList<>();
	     try {
			al=Userdao.payeelist(myemail);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    HttpSession session1=req.getSession();
	    session1.setAttribute("myemail", myemail);
	    
	   
	     out.println("<table border='1'><tr><th>Name</th><th>Account No</th><th>Delete</th><th>Pay</th></tr>");
	     for(User u:al)
	     {
	    	 out.println("<tr><td>"+u.getName()+"</td><td>"+u.getAccount()+"</td><td><a href='Delete_Payee_servlet?payeemail="+u.getEmail()+"'>Delete</a></td><td><a href='payment_servlet?payeemail="+u.getEmail()+"'>Pay</a></td></tr>");
	     }
	     out.println("</form></body>");
		
		
	}

}
