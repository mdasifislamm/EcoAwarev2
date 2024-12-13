package application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminDashboard {
    public void start(Stage primaryStage) {
        TabPane adminTabs = new TabPane();

        Tab eventsTab = createEventsTab();
        Tab resourcesTab = createResourcesTab();
        Tab pledgesTab = createPledgesTab();
        Tab feedbackTab = createFeedbackTab();
        Tab announcementsTab = createAnnouncementsTab();

        adminTabs.getTabs().addAll(eventsTab, resourcesTab, pledgesTab, feedbackTab, announcementsTab);

        Button homeButton = new Button("Home");
        homeButton.setOnAction(e -> new MainMenu().start(primaryStage));

        VBox layout = new VBox(10, adminTabs, homeButton);
        layout.setPadding(new Insets(10));

        primaryStage.setScene(new Scene(layout, 800, 600));
        primaryStage.setTitle("Admin Dashboard");
        primaryStage.show();
    }

    private Tab createEventsTab() {
        Tab tab = new Tab("Manage Events");
        VBox layout = new VBox(10);

        ListView<Event> eventList = new ListView<>();
        eventList.getItems().addAll(DataStore.getEvents());

        TextField titleField = new TextField();
        titleField.setPromptText("Event Title");

        TextField dateField = new TextField();
        dateField.setPromptText("Event Date");

        TextField locationField = new TextField();
        locationField.setPromptText("Event Location");

        Button addEventButton = new Button("Add Event");
        addEventButton.setOnAction(e -> {
            String title = titleField.getText();
            String date = dateField.getText();
            String location = locationField.getText();
            if (!title.isEmpty() && !date.isEmpty() && !location.isEmpty()) {
                Event newEvent = new Event(title, date, location);
                DataStore.getEvents().add(newEvent);
                eventList.getItems().add(newEvent);
                DataStore.saveData();
                titleField.clear();
                dateField.clear();
                locationField.clear();
            }
        });

        Button deleteEventButton = new Button("Delete Selected");
        deleteEventButton.setOnAction(e -> {
            Event selected = eventList.getSelectionModel().getSelectedItem();
            if (selected != null) {
                DataStore.getEvents().remove(selected);
                eventList.getItems().remove(selected);
                DataStore.saveData();
            }
        });

        layout.getChildren().addAll(new Label("Manage Events"), eventList, titleField, dateField, locationField, addEventButton, deleteEventButton);
        tab.setContent(layout);
        return tab;
    }

    private Tab createResourcesTab() {
        Tab tab = new Tab("Manage Resources");
        VBox layout = new VBox(10);

        ListView<String> resourceList = new ListView<>();
        resourceList.getItems().addAll(DataStore.getResources());

        TextField resourceField = new TextField();
        resourceField.setPromptText("Resource Name");

        Button addResourceButton = new Button("Add Resource");
        addResourceButton.setOnAction(e -> {
            String resource = resourceField.getText();
            if (!resource.isEmpty()) {
                DataStore.getResources().add(resource);
                resourceList.getItems().add(resource);
                DataStore.saveData();
                resourceField.clear();
            }
        });

        Button deleteResourceButton = new Button("Delete Selected");
        deleteResourceButton.setOnAction(e -> {
            String selected = resourceList.getSelectionModel().getSelectedItem();
            if (selected != null) {
                DataStore.getResources().remove(selected);
                resourceList.getItems().remove(selected);
                DataStore.saveData();
            }
        });

        layout.getChildren().addAll(new Label("Manage Resources"), resourceList, resourceField, addResourceButton, deleteResourceButton);
        tab.setContent(layout);
        return tab;
    }

    private Tab createPledgesTab() {
        Tab tab = new Tab("View Pledges");
        VBox layout = new VBox(10);

        ListView<String> pledgeList = new ListView<>();
        pledgeList.getItems().addAll(DataStore.getPledges());

        layout.getChildren().addAll(new Label("User Pledges"), pledgeList);
        tab.setContent(layout);
        return tab;
    }

    private Tab createFeedbackTab() {
        Tab tab = new Tab("Manage Feedback");
        VBox layout = new VBox(10);

        ListView<String> feedbackList = new ListView<>();
        feedbackList.getItems().addAll(DataStore.getFeedback());

        Button deleteFeedbackButton = new Button("Delete Selected");
        deleteFeedbackButton.setOnAction(e -> {
            String selected = feedbackList.getSelectionModel().getSelectedItem();
            if (selected != null) {
                DataStore.getFeedback().remove(selected);
                feedbackList.getItems().remove(selected);
                DataStore.saveData();
            }
        });

        layout.getChildren().addAll(new Label("Feedback"), feedbackList, deleteFeedbackButton);
        tab.setContent(layout);
        return tab;
    }

    private Tab createAnnouncementsTab() {
        Tab tab = new Tab("Manage Announcements");
        VBox layout = new VBox(10);

        ListView<String> announcementList = new ListView<>();
        announcementList.getItems().addAll(DataStore.getAnnouncements());

        TextField announcementField = new TextField();
        announcementField.setPromptText("New Announcement");

        Button addAnnouncementButton = new Button("Add Announcement");
        addAnnouncementButton.setOnAction(e -> {
            String announcement = announcementField.getText();
            if (!announcement.isEmpty()) {
                DataStore.getAnnouncements().add(announcement);
                announcementList.getItems().add(announcement);
                DataStore.saveData();
                announcementField.clear();
            }
        });

        Button deleteAnnouncementButton = new Button("Delete Selected");
        deleteAnnouncementButton.setOnAction(e -> {
            String selected = announcementList.getSelectionModel().getSelectedItem();
            if (selected != null) {
                DataStore.getAnnouncements().remove(selected);
                announcementList.getItems().remove(selected);
                DataStore.saveData();
            }
        });

        layout.getChildren().addAll(new Label("Announcements"), announcementList, announcementField, addAnnouncementButton, deleteAnnouncementButton);
        tab.setContent(layout);
        return tab;
    }
}
