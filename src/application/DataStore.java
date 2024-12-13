package application;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataStore {
    private static final String EVENTS_FILE = "events.dat";
    private static final String RESOURCES_FILE = "resources.dat";
    private static final String PLEDGES_FILE = "pledges.dat";
    private static final String FEEDBACK_FILE = "feedback.dat";
    private static final String ANNOUNCEMENTS_FILE = "announcements.dat";

    private static List<Event> events = new ArrayList<>();
    private static List<String> resources = new ArrayList<>();
    private static List<String> pledges = new ArrayList<>();
    private static List<String> feedback = new ArrayList<>();
    private static List<String> announcements = new ArrayList<>();

    // Load all data from files
    public static void loadData() {
        events = loadFromFile(EVENTS_FILE);
        resources = loadFromFile(RESOURCES_FILE);
        pledges = loadFromFile(PLEDGES_FILE);
        feedback = loadFromFile(FEEDBACK_FILE);
        announcements = loadFromFile(ANNOUNCEMENTS_FILE);
    }

    // Save all data to files
    public static void saveData() {
        saveToFile(EVENTS_FILE, events);
        saveToFile(RESOURCES_FILE, resources);
        saveToFile(PLEDGES_FILE, pledges);
        saveToFile(FEEDBACK_FILE, feedback);
        saveToFile(ANNOUNCEMENTS_FILE, announcements);
    }

    // Getters for shared data
    public static List<Event> getEvents() {
        return events;
    }

    public static List<String> getResources() {
        return resources;
    }

    public static List<String> getPledges() {
        return pledges;
    }

    public static List<String> getFeedback() {
        return feedback;
    }

    public static List<String> getAnnouncements() {
        return announcements;
    }

    // Serialization utility methods
    private static <T> List<T> loadFromFile(String fileName) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            return (List<T>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    private static <T> void saveToFile(String fileName, List<T> list) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
