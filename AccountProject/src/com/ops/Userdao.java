package com.ops;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class Userdao {
	public static Connection getConnection() throws ClassNotFoundException, SQLException
	{
		Connection con=null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost/ops","root","ROOT");
		return con;
		
		
	}
	
	public static int insert(User u) throws SQLException, ClassNotFoundException
	{
		Connection con=Userdao.getConnection();
		PreparedStatement ps=con.prepareStatement("insert into user(name,email,bdate,pass,balance,rid,active) values(?,?,?,?,?,?,?)");
		ps.setString(1, u.getName());
		ps.setString(2, u.getEmail());
		ps.setString(3, u.getBdate());
		ps.setString(4, u.getPassword());
		ps.setString(5, u.getBalance());
		ps.setInt(6, 2);
		ps.setString(7, "Enable");
		int a=ps.executeUpdate();
		return a;
	}
	
	public static String confirm(User u) throws ClassNotFoundException, SQLException
	{
		Connection con=Userdao.getConnection();
		PreparedStatement ps=con.prepareStatement("select email,pass,rid,active from user where email=?");
		ps.setString(1, u.getEmail());
		int count=0;
		String s="";
		ResultSet rs=ps.executeQuery();
		int rid=0;
		String active="";
		while(rs.next())
		{
			
			String email=rs.getString(1);
			String pass=rs.getString(2);
			 rid=rs.getInt(3);
			 active=rs.getString(4);
			if(email.equals(u.getEmail()) && pass.equals(u.getPassword()))
			{
				count++;
			}
			
		}
	     if(active.equals("Disable"))
	     {
	    	 return "Disable";
	     }
		else if(count==1 && rid==1)
		{
			return "admin";
		}
		else if(count==1)
		{
			return "user";
		}
		else 
		{
			return "unknown";
		}
		
		
	}
	
	public static User user(String name, String pass) throws SQLException, ClassNotFoundException
	{
		
		Connection con=Userdao.getConnection();
		
		PreparedStatement ps=con.prepareStatement("select * from user where email=? and pass=?");
		ps.setString(1, name);
		ps.setString(2, pass);
		ResultSet rs=ps.executeQuery();
		rs.next();
		
		User u=new User();
		   u.setAccount(rs.getInt(1));
		   u.setName(rs.getString(2));
		   u.setEmail(rs.getString(3));
		   u.setBdate(rs.getString(4));
		   u.setPassword(rs.getString(5));
		   u.setBalance(rs.getString(6));
		   u.setRid(rs.getInt(7));
		  
		  
		return u;
	}
	
	
	
	public static User userdetails1(int id) throws SQLException, ClassNotFoundException
	{
		
		Connection con=Userdao.getConnection();
		
		PreparedStatement ps=con.prepareStatement("select * from user where acctno=?");
		ps.setInt(1, id);
		
		ResultSet rs=ps.executeQuery();
		rs.next();
		
		User u=new User();
		   u.setAccount(rs.getInt(1));
		   u.setName(rs.getString(2));
		   u.setEmail(rs.getString(3));
		   u.setBdate(rs.getString(4));
		   u.setPassword(rs.getString(5));
		   u.setBalance(rs.getString(6));
		   u.setRid(rs.getInt(7));
		  
		return u;
	}
	
	
	public static int updateuserinfo(User u) throws ClassNotFoundException, SQLException
	{
         Connection con=Userdao.getConnection();
		PreparedStatement ps=con.prepareStatement("update user set name=? ,email=?, bdate=? ,pass=? where acctno=?");
		ps.setString(1, u.getName());
		ps.setString(2, u.getEmail());
		ps.setString(3, u.getBdate());
		ps.setString(4, u.getPassword());
		ps.setInt(5, u.getAccount());
		int a=ps.executeUpdate();
		return a;
		
	}
	
	public static int insertpayee(String myemail,String pemail) throws ClassNotFoundException, SQLException
	{
		 Connection con=Userdao.getConnection();
		PreparedStatement ps=con.prepareStatement("insert into payee1 values(?,?)");
		ps.setString(1, myemail);
		ps.setString(2, pemail);
		int status=ps.executeUpdate();
		return status;
	}
	
	public static ArrayList<User> payeelist(String email) throws SQLException, ClassNotFoundException
	{
		Connection con=Userdao.getConnection();
		PreparedStatement ps=con.prepareStatement("select payee from payee1 where myemail=?");
		ps.setString(1, email);
		ResultSet rs=ps.executeQuery();
		ArrayList<User> al=new ArrayList<>();
		while(rs.next())
		{
			String a=rs.getString(1);
			PreparedStatement pts=con.prepareStatement("select acctno, name from user where email=?");
			pts.setString(1,a);
			ResultSet rs1=pts.executeQuery();
			rs1.next();
			User u=new User();
			u.setAccount(rs1.getInt(1));
			u.setName(rs1.getString(2));
			u.setEmail(a);
			al.add(u);
		}
		return al;

	}
	
	
	public static int deletepayee(String myemail,String payeemail) throws SQLException, ClassNotFoundException
	{
		Connection con=Userdao.getConnection();
		PreparedStatement ps=con.prepareStatement("delete from payee1 where myemail=? and payee=?");
		ps.setString(1, myemail);
		ps.setString(2, payeemail);
		int status=ps.executeUpdate();
		return status;
	}
	
	
	public static User payeedetails(String name) throws SQLException, ClassNotFoundException
	{
		
		Connection con=Userdao.getConnection();
		
		PreparedStatement ps=con.prepareStatement("select * from user where email=? ");
		ps.setString(1, name);
		ResultSet rs=ps.executeQuery();
		rs.next();
		User u=new User();
		   u.setAccount(rs.getInt(1));
		   u.setName(rs.getString(2));
		   u.setEmail(rs.getString(3));
		   u.setBdate(rs.getString(4));
		   u.setPassword(rs.getString(5));
		   u.setBalance(rs.getString(6));
		  
		return u;
	}
	
	public static int succefulltransaction(String myemail,String payeeemail,String amount) throws ClassNotFoundException, SQLException
	{
        Connection con=Userdao.getConnection();
        
        int payamount=Integer.parseInt(amount);
        int status=0;
        
		PreparedStatement ps1=con.prepareStatement("select balance from user where email=? ");
		ps1.setString(1, myemail);
		ResultSet rs1=ps1.executeQuery();
		rs1.next();

		int mybalance=Integer.parseInt(rs1.getString(1));
		if(mybalance>payamount)
		{
		int mynewbalance=mybalance-payamount;
		String balance1=""+mynewbalance;
		
		
		PreparedStatement ps2=con.prepareStatement("select balance from user where email=? ");
		ps2.setString(1, payeeemail);
		ResultSet rs2=ps2.executeQuery();
		rs2.next();
		
		int payeebalance=Integer.parseInt(rs2.getString(1));
		int payeenewbalance=payeebalance+payamount;
		String balance2=""+payeenewbalance;
		
		PreparedStatement ps11=con.prepareStatement("update user set balance=? where email=?");
		ps11.setString(1, balance1);
		ps11.setString(2, myemail);
		int status1=ps11.executeUpdate();
		
		PreparedStatement ps22=con.prepareStatement("update user set balance=? where email=?");
		ps22.setString(1, balance2);
		ps22.setString(2, payeeemail);
		int status2=ps22.executeUpdate();
		
		 status=status1+status2;
		}
		else
		{
			status=0;
		}
		
		return status;
		
		
	}
	
	public static int transactiondetails(String myemail,String payeeemail,String amount) throws ClassNotFoundException, SQLException
	{
        Connection con=Userdao.getConnection();
		PreparedStatement ps=con.prepareStatement("insert into transaction(userid,payeeid,amout) values(?,?,?) ");
		ps.setString(1, myemail);
		ps.setString(2, payeeemail);
		ps.setString(3, amount);
		int status=ps.executeUpdate();
		return status;
	}
	
	
	public static ArrayList<User> alluserdetails() throws ClassNotFoundException, SQLException
	{
		 Connection con=Userdao.getConnection();
		 PreparedStatement ps=con.prepareStatement("select * from user ");
		 ResultSet rs=ps.executeQuery();
		 ArrayList<User> al=new ArrayList<>();
		 while(rs.next())
		 {
			 User u=new User();
			 u.setAccount(rs.getInt(1));
			 u.setName(rs.getString(2));
			 u.setEmail(rs.getString(3));
			 u.setBdate(rs.getString(4));
			 u.setPassword(rs.getString(5));
			 u.setBalance(rs.getString(6));
			 u.setRid(rs.getInt(7));
			 u.setActive(rs.getString(8));
			 al.add(u);
		 }
		 return al;
	}
	
	public static ArrayList<User> disableuserdetails() throws ClassNotFoundException, SQLException
	{
		
		 Connection con=Userdao.getConnection();
		 PreparedStatement ps=con.prepareStatement("select * from user where active=? ");
		 ps.setString(1, "Disable");
		 ResultSet rs=ps.executeQuery();
		 ArrayList<User> al=new ArrayList<>();
		 while(rs.next())
		 {
			 User u=new User();
			 u.setAccount(rs.getInt(1));
			 u.setName(rs.getString(2));
			 u.setEmail(rs.getString(3));
			 u.setBdate(rs.getString(4));
			 u.setPassword(rs.getString(5));
			 u.setBalance(rs.getString(6));
			 u.setRid(rs.getInt(7));
			 u.setActive(rs.getString(8));
			 al.add(u);
		 }
		 return al;
	}
	
	public static ArrayList<Transaction> allusertransaction() throws ClassNotFoundException, SQLException
	{
		 Connection con=Userdao.getConnection();
		 PreparedStatement ps=con.prepareStatement("select * from transaction");
		 ResultSet rs=ps.executeQuery();
	     ArrayList al=new ArrayList<>();
	     while(rs.next())
	     {
	    	 Transaction t=new Transaction();
	    	 t.setTid(rs.getInt(1));
	    	 t.setUserid(rs.getString(2));
	    	 t.setPayeeid(rs.getString(3));
	    	 t.setAmount(rs.getInt(4));
	    	 al.add(t);
	     }
	     
	     return al;
	}
	
	public static ArrayList<Transaction> oneusertransaction(String email) throws ClassNotFoundException, SQLException
	{
		 Connection con=Userdao.getConnection();
		 PreparedStatement ps=con.prepareStatement("select * from transaction where userid=?");
		 ps.setString(1, email);
		 ResultSet rs=ps.executeQuery();
	     ArrayList al=new ArrayList<>();
	     while(rs.next())
	     {
	    	 Transaction t=new Transaction();
	    	 t.setTid(rs.getInt(1));
	    	 t.setUserid(rs.getString(2));
	    	 t.setPayeeid(rs.getString(3));
	    	 t.setAmount(rs.getInt(4));
	    	 al.add(t);
	     }
	     
	     return al;
	}
	
	public static int userdisable(String email) throws ClassNotFoundException, SQLException
	{
		 Connection con=Userdao.getConnection();
		 PreparedStatement ps=con.prepareStatement("update user set active=? where email=?");
		 ps.setString(1, "Disable");
		 ps.setString(2, email);
		 int status=ps.executeUpdate();
		 return status;
	}
	
	public static int userenable(String email) throws ClassNotFoundException, SQLException
	{
		 Connection con=Userdao.getConnection();
		 PreparedStatement ps=con.prepareStatement("update user set active=? where email=?");
		 ps.setString(1, "Enable");
		 ps.setString(2, email);
		 int status=ps.executeUpdate();
		 return status;
	}
	
	public static int setadmin(String email) throws SQLException, ClassNotFoundException
	{
		 Connection con=Userdao.getConnection();
		 PreparedStatement ps=con.prepareStatement("update user set rid=? where email=?");
		 ps.setInt(1, 1);
		 ps.setString(2, email);
		 int status=ps.executeUpdate();
		 return status;
	}
	
	public static int removeadmin(String email) throws SQLException, ClassNotFoundException
	{
		 Connection con=Userdao.getConnection();
		 PreparedStatement ps=con.prepareStatement("update user set rid=? where email=?");
		 ps.setInt(1, 2);
		 ps.setString(2, email);
		 int status=ps.executeUpdate();
		 return status;
	}
	
	
}
