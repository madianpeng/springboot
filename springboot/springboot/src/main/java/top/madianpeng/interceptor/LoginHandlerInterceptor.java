package top.madianpeng.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import top.madianpeng.pojo.TbUser;
import top.madianpeng.utils.NonUtil;

public class LoginHandlerInterceptor implements HandlerInterceptor {
	/**
	 * 未登录拦截器
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		TbUser loginUser = (TbUser) session.getAttribute("loginUser");
		if (NonUtil.isNon(loginUser)) {
			response.sendRedirect("/tologin");
			return false;
		}
		return true;
	}

}
