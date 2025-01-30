public class Deadline extends Task {
    private final String end;

    public Deadline(String description, String end) {
        super(description);
        this.end = end;
    }

    public Deadline(String description, String end, boolean isDone) {
        super(description, isDone);
        this.end = end;
    }

    @Override
    public String toDataString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + end;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + end + ")";
    }
}
