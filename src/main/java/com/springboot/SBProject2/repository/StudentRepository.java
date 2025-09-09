package com.springboot.SBProject2.repository;

import com.springboot.SBProject2.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
