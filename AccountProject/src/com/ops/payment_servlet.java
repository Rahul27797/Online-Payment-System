package com.ops;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class payment_servlet
 */
@WebServlet("/payment_servlet")
public class payment_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public payment_servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out =res.getWriter();
		res.setContentType("text/html");
		
		HttpSession session=req.getSession();
		String mybalance=(String)session.getAttribute("mybalance");
		String myemail=(String)session.getAttribute("username");
		
		String payeeemail=req.getParameter("payeemail");
        User u=new User();
		
		try {
			u=Userdao.payeedetails(payeeemail);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HttpSession deduct=req.getSession();
		deduct.setAttribute("myemail", myemail);
		deduct.setAttribute("payeeemail", payeeemail);
		
		out.println("<body><h1>Transaction Amount</h1>");
		out.println("<br><h3>Available Balance : "+mybalance+"</h3>");
		out.println("<br><h3>Payee : "+u.getName()+"</h3>");
		out.println("<form action='Transaction_status_servlet'>");
		out.println("Enter Amount To Pay : <input type='text' name='amount'><input type='submit' value='PayAmount'>");
		out.println("</form></body>");
		
		
		
		
	}

}
