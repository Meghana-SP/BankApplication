package com.dxc.controller;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dxc.pojos.Transaction;
import com.dxc.pojos.UserDetails;
import com.dxc.service.AdminServiceImpl;
import com.dxc.service.IAdminService;
import com.dxc.service.IUserService;
import com.dxc.service.UserServiceImpl;

import java.util.List;
import java.util.List.*;



@WebServlet("/user")
public class UserServlet extends HttpServlet {
	
	private int accno;
	public void init()
	{
		accno=0;
	}
	
	IUserService userservice= new UserServiceImpl();
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	
		String action="";
		String message="";
		String temp=request.getParameter("btn");
		
		if(temp!=null)
		
			action=request.getParameter("btn");
		HttpSession session=request.getSession();
		
		if(action.equals("login"))
		{
          int user_id=Integer.parseInt(request.getParameter("user_id"));
          String password=request.getParameter("password");
          accno=userservice.getAccno(user_id,password);
          
          boolean b=userservice.authenticate(user_id,password);
          
          if(b == true)
          {
        	  message = "User Login Succesfully !!!";
        	  session.setAttribute("message", message);
        	  response.sendRedirect("useroption.jsp");
          }
          else
          {
        	  message = "User Login Failed !!!";
        	  session.setAttribute("message", message);
        	  response.sendRedirect("view.jsp");
          }
		}
		  
		else if(action.equals("deposite"))
		{
			int accno=Integer.parseInt(request.getParameter("accno"));
			double accbalance=Double.parseDouble(request.getParameter("accbalance"));
			userservice.depositeAmount(accno,accbalance);
		//	adminservice.depositeMoney(us);
			
			message="Amount Added Sucessfully.....!!!";
			session.setAttribute("message",message);
			response.sendRedirect("view.jsp");
			
		}
		
		else if(action.equals("transfer"))
		{
			int accno=Integer.parseInt(request.getParameter("accno"));
			String password=request.getParameter("password");
			boolean c=userservice.checkpassword(accno,password);
			if(c==true)
			{
				int taccno=Integer.parseInt(request.getParameter("taccno"));
				double tamount=Double.parseDouble(request.getParameter("tamount"));
				
				boolean c1=userservice.transfer(accno,taccno,tamount);
				if(c1==true)
				{
					try {
                         // PrintWriter pwt=response.getWriter();
						  //pwt.println("Amount transfered succesfully");
                         	
						message="Amount Transfered Sucessfully.....!!!";
						session.setAttribute("message",message);
						response.sendRedirect("view.jsp");
						
			         	}catch (IOException e) {
			         		e.printStackTrace();
			         	}
				}
		 }
	}
		else if(action.equals("withdraw"))
		{
			int accno=Integer.parseInt(request.getParameter("accno"));
			String password=request.getParameter("password");
			double accbalance=Double.parseDouble(request.getParameter("accbalance"));
	
			boolean c=userservice.checkpassword(accno, password);
			if(c==true)
			{
				
				boolean b2=userservice.WithdrawAmount(accno,accbalance);
				
				if(b2==true)
				{
					try {
						//PrintWriter pwt=response.getWriter();
						//pwt.println("Amount succesfully withdrawed");
						
						message="Amount Withdrawed Sucessfully.....!!!";
						session.setAttribute("message",message);
						response.sendRedirect("view.jsp");
					}
					catch(IOException e) {
						
						e.printStackTrace();
					}    
				}
				else
				{
					try {
						PrintWriter pwt=response.getWriter();
						pwt.println("Insufficient Fund");
					}
					catch(IOException e) {
						
						e.printStackTrace();
					}    
				}
			}
				
				else
				{
					try {
						PrintWriter pwt=response.getWriter();
						pwt.println("Password is incorrect");
					}
					catch(IOException e) {
						e.printStackTrace();
					}
					
		                
				}
			}
		
		
		else if(action.equals("checkbalance"))
		{
			int accno=Integer.parseInt(request.getParameter("accno"));
			double b=userservice.checkbalance(accno);
			try {
				PrintWriter pwt=response.getWriter();
				pwt.println("Amount balance is = " +b);
				
			} catch(Exception e)	{
				e.printStackTrace();
			}
		}
		
		else if(action.equals("passwordchange"))
		{
			String cpassword=request.getParameter("cpassword");
			String password=request.getParameter("password");
			if(password.equals(cpassword))
			{
				userservice.PasswordChange(accno,password);
		        message="Password changed succesfully.....!!!";
	            session.setAttribute("message",message);
	            response.sendRedirect("view.jsp");    
			}
		
			
			else
			{
					response.getWriter().println("Password Is Incorrect..!!Please Re-Enter The Password");			
					}
			}

	
			else
			{
				int accno=Integer.parseInt(request.getParameter("accno"));
				List<Transaction> list=userservice.TransactionDetails(accno);
				session.setAttribute("list", list);
				response.sendRedirect("transactiondetails.jsp");
			}
    }
}

