import model.Course;
import model.Student;

import service.StudentService;
import utils.PrinterHelper;
import service.CourseService;

import java.text.ParseException;
import java.util.Scanner;

public class Main
{

    public static void main( String[] args )
            throws ParseException
    {
        //create instance
        StudentService studentService = new StudentService();
        CourseService courseService = new CourseService();
        Scanner scanner = new Scanner( System.in );
        int option = 0;

        do
        {
            PrinterHelper.showMainMenu();
            option = scanner.nextInt();
            switch ( option )
            {
                case 1:
                    registerStudent( studentService, scanner );
                    break;
                case 2:
                    findStudent( studentService, scanner );
                    break;
                case 3:
                    gradeStudent( studentService,courseService,scanner );
                    break;
                case 4:
                    enrollStudentToCourse( studentService, courseService, scanner );
                    break;
                case 5:
                    showStudentsSummary( studentService, scanner );
                    break;
                case 6:
                    showCoursesSummary( courseService, scanner );
                    break;
                case 7:
                    showAverageGrade( studentService,courseService, scanner );
                    break;
            }
        }
        while ( option != 8 );
    }

    private static void enrollStudentToCourse( StudentService studentService, CourseService courseService,
                                               Scanner scanner )
    {
        System.out.println( "Insert student ID" );
        String studentId = scanner.next();
        Student student = studentService.findStudent( studentId );
        if ( student == null )
        {
            System.out.println( "Invalid Student ID" );
            return;
        }
        System.out.println( student );
        System.out.println( "Insert course ID" );
        String courseId = scanner.next();
        Course course = courseService.getCourse( courseId );
        if ( course == null )
        {
            System.out.println( "Invalid Course ID" );
            return;
        }
        System.out.println( course );
        courseService.enrollStudent( courseId, student );
        studentService.enrollToCourse( studentId, course );
        System.out.println( "Student with ID: " + studentId + " enrolled successfully to " + courseId );

    }

    private static void showAverageGrade( StudentService studentService, CourseService courseService, Scanner scanner )
    {
        Course course=null;
        String courseId="";
        while(course==null){
            System.out.println( "Insert course ID: " );
            courseId=scanner.next();
            course=courseService.getCourse(courseId);
            if(course==null){
                System.out.println(courseId+" not found.");
            }
        }
        courseService.showEnrolledSummary(courseId);
        System.out.println("Average grade: "+studentService.calAverage(courseId));
    }

    private static void showCoursesSummary( CourseService courseService, Scanner scanner )
    {
        courseService.showSummary();
    }

    private static void showStudentsSummary( StudentService studentService, Scanner scanner )
    {
        studentService.showSummary();
    }

    private static void gradeStudent( StudentService studentService,CourseService courseService,Scanner scanner )
    {
        Student student=null;
        String studentId="";
        String courseId="";
        while(student==null){
            System.out.println("Insert student ID: ");
            studentId=scanner.next();
            student=studentService.findStudent(studentId);
            if(student==null){
                System.out.println(studentId+" not found.");
            }
        }
        System.out.println(student);

        System.out.println("Insert course ID: ");
        courseId=scanner.next();
        while(!student.isAttendingCourse(courseId)){
            System.out.println(courseId+" not found.");
            courseId=scanner.next();
        }

        System.out.println("Insert grade: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Only numeric input (1-100)");
            scanner.next(); //clear the invalid input
        }
        int grade=scanner.nextInt();
        studentService.setStudentScore(courseId,studentId,grade);
        courseService.setStudentScore(courseId,studentId,grade);
        System.out.println(studentService.getStudentResult(studentId));//show result
    }

    private static void findStudent( StudentService studentService, Scanner scanner )
    {
        System.out.println( "Enter student ID: " );
        String studentId = scanner.next();
        Student student = studentService.findStudent( studentId );
        if ( student != null )
        {
            System.out.println( "Student Found: " );
            System.out.println( student );
        }
        else
        {
            System.out.println( "Student with Id = " + studentId + " not found" );
        }
    }

    private static void registerStudent( StudentService studentService, Scanner scanner )
            throws ParseException
    {
        Student student = PrinterHelper.createStudentMenu( scanner );
        studentService.subscribeStudent( student );
    }
}
