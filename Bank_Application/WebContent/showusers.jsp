<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"

    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>

<html>

<head>

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

					table, tr, td{

						 border: 1px solid black;

						 width : 40%;

						 text-align: center;

					}

				</style>

</head>

<body bgColor="<%=myColor%>">

					<h1>SPECIFIC  USER   DETAIL</h1>

						<table>

							<tr>

								<td>

									<h5><c:out value="${findStatus.getAccno()}"></c:out></h5>

								</td>

								<td>

									<h5><c:out value="${findStatus.getUname()}"></c:out></h5>

								</td>

								<td>

									<h5><c:out value="${findStatus.getAccbalance()}"></c:out></h5>

								</td>	
								
									<td>

									<h5><c:out value="${findStatus.getUser_id()}"></c:out></h5>

								</td>	
								
									<td>

									<h5><c:out value="${findStatus.getPassword()}"></c:out></h5>

								</td>	
								

							</tr>

						</table>

				

</body>

</html>

