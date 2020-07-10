<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>

  <%
              String bgColor="FFF0F5";
              String myColor=request.getParameter("color");
              if(myColor!=null){
            	  myColor=myColor;
              }
              else{
            	  myColor=bgColor;
              }
              
              
          %> 


                          <style>
                              table,tr,td{
                              border:1px solid black;
                              width : 40%;
                              text-align:center;
                              }
                              </style>
<body bgColor="<%=myColor%>">

               <h1>MINI STATEMENT</h1>
               <c:forEach var="Transaction" items="${list}">
                  <table>
                    <tr>
                    
                    <td>
                        <h4><c:out value="${Transaction.getAccno()}"></c:out></h4>
                     </td>   
                     
                      <td>
                        <h4><c:out value="${Transaction.getTransaction()}"></c:out></h4>
                     </td>
                     
                      <td>
                        <h4><c:out value="${Transaction.getTransferable()}"></c:out></h4>
                     </td>      
                     
                    </tr>
                   </table>
                   </c:forEach>


</body>
</html>