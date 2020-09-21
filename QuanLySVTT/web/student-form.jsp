<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>User Management Application</title>
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
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${student != null}">
            <form action="updates" method="post">
                </c:if>
                <c:if test="${student == null}">
                <form action="inserts" method="post">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${student != null}">
                                Edit Student
                            </c:if>
                            <c:if test="${student == null}">
                                Add New Student
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${student != null}">
                        <input type="hidden" name="studentID" value="<c:out value='${student.studentID}' />" />
                    </c:if>

                    <fieldset class="form-group">
                        <label>Student Name</label> <input type="text"
                                                           pattern="[a-z]{1-20}"
                                                        value="<c:out value='${student.studentName}' />" class="form-control"
                                                        name="studentName" required="required">
                    </fieldset >
                        <fieldset class="form-group">
                            <label>Gender</label>
                            <select class="form-control" name="gender" id="gender" >
                                <option value="F" >Female</option>
                                <option value="M" >Male</option>

                            </select>
                        </fieldset>
<%--                        <fieldset class="form-group">--%>
<%--                            <label>Gender</label> <input type="text"--%>
<%--                                                            value="<c:out value='${student.gender}' />" class="form-control"--%>
<%--                                                            name="gender" required="required">--%>
<%--                        </fieldset>--%>

                    <fieldset class="form-group">
                        <label>Age</label> <input type="number" min="0"

                                                  value="<c:out value='${student.age}' />" class="form-control"
                                                         name="age" required="required">
                    </fieldset>


                        <c:if test="${student != null}">
                            <input type="hidden" name="clasID" required="required" value="<c:out value='${student.clasID}' /> " />
                        </c:if>

                    <button type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>
</body>
</html>
