package application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PublicDashboard {
    public void start(Stage primaryStage) {
        TabPane publicTabs = new TabPane();

        Tab eventsTab = createEventsTab();
        Tab resourcesTab = createResourcesTab();
        Tab pledgesTab = createPledgesTab();
        Tab feedbackTab = createFeedbackTab();
        Tab announcementsTab = createAnnouncementsTab();

        publicTabs.getTabs().addAll(eventsTab, resourcesTab, pledgesTab, feedbackTab, announcementsTab);

        Button homeButton = new Button("Home");
        homeButton.setOnAction(e -> new MainMenu().start(primaryStage));

        VBox layout = new VBox(10, publicTabs, homeButton);
        layout.setPadding(new Insets(10));

        primaryStage.setScene(new Scene(layout, 800, 600));
        primaryStage.setTitle("Public Dashboard");
        primaryStage.show();
    }

    private Tab createEventsTab() {
        Tab tab = new Tab("View Events");
        VBox layout = new VBox(10);

        ListView<Event> eventList = new ListView<>();
        eventList.getItems().addAll(DataStore.getEvents());

        layout.getChildren().addAll(new Label("Available Events"), eventList);
        tab.setContent(layout);
        return tab;
    }

    private Tab createResourcesTab() {
        Tab tab = new Tab("View Resources");
        VBox layout = new VBox(10);

        ListView<String> resourceList = new ListView<>();
        resourceList.getItems().addAll(DataStore.getResources());

        layout.getChildren().addAll(new Label("Available Resources"), resourceList);
        tab.setContent(layout);
        return tab;
    }

    private Tab createPledgesTab() {
        Tab tab = new Tab("Submit Pledges");
        VBox layout = new VBox(10);

        ListView<String> pledgeList = new ListView<>();
        pledgeList.getItems().addAll(DataStore.getPledges());

        TextField pledgeField = new TextField();
        pledgeField.setPromptText("Enter your pledge");

        Button submitPledgeButton = new Button("Submit Pledge");
        submitPledgeButton.setOnAction(e -> {
            String pledge = pledgeField.getText();
            if (!pledge.isEmpty()) {
                DataStore.getPledges().add(pledge);
                pledgeList.getItems().add(pledge);
                DataStore.saveData();
                pledgeField.clear();
            }
        });

        layout.getChildren().addAll(new Label("Your Pledges"), pledgeList, pledgeField, submitPledgeButton);
        tab.setContent(layout);
        return tab;
    }

    private Tab createFeedbackTab() {
        Tab tab = new Tab("Submit Feedback");
        VBox layout = new VBox(10);

        ListView<String> feedbackList = new ListView<>();
        feedbackList.getItems().addAll(DataStore.getFeedback());

        TextField feedbackField = new TextField();
        feedbackField.setPromptText("Enter your feedback");

        Button submitFeedbackButton = new Button("Submit Feedback");
        submitFeedbackButton.setOnAction(e -> {
            String feedbackEntry = feedbackField.getText();
            if (!feedbackEntry.isEmpty()) {
                DataStore.getFeedback().add(feedbackEntry);
                feedbackList.getItems().add(feedbackEntry);
                DataStore.saveData();
                feedbackField.clear();
            }
        });

        layout.getChildren().addAll(new Label("Submitted Feedback"), feedbackList, feedbackField, submitFeedbackButton);
        tab.setContent(layout);
        return tab;
    }

    private Tab createAnnouncementsTab() {
        Tab tab = new Tab("View Announcements");
        VBox layout = new VBox(10);

        ListView<String> announcementList = new ListView<>();
        announcementList.getItems().addAll(DataStore.getAnnouncements());

        layout.getChildren().addAll(new Label("Announcements"), announcementList);
        tab.setContent(layout);
        return tab;
    }
}
