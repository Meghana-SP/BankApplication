package com.dxc.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dxc.pojos.UserDetails;
import com.dxc.service.AdminServiceImpl;
import com.dxc.service.IAdminService;
//import java.util.List.*;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;


@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
	
	IAdminService adminservice=new AdminServiceImpl();
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String action="";
		String message="";
		String temp=request.getParameter("btn");
		
		if(temp!=null)
		
			action=request.getParameter("btn");
		HttpSession session=request.getSession();
		
		if(action.equals("login"))
		{
          String admin_id=request.getParameter("admin_id");
          String password=request.getParameter("password");
          
          boolean b=adminservice.authenticate(admin_id,password);
          
          if(b==false)
          {
        	  message = "Admin Login Succesfully !!!";
        	  session.setAttribute("message", message);
        	  response.sendRedirect("adminoption.jsp");
          }
          else
          {
        	  message = "Admin Login Failed !!!";
        	  session.setAttribute("message", message);
        	  response.sendRedirect("view.jsp");
          }
		}
		else if(action.equals("adduser"))
		{
			int accno=Integer.parseInt(request.getParameter("accno"));
			String uname=request.getParameter("uname");
			double accbalance=Double.parseDouble(request.getParameter("accbalance"));
			int user_id=Integer.parseInt(request.getParameter("user_id"));
			String password=request.getParameter("password");
			
			UserDetails us=new UserDetails(accno,uname,accbalance,user_id,password);
			adminservice.adduser(us);
			
			message = "User Succesfully Added....!!!";
      	  session.setAttribute("message", message);
      	  response.sendRedirect("view.jsp");
		}
		
		else if(action.equals("finduser"))
		{

			int accno=Integer.parseInt(request.getParameter("accno"));

			session.setAttribute("accno", accno);
			
			UserDetails findStatus=adminservice.finduser(accno);
			
			session.setAttribute("findStatus", findStatus);
			
			response.sendRedirect("showusers.jsp");
			
		}
		
		else if(action.equals("search"))
         {
			int accno=Integer.parseInt(request.getParameter("accno"));
			session.setAttribute("accno", accno);
			boolean search=adminservice.searchUsers(accno);
			if(search==true)
			{
				response.sendRedirect("updateusers2.jsp");
             }
			
			else
			{
				message="User Is Not Found.....!!";
				session.setAttribute("message",message);
				response.sendRedirect("view.jsp");
			}
         }
			
			else if(action.equals("updateusers"))
			{
				int accno=(int)session.getAttribute("accno");
				String uname=request.getParameter("uname");
				double accbalance=Double.parseDouble(request.getParameter("accbalance"));
			//	int user_id=(int)session.getAttribute("user_id");
				
				int user_id=Integer.parseInt(request.getParameter("user_id"));
				String password=request.getParameter("password");
				
				UserDetails us=new UserDetails(accno,uname,accbalance,user_id,password);
				adminservice.updateusers(us);
				message="User Details Updated Succesfully......";
				session.setAttribute("message",message);
				response.sendRedirect("view.jsp");
			}
			
			else if(action.equals("showbalance"))
			{
				int accno=Integer.parseInt(request.getParameter("accno"));
				double b=adminservice.getBalance(accno);
				try {
					
					PrintWriter pwt=response.getWriter();
					pwt.println("Account balance is =" +b);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
          
			else if(action.equals("delete"))
			{
				int accno=Integer.parseInt(request.getParameter("accno"));
				adminservice.removeuser(accno);
				message="User Account Removed Sucessfully.....";
				session.setAttribute("message", message);
				response.sendRedirect("view.jsp");
			}
			
			else
			{
				java.util.List<UserDetails> list=adminservice.getAllUsers();
				session.setAttribute("list",list);
				response.sendRedirect("showaccounts.jsp");
			}
		


		
         }
	}

