package service;

import model.Course;
import model.Evaluation;
import model.Student;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentService {
    private final Map<String, Student> students = new HashMap<>();

    public StudentService() {
        subscribeStudent(new Student("Adrian", "Tan", "adriantan@gmail.com", new Date(101, 0, 17), "stu01")); // 2001 Jan 17
        subscribeStudent(new Student("Goh", "Hui Xin", "gohhuixin@hotmail.com", new Date(102, 8, 4), "stu02")); // 2002 Sep 4
        subscribeStudent(new Student("Alex", "Lim", "alexlim@gmail.com", new Date(103, 4, 30), "stu03")); // 2003 May 30
    }

    public void subscribeStudent(Student student) {
        students.put(student.getId(), student);
    }

    public Student findStudent(String studentId) {
        if (students.containsKey(studentId)) {
            return students.get(studentId);
        }
        return null;
    }

    public boolean isSubscribed(String studentId) {
        //TODO implement this method
        return students.containsKey(studentId);
    }

    public void showSummary() {
        //TODO implement
        System.out.println("Students and the courses enrolled into");
        for (String key : students.keySet()) {
            Student stud = students.get(key);
            System.out.println(stud.getFirstName() + " " + stud.getLastName());
            List<Course> courses = stud.getApprovedCourses();
            if (courses == null) {
                System.out.println("no course found");
            }
            else {
                for(Course course:courses){
                    System.out.println(course);
                    if(stud.getGrade()!=0){ //students are graded then show
                        System.out.println("Grade: "+stud.getGrade());
                        System.out.println("Result: "+stud.getGradeResult());
                    }
                }
            }
            System.out.println("***********************************************************");
        }
    }

    public void enrollToCourse(String studentId, Course course) {
        if (students.containsKey(studentId)) {
            students.get(studentId).enrollToCourse(course);
        }
    }

    public void setStudentScore(String courseId, String studentId, int grade) {
        students.get(studentId).setCourseGrades(courseId, grade);
    }

    public String getStudentResult(String studentId){
        return students.get(studentId).getGradeResult();
    }

    public double calAverage (String courseId) {
        double sum = 0, count = 0, average;
        for(Student student : students.values()){
            Integer grade = student.getCourseGrade(courseId);
            if (grade != null) {
                sum += grade; //sum of students' grade
                count++; //number of students
            }
        }
        average=sum/count;
        return average;
    }





}