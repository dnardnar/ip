public class Task {
    private final String description;
    private boolean isDone;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public void markDone() {
        this.isDone = true;
    }
    public void markNotDone() {
        this.isDone = false;
    }
    public String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }
    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}