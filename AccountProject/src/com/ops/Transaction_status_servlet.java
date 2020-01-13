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
 * Servlet implementation class Transaction_status_servlet
 */
@WebServlet("/Transaction_status_servlet")
public class Transaction_status_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Transaction_status_servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out=res.getWriter();
		res.setContentType("text/html");
		
		HttpSession deduct=req.getSession();
		String myemail=(String)deduct.getAttribute("myemail");
		String payeeemail=(String)deduct.getAttribute("payeeemail");
		String amount=req.getParameter("amount");
		
		int status=0;
		try {
			 status=Userdao.succefulltransaction(myemail, payeeemail, amount);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 HttpSession session=req.getSession();
			String username=(String)session.getAttribute("username");
			String useremail=(String)session.getAttribute("useremail");
		
		if(status>1)
		{
			try {
				int r=Userdao.transactiondetails(myemail, payeeemail, amount);
				
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.println("<h1>succesfull Transaction</h1><br>");
			out.println("<form action='Userlogin'>");
			out.println("<input type='hidden' name='uname' value='"+username+"'>");
			out.println("<input type='hidden' name='upass' value='"+useremail+"'>");
			out.println("<input type='submit' value='Your Profile'>");
			out.println("</form></body>");
			
		}
		else
		{
			out.println("<h1>NO Transaction</h1>");
			out.println("<h3>Insufficient Balance</h3>");
			
		}
	}

}
