
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
</head>

          <%
              String bgColor="#BDB76B";
              String myColor=request.getParameter("color");
              if(myColor!=null){
            	  myColor=myColor;
              }
              else{
            	  myColor=bgColor;
              }
              
              
          %> 
         

<body bgColor="<%=myColor%>">

                <h1>LIST OF USER MENU </h1>
         
				<a href="depositeMoney.jsp"><h2>To Deposite Amount.......</h2></a><br>
				
				<a href="transferMoney.jsp"><h2>To Transfer Amount To Other Account.......</h2></a><br>
				
				<a href="withdrawMoney.jsp"><h2>For Withdraw Amount.......</h2></a><br>
				
				<a href="checkBalance.jsp"><h2>To Check account balance........</h2></a><br>
				
				<a href="printStatement.jsp"><h2>For Printing Mini Statement........</h2></a><br>
				
				<a href="changePassword.jsp"><h2>To Change Password........</h2></a><br>
</body>

</html>