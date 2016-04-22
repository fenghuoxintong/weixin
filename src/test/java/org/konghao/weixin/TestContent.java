package org.konghao.weixin;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.apache.wicket.request.Url;

public class TestContent {

	public static void main(String[] args) {
		String str="浣犲ソ";
		try {
			System.out.println(new String(str.getBytes("GBK"), "utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
