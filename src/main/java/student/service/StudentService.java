package student.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import student.exception.StudentNotFoundException;
import student.contract.StudentRequestDto;
import student.contract.StudentResponseDto;
import student.model.Student;
import student.repository.StudentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;
    private static final Integer STUDENT_CODE_START = 100000;

    @Transactional
    public StudentResponseDto createStudent(StudentRequestDto request)
    {
        validateStudentData (request);
        Integer lastStudentCode = studentRepository.findTopByOrderByStudentCodeDesc()
                .map(Student::getStudentCode)
                .orElse(null);
        Integer newStudentCode = (lastStudentCode == null) ? STUDENT_CODE_START : lastStudentCode + 1;

        Student student = modelMapper.map(request, Student.class);
        student.setStudentCode(newStudentCode);
        Student saved = studentRepository.save(student);
        return modelMapper.map(saved, StudentResponseDto.class);
    }

    private void validateStudentData(StudentRequestDto request)
    {
        if(request.getName() == null)
        {
                throw new StudentDataException("Name");
        } 
        if(request.getAge() == null || request.getAge()<3 || request.getAge()> 30 )
        {      
                throw new StudentDataException("Age");
        }
                if(request.getCourse() == null)
        {
                throw new StudentDataException("Course");
        } 
    }
    
    public List<StudentResponseDto> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(student -> modelMapper.map(student, StudentResponseDto.class))
                .toList();
    }

    public StudentResponseDto getStudentByCode(Integer studentCode) {
        return studentRepository.findByStudentCode(studentCode)
                .map(this::mapToDto)
                .orElse(null);
    }

    private StudentResponseDto mapToDto(Student student) {
            return new StudentResponseDto(
                    student.getId(),
                    student.getStudentCode(),
                    student.getName(),
                    student.getAge(),
                    student.getCourse(),
                    student.getEmail()
            );
    }

    @Transactional
    public StudentResponseDto updateStudentByCode(Integer studentCode, StudentRequestDto request) {
        Student student = studentRepository.findByStudentCode(studentCode)
                .orElseThrow(() -> new StudentNotFoundException(studentCode));
        if (request.getName() != null) student.setName(request.getName());
        if (request.getAge() != null) student.setAge(request.getAge());
        if (request.getCourse() != null) student.setCourse(request.getCourse());
        if (request.getEmail() != null) student.setEmail(request.getEmail());
        Student updatedStudent = studentRepository.save(student);
        return modelMapper.map(updatedStudent, StudentResponseDto.class);
    }

    public String deleteStudentByCode(Integer studentCode) {
        Student student = studentRepository.findByStudentCode(studentCode)
                .orElseThrow(() -> new StudentNotFoundException(studentCode));

        studentRepository.delete(student);
        return "Deleted successfully";
    }

}

