package com.java.cotroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.java.model.Student;

@Controller // 注解定义为控制层 通过context:component-scan进行扫描，并实例化成bean
@RequestMapping("/student") // 请求路径
public class StudentController {
	private static List<Student> studentList = new ArrayList<Student>();

	static {
		studentList.add(new Student(1, "张三", 11));
		studentList.add(new Student(2, "张三2", 11));
		studentList.add(new Student(3, "张三3", 11));
	}

	@RequestMapping("/list") // 最终的请求路径是重student/list.do
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("studentList", studentList);
		mav.setViewName("student/list");// 返回到页面
		return mav;
	}

	@RequestMapping("/preSave") // 最终的请求路径是重student/list.do
	public ModelAndView preSave(@RequestParam(value = "id", required=false) String id) { // 请求参数@RequestParam(value="id",required=false)
																							// String
																							// id
																							// 把页面请求过来的id放入到请求参数里面去，默认是不需要的
		ModelAndView mav = new ModelAndView();
		if (id != null) {
			mav.addObject("student", studentList.get(Integer.parseInt(id) - 1));
			mav.setViewName("student/update");
		} else {
			mav.setViewName("student/add");// 返回到页面
		}
		return mav;
	}
	@RequestMapping("/save") 
	public String save(Student student){
		if(student.getId() != 0){
			Student s = studentList.get(student.getId() - 1);
			s.setName(student.getName());
			s.setAge(student.getAge());
		}else{
			studentList.add(student);
		}
		
		return "redirect:/student/list.do";
	}
	
	@RequestMapping("/delete") 
	public String delete(@RequestParam("id") int id){
		
		studentList.remove(id - 1);
		return "redirect:/student/list.do";
	}
}
