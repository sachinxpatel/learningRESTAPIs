package com.coding.learningRESTAPIs.service;

import com.coding.learningRESTAPIs.dto.AddStudentRequestDto;
import com.coding.learningRESTAPIs.dto.StudentDto;
import com.coding.learningRESTAPIs.entity.Student;

import java.util.List;
import java.util.Map;

public interface StudentService {

    List<StudentDto> getAllStudents();

    StudentDto getStudentById(Long id);

    StudentDto createNewStudent(AddStudentRequestDto addStudentRequestDto);

    void deleteStudentBYId(Long id);

    StudentDto updatestudent(Long id, AddStudentRequestDto addStudentRequestDto);

    StudentDto updatepartialstudent(Long id, Map<String, Object> update);
}
