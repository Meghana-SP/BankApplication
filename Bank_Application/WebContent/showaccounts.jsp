<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>

    <%
              String bgColor="#FFF0F5";
              String myColor=request.getParameter("color");
              if(myColor!=null){
            	  myColor=myColor;
              }
              else{
            	  myColor=bgColor;
              }
              
              
          %> 

                  <style>
                      table, tr, td{
                            border    : 1px solid black;
                            width     : 40%;
                            text-align: center;
                      }                  
                  </style>
</head>

              
<body bgColor="<%=myColor%>">

             <h1>DISPLAY OF ALL THE USER</h1>
                  <c:forEach var="us" items="${list}">
                  <table>
                      <tr>
                         <td>
                               <h5><c:out value="${us.getAccno()}"></c:out></h5>
                         </td>
                         <td>
                               <h5><c:out value="${us.getUname()}"></c:out></h5>
                         </td>
                         <td>
                               <h5><c:out value="${us.getAccbalance()}"></c:out></h5>
                          </td>  
                          
                           <td>
                               <h5><c:out value="${us.getUser_id()}"></c:out></h5>
                          </td>  
                          
                           <td>
                               <h5><c:out value="${us.getPassword()}"></c:out></h5>
                          </td>     
                        </tr>              
                       
                  </table>
                  
                  </c:forEach>

 </body>
</html>