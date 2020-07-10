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
      
          <h2> TO UPDATE NAME AND ACCOUNT BALANCE OF USER</h2>
       
          

          <form action="admin">

          Enter User Name To Update - <input type="text" name="uname"><br>
          
          Enter Account Balance To Update - <input type="text" name="accbalance"><br>
          
          Enter User Id To Update - <input type="text" name="user_id"><br>
            
          Enter Password To Update - <input type="text" name="password"><br>
          
          <input type="submit" name="btn" value="updateusers">

           </form> 

</body>
</html>