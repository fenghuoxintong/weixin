package org.konghao.weixin.web.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.konghao.weixin.kit.SecurityKit;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WeinxinController {
	
	private static Map messageMap=new HashMap<String, String>();
	static{
		messageMap.put("1", "2");
		messageMap.put("11","22");
	}
	public static final String TOKEN = "zhaokui";

	@RequestMapping(value={"/wget"},method=RequestMethod.GET)
	public void wget(HttpServletRequest req,HttpServletResponse resp) throws IOException {
//		signature	微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
//		timestamp	时间戳
//		nonce	随机数
//		echostr
		String signature = req.getParameter("signature");
		String timestamp = req.getParameter("timestamp");
		String nonce = req.getParameter("nonce");
		String echostr = req.getParameter("echostr");
		String[] arr = {WeinxinController.TOKEN,timestamp,nonce};
		Arrays.sort(arr);
		StringBuffer sb = new StringBuffer();
		for(String a:arr) {
			sb.append(a);
		}
		String sha1Msg = SecurityKit.sha1(sb.toString());
		if(signature.equals(sha1Msg)) {
			resp.getWriter().println(echostr);
		}
	}
	@RequestMapping(value={"/wget"},method=RequestMethod.POST)
	public void getInfo(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		
		
		BufferedReader br=new BufferedReader(new InputStreamReader(req.getInputStream()));
		String str=null;
		StringBuffer sb=new StringBuffer();
		while((str=br.readLine())!=null)
		{
			sb.append(str);
		}
		String huiying=senInfo(sb.toString());
		resp.setContentType("application/xml;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(huiying);
	}
	private static String senInfo(String str){
		Map<String,String> map2xml=new HashMap<String, String>();
		try {
			Document document= DocumentHelper.parseText(str);
			Element root = document.getRootElement();  
			Map<String,String> map=new HashMap<String,String>();
			for(Iterator it=root.elementIterator();it.hasNext();){       
		        Element element = (Element) it.next(); 
		        try {
					map.put(element.getName(), new String(element.getStringValue().getBytes("GBK"), "utf-8"));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		       // do something   
		     } 
			
			System.out.println(map);
			map2xml.put("ToUserName",map.get("FromUserName"));
			map2xml.put("FromUserName", map.get("ToUserName"));
			map2xml.put("CreateTime", new Date().getTime()+"");
			map2xml.put("MsgType","text");
			String content=map.get("Content");
			if(messageMap.containsKey(content)){
				map2xml.put("Content", messageMap.get(content).toString());
			}else{
				map2xml.put("Content","you error");
			}
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		System.out.println(map2xml);
		return map2xml(map2xml);
	}
	private static String map2xml(Map<String, String> map) {
		// TODO Auto-generated method stub
		Document document=DocumentHelper.createDocument();
		Element root=document.addElement("xml");
		Set<String> keys=map.keySet();
		for(String key:keys){
			root.addElement(key).addText(map.get(key));
		}
		StringWriter sw=new StringWriter();
		try {
			document.write(sw);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sw.toString();
	}
}
