package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class MainMenu extends Application {

    @Override
    public void start(Stage primaryStage) {
        DataStore.loadData(); // Load data at start
        setPredefinedData(); 
        // Title Section
        Label appTitle = new Label("EcoAware: Climate Action Platform");
        appTitle.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        appTitle.setTextFill(Color.DARKGREEN);
        appTitle.setTextAlignment(TextAlignment.CENTER);

        Label appSubtitle = new Label("Taking steps towards a sustainable future");
        appSubtitle.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        appSubtitle.setTextFill(Color.GRAY);

        // Buttons Section
        Button adminDashboardButton = new Button("Admin Dashboard");
        adminDashboardButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14px;");
        adminDashboardButton.setOnAction(e -> showAdminLogin(primaryStage)); // Fixed Action

        Button publicDashboardButton = new Button("Public Dashboard");
        publicDashboardButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14px;");
        publicDashboardButton.setOnAction(e -> new UserLogin().show(primaryStage));

        Button exitButton = new Button("Exit");
        exitButton.setStyle("-fx-background-color: #FF5722; -fx-text-fill: white; -fx-font-size: 14px;");
        exitButton.setOnAction(e -> primaryStage.close());

        // Layout Section
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #F0F8FF;"); // Light blue background
        layout.getChildren().addAll(appTitle, appSubtitle, adminDashboardButton, publicDashboardButton, exitButton);

        // Scene and Stage Setup
        Scene scene = new Scene(layout, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("EcoAware: Climate Action Platform");
        primaryStage.show();
    }
    
    private void setPredefinedData() {
        if (DataStore.getEvents().isEmpty()) {
            DataStore.getEvents().add(new Event("Climate Conference", "2024-01-15", "City Hall"));
            DataStore.getEvents().add(new Event("Tree Plantation Drive", "2024-02-10", "Community Park"));
        }

        if (DataStore.getResources().isEmpty()) {
            DataStore.getResources().add("Reusable Bags");
            DataStore.getResources().add("Recycling Bins");
        }

        if (DataStore.getPledges().isEmpty()) {
            DataStore.getPledges().add("Asif: Reduce single-use plastics");
            DataStore.getPledges().add("Hamzah: Carpool to work");
            DataStore.getPledges().add("ABDALRHMAN: Clean Water tips");
            DataStore.getPledges().add("MARAWAN: Resource Management");
        }

        if (DataStore.getFeedback().isEmpty()) {
            DataStore.getFeedback().add("Great initiative, keep it up!");
            DataStore.getFeedback().add("More tree plantation drives, please.");
        }

        if (DataStore.getAnnouncements().isEmpty()) {
            DataStore.getAnnouncements().add("New recycling centers opened in downtown.");
            DataStore.getAnnouncements().add("Upcoming Climate Action Week in March.");
        }

        DataStore.saveData(); // Save predefined data only if added
    }


    // Admin Login Action (Correct Admin Login Method)
    private void showAdminLogin(Stage primaryStage) {
        // Call the admin login screen or redirect to Admin Dashboard after login
        new AdminLogin().show(primaryStage); // Assuming AdminLogin is the class for admin login
    }

    public static void main(String[] args) {
        launch(args);
    }
}
