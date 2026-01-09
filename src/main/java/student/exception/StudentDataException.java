package student.exception;

public class StudentDataException extends RuntimeException {
    public StudentDataException(String data) {
        super("Invalid Student Data: " + data);
    }
}
