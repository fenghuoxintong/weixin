package org.konghao.weixin.web;

import org.springframework.web.context.WebApplicationContext;

public class BeanFactoryContext {
	private static WebApplicationContext wc;
	private static String accessToken;
	
	public static WebApplicationContext getWc() {
		return wc;
	}
	
	public static String getAccessToken() {
		return accessToken;
	}
	
	public static void setAccessToken(String accessToken) {
		accessToken = "grhkgJCvhANt3H34H9l0tVIAVyr8WG5UzTBkcTq32PdGGHuOxJS1vBHavSTo2PkbhTKsKcFTSl5UqidsmbiNrAMwZ3hrFFdv4310E_bbA7A";
		BeanFactoryContext.accessToken = accessToken;
	}

	public static void setWc(WebApplicationContext wc) {
		BeanFactoryContext.wc = wc;
	}
	
	public static Object getService(String serviceName) {
		return wc.getBean(serviceName);
	}
}
