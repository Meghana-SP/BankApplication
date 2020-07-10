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
      
          <h2> FOR ACCOUNT BALANCE</h2>
       
          

          <form action="admin">
          
          Enter Account Number To View Balance - <input type="text" name="accno"><br>
          
          <input type="submit" name="btn" value="showbalance">
          
          </form>

</body>
</html>