package student.exception;

public class StudentDataException extends RuntimeException {

    public StudentDataException(String message) {
        super("Invalid Student Data : " + message);
    }
}
