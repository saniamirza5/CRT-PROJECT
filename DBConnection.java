import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static void main(String[] args) {

        String url =
                "jdbc:mysql://localhost:3306/hospitaldb";

        String username = "root";

        String password = "system";

        try {

            Connection con =
                    DriverManager.getConnection(
                            url,
                            username,
                            password
                    );

            System.out.println(
                    "Database Connected Successfully");

            con.close();

        } catch (Exception e) {

            System.out.println(e);
        }
    }
}