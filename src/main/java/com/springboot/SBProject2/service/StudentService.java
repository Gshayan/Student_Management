package com.springboot.SBProject2.service;

import com.springboot.SBProject2.DTO.AddStudentDto;
import com.springboot.SBProject2.DTO.StudentDto;

import java.util.List;
import java.util.Map;

public interface StudentService {
    List<StudentDto> getAllStudent();

    StudentDto getStudentById(Long id);

    StudentDto createNewStudent(AddStudentDto addStudentDto);

    void deleteStudentById(Long id);

    StudentDto updateStudent(Long id, AddStudentDto addStudentDto);

    StudentDto updatePartialStudent(Long id, Map<String, Object> updatedField);
}
