<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Student Management Application</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>

<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: tomato">
        <a href="<%=request.getContextPath()%>/list" class="navbar-brand"> Class
        </a>
        <a href="<%=request.getContextPath()%>/student" class="navbar-brand"> Student
        </a>
    </nav>
</header>
<br>

<div class="row">
    <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

    <div class="container">
        <h3 class="text-center">List Student off class</h3>
        <hr>
        <div class="row ">
            <div class=" text-right">
                <form action="searchstu" method="get" style="max-width:500px">
                    <input type ="text" class="form-control" name="search" placeholder="Search" value="<c:out value="${searchstu}"/>">
                </form>
            </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>StudentID</th>
                <th>Student Name</th>
                <th>Gender</th>
                <th>Age</th>
                <th>ClassID</th>
                <th>Actions</th>

            </tr>
            </thead>
            <tbody>
            <!--   for (Todo todo: todos) {  -->
            <c:forEach var="student" items="${listStudent}">

                <tr>
                    <td><c:out value="${student.studentID}"/></td>
                    <td><c:out value="${student.studentName}"/></td>
                    <td><c:out value="${student.gender}"/></td>
                    <td><c:out value="${student.age}"/></td>
                    <td><c:out value="${student.clasID}"/></td>
                    <td> <a href="deleteClas2?studentID=<c:out value='${student.studentID}' />&clasID=<c:out value='${student.clasID}'/>">Delete</a></td>
                </tr>
            </c:forEach>
            <!-- } -->
            </tbody>

        </table>
    </div>
</div>
</body>
</html>
