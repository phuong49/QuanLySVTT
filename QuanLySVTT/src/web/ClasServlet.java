package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dao.UserDao;
import model.Clas;
import model.Student;

/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 *
 * @email Ramesh Fadatare
 */

@WebServlet("/")
public class ClasServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao userDao;

    public void init() {
        userDao = new UserDao();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/searchstu":
                    searchStu(request, response);
                    break;
                case "/searchclas":
                    searchClas(request, response);
                    break;
                case "/deleteClas2":
                    deleteClas2(request, response);
                    break;
                case "/view":
                    viewStudent(request, response);
                    break;
                case "/addstudent2":
                    addStudent2(request, response);
                    break;
                case "/addstudent":
                    addStudent(request, response);
                    break;
                case "/student":
                    listStudent(request, response);
                    break;
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertClas(request, response);
                    break;
                case "/delete":
                    deleteClas(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateClas(request, response);
                    break;
                case "/news":
                    showNewForms(request, response);
                    break;
                case "/inserts":
                    insertStudent(request, response);
                    break;
                case "/deletes":
                    deleteStudent(request, response);
                    break;
                case "/edits":
                    showEditForms(request, response);
                    break;
                case "/updates":
                    updateStudent(request, response);
                    break;

                default:
                    listClas(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void searchStu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String searchstu = request.getParameter("search");
        request.setAttribute("search", searchstu);
        List<Student> listStudent = userDao.searchStu(searchstu);
        request.setAttribute("listStudent", listStudent);
        RequestDispatcher dispatcher = request.getRequestDispatcher("searchstudent-list.jsp");
        dispatcher.forward(request, response);
    }

    private void searchClas(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String searchclas = request.getParameter("search");
        request.setAttribute("search", searchclas);
        List<Clas> listClas = userDao.searchClas(searchclas);
        request.setAttribute("listClas", listClas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("search-list.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteClas2(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int studentID = Integer.parseInt(request.getParameter("studentID"));
        int clasID = Integer.parseInt(request.getParameter("clasID"));
        request.setAttribute("clasID", clasID);
        userDao.deleteStudent2(studentID);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view");
        dispatcher.forward(request, response);

    }

    private void viewStudent(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int clasID = Integer.parseInt(request.getParameter("clasID"));
        List<Student> listStudent = userDao.viewStudent(clasID);
        request.setAttribute("listStudent", listStudent);
        RequestDispatcher dispatcher = request.getRequestDispatcher("student-of-class-list.jsp");
        dispatcher.forward(request, response);

    }

    private void addStudent2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int clasID = Integer.parseInt(request.getParameter("clasID"));
        int studentID = Integer.parseInt(request.getParameter("studentID"));
        Student student = new Student(studentID, clasID);
        userDao.addStudent(student);
        userDao.addStudent2(clasID);
        request.setAttribute("clasID", clasID);
        RequestDispatcher dispatcher = request.getRequestDispatcher("addstudent");
        dispatcher.forward(request, response);

    }

    private void addStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int clasID = Integer.parseInt(request.getParameter("clasID"));
        request.setAttribute("clasID", clasID);
        List<Student> listStudent = userDao.selectAllStudent2();
        request.setAttribute("listStudent", listStudent);
        RequestDispatcher dispatcher = request.getRequestDispatcher("addstudent-list.jsp");
        dispatcher.forward(request, response);
    }

    private void listClas(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int page = 1;
        int recordsPerPage = 5;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        List<Clas> listClas = userDao.selectAllClas((page - 1) * recordsPerPage, recordsPerPage);
        int noOfRecords = userDao.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        request.setAttribute("listClas", listClas);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        RequestDispatcher view = request.getRequestDispatcher("user-list.jsp");
        view.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int clasID = Integer.parseInt(request.getParameter("clasID"));
        Clas existingClas = userDao.selectClas(clasID);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        request.setAttribute("clas", existingClas);
        dispatcher.forward(request, response);

    }

    private void insertClas(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        String clasName = request.getParameter("clasName");
        int maxNumber = Integer.parseInt(request.getParameter("maxNumber"));
        int clasNumber = Integer.parseInt(request.getParameter("clasNumber"));
        Clas newClas = new Clas(clasName, maxNumber, clasNumber);
        userDao.insertClas(newClas);
        response.sendRedirect("listádasd");

    }

    private void updateClas(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int clasID = Integer.parseInt(request.getParameter("clasID"));
        String clasName = request.getParameter("clasName");
        int maxNumber = Integer.parseInt(request.getParameter("maxNumber"));
        int clasNumber = Integer.parseInt(request.getParameter("clasNumber"));

        Clas book = new Clas(clasID, clasName, maxNumber, clasNumber);
        userDao.updateClas(book);
        response.sendRedirect("list");
    }

    private void deleteClas(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int clasID = Integer.parseInt(request.getParameter("clasID"));
        userDao.deleteClas(clasID);
        response.sendRedirect("list");

    }

    private void listStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int page = 1;
        int recordsPerPage = 5;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        List<Student> listStudent = userDao.selectAllStudent((page - 1) * recordsPerPage, recordsPerPage);
        int noOfRecords = userDao.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        request.setAttribute("listStudent", listStudent);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        RequestDispatcher view = request.getRequestDispatcher("student-list.jsp");
        view.forward(request, response);

    }

    private void showNewForms(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("student-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForms(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int studentID = Integer.parseInt(request.getParameter("studentID"));
        Student existingStudent = userDao.selectStudent(studentID);
        RequestDispatcher dispatcher = request.getRequestDispatcher("student-form.jsp");
        request.setAttribute("student", existingStudent);
        dispatcher.forward(request, response);

    }

    private void insertStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String studentName = request.getParameter("studentName");
        String gender = request.getParameter("gender");
        int age = Integer.parseInt(request.getParameter("age"));

        Student newStudent = new Student(studentName, gender, age);
        userDao.insertStudent(newStudent);
        response.sendRedirect("student");

    }

    private void updateStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int studentID = Integer.parseInt(request.getParameter("studentID"));
        String studentName = request.getParameter("studentName");
        String gender = request.getParameter("gender");
        int age = Integer.parseInt(request.getParameter("age"));


        Student book1 = new Student(studentID, studentName, gender, age);
        userDao.updateStudent(book1);
        response.sendRedirect("student");
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int studentID = Integer.parseInt(request.getParameter("studentID"));
        userDao.deleteStudent(studentID);
        response.sendRedirect("student");
    }
}
