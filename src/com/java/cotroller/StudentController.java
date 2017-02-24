package com.java.cotroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.java.model.Student;

@Controller // ע�ⶨ��Ϊ���Ʋ� ͨ��context:component-scan����ɨ�裬��ʵ������bean
@RequestMapping("/student") // ����·��
public class StudentController {
	private static List<Student> studentList = new ArrayList<Student>();

	static {
		studentList.add(new Student(1, "����", 11));
		studentList.add(new Student(2, "����2", 11));
		studentList.add(new Student(3, "����3", 11));
	}

	@RequestMapping("/list") // ���յ�����·������student/list.do
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("studentList", studentList);
		mav.setViewName("student/list");// ���ص�ҳ��
		return mav;
	}

	@RequestMapping("/preSave") // ���յ�����·������student/list.do
	public ModelAndView preSave(@RequestParam(value = "id", required=false) String id) { // �������@RequestParam(value="id",required=false)
																							// String
																							// id
																							// ��ҳ�����������id���뵽�����������ȥ��Ĭ���ǲ���Ҫ��
		ModelAndView mav = new ModelAndView();
		if (id != null) {
			mav.addObject("student", studentList.get(Integer.parseInt(id) - 1));
			mav.setViewName("student/update");
		} else {
			mav.setViewName("student/add");// ���ص�ҳ��
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
