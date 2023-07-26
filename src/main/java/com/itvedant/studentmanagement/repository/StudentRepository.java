package com.itvedant.studentmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itvedant.studentmanagement.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{

}
