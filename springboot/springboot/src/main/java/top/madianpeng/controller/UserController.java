package top.madianpeng.controller;

import java.net.URLEncoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import top.madianpeng.pojo.TbUser;
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
	 * 用户登录
	 * @param tbUser
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/login")
	public String login(TbUser tbUser,HttpServletRequest request) throws Exception {
		String vercode = (String) request.getSession().getAttribute("code");
		String msg="验证码不正确";
		if (!tbUser.getVercode().toUpperCase().equals(vercode)) {
			return "redirect:/user/tologin?msg="+URLEncoder.encode(msg,"UTF-8");
		}
		TbUser user = userService.findUserByName(tbUser);
		msg="用户名或密码不正确";
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
	/**
	 * 注销用户
	 * @param request
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		logger.info("用户注销");
		session.invalidate();
		return "redirect:/user/tologin";
	}
	
	/**
	 * 验证码路径
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/code")
    public String getCode(HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setContentType("image/jpeg");
        //禁止图像缓存
        response.setHeader("Pragma","no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        HttpSession session = request.getSession();
        ImageUtil imageUtil = new ImageUtil(120, 40, 5,30);
        session.setAttribute("code", imageUtil.getCode());
        imageUtil.write(response.getOutputStream());
        return null;
    }

}
