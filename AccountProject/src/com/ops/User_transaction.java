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
 * Servlet implementation class User_transaction
 */
@WebServlet("/User_transaction")
public class User_transaction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public User_transaction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out=res.getWriter();
		res.setContentType("text/html");
		String email=req.getParameter("email");
		
		HttpSession admin=req.getSession();
		 String ademail=(String)admin.getAttribute("adname");
		 String adpass=(String)admin.getAttribute("adpass");
		ArrayList<Transaction> al=new ArrayList<>();
		try {
			al=Userdao.oneusertransaction(email);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 out.println("<center><form action='Userlogin'>");
		 out.println("<input type='hidden' name='uname' value='"+ademail+"'>");
		 out.println("<input type='hidden' name='upass' value='"+adpass+"'>");
		 out.println("<table border='1'>");
		 out.println("<tr><th>Transaction Id</th><th>User Id</th><th>Payee Id</th><th>Transfer Amount</th></tr>");
		 for(Transaction t:al)
		 {
			 out.println("<tr><td>"+t.getTid()+"</td><td>"+t.getUserid()+"</td><td>"+t.getPayeeid()+"</td><td>"+t.getAmount()+"</td></tr>");
		 }
		 out.println("</table><input type='submit' value='Your Profile'>");
		 out.println("</form></center></body>");
		
	}

}
