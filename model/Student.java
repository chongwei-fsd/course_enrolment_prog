package model;

import java.util.*;

public class Student implements Evaluation {
    String firstName;
    String lastName;
    String id;
    Date birthDate;
    String email;
    int grade;
    double average;

    ArrayList<Course> enrolledCourse = new ArrayList<>();
    Map<String, Course> approvedCourses = new HashMap<>();
    Map<String, Integer> courseGrade = new HashMap<>();

    public Student(String firstName, String lastName, String email, Date birthDate, String id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        this.id = id;
        this.grade = 0;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getGrade() {
        return grade;
    }


    public boolean enrollToCourse(Course course) {
        //TODO: 1 if the course is not found in Hashmap approvedCourses
        //TODO: 2. add it to the student's approvedCourses
        if (!approvedCourses.containsKey(course.getId()))           // Hashmap key:value pair
            approvedCourses.put(course.getId(), course);

        return enrolledCourse.add(course);
    }

    public void setCourseGrades(String courseid, int grade) {
        if (approvedCourses.containsKey(courseid)) {
            courseGrade.put(courseid, grade);
        }
    }

    public Integer getCourseGrade(String courseId) {
        return courseGrade.get(courseId);
    }

    public String getGradeResult(){
        String result="";
        List<Course> passedCourses = findPassedCourses();
        if (passedCourses.isEmpty())result="fail";
        if (!passedCourses.isEmpty())result="pass";
        return result;
    }

    public void registerApprovedCourse(Course course) {
        approvedCourses.put(course.getId(), course);
    }

    public boolean isCourseApproved(String courseId) {
        //TODO implement this method
        return approvedCourses.containsKey(courseId);
    }

    // CHALLENGE IMPLEMENTATION: Read README.md to find instructions on how to solve.
    public List<Course> findPassedCourses() {
        //TODO implement this method
        ArrayList<Course> passedCourses = new ArrayList<>();
        courseGrade.forEach((courseid, grade) -> {
            if (grade >= 50) {
                passedCourses.add(approvedCourses.get(courseid));
            }
        });
        return passedCourses;
    }

    public boolean isAttendingCourse(String courseId) {
        //TODO implement this method
        Course courseid = approvedCourses.get(courseId);
        return enrolledCourse.contains(courseid);
    }

    @Override
    public String toString() {
        return String.format("Student{firstName: %s, lastName: %s, grade: %d, email: %s, birthdate: %s, id: %s}", firstName, lastName, grade, email, birthDate, id);
    }

    @Override
    public double getAverage() {
        return 0;
    }

    @Override
    public List<Course> getApprovedCourses() {
        ArrayList<Course> approvedCourses = new ArrayList<>();
        boolean status = approvedCourses.addAll(this.approvedCourses.values());
        if (status == false) { //false when no added - approvedCourses is empty
            return null;
        }
        return approvedCourses; //true when added - approvedCourses has course
    }


}
