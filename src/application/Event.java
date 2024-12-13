package application;

import javafx.beans.property.SimpleStringProperty;
import java.io.IOException;
import java.io.Serializable;

public class Event implements Serializable {
    private static final long serialVersionUID = 1L;

    private transient SimpleStringProperty title;
    private transient SimpleStringProperty date;
    private transient SimpleStringProperty location;

    public Event(String title, String date, String location) {
        this.title = new SimpleStringProperty(title);
        this.date = new SimpleStringProperty(date);
        this.location = new SimpleStringProperty(location);
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getDate() {
        return date.get();
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getLocation() {
        return location.get();
    }

    public void setLocation(String location) {
        this.location.set(location);
    }

    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeUTF(getTitle());
        out.writeUTF(getDate());
        out.writeUTF(getLocation());
    }

    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.title = new SimpleStringProperty(in.readUTF());
        this.date = new SimpleStringProperty(in.readUTF());
        this.location = new SimpleStringProperty(in.readUTF());
    }

    @Override
    public String toString() {
        return String.format("Title: %s | Date: %s | Location: %s", getTitle(), getDate(), getLocation());
    }
}
