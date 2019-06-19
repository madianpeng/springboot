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
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private UserService userService;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/*	*//**
			 * 用户登录
			 * 
			 * @param tbUser
			 * @param request
			 * @return
			 * @throws Exception
			 *//*
				 * @RequestMapping("/login") public String login(TbUser tbUser,
				 * HttpServletRequest request) throws Exception { String vercode = (String)
				 * request.getSession().getAttribute("code"); String msg = "验证码不正确"; if
				 * (!tbUser.getVercode().toUpperCase().equals(vercode)) { return
				 * "redirect:/user/tologin?msg=" + URLEncoder.encode(msg, "UTF-8"); } TbUser
				 * user = userService.findUserByCode(tbUser); msg = "用户名或密码不正确"; if
				 * (NonUtil.isNon(user)) { return "redirect:/user/tologin?msg=" +
				 * URLEncoder.encode(msg, "UTF-8"); } if
				 * (!tbUser.getPassword().equals(user.getPassword())) { return
				 * "redirect:/user/tologin?msg=" + URLEncoder.encode(msg, "UTF-8"); }
				 * request.getSession().setAttribute("user", user); return "redirect:/main"; }
				 */

	/**
	 * 登录页
	 * 
	 * @param msg
	 * @param model
	 * @return
	 */
	@RequestMapping("/tologin")
	public String toLogin(String msg, Model model) {
		if (NonUtil.isNotNon(msg)) {
			model.addAttribute("msg", msg);
		}
		return "user/login";
	}

	/**
	 * 注销用户
	 * 
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
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/code")
	public String getCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("image/jpeg");
		// 禁止图像缓存
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		HttpSession session = request.getSession();
		ImageUtil imageUtil = new ImageUtil(120, 40, 5, 30);
		session.setAttribute("code", imageUtil.getCode());
		imageUtil.write(response.getOutputStream());
		return null;
	}

	// 跳转到登录表单页面
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "need login";
	}

	// 登录成功后，跳转的页面
	@RequestMapping("/main")
	public String index(Model model) {
		return "success";
	}

	// 未登录，可以访问的页面
	@RequestMapping("/index")
	public String list(Model model) {
		return "index";
	}

	// 登陆验证，这里方便url测试，正式上线需要使用POST方式提交
	@RequestMapping(value = "/ajaxLogin", method = RequestMethod.POST)
	public String index(UUser user) {
		if (user.getNickname() != null && user.getPswd() != null) {
			UsernamePasswordToken token = new UsernamePasswordToken(user.getNickname(), user.getPswd(), "login");
			Subject currentUser = SecurityUtils.getSubject();
			logger.info("对用户[" + user.getNickname() + "]进行登录验证..验证开始");
			try {
				currentUser.login(token);
				// 验证是否登录成功
				if (currentUser.isAuthenticated()) {
					logger.info("用户[" + user.getNickname() + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
					System.out.println("用户[" + user.getNickname() + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
					return "redirect:/main";
				} else {
					token.clear();
					System.out.println("用户[" + user.getNickname() + "]登录认证失败,重新登陆");
					return "redirect:/tologin";
				}
			} catch (UnknownAccountException uae) {
				logger.info("对用户[" + user.getNickname() + "]进行登录验证..验证失败-username wasn't in the system");
			} catch (IncorrectCredentialsException ice) {
				logger.info("对用户[" + user.getNickname() + "]进行登录验证..验证失败-password didn't match");
			} catch (LockedAccountException lae) {
				logger.info("对用户[" + user.getNickname() + "]进行登录验证..验证失败-account is locked in the system");
			} catch (AuthenticationException ae) {
				logger.error(ae.getMessage());
			}
		}
		return "redirect:/auth/tologin";
	}
	// 错误页面展示
	@GetMapping("/403")
	public String error() {
		return "error/403";
	}

	// 管理员功能
	@RequiresRoles("admin")
	@RequiresPermissions("管理员添加")
	@RequestMapping(value = "/admin/add")
	public String create() {
		return "add success!";
	}

	// 用户功能
	@RequiresRoles("user")
	@RequiresPermissions("用户查询")
	@RequestMapping(value = "/user/select")
	public String detail() {
		return "select success";
	}
}
