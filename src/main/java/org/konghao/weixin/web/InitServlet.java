package org.konghao.weixin.web;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.konghao.weixin.kit.RefreshAccessToken;
import org.konghao.weixin.menu.CreateMenu;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


public class InitServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static WebApplicationContext wc;
	private static String realpath;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		//初始化spring的工厂
		wc = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		realpath = config.getServletContext().getRealPath("");
		BeanFactoryContext.setWc(wc);
		new RefreshAccessToken(7200);
		
		
	}

	public static String getRealpath() {
		return realpath;
	}
	
	public static WebApplicationContext getWc() {
		return wc;
	}

}
