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
import com.dxc.pojos.Transaction;
import com.dxc.pojos.UserDetails;

public class UserDaoImpl implements IUserDao
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
	public boolean authenticate(int user_id,String password)
	{
		try {
		      PreparedStatement pstmt=conn.prepareStatement("select user_id,password from busers where user_id=? and password=?");
		      pstmt.setInt(1, user_id);
		      
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
public boolean depositeAmount(int accno,double accbalance)
{
   double balance=0;
   try {
	   
	   PreparedStatement pstmt=conn.prepareStatement("select * from busers where accno=?");
	   
	   pstmt.setInt(1, accno);
	   
	      ResultSet rs=pstmt.executeQuery();
	      
	      rs.next();
	      
	      balance=rs.getDouble(3);
	      
	      balance=balance+accbalance;
	      
	      
	      pstmt.close();
	      PreparedStatement pstmt1=conn.prepareStatement("update busers set accbalance=? where accno=?");
	      pstmt1.setDouble(1, balance);
	      pstmt1.setInt(2, accno);
	      pstmt1.executeUpdate();
	      
	      PreparedStatement pstmt2=conn.prepareStatement("insert into transactiondetails values(?,?,?)");
	      pstmt2.setInt(1, accno);
	      pstmt2.setString(2, "deposite");
	      pstmt2.setDouble(3, accbalance);
	      pstmt2.execute();
	      
	      return true;
	     }
	catch(Exception e) {
	
		e.printStackTrace();
       }
   return false;
}
   @Override
   public boolean WithdrawAmount(int accno,double accbalance)
   {
	   if(accbalance>this.checkbalance(accno))
	   {
		   return false;
       }
	   double balance=0;
	   try {
		   PreparedStatement pstmt=conn.prepareStatement("select * from busers where accno=?");
		   pstmt.setInt(1,accno);
		   ResultSet rs=pstmt.executeQuery();
		   rs.next();
		   
		   balance=rs.getDouble(3);
		   balance=balance-accbalance;
		   
		   pstmt.close();
		   PreparedStatement pstmt1=conn.prepareStatement("update busers set accbalance=? where accno=?");
		   pstmt1.setDouble(1,balance);
		   pstmt1.setInt(2, accno);
		   pstmt1.executeUpdate();
		   
		   PreparedStatement pstmt2=conn.prepareStatement("insert into transactiondetails values(?,?,?)");
		   pstmt2.setInt(1,accno);
		   pstmt2.setString(2, "withdraw");
		   pstmt2.setDouble(3, accbalance);
		   pstmt2.execute();
		   return true;
	   }
	   catch(Exception e) {
		   e.printStackTrace();
	   }
   return false;
   }
   
   public double checkbalance(int accno)
   {
	   double accbalance=0;
	   Statement stmt;
	   try {
		   stmt=conn.createStatement();
		   ResultSet rs=stmt.executeQuery("select * from busers");
		   while(rs.next())
		   {
			   if(accno==rs.getInt(1))
			   {
				   accbalance=rs.getDouble(3);
			   }
		   }
		   stmt.close();
	   }catch(SQLException e) {
		   e.printStackTrace();
	   }
	   return accbalance;
  }
   
   public List<Transaction>TransactionDetails(int accno) {
	   List<Transaction> list=new ArrayList<>();
	   try {
		   Statement stmt=conn.createStatement();
		   ResultSet rs=stmt.executeQuery("select * from transactiondetails");
		   while(rs.next())
		   {
			   if(accno==rs.getInt(1))
			   {
				   list.add(new Transaction(rs.getInt(1),rs.getString(2),rs.getDouble(3)));
				}
			}
		  }catch(Exception e) {
			  e.printStackTrace();
		  }
	   return list;
  }
   
   public void PasswordChange(int accno,String password)
   {
	   PreparedStatement pstmt5;
	   try {
		   pstmt5=conn.prepareStatement("update busers set password=? where accno=?");
		   pstmt5.setString(1, password);
		   pstmt5.setInt(2, accno);
		   pstmt5.executeUpdate();
	   } catch(SQLException e) {
		   e.printStackTrace();
	   }
   
   }

   @Override	   
   public int getAccno(int user_id,String password)
   {
	   int accno=0;
	   try {
		   PreparedStatement pstmt=conn.prepareStatement("select accno from busers where user_id=? and password=?");
		   pstmt.setInt(1, user_id);
		   pstmt.setString(2, password);
		   ResultSet rs=pstmt.executeQuery();
		   rs.next();
		   accno=rs.getInt(1);
		   
	   }catch(SQLException e) {
		   e.printStackTrace();
	   }
	   return accno;
   }
   
   public boolean checkpassword(int accno,String password)
   {
	   try {
		   PreparedStatement pstmt=conn.prepareStatement("select * from busers");
		   ResultSet rs=pstmt.executeQuery();
		   while(rs.next())
		   {
			   if(accno==rs.getInt(1) &&  password.equals(rs.getString(5)))
			   {
				   return true;
			   }
		   }
	   }catch(SQLException e) {
		   e.printStackTrace();
	   }
	   return false;
   }
   
   @Override
   public boolean transfer(int accno,int taccno,double tamount)
   {
	   System.out.println(tamount);
	   System.out.println(this.checkbalance(taccno));
	   if(tamount>this.checkbalance(accno))
	   {
		   return false;
	   }
	   this.WithdrawAmount(accno, tamount);
	   this.depositeAmount(taccno, tamount);
	   try {
		   PreparedStatement pstmt=conn.prepareStatement("Insert into transactiondetails values(?,?,?)");
		   pstmt.setInt(1,accno);
		   pstmt.setString(2, "transfered");
		   pstmt.setDouble(3, tamount);
		   pstmt.execute();
		   PreparedStatement pstmt2=conn.prepareStatement("Insert into transactiondetails values(?,?,?)");
		   pstmt2.setInt(1,taccno);
		   pstmt2.setString(2, "credit");
		   pstmt2.setDouble(3, tamount);
		   pstmt2.execute();
		   return true;
	 
	   } catch(SQLException e) {
		  
		   e.printStackTrace();
	   }
	   
	   
	   return false;
   }

}


   
	   
   
