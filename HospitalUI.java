import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.util.ArrayList;

// ================= ABSTRACT CLASS =================
abstract class Hospital {

    int patientId;
    String patientName;

    Hospital(int patientId, String patientName) {

        this.patientId = patientId;
        this.patientName = patientName;
    }

    abstract int calculateFee(int days);
}

// ================= ROOM CLASS =================
class Room extends Hospital {

    Room(int patientId, String patientName) {

        super(patientId, patientName);
    }

    @Override
    int calculateFee(int days) {

        return days * 700;
    }
}

// ================= BED CLASS =================
class Bed {

    int bedNumber;

    Room patient;

    Bed(int bedNumber) {

        this.bedNumber = bedNumber;
    }

    boolean isEmpty() {

        return patient == null;
    }
}

// ================= HOSPITAL MANAGEMENT =================
class HospitalManagement {

    ArrayList<Bed> beds = new ArrayList<>();

    HospitalManagement(int totalBeds) {

        for (int i = 1; i <= totalBeds; i++) {

            beds.add(new Bed(i));
        }
    }

    // Admit Patient
    String admitPatient(Room patient) {

        for (Bed bed : beds) {

            if (bed.isEmpty()) {

                bed.patient = patient;

                return "✅ " +
                        patient.patientName +
                        " admitted to Bed " +
                        bed.bedNumber;
            }
        }

        return "❌ Hospital Full";
    }

    // Search Patient
    String searchPatient(int patientId) {

        for (Bed bed : beds) {

            if (!bed.isEmpty() &&
                    bed.patient.patientId == patientId) {

                return "🔍 Patient Found\n\n"
                        + "Name : "
                        + bed.patient.patientName
                        + "\nPatient ID : "
                        + bed.patient.patientId
                        + "\nBed Number : "
                        + bed.bedNumber;
            }
        }

        return "❌ Patient Not Found";
    }

    // Discharge Patient
    String dischargePatient(int patientId, int days) {

        for (Bed bed : beds) {

            if (!bed.isEmpty() &&
                    bed.patient.patientId == patientId) {

                int fee =
                        bed.patient.calculateFee(days);

                String result =
                        "✅ Patient Discharged\n\n"
                                + "Name : "
                                + bed.patient.patientName
                                + "\nTotal Fee : ₹"
                                + fee;

                bed.patient = null;

                return result;
            }
        }

        return "❌ Patient Not Found";
    }

    // Display Status
    String displayStatus() {

        StringBuilder status =
                new StringBuilder();

        status.append(
                "🏥 HOSPITAL STATUS\n\n");

        for (Bed bed : beds) {

            if (bed.isEmpty()) {

                status.append(
                        "🛏 Bed ")
                        .append(bed.bedNumber)
                        .append(" : Empty\n");

            } else {

                status.append(
                        "🛏 Bed ")
                        .append(bed.bedNumber)
                        .append(" : ")
                        .append(bed.patient.patientName)
                        .append("\n");
            }
        }

        return status.toString();
    }
}

// ================= MAIN JAVAFX CLASS =================
public class HospitalUI extends Application {

    HospitalManagement hospital =
            new HospitalManagement(5);

    // ================= DATABASE =================
    String url =
            "jdbc:mysql://localhost:3306/hospitaldb";

    String username = "root";

    String password = "system";

