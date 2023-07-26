package com.itvedant.studentmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itvedant.studentmanagement.dto.AddStudentDto;
import com.itvedant.studentmanagement.dto.UpdateStudentDto;
import com.itvedant.studentmanagement.entity.Student;
import com.itvedant.studentmanagement.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository; 
	
	
	public Student createStudent(AddStudentDto addStudentDto) {
		
		Student student = new Student();
		
		student.setName(addStudentDto.getName());
		student.setEmail(addStudentDto.getEmail());
		student.setAge(addStudentDto.getAge());
		student.setDepartment(addStudentDto.getDepartment());
		
		this.studentRepository.save(student);
		
		return student;
	}
	
	
	public List<Student> getAllStudent(){
		List<Student> students = this.studentRepository.findAll();
		return students;
	}
	
	public Student getStudent(Integer id) {
		Student student = new Student();
		student = this.studentRepository.findById(id).orElse(null);
		return student;
	}
	
	public Student UpdateStudent(Integer id, UpdateStudentDto updateStudentDto) {
		Student student = getStudent(id);
		
		if(updateStudentDto.getName() != null){
			student.setName(updateStudentDto.getName());
		}
		
		if(updateStudentDto.getAge() != null){
			student.setAge(updateStudentDto.getAge());
		}
		
		if(updateStudentDto.getEmail() != null){
			student.setEmail(updateStudentDto.getEmail());
		}
		
		if(updateStudentDto.getDepartment() != null){
			student.setDepartment(updateStudentDto.getDepartment());
		}
		
		this.studentRepository.save(student);
		
		return student;
	}
	
	
	public void deleteStudent(Integer id){
		Student student = getStudent(id);
		this.studentRepository.delete(student);
	}

}
