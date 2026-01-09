package student.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import student.contract.StudentRequestDto;
import student.contract.StudentResponseDto;
import student.service.StudentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/save")
    public ResponseEntity<StudentResponseDto> createStudent(
            @Valid @RequestBody StudentRequestDto request) {
        StudentResponseDto response = studentService.createStudent(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<StudentResponseDto>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/get-by-code/{studentCode}")
    public ResponseEntity<StudentResponseDto> getStudentByCode(@PathVariable Integer studentCode) {
        return ResponseEntity.ok(studentService.getStudentByCode(studentCode));
    }

    @PutMapping("update/{studentCode}")
    public ResponseEntity<StudentResponseDto> updateStudent(
            @PathVariable Integer studentCode,
            @Valid @RequestBody StudentRequestDto request) {
        return ResponseEntity.ok(studentService.updateStudentByCode(studentCode, request));
    }

    @DeleteMapping("delete/{studentCode}")
    public ResponseEntity<String> deleteStudent(@PathVariable Integer studentCode) {
        return ResponseEntity.ok(studentService.deleteStudentByCode(studentCode));
    }
}

