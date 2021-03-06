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
 * Servlet implementation class remove_admin_servlet
 */
@WebServlet("/remove_admin_servlet")
public class remove_admin_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public remove_admin_servlet() {
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
			 status=Userdao.removeadmin(uemail);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(status>0)
		{
			out.println("<h3>succesfully To Remove Admin</h3>");
			RequestDispatcher rd=req.getRequestDispatcher("View_user_servlet");
			rd.include(req, res);
		}
		else
		{
			out.println("<h3>Unsuccesfully To Remove Admin</h3>");
			RequestDispatcher rd=req.getRequestDispatcher("View_user_servlet");
			rd.include(req, res);
		}
		
		
	}

}
