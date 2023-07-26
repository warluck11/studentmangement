package com.itvedant.studentmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import com.itvedant.studentmanagement.dto.AddStudentDto;
import com.itvedant.studentmanagement.dto.UpdateStudentDto;
import com.itvedant.studentmanagement.entity.Student;
import com.itvedant.studentmanagement.service.StudentService;

@Controller
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping("/")
	public ModelAndView getAll(){
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject("students", this.studentService.getAllStudent());
		return modelAndView;
	}
	
	
	@GetMapping("/student/create")
	public String index(Model model) {
		model.addAttribute("studentform", new Student());
		return "addstudent";
	}
	
	@PostMapping("/student/add")
	public String create(@ModelAttribute AddStudentDto addStudentDto, Model model){
		model.addAttribute("studentform", new Student());
		this.studentService.createStudent(addStudentDto);
		return "redirect:/";
	}
	
	@GetMapping("/student/edit/{id}")
	public String edit(Model model,@PathVariable Integer id) {
		model.addAttribute("editstud", this.studentService.getStudent(id));
		return "editstudent";
	}
	
	@PostMapping("/student/update/{id}")
	public String update(@ModelAttribute UpdateStudentDto updateStudentDto, @PathVariable Integer id){
		this.studentService.UpdateStudent(id, updateStudentDto);
		return "redirect:/";
	}
	
	@GetMapping("/students/delete/{id}")
	public String delete(@PathVariable Integer id){
		this.studentService.deleteStudent(id);
		return "redirect:/";
	}

}
