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

/**
 * Servlet implementation class User_enable_servlet1
 */
@WebServlet("/User_enable_servlet1")
public class User_enable_servlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public User_enable_servlet1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out=res.getWriter();
		res.setContentType("text/html");
		
		String uemail=req.getParameter("email");
		int status=0;
		try {
			 status=Userdao.userenable(uemail);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(status>0)
		{
			out.println("Succefully Enable Account");
			RequestDispatcher rd=req.getRequestDispatcher("User_disable_account_view_servlet");
			rd.include(req, res);
		}
		else
		{
			out.println("Unsuccefully Enable Account");
			RequestDispatcher rd=req.getRequestDispatcher("User_disable_account_view_servlet");
			rd.include(req, res);
		}
		
		
	}

}
