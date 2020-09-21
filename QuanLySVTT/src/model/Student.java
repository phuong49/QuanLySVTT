package model;

public class Student {
    private int studentID;
    private String studentName;
    private String gender;
    private int age;
    private int clasID;

    public Student(String studentName, String gender, int age, int clasID) {
        this.studentName = studentName;
        this.gender = gender;
        this.age = age;
        this.clasID = clasID;
    }

    public Student(int studentID, String studentName, String gender, int age) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.gender = gender;
        this.age = age;
    }

    public Student(int studentID, String studentName, String gender, int age, int clasID) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.gender = gender;
        this.age = age;
        this.clasID = clasID;
    }

    public Student(String studentName, String gender, int age) {
        this.studentName = studentName;
        this.gender = gender;
        this.age = age;
    }

    public Student(int studentID, int clasID) {
        this.studentID=studentID;
        this.clasID=clasID;

    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getClasID() {
        return clasID;
    }

    public void setClasID(int clasID) {
        this.clasID = clasID;
    }
}