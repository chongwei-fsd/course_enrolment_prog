package service;

import model.Course;
import model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CourseServiceTest {

    private CourseService courseService;
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        courseService=new CourseService();
        studentService=new StudentService();
    }

    @Test
    void testEnrollStudent() {
        Student student=studentService.findStudent("stu01");
        courseService.enrollStudent("INTRO-CS-1",student);

        List<Student> enrolledStudents=courseService.enrolledStudents.get("INTRO-CS-1");
        assertNotNull(enrolledStudents,"list of enrolled students should not null");
        assertEquals(1,enrolledStudents.size(),"enrolled student size should be two");
    }

    @Test
    void testGetCourse() {
        Course course=courseService.getCourse("INTRO-CS-5");
        assertNotNull(course,"course should be found");
        assertEquals("INTRO-CS-5",course.getId(),"course id should match");
        assertNotEquals("INTRO-CS-11",course.getId(),"course id should not match");
    }

}