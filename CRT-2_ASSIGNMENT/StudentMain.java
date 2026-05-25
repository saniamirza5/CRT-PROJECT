import java.util.*;
class Student
{
    public int studentId;
    public String studentName;
    public double marks;

    public Student(int studentId,String studentName , double marks)
    {
        this.studentId = studentId;
        this.studentName = studentName;
        this.marks = marks;
    }

    public void displayDetails()
    {
        System.out.println("Student ID : " +studentId);
        System.out.println("Student Name : " +studentName);
        System.out.println("Student Marks : " +marks);
    }

}
public class StudentMain
{
    public static void main(String args[])
    {
        Student s1 = new Student(1,"Sania Mirza",9);
        Student s2 = new Student(2,"Anushka Koshta",8);
        Student s3 = new Student(3,"Ayan Khan", 10);
        s1.displayDetails();
        s2.displayDetails();
        s3.displayDetails();
        
        double highestMarks = s1.marks;
        String topper = s1.studentName;
        if(s2.marks > highestMarks)
        {
            highestMarks = s2.marks;
            topper = s2.studentName;
        }
        if(s3.marks> highestMarks)
        {
            highestMarks = s3.marks;
            topper = s3.studentName;
        }
        System.out.println("Highest marks in class is : " +highestMarks);
        System.out.println("Topper of class is : " +topper);
        double avgMarks = (s1.marks + s2.marks + s3.marks)/3.0;
        System.out.println("Average marks of class is : " +avgMarks);
    }
}