    @Override
    public void start(Stage stage) {

        // ================= TITLE =================
        Label title =
                new Label("🏥 Hospital Management System");

        title.setFont(Font.font("Arial", 28));

        title.setTextFill(Color.WHITE);

        // ================= TEXTFIELDS =================
        TextField idField =
                new TextField();

        idField.setPromptText("Enter Patient ID");

        styleTextField(idField);

        TextField nameField =
                new TextField();

        nameField.setPromptText("Enter Patient Name");

        styleTextField(nameField);

        TextField daysField =
                new TextField();

        daysField.setPromptText("Enter Number of Days");

        styleTextField(daysField);

        // ================= BUTTONS =================
        Button admitBtn =
                new Button("Admit");

        Button searchBtn =
                new Button("Search");

        Button dischargeBtn =
                new Button("Discharge");

        Button statusBtn =
                new Button("Show Status");

        styleButton(admitBtn, "#4CAF50");

        styleButton(searchBtn, "#2196F3");

        styleButton(dischargeBtn, "#f44336");

        styleButton(statusBtn, "#9C27B0");

        // ================= OUTPUT AREA =================
        TextArea output =
                new TextArea();

        output.setPrefHeight(250);

        output.setEditable(false);

        output.setStyle(
                "-fx-font-size: 15px;" +
                        "-fx-background-color: white;" +
                        "-fx-border-color: #cccccc;" +
                        "-fx-border-radius: 10;" +
                        "-fx-background-radius: 10;"
        );

        // ================= ADMIT ACTION =================
        admitBtn.setOnAction(e -> {

            try {

                int id =
                        Integer.parseInt(
                                idField.getText());

                String name =
                        nameField.getText();

                Room patient =
                        new Room(id, name);

                String result =
                        hospital.admitPatient(patient);

                // DATABASE CONNECTION
                Connection con =
                        DriverManager.getConnection(
                                url,
                                username,
                                password
                        );

                // INSERT QUERY
                String query =
                        "INSERT INTO patients VALUES (?, ?, ?, ?)";

                PreparedStatement ps =
                        con.prepareStatement(query);

                ps.setInt(1, id);

                ps.setString(2, name);

                ps.setInt(3, 1);

                ps.setInt(4, 0);

                ps.executeUpdate();

                con.close();

                output.setText(
                        result +
                                "\n\n✅ Patient Saved in Database");

            } catch (Exception ex) {

                output.setText(
                        "⚠ Error : " + ex.getMessage());
            }
        });

        // ================= SEARCH ACTION =================
        searchBtn.setOnAction(e -> {

            try {

                int id =
                        Integer.parseInt(
                                idField.getText());

                output.setText(
                        hospital.searchPatient(id));

            } catch (Exception ex) {

                output.setText(
                        "⚠ Invalid Patient ID");
            }
        });

        // ================= DISCHARGE ACTION =================
        dischargeBtn.setOnAction(e -> {

            try {

                int id =
                        Integer.parseInt(
                                idField.getText());

                int days =
                        Integer.parseInt(
                                daysField.getText());

                output.setText(
                        hospital.dischargePatient(
                                id,
                                days));

            } catch (Exception ex) {

                output.setText(
                        "⚠ Enter Correct Details");
            }
        });

        // ================= STATUS ACTION =================
        statusBtn.setOnAction(e -> {

            output.setText(
                    hospital.displayStatus());
        });

        // ================= BUTTON LAYOUT =================
        HBox buttonBox =
                new HBox(15);

        buttonBox.setAlignment(Pos.CENTER);

        buttonBox.getChildren().addAll(
                admitBtn,
                searchBtn,
                dischargeBtn,
                statusBtn
        );

        // ================= MAIN LAYOUT =================
        VBox root =
                new VBox(20);

        root.setPadding(new Insets(25));

        root.setAlignment(Pos.TOP_CENTER);

        root.setStyle(
                "-fx-background-color: linear-gradient(to bottom right, #1e3c72, #2a5298);"
        );

        root.getChildren().addAll(
                title,
                idField,
                nameField,
                daysField,
                buttonBox,
                output
        );

        // ================= SCENE =================
        Scene scene =
                new Scene(root, 700, 650);

        stage.setTitle(
                "Hospital Management System");

        stage.setScene(scene);

        stage.show();
    }

    // ================= BUTTON STYLE METHOD =================
    private void styleButton(Button button, String color) {

        button.setStyle(
                "-fx-background-color: " + color + ";" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 15px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 12;" +
                        "-fx-padding: 10 20 10 20;"
        );

        button.setPrefWidth(130);
    }

    // ================= TEXTFIELD STYLE METHOD =================
    private void styleTextField(TextField textField) {

        textField.setMaxWidth(350);

        textField.setStyle(
                "-fx-font-size: 15px;" +
                        "-fx-background-radius: 10;" +
                        "-fx-border-radius: 10;" +
                        "-fx-padding: 10;"
        );
    }

    // ================= MAIN METHOD =================
    public static void main(String[] args) {

        launch();
    }
}