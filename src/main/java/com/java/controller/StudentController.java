package com.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.java.model.Student;
import com.java.service.StudentService;

@Controller
public class StudentController {
	@Autowired
	private StudentService service;
	
	@RequestMapping("/list")
	public String viewHomePage(Model model) {
		List<Student> students = service.listAll();
		model.addAttribute("liststudents", students);
		return "student";
	}
	
	@RequestMapping("/new")
	public String showNewStudentForm(Model model) {
		Student student = new Student();
		model.addAttribute("student", student);
		
		return "new_student";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveStudent(@ModelAttribute("student") Student student) {
		service.save(student);
		
		return "redirect:/list";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditStudentForm(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("edit_student");
		
		Student student = service.get(id);
		mav.addObject("student", student);
		
		return mav;
	}	
	
	@RequestMapping("/delete/{id}")
	public String deleteStudent(@PathVariable(name = "id") Long id) {
		service.delete(id);
		
		return "redirect:/list";
	}
}
