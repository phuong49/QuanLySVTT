<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
        <h3 class="text-center">List of Student</h3>
        <hr>
        <div class="container text-left">

            <a href="<%=request.getContextPath()%>/news" class="btn btn-success">Add
                New Student</a>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>StudentID</th>
                <th>Student Name</th>
                <th>Gender</th>
                <th>Age</th>

                <th ><center>Acction</center></th>
            </tr>
            </thead>
            <tbody>
            <!--   for (Todo todo: todos) {  -->
            <c:forEach var="student" items="${listStudent}">


                <tr>
                    <td><c:out value="${student.studentID}" /></td>
                    <td><c:out value="${student.studentName}" /></td>
                    <td><c:out value="${student.gender}" /></td>
                    <td><c:out value="${student.age}" /></td>
                    <td><a href="addstudent2?clasID=<c:out value='${clasID}'/>&studentID=<c:out value='${student.studentID}' />">ADD</a>

                    </td>


                    </td>

                </tr>
            </c:forEach>

            <!-- } -->
            </tbody>

        </table>
    </div>
</div>
</body>
</html>
