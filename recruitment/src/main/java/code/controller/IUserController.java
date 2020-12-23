package code.controller;

import code.domain.User;
import code.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequestMapping("/user")
public class IUserController {
	@Autowired
	private IUserService userService;


	@ResponseBody
	@RequestMapping("/save")
	public String save(User user) throws IOException {
		Integer uid = userService.login(user.getPhone(),user.getPwd());
		System.out.println(user);
		System.out.println(uid);
		Integer id = userService.findByAccount(user.getPhone());
		System.out.println(id);
		if(id == null){
			userService.save(user);
			return "success";
		}
		else if(uid == null){
			return "fail";
		}
		else{
			return "successfail";
		}
	}


	@ResponseBody
	@RequestMapping("/login")
	public  String login(@RequestParam(value = "account")String uaccount, @RequestParam(value = "pwd")String upassword) throws Exception{
		Integer uid = userService.login(uaccount,upassword);
		String id = String.valueOf(uid);
		if(uid == null){
			return "fail";
		}
		else{
			return id;
		}
	}


	@RequestMapping("/jump")
	public String jump(@RequestParam(value = "uid")Integer uid, Model model){
		//根据用户id查询用户身份
		String role = userService.findRoleById(uid);
		if (role.equals("applicant")){
			String aid = String.valueOf(uid);
			model.addAttribute("aid",aid);
			return "applicant_homepage";
		}
		else {
			String cid = String.valueOf(uid);
			model.addAttribute("cid",cid);
			return "CompanyHome";
		}
	}

}
