package service;

import model.Course;
import model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    private StudentService studentService;
    private CourseService courseService;

    @BeforeEach
    void setUp() {
        studentService = new StudentService();
        courseService = new CourseService();
    }

    @Test
    void testFindStudent() {
        Student student = studentService.findStudent("stu01");
        assertNotNull(student, "student should be found");
        assertEquals("Adrian", student.getFirstName(), "name is Adrian");
        assertEquals("Tan", student.getLastName(), "name is tan");
        assertEquals("adriantan@gmail.com", student.getEmail(), "name is tan");
    }

    @Test
    void testNotFoundStudent() {
        Student student = studentService.findStudent("stu09");
        assertNull(student, "student should not be found");
    }

    @Test
    void testIsSubscribed() {
        assertTrue(studentService.isSubscribed("stu01"));
        assertFalse(studentService.isSubscribed("stu09"));
    }

    @Test
    void testIsAttendingCourse() {
        Course course = courseService.getCourse("INTRO-CS-1");
        Student student = studentService.findStudent("stu01");
        student.enrollToCourse(course);
        assertTrue(student.isAttendingCourse("INTRO-CS-1"));
    }

}