<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Class Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a href="<%=request.getContextPath()%>/list" class="navbar-brand"> Class
					 </a>
				<a href="<%=request.getContextPath()%>/student" class="navbar-brand"> Student
				</a>
			</div>
		</nav>
	</header>

	<br>
<div class="row">
<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

<div class="container">
	<h3 class="text-center">List of Class</h3>
	<hr>
	<div class="row justify-content-between">
		<div class="col-6">
			<form action="searchclas" method="get" style="max-width:500px">
				<input type ="text" class="form-control" name="search" placeholder="Search" value="<c:out value="${searchclas}"/>">
			</form>
		</div>
		<div class="col-6 text-right" >
			<a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
				New Class</a>
		</div>

	</div>
	<br>

			<table class="table table-bordered" text-align="center" >
				<thead>
                <tr>
						<th><center>ClassID</center></th>
						<th><center>Class Name</center></th>
						<th><center>Max Number</center></th>
						<th><center>Class Number</center></th>
						<th colspan="4"><center>Actions</center></th>
                        </tr>
				</thead>
				<tbody>
					<!--   for (Todo todo: todos) {  -->
					<c:forEach var="clas" items="${listClas}">

						<tr>
							<td><center><c:out value="${clas.clasID}"/></center></td>
							<td><center><c:out value="${clas.clasName}"/></center></td>
							<td><center><c:out value="${clas.maxNumber}"/></center></td>
							<td><center><c:out value="${clas.clasNumber}"/></center></td>
							<td><c:if test="${clas.maxNumber > clas.clasNumber}">
								<a href="addstudent?clasID=<c:out value='${clas.clasID}' />">Add Student</a>
							</c:if></td>
							<td><a href="view?clasID=<c:out value='${clas.clasID}' />">View</a></td>
							<td><a href="edit?clasID=<c:out value='${clas.clasID}' />">Edit</a></td>
							<td><a href="delete?clasID=<c:out value='${clas.clasID}' />">Delete</a></td>


						</tr>

					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
<table border="0" cellpadding="0" cellspacing="0" align="center">
    <td>
        <c:if test="${currentPage != 1}">
            <!--  <td> --><a href="de?page=${currentPage - 1}">&nbsp;Previous</a><!-- </td> -->
        </c:if>
        <c:forEach begin="1" end="${noOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <!-- <td> -->${i}&nbsp;&nbsp;<!-- </td> -->
                </c:when>
                <c:otherwise>
                    <!--  <td> --><a href="de?page=${i}">${i}&nbsp;</a><!-- </td> -->
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:if test="${currentPage lt noOfPages}">
            <!--  <td> --><a href="de?page=${currentPage + 1}">&nbsp;Next</a><!-- </td> -->
        </c:if>
    </td>
</table>
</body>
</html>
