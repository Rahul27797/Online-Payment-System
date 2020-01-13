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
 * Servlet implementation class Delete_Payee_servlet
 */
@WebServlet("/Delete_Payee_servlet")
public class Delete_Payee_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delete_Payee_servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out=res.getWriter();
		res.setContentType("text/html");
		 HttpSession session1=req.getSession();
		 String myemail=(String)session1.getAttribute("myemail");
		 String payeemail=req.getParameter("payeemail");
		 int status=0;
		 try {
	           status=Userdao.deletepayee(myemail, payeemail);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 HttpSession session=req.getSession();
			String username=(String)session.getAttribute("username");
			String useremail=(String)session.getAttribute("useremail");
		 if(status>0)
		 {
			
			 out.println("<body><h3>Succefully Delete</h3>");
				out.println("<form action='Userlogin'>");
				out.println("<input type='hidden' name='uname' value='"+username+"'>");
				out.println("<input type='hidden' name='upass' value='"+useremail+"'>");
				out.println("<input type='submit' value='Your Profile'>");
				out.println("</form></body>");
			
		 }
		 else
		 {
			 out.println("Unsuccefull To Delete");
			
		 }
		
	}

}
