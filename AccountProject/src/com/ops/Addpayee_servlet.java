package com.ops;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Addpayee_servlet
 */
@WebServlet("/Addpayee_servlet")
public class Addpayee_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Addpayee_servlet() {
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
		out.println("<h3>Add Payee</h3><br><br>");
		out.println("<body><form action='Payee_servlet'>Enter Email : ");
		out.println("<input type='hidden' name='myemail' value='"+email+"'>");
		out.println("<input type='text' name='uemail'><br><br>");
		out.println("Enter Accoutn no : ");
		out.println("<input type='text' name='uacctno'>");
		out.println("<input type='submit' value='Add'>");
		out.println("</form></body>");
		
	}

}
