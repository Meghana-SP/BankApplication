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
      
          <h2>FOR MINI STATEMENT</h2>

		<form action="user">
		
				Enter account number - <input type="text" name="accno"/><br>
		
				<input type="submit" name="userbtn" value="printstatement">
		
		</form>
</body>
</html>