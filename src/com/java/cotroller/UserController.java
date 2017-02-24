package com.java.cotroller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.model.User;

@Controller
@RequestMapping("/user")
public class UserController {
	@RequestMapping("/login")
	public String login(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("��¼��֤");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		Cookie cookie = new Cookie("user", userName + "-" + password);// ���û���Ϣ
		cookie.setMaxAge(1 * 60 * 60 * 24 * 7); // ����cookie����Чʱ�� 1��
		User currentUser = new User(userName, password);
		response.addCookie(cookie);// ���cookie
		HttpSession session = request.getSession();// �ѵ�ǰ�û��ŵ�session��
		session.setAttribute("currentUser", currentUser);
		return "redirect:/main.jsp";
	}
	
	@RequestMapping("/login2")
	public String login2(HttpServletRequest request) {
		return "redirect:/main.jsp";
	}
	
	@RequestMapping("/login3")
	public String login3(HttpSession session) {
		return "redirect:/main.jsp";
	}
}
