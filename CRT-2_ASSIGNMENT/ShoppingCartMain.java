import java.util.Scanner;

public class ShoppingCartMain {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String correctUsername = "sania";
        String correctPassword = "1234";

        System.out.print("Enter Username: ");
        String username = sc.nextLine();

        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        if (username.equals(correctUsername) && password.equals(correctPassword)) {

            System.out.println("Login Successful");

            System.out.print("Enter Product Name: ");
            String productName = sc.nextLine();

            System.out.print("Enter Quantity: ");
            int quantity = sc.nextInt();

            System.out.print("Enter Price per Item: ");
            double price = sc.nextDouble();

            double totalAmount = quantity * price;
            double discount = 0;

            if (totalAmount > 5000) {
                discount = totalAmount * 0.10;
            }

            double finalAmount = totalAmount - discount;

            System.out.println("\n----- BILL -----");
            System.out.println("Product Name : " + productName);
            System.out.println("Total Amount : " + totalAmount);
            System.out.println("Discount     : " + discount);
            System.out.println("Final Amount : " + finalAmount);

        } else {
            System.out.println("Invalid Credentials");
        }

        sc.close();
    }
}