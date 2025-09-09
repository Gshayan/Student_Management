package com.springboot.SBProject2.service.Implementation;

import com.springboot.SBProject2.DTO.AddStudentDto;
import com.springboot.SBProject2.DTO.StudentDto;
import com.springboot.SBProject2.entity.Student;
import com.springboot.SBProject2.repository.StudentRepository;
import com.springboot.SBProject2.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StudentServiceImplementation implements StudentService {
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<StudentDto> getAllStudent() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map(student -> modelMapper.map(student, StudentDto.class)).toList();
    }

    @Override
    public StudentDto getStudentById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Student not found with id: " + id));
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public StudentDto createNewStudent(AddStudentDto addStudentDto) {
        Student newStudent = modelMapper.map(addStudentDto, Student.class);
        Student student = studentRepository.save(newStudent);
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public void deleteStudentById(Long id) {
        if(!studentRepository.existsById(id)){
            throw new IllegalArgumentException("Student not found with id: " + id);
        }
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDto updateStudent(Long id, AddStudentDto addStudentDto) {
        Student student = studentRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Student not found with id: " + id));
        modelMapper.map(addStudentDto, student);
        student = studentRepository.save(student);
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public StudentDto updatePartialStudent(Long id, Map<String, Object> updatedField) {
        Student student = studentRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Student not found with id: " + id));
        updatedField.forEach((field, value) -> {
            switch (field) {
                case "name": student.setName(value.toString()); break;
                case "email": student.setEmail(value.toString()); break;
                default: throw new IllegalArgumentException("Invalid field name: " + field);
            }
        });
        Student updatedStudent = studentRepository.save(student);
        return modelMapper.map(updatedStudent, StudentDto.class);
    }
}
