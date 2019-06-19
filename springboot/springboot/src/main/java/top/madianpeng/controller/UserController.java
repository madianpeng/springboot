package top.madianpeng.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import top.madianpeng.pojo.ReturnValue;
import top.madianpeng.pojo.TbUser;
import top.madianpeng.pojo.UUser;
import top.madianpeng.service.UserService;
import top.madianpeng.utils.ImageUtil;
import top.madianpeng.utils.NonUtil;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 获取修改资料页面
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping("/modifypage")
	public String modifyPage(Model model, HttpSession session) {
		TbUser loginUser = (TbUser) session.getAttribute("user");
		TbUser userinfo = userService.findUserByCode(loginUser);
		model.addAttribute("userinfo", userinfo);
		return "/set/user/info";
	}

	/**
	 * 修改用户资料
	 * 
	 * @param tbUser
	 * @return
	 */
	@PostMapping("/modifyinfo")
	@ResponseBody
	public Object modifyInfo(TbUser tbUser) {
		ReturnValue value = new ReturnValue();
		userService.modifyInfo(tbUser);
		value.setMsg("修改成功");
		return value;
	}
}
