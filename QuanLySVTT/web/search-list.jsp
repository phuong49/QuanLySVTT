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
        <h3 class="text-center">List of Student</h3>
        <hr>
        <div class="row justify-content-between">
            <div class="col-6">
                <form action="searchclas" method="get" style="max-width:500px">
                    <input type ="text" class="form-control" name="search" placeholder="Search" value="<c:out value="${search}"/>">
                </form>
            </div>
            <div class="col-6 text-right" >
                <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
                    New Class</a>
            </div>

        </div>
        <br>

        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ClassID</th>
                <th>Class Name</th>
                <th>Max Number</th>
                <th>Class Number</th>

            </tr>
            </thead>
            <tbody>
            <!--   for (Todo todo: todos) {  -->
            <c:forEach var="clas" items="${listClas}">

                <tr>
                    <td><c:out value="${clas.clasID}"/></td>
                    <td><c:out value="${clas.clasName}"/></td>
                    <td><c:out value="${clas.maxNumber}"/></td>
                    <td><c:out value="${clas.clasNumber}"/></td>


                </tr>

            </c:forEach>
            <!-- } -->
            </tbody>

        </table>
    </div>
</div>
</body>
</html>
