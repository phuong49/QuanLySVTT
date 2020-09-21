//package web;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.SQLException;
//import java.util.List;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import dao.StudentDao;
//import dao.UserDao;
//import model.Clas;
//import model.Student;
//
///**
// * ControllerServlet.java
// * This servlet acts as a page controller for the application, handling all
// * requests from the user.
// * @email Ramesh Fadatare
// */
//
//@WebServlet("/student")
//public class StudentServlet extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//    private StudentDao studentDao;
//
//    public void init() {
//        studentDao = new StudentDao();
//    }
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        doGet(request, response);
//    }
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String action = request.getServletPath();
//
//        try {
//            switch (action) {
//                case "/new":
//                    showNewForm(request, response);
//                    break;
//                case "/insert":
//                    insertStudent(request, response);
//                    break;
//                case "/delete":
//                    deleteStudent(request, response);
//                    break;
//                case "/edit":
//                    showEditForm(request, response);
//                    break;
//                case "/update":
//                    updateStudent(request, response);
//                    break;
//                default:
//                    listStudent(request, response);
//                    break;
//            }
//        } catch (SQLException ex) {
//            throw new ServletException(ex);
//        }
//    }
//
//    private void listStudent(HttpServletRequest request, HttpServletResponse response)
//            throws SQLException, IOException, ServletException {
//        List<Student> listStudent = studentDao.selectAllStudent();
//        request.setAttribute("listStudent", listStudent);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("student-list.jsp");
//        dispatcher.forward(request, response);
//    }
//
//    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        RequestDispatcher dispatcher = request.getRequestDispatcher("student-form.jsp");
//        dispatcher.forward(request, response);
//    }
//
//    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
//            throws SQLException, ServletException, IOException {
//        int studentID = Integer.parseInt(request.getParameter("studentID"));
//        Student existingStudent = studentDao.selectStudent(studentID);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("student-form.jsp");
//        request.setAttribute("student", existingStudent);
//        dispatcher.forward(request, response);
//
//    }
//
//    private void insertStudent(HttpServletRequest request, HttpServletResponse response)
//            throws SQLException, IOException {
//        String studentName = request.getParameter("studentName");
//        String gender = request.getParameter("gender");
//        int age = Integer.parseInt(request.getParameter("age"));
//
//        Student newStudent = new Student(studentName, gender, age);
//        studentDao.insertStudent(newStudent);
//        response.sendRedirect("list");
//
//    }
//
//    private void updateStudent(HttpServletRequest request, HttpServletResponse response)
//            throws SQLException, IOException {
//        int studentID = Integer.parseInt(request.getParameter("studentID"));
//        String studentName = request.getParameter("studentName");
//        String gender = request.getParameter("gender");
//        int age = Integer.parseInt(request.getParameter("age"));
//
//
//        Student book = new Student(studentID, studentName, gender, age);
//
//        response.sendRedirect("list");
//    }
//
//    private void deleteStudent(HttpServletRequest request, HttpServletResponse response)
//            throws SQLException, IOException {
//        int studentID = Integer.parseInt(request.getParameter("studentID"));
//        studentDao.deleteStudent(studentID);
//        response.sendRedirect("list");
//
//    }
//
//}
