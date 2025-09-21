package com.coding.learningRESTAPIs.service.impl;

import com.coding.learningRESTAPIs.dto.AddStudentRequestDto;
import com.coding.learningRESTAPIs.dto.StudentDto;
import com.coding.learningRESTAPIs.entity.Student;
import com.coding.learningRESTAPIs.repository.StudentRepository;
import com.coding.learningRESTAPIs.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StudentServiceimpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students
                .stream()
                .map(student -> modelMapper.map(student, StudentDto.class))
                .toList();

    }


    @Override
    public StudentDto getStudentById(Long id) {
         Student student = studentRepository.findById(id)
                 .orElseThrow(() -> new IllegalArgumentException("student not found with id " + id));
         return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public StudentDto createNewStudent(AddStudentRequestDto addStudentRequestDto) {
        Student newstudent = modelMapper.map(addStudentRequestDto, Student.class);
       Student student = studentRepository.save(newstudent);
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public void deleteStudentBYId(Long id) {
        if (!studentRepository.existsById(id)) {
            throw  new IllegalArgumentException("Student does not exists by id " + id);
        }
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDto updatestudent(Long id, AddStudentRequestDto addStudentRequestDto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("student not found with id " + id));
        modelMapper.map(addStudentRequestDto, student);
        student = studentRepository.save(student);
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public StudentDto updatepartialstudent(Long id, Map<String, Object> update) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("student not found with id " + id));
        update.forEach((field,value)->{
           switch (field) {
               case "Name":
                   student.setName((String) value);
                   break;
               case "Email":
                   student.setEmail((String) value);
                   break;
               default:
                   throw  new IllegalArgumentException("field is not supported ");

           }
        });
        Student savedstudent = studentRepository.save(student);
        return modelMapper.map(savedstudent, StudentDto.class);
    }
}
