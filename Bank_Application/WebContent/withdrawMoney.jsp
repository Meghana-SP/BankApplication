<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>


  <%
              String bgColor="Grey";
              String myColor=request.getParameter("color");
              if(myColor!=null){
            	  myColor=myColor;
              }
              else{
            	  myColor=bgColor;
              }
              
              
          %> 
         

<body bgColor="<%=myColor%>">
      
          <h2>TO WITHDRAW MONEY</h2>


			<form action="user">
			   
			    Enter Account Number - <input type="text" name="accno"><br>
			
				Enter Password    - <input type="text" name="password"><br>
			
				Enter Amount to withdraw  - <input type="text" name="accbalance"><br>
			
				<input type="submit" name="btn" value="withdraw">
			
			</form>
</body>
</html>