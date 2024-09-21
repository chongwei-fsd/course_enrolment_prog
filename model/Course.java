package model;

public class Course {

    // properties of Course (default as private)
    String name;
    String id;
    int credits;

    // default constructor for Course
    public Course(String name, String id, int credits) {
        this.name = name;
        this.id = id;
        this.credits = credits;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("Course{name: %s, id: %s, credits: %d}", name, id, credits);
    }
}
