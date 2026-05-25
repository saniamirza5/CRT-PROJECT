import java.util.Scanner;

public class EmployeeMenu {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String employeeName = "";
        String attendanceStatus = "";

        int choice;

        do {
    
            System.out.println("\n----- MENU -----");
            System.out.println("1. Mark Attendance");
            System.out.println("2. View Employee Details");
            System.out.println("3. Calculate Working Hours");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                
                    System.out.print("Enter Employee Name: ");
                    employeeName = sc.nextLine();

                    System.out.print("Enter Attendance Status (Present/Absent): ");
                    attendanceStatus = sc.nextLine();

                    System.out.println("Attendance Marked Successfully");
                    break;

                case 2:

                    System.out.println("\n--- Employee Details ---");
                    System.out.println("Employee Name : " + employeeName);
                    System.out.println("Attendance    : " + attendanceStatus);
                    break;

                case 3:
                    
                    System.out.print("Enter Working Days: ");
                    int days = sc.nextInt();

                    System.out.print("Enter Hours Per Day: ");
                    int hours = sc.nextInt();

                    int totalHours = days * hours;

                    System.out.println("Total Working Hours: " + totalHours);
                    break;

                case 4:
        
                    System.out.println("Exiting System...");
                    break;

                default:
                    System.out.println("Invalid Choice");
            }

        } while (choice != 4);

        sc.close();
    }
}