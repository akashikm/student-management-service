package student.exception;

public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException(Integer studentCode) {
        super("Student not found with code: " + studentCode);
    }
}
