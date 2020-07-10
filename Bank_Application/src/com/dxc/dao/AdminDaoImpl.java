package com.dxc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dxc.pojos.BankAdmin;
import com.dxc.pojos.UserDetails;

public class AdminDaoImpl implements IAdminDao
{


	private static Connection conn;

	static {

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			System.out.println("driver loaded...");

			

		 conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:Library", "system", "password");

			System.out.println("connected to database....");

		}catch(Exception e)

		{

			e.printStackTrace();

		}

	}
	
	@Override
	public boolean authenticate(String admin_id,String password)
	{
		try {
		      PreparedStatement pstmt=conn.prepareStatement("select admin_id,password from badmin where admin_id=? and password=?");
		      pstmt.setString(1, admin_id);
		      
		      pstmt.setString(2, password);
		      
		      ResultSet rs=pstmt.executeQuery();
		      
		      if(rs.next()) {
		    	
		    	  return true;
		      }
		      else
		      {
		    	  return false;
		      }
		}
		catch(SQLException e) {
		
			e.printStackTrace();
	}
		return false;

	}
	@Override
	public void adduser(UserDetails us)
	{
       try {
    	   PreparedStatement pstmt=conn.prepareStatement("insert into busers values(?,?,?,?,?)");
    	   
    	   pstmt.setInt(1,us.getAccno());
    	   
    	   pstmt.setString(2,us.getUname());
    	   
    	   pstmt.setDouble(3, us.getAccbalance());
    	   
    	   pstmt.setInt(4,us.getUser_id());
    	   
    	   pstmt.setString(5,us.getPassword());
    	   
    	   pstmt.executeUpdate();
    	   
       } catch(Exception e) {
    	   
    	   e.printStackTrace();
       }
       
	}
	@Override
	public UserDetails finduser(int accno) {
		
		try {

			Statement stmt=conn.createStatement();

			ResultSet rs=stmt.executeQuery("select * from busers");

			while(rs.next())

			{

				if(accno==rs.getInt(1))

				{

					return new UserDetails(rs.getInt(1), rs.getString(2),rs.getDouble(3),rs.getInt(4),rs.getString(5));

				}

			}

		}catch(Exception e)

		{

			e.printStackTrace();

		}

		return null;
		
	}
	
	@Override
	public boolean searchUsers(int accno)
	{
		try {
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery("select * from busers");
			while(rs.next())
			{
				if(accno==rs.getInt(1))
				{
					return true;
				}
			}
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public void updateusers(UserDetails us)
	{
		try {
			
			PreparedStatement pstmt=conn.prepareStatement("update busers set uname=?,accbalance=?,user_id=?,password=? where accno=?");
			pstmt.setString(1,us.getUname());
			pstmt.setDouble(2, us.getAccbalance());
		//	pstmt.setInt(3, us.getAccno());
			pstmt.setInt(3, us.getUser_id());
			pstmt.setString(4, us.getPassword());
			pstmt.setInt(5, us.getAccno());
            pstmt.executeUpdate(); 			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public double getBalance(int accno)
	{
		double accbalance=0;
		Statement stmt;
		try {
			stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery("select * from busers");
			while(rs.next())
			{
				if(accno==rs.getInt(1));
				{
					accbalance=rs.getDouble(3);
				}
			}
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return accbalance;
		
	}
	
	@Override
	public void removeuser(int accno)
	{
		try
		{
			PreparedStatement pstmt=conn.prepareStatement("delete from busers where accno=?");
			pstmt.setInt(1, accno);;
			pstmt.execute();
		} catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public List<UserDetails> getAllUsers()
	{
		List<UserDetails> list=new ArrayList<>();
		try {
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery("select * from busers");
			while(rs.next())
			{
				list.add(new UserDetails(rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getInt(4),rs.getString(5)));
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}
}
