package top.madianpeng.controller;

import java.net.URLEncoder;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import top.madianpeng.pojo.TbUser;
import top.madianpeng.service.UserService;
import top.madianpeng.utils.NonUtil;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 用户登录
	 * @param tbUser
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/login")
	public String login(TbUser tbUser,HttpServletRequest request) throws Exception {
		TbUser user = userService.findUserByName(tbUser);
		String msg="用户名或密码不正确";
		if (NonUtil.isNon(user)) {
			return "redirect:/user/tologin?msg="+URLEncoder.encode(msg,"UTF-8");
		}
		if (!tbUser.getPassword().equals(user.getPassword())) {
			return "redirect:/user/tologin?msg="+URLEncoder.encode(msg,"UTF-8");
		}
		request.getSession().setAttribute("user", user);
		return "redirect:/main";
	}
	
	/**
	 * 登录页
	 * @param msg
	 * @param model
	 * @return
	 */
	@RequestMapping("/tologin")
	public String toLogin(String msg,Model model) {
		if (NonUtil.isNotNon(msg)) {
			model.addAttribute("msg", msg);
		}
		return "user/login";
	}
}
