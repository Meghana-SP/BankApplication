<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>

     <%
              String bgColor="Lavender";
              String myColor=request.getParameter("color");
              if(myColor!=null){
            	  myColor=myColor;
              }
              else{
            	  myColor=bgColor;
              }
              
              
          %> 
         

<body bgColor="<%=myColor%>">

            <h2>CHOOSE THE OPTION</h2>

        	<a href="admin.jsp"><h2>Login As Admin</h2></a><br>

			<a href="user.jsp"><h2>Login As User</h2></a><br>
</body>
</html>