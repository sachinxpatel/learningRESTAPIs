package com.coding.learningRESTAPIs.controller;

import com.coding.learningRESTAPIs.dto.AddStudentRequestDto;
import com.coding.learningRESTAPIs.dto.StudentDto;
import com.coding.learningRESTAPIs.entity.Student;
import com.coding.learningRESTAPIs.repository.StudentRepository;
import com.coding.learningRESTAPIs.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity <List<StudentDto>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity <StudentDto> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PostMapping
    public ResponseEntity<StudentDto> createNewStudent(@RequestBody @Valid AddStudentRequestDto addStudentRequestDto) {
      return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createNewStudent(addStudentRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
       studentService.deleteStudentBYId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable Long id, @RequestBody AddStudentRequestDto addStudentRequestDto) {
        return ResponseEntity.ok(studentService.updatestudent(id,addStudentRequestDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<StudentDto> updatePartialStudent(@PathVariable Long id,
                                                           @RequestBody Map<String, Object> update) {
        return ResponseEntity.ok(studentService.updatepartialstudent(id,update));
    }
}
