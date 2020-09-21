package dao;

import model.Clas;
import model.Student;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private String jdbcURL = "jdbc:mysql://localhost:3306/quanlysvtt?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";
    private static final String INSERT_CLAS_SQL = "INSERT INTO classs" + "  (class_name, max_number, class_number) VALUES " + " (?, ?, ?);";
    private static final String SELECT_CLAS_BY_ID = "select class_id,class_name,max_number,class_number from classs where class_id =?";
    private static final String SELECT_ALL_CLAS = "select * from classs";
    private static final String DELETE_CLAS_SQL = "delete from classs where class_id = ?;";
    private static final String UPDATE_CLAS_SQL = "update classs set class_name = ?,max_number= ?, class_number =? where class_id = ?;";
    private static final String SELECT_NAME_SQL = "SELECT count( * ) as checkExist FROM classs where class_name LIKE" + " (?);";
    private static final String INSERT_STUDENT_SQL = "INSERT INTO student" + "  (student_name, gender, age,class_id) VALUES " + " (?, ?, ?,0);";
    private static final String SELECT_STUDENT_BY_ID = "select student_id,student_name,gender,age,class_id from student where student_id =?";
    private static final String SELECT_ALL_STUDENT = "select * from student";
    private static final String SELECT_ALL_STUDENT2 = "select*from student where class_id=0";
    private static final String DELETE_STUDENT_SQL = "delete from student where student_id = ?;";
    private static final String UPDATE_STUDENT_SQL = "update student set student_name = ?,gender= ?, age =? where student_id = ?;";
    private static final String UPDATE_ADDSTUDENT_SQL = "update student set student_name=?, gender=?, age=?, class_id=? where student_id=?";
    private int noOfRecords;


    public UserDao() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(this.jdbcURL, this.jdbcUsername, this.jdbcPassword);
        } catch (SQLException var3) {
            var3.printStackTrace();
        } catch (ClassNotFoundException var4) {
            var4.printStackTrace();
        }

        return connection;
    }

    public void addStudent(Student student) throws SQLException {
        String sql = "select student_id,student_name,gender,age from student where student_id=?";
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, student.getStudentID());
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            int studentID = rs.getInt("student_ID");
            String studentName = rs.getString("student_Name");
            String gender = rs.getString("gender");
            int age = rs.getInt("age");
            int classID = student.getClasID();
            student = new Student(studentID, studentName, gender, age, classID);
        }
        PreparedStatement preparedStatement1 = connection.prepareStatement(UPDATE_ADDSTUDENT_SQL);
        {
            preparedStatement1.setInt(5, student.getStudentID());
            preparedStatement1.setString(1, student.getStudentName());
            preparedStatement1.setString(2, student.getGender());
            preparedStatement1.setInt(3, student.getAge());
            preparedStatement1.setInt(4, student.getClasID());
            preparedStatement1.executeUpdate();
            JOptionPane.showMessageDialog(null, "Thêm thành công.");
        }
    }

    public void addStudent2(int clasID) throws SQLException {
        Clas clas = null;
        Connection connection = getConnection();
        String sql = "select*from classs where class_id=?";
        String sql2 = "update classs set class_name = ?,max_number= ?, class_number =? where class_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        {
            preparedStatement.setInt(1, clasID);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String clasName = rs.getString("class_Name");
                int maxNumber = rs.getInt("max_Number");
                int clasNumber = rs.getInt("class_Number") + 1;
                clas = new Clas(clasID, clasName, maxNumber, clasNumber);
            }
        }
        PreparedStatement statement = connection.prepareStatement(sql2);
        {
            statement.setInt(4, clas.getClasID());
            statement.setString(1, clas.getClasName());
            statement.setInt(2, clas.getMaxNumber());
            statement.setInt(3, clas.getClasNumber());
            statement.executeUpdate();

        }
    }


    public void insertClas(Clas clas) throws SQLException {
        System.out.println(INSERT_CLAS_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement2 = connection.prepareStatement(SELECT_NAME_SQL)) {
            preparedStatement2.setString(1, clas.getClasName());
            ResultSet rs = preparedStatement2.executeQuery();
            // show data
            int count;
            while (rs.next()) {
                count = rs.getInt(1);
                if (count == 0) {
                    PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CLAS_SQL);
                    {
                        preparedStatement.setString(1, clas.getClasName());
                        preparedStatement.setInt(2, clas.getMaxNumber());
                        preparedStatement.setInt(3, clas.getClasNumber());
                        System.out.println(preparedStatement);
                        int a=clas.getMaxNumber();
                        int b=clas.getClasNumber();
                        if(a>=b){
                        preparedStatement.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Thêm lớp thành công.");}
                        else{
                            JOptionPane.showMessageDialog(null, "Số lượng học sinh của lớp không thể lớn hơn số học sinh tối đa.", "error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Lớp đã tồn tại.", "error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException e) {
            printSQLException(e);
        }

    }

    public Clas selectClas(int clasID) {
        Clas clas = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CLAS_BY_ID);) {
            preparedStatement.setInt(1, clasID);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String clasName = rs.getString("class_Name");
                int maxNumber = rs.getInt("max_Number");
                int clasNumber = rs.getInt("class_Number");
                clas = new Clas(clasID, clasName, maxNumber, clasNumber);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return clas;
    }

    public List<Clas> selectAllClas(int offset, int noOfRecords) {
        String query = "select SQL_CALC_FOUND_ROWS * from classs limit "+ offset + ", " + noOfRecords;
        List<Clas> list = new ArrayList<Clas>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int clasID = rs.getInt("class_id");
                String clasName = rs.getString("class_name");
                int maxNumber = rs.getInt("Max_number");
                int clasNumber = rs.getInt("Class_number");
                list.add(new Clas(clasID, clasName, maxNumber, clasNumber));
            }
            rs.close();
            rs = preparedStatement.executeQuery("SELECT FOUND_ROWS()");
            if(rs.next())
                this.noOfRecords = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void deleteClas(int classId) throws SQLException {
        int opt = JOptionPane.showConfirmDialog(null, "Are you sure to Delete", "Delete", JOptionPane.YES_NO_OPTION);
        if (opt == 0) {
            Connection connection = getConnection();
            String sql = "select class_number from classs where class_id=?";
            PreparedStatement statement2 = connection.prepareStatement(sql);
            statement2.setInt(1, classId);
            ResultSet rs = statement2.executeQuery();
            rs.next();
            int clasNumber = rs.getInt("class_number");
            if (clasNumber == 0) {
                PreparedStatement statement = connection.prepareStatement(DELETE_CLAS_SQL);
                {
                    statement.setString(1, String.valueOf(classId));
                    statement.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Xóa lớp thành công.");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Không thể xóa vì lớp có học sinh.", "error", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    public void updateClas(Clas clas) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_CLAS_SQL);) {
            statement.setInt(4, clas.getClasID());
            statement.setString(1, clas.getClasName());
            statement.setInt(2, clas.getMaxNumber());
            int a=clas.getMaxNumber();
            int b=clas.getClasNumber();
            statement.setInt(3, clas.getClasNumber());
            if(a>=b) {
                statement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Thành công.");
            }
            else {
                JOptionPane.showMessageDialog(null, "Số lượng học sinh tối đa không thể ít hơn số học sinh trong lớp", "error", JOptionPane.ERROR_MESSAGE);
            }
        }


    }

    public void insertStudent(Student student) throws SQLException {
        System.out.println(INSERT_STUDENT_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT_SQL);) {
            preparedStatement.setString(1, student.getStudentName());
            preparedStatement.setString(2, student.getGender());
            preparedStatement.setInt(3, student.getAge());

            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Thêm thành công.");

        } catch (SQLException e) {
            printSQLException(e);
        }

    }

    public Student selectStudent(int studentID) {
        Student student = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT_BY_ID);) {
            preparedStatement.setInt(1, studentID);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String studentName = rs.getString("student_Name");
                String gender = rs.getString("gender");
                int age = rs.getInt("age");
                int clasID = rs.getInt("class_id");
                student = new Student(studentID, studentName, gender, age, clasID);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return student;
    }

    public List<Student> selectAllStudent2() {
        // using try-with-resources to avoid closing resources (boiler plate code)
        List<Student> student = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STUDENT2);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();
            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int studentID = rs.getInt("student_id");
                String studentName = rs.getString("student_name");
                String gender = rs.getString("gender");
                int age = rs.getInt("age");
                int clasID = rs.getInt("Class_id");
                student.add(new Student(studentID, studentName, gender, age, clasID));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return student;
    }

    public List<Student> selectAllStudent(int offset, int noOfRecords) {
        String query = "select SQL_CALC_FOUND_ROWS * from student limit "+ offset + ", " + noOfRecords;
        List<Student> list = new ArrayList<Student>();
        Student student=null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int studentID = rs.getInt("student_id");
                String studentName = rs.getString("student_name");
                String gender = rs.getString("gender");
                int age = rs.getInt("age");
                int clasID = rs.getInt("Class_id");
                list.add(new Student(studentID, studentName, gender, age, clasID));
            }
            rs.close();
            rs = preparedStatement.executeQuery("SELECT FOUND_ROWS()");
            if(rs.next())
                this.noOfRecords = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public int getNoOfRecords() {
        return noOfRecords;
    }




    public void deleteStudent(int studenID) throws SQLException {
        int opt = JOptionPane.showConfirmDialog(null, "Are you sure to Delete", "Delete", JOptionPane.YES_NO_OPTION);
        if (opt == 0) {
            String sql2 = "select class_id from student where student_id=?";
            String sql3 = "select*from classs where class_id=?";
            String sql4 = "update classs set  class_name = ?,max_number= ?, class_number =?  where class_id= ?";

            Connection connection = getConnection();
            PreparedStatement statement2 = connection.prepareStatement(sql2);
            PreparedStatement statement3 = connection.prepareStatement(sql3);
            PreparedStatement statement4 = connection.prepareStatement(sql4);
            statement2.setInt(1, studenID);
            ResultSet rs = statement2.executeQuery();
            rs.next();
            int clasID = rs.getInt("class_id");
            statement3.setInt(1, clasID);
            ResultSet rs3 = statement3.executeQuery();
            rs3.next();
            String clasName = rs3.getString("class_Name");
            int maxNumber = rs3.getInt("max_number");
            int clasNumber = rs3.getInt("class_number") - 1;
            statement4.setInt(4, clasID);
            statement4.setString(1, clasName);
            statement4.setInt(2, maxNumber);
            statement4.setInt(3, clasNumber);
            statement4.executeUpdate();
            PreparedStatement statement = connection.prepareStatement(DELETE_STUDENT_SQL);
            {
                statement.setString(1, String.valueOf(studenID));
                statement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Xóa học sinh thành công.");
            }

        }
    }

    public boolean updateStudent(Student student) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_STUDENT_SQL);) {
            statement.setInt(4, student.getStudentID());
            statement.setString(1, student.getStudentName());
            statement.setString(2, student.getGender());
            statement.setInt(3, student.getAge());


            rowUpdated = statement.executeUpdate() > 0;
            JOptionPane.showMessageDialog(null, "Sửa thành công.");
        }
        return rowUpdated;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }


    public List<Student> viewStudent(int clasID) throws SQLException {
        List<Student> student = new ArrayList<>();
        String sql = "select * from student where CLASS_ID=?";
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, clasID);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            int studentID = rs.getInt("student_ID");
            String studentName = rs.getString("student_Name");
            String gender = rs.getString("gender");
            int age = rs.getInt("age");
            student.add(new Student(studentID, studentName, gender, age, clasID));
        }
        return student;
    }

    public void deleteStudent2(int studentID) throws SQLException {
        int opt = JOptionPane.showConfirmDialog(null, "Are you sure to Delete", "Delete", JOptionPane.YES_NO_OPTION);
        if (opt == 0) {
            String sql = "update student set class_ID=0 where student_ID=?";
            String sql2 = "select class_id from student where student_id=?";
            String sql3 = "update classs set class_number=class_number-1 where class_id=?";
            Connection connection = getConnection();
            PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
            PreparedStatement preparedStatement3 = connection.prepareStatement(sql3);
            preparedStatement2.setInt(1, studentID);
            ResultSet rs = preparedStatement2.executeQuery();
            rs.next();
            int clasID = rs.getInt("class_id");
            preparedStatement3.setInt(1, clasID);
            preparedStatement3.executeUpdate();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, studentID);
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Xóa học sinh thành công.");

        }

    }

    public List<Clas> searchClas(String searchclas) throws SQLException {
        List<Clas> clasList = new ArrayList<>();
        Connection connection = getConnection();
        String sql = "select*from classs where class_name  LIKE ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "%" + searchclas + "%");
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            int clasID = rs.getInt("class_id");
            String clasName = rs.getString("class_name");
            int maxNumber = rs.getInt("max_number");
            int clasNumber = rs.getInt("class_number");
            clasList.add(new Clas(clasID, clasName, maxNumber, clasNumber));
        }
        return clasList;
    }

    public List<Student> searchStu(String searchstu) throws SQLException {
        List<Student> studentList = new ArrayList<>();
        Connection connection = getConnection();
        String sql = "select*from student s where s.student_name LIKE ? OR s.gender LIKE ? or s.age LIKE ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "%" + searchstu + "%");
        preparedStatement.setString(2, "%" + searchstu + "%");
        preparedStatement.setString(3, "%" + searchstu + "%");
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            int studentID = rs.getInt("student_id");
            String studentName = rs.getString("student_name");
            String gender = rs.getString("gender");
            int age = rs.getInt("age");
            int clasID = rs.getInt("class_id");
            studentList.add(new Student(studentID, studentName, gender, age, clasID));
        }
        return studentList;
    }
}