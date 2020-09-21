//package dao;
//
//import model.Clas;
//import model.Student;
//
//import java.io.PrintWriter;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class StudentDao {
//    private String jdbcURL = "jdbc:mysql://localhost:3306/quanlysvtt?useSSL=false";
//    private String jdbcUsername = "root";
//    private String jdbcPassword = "";
//    private static final String INSERT_STUDENT_SQL = "INSERT INTO student" + "  (student_name, gender, age) VALUES " + " (?, ?, ?);";
//    private static final String SELECT_STUDENT_BY_ID = "select student_id,student_name,gender,age,class_id from student where student_id =?";
//    private static final String SELECT_ALL_STUDENT = "select * from student";
//    private static final String DELETE_STUDENT_SQL = "delete from student where student_id = ?;";
//    private static final String UPDATE_STUDENT_SQL = "update student set student_name = ?,gender= ?, age =? where student_id = ?;";
//    private static final String SELECT_NAME_SQL= "SELECT count( * ) as checkExist FROM classs where class_name LIKE" + " (?);";
//
//    public StudentDao() {
//    }
//
//    protected Connection getConnection() {
//        Connection connection = null;
//
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            connection = DriverManager.getConnection(this.jdbcURL, this.jdbcUsername, this.jdbcPassword);
//        } catch (SQLException var3) {
//            var3.printStackTrace();
//        } catch (ClassNotFoundException var4) {
//            var4.printStackTrace();
//        }
//
//        return connection;
//    }
//
//
//    public void insertStudent(Student student) throws SQLException {
//        System.out.println(INSERT_STUDENT_SQL);
//        // try-with-resource statement will auto close the connection.
//        try (Connection connection = getConnection();
//
//                    PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT_SQL);)
//                    {
//                        preparedStatement.setString(1, student.getStudentName());
//                        preparedStatement.setString(2, student.getGender());
//                        preparedStatement.setInt(3, student.getAge());
//                        preparedStatement.setInt(4,student.getClasID());
//                        System.out.println(preparedStatement);
//                        preparedStatement.executeUpdate();
//
//
//        } catch (SQLException e) {
//            printSQLException(e);
//        }
//
//    }
//    public Student selectStudent(int studentID) {
//        Student student = null;
//        // Step 1: Establishing a Connection
//        try (Connection connection = getConnection();
//             // Step 2:Create a statement using connection object
//             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT_BY_ID);) {
//            preparedStatement.setInt(1, studentID);
//            System.out.println(preparedStatement);
//            // Step 3: Execute the query or update query
//            ResultSet rs = preparedStatement.executeQuery();
//
//            // Step 4: Process the ResultSet object.
//            while (rs.next()) {
//                String studentName = rs.getString("student_Name");
//                String gender = rs.getString("gender");
//                int age = rs.getInt("age");
//                int clasID = rs.getInt("class_id");
//                student = new Student(studentID, studentName, gender,age,clasID);
//            }
//        } catch (SQLException e) {
//            printSQLException(e);
//        }
//        return student;
//    }
//    public List<Student> selectAllStudent() {
//
//        // using try-with-resources to avoid closing resources (boiler plate code)
//        List<Student> student = new ArrayList<>();
//        // Step 1: Establishing a Connection
//        try (Connection connection = getConnection();
//
//             // Step 2:Create a statement using connection object
//             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STUDENT);) {
//            System.out.println(preparedStatement);
//            // Step 3: Execute the query or update query
//            ResultSet rs = preparedStatement.executeQuery();
//
//            // Step 4: Process the ResultSet object.
//            while (rs.next()) {
//                int studentID = rs.getInt("student_id");
//                String studentName = rs.getString("student_name");
//                String gender = rs.getString("gender");
//                int age = rs.getInt("age");
//                int clasID = rs.getInt("Class_id");
//                student.add(new Student(studentID, studentName, gender,age, clasID));
//            }
//        } catch (SQLException e) {
//            printSQLException(e);
//        }
//        return student;
//    }
//    public boolean deleteStudent(int studenID) throws SQLException {
//        boolean rowDeleted;
//        try (Connection connection = getConnection();
//             PreparedStatement statement = connection.prepareStatement(DELETE_STUDENT_SQL);) {
//            statement.setString(1, String.valueOf(studenID));
//            rowDeleted = statement.executeUpdate() > 0;
//        }
//        return rowDeleted;
//    }
//    public boolean updateStudent(Student student) throws SQLException {
//        boolean rowUpdated;
//        try (Connection connection = getConnection();
//             PreparedStatement statement = connection.prepareStatement(UPDATE_STUDENT_SQL);) {
//            statement.setInt(5, student.getStudentID());
//            statement.setString(1, student.getStudentName());
//            statement.setString(2, student.getGender());
//            statement.setInt(3, student.getAge());
//            statement.setInt(4, student.getClasID());
//
//            rowUpdated = statement.executeUpdate() > 0;
//        }
//        return rowUpdated;
//    }
//    private void printSQLException(SQLException ex) {
//        for (Throwable e : ex) {
//            if (e instanceof SQLException) {
//                e.printStackTrace(System.err);
//                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
//                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
//                System.err.println("Message: " + e.getMessage());
//                Throwable t = ex.getCause();
//                while (t != null) {
//                    System.out.println("Cause: " + t);
//                    t = t.getCause();
//                }
//            }
//        }
//    }
//}
