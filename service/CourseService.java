package service;

import model.Course;
import model.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseService {
    Map<String, List<Student>> enrolledStudents = new HashMap<>();
    HashMap<String, Course> courses = new HashMap<>();

    public CourseService() {
        courses.put("INTRO-CS-1", new Course("Introduction to Computer Science", "INTRO-CS-1", 9));
        courses.put("INTRO-CS-2", new Course("Introduction to Algorithms", "INTRO-CS-2", 9));
        courses.put("INTRO-CS-3", new Course("Algorithm Design and Problem Solving - Introduction ", "INTRO-CS-3", 9));
        courses.put("INTRO-CS-4", new Course("Algorithm Design and Problem Solving - Advanced", "INTRO-CS-4", 9));
        courses.put("INTRO-CS-5", new Course("Terminal Fundamentals", "INTRO-CS-5", 9));
        courses.put("INTRO-CS-6", new Course("Source Control Using Git and Github", "INTRO-CS-6", 9));
        courses.put("INTRO-CS-7", new Course("Agile Software Development with SCRUM", "INTRO-CS-7", 9));
    }

    public void enrollStudent(String courseId, Student student) {

        if (!enrolledStudents.containsKey(courseId)) {
            enrolledStudents.put(courseId, new ArrayList<>());
        }
        enrolledStudents.get(courseId).add(student);
    }

    public Course getCourse(String code) {
        if (courses.containsKey(code)) {
            return courses.get(code);
        }
        return null;
    }

    public void showSummary() {
        if (enrolledStudents.isEmpty()) {
            System.out.println("No student enrol to the course.");
        } else {
            System.out.println("Available Courses:");

            for (String key : courses.keySet()) //cs1
            {
                Course course = courses.get(key);  //course{....}
                System.out.println(course);
            }

            System.out.println("Enrolled Students");
            for (String key : enrolledStudents.keySet()) {
                List<Student> students = enrolledStudents.get(key);
                System.out.println("Students on Course " + key + ": ");
                for (Student student : students) {
                    System.out.println(student);
                }
            }
        }
    }

    public void showEnrolledSummary(String courseId) {
        if(!enrolledStudents.containsKey(courseId)){
            System.out.println("No student enrol to the course.");
        }
        else {
            System.out.println("Enrolled Students");
            enrolledStudents.forEach((cId,studCid)->{
                if(cId.equals(courseId)){
                    studCid.forEach(stud-> System.out.println(stud));
                }
            });
        }
    }

    public void setStudentScore(String courseId,String studentId,int grade){
        List<Student>students=enrolledStudents.get(courseId);
        for(Student stud:students){
            if(stud.getId().equals(studentId)){
                stud.setGrade(grade);
            }
        }
    }

}
