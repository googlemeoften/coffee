package cn.edu.coffee.controller;

import cn.edu.coffee.mapper.UserMapper;
import cn.edu.coffee.model.User;
import cn.edu.coffee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@ResponseBody
	@RequestMapping("/register")
	public Object register(String username, String password, String phone,
			HttpSession session) {

		HashMap<String, Object> data = new HashMap<String, Object>();

		username = username.trim();
		password = password.trim();

		System.out.println(username + password + phone);

		if (username != null) {

			if (username.length() < 6) {
				data.put("username", "用户名长度不能小于6位");
			}
		} else {
			data.put("username", "用户名不能为空");
		}
		if (password != null) {
			if (password.length() < 6) {
				data.put("password", "密码长度不能小于6位");
			}
		} else {
			data.put("password", "密码不能为空");
		}

		if (phone != null
				&& !phone
						.matches("(\\d+-)?(\\d{4}-?\\d{7}|\\d{3}-?\\d{8}|^\\d{7,8})(-\\d+)?")) {
			data.put("phone", "手机号不正确");
		}

		if (data.size() != 0) {
			return JSON.toJSON(data);
		}

		User form = new User();
		form.setUsername(username);
		User user = userService.findUser(form);

		if (user != null) {
			data.put("username", "该用户名已经注册");
		}

		if (phone != null) {
			form = new User();
			form.setPhone(phone);
			user = userService.findUser(form);
			if (user != null) {
				data.put("phone", "改手机号已经注册");
			}
		}

		if (data.size() != 0) {
			return JSON.toJSON(data);
		}
		
		form  = new User();
		form.setUsername(username).setPassword(password).setPhone(phone)
				.setLevel(0);// 0:代表普通用户

		userService.addUser(form);
		session.setAttribute("user", form);

		data.put("redirect", true);

		return JSON.toJSON(data);
	}

	@ResponseBody
	@RequestMapping("/allUser")
	public List<User> allUser() {
		return userService.findAllUser();
	}

	@ResponseBody
	@RequestMapping("/login")
	public Object login(String username, String password, HttpSession session) {

		User from = new User();
		from.setUsername(username.trim());
		from.setPassword(password.trim());

		User user = userService.findUser(from);

		session.setAttribute("user", user);

		HashMap<String, Object> data = new HashMap<String, Object>();

		if (user == null) {
			data.put("username", "该用户不存在");
		}

		if (user != null && !user.getPassword().equals(from.getPassword())) {
			data.put("password", "密码错误");
		}

		if (data.size() == 0) {
			data.put("redirect", true);
		}

		return JSON.toJSON(data);
	}
}
