package student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import student.model.Student;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface StudentRepository extends JpaRepository <Student, UUID> {
    Optional<Student> findByStudentCode(Integer studentCode);

    Optional<Student> findTopByOrderByStudentCodeDesc();
}
