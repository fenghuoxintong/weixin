package org.konghao.weixin.kit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.konghao.weixin.json.AccessToken;
import org.konghao.weixin.json.JsonUtil;
import org.konghao.weixin.menu.CreateMenu;
import org.konghao.weixin.menu.Menu;
import org.konghao.weixin.web.BeanFactoryContext;

public class RefreshAccessToken {
	public RefreshAccessToken(int second) {
		Timer timer = new Timer();
		timer.schedule(new RefreshAccessTokenTask(), 0, second*1000);
	}
	
	private class RefreshAccessTokenTask extends TimerTask {
		@Override
		public void run() {
			String str=WeixinFinalValue.TOKEN_URL;
			String content = WeixinKit.sendGet(WeixinFinalValue.TOKEN_URL);
			AccessToken at = (AccessToken)JsonUtil.stringToObj(content, AccessToken.class);
			System.out.println("重新获取了Token:"+at.getAccess_token());
			String getipurl=WeixinFinalValue.GetIp_Url.replace("ACCESS_TOKEN",at.getAccess_token());
			String weixinIp=WeixinKit.sendGet(getipurl);
			System.out.println(weixinIp);
			
			
			
			String menu_url=WeixinFinalValue.Menu_url;
			menu_url=menu_url.replace("ACCESS_TOKEN",at.getAccess_token());
			System.out.println(menu_url);
			List<Menu> list=new ArrayList<Menu>();
			Menu menu=new Menu();
			menu.setId(1);
			menu.setName("流量");
			menu.setType("click");
			menu.setKey("V1001_TODAY_MUSIC");
			List<Menu> sub_button2=new ArrayList<Menu>();
			Menu menu4=new Menu();
			menu4.setName("充值前台");
			menu4.setType("view");
			menu4.setUrl("http://121.41.57.44:8080/ymdata/login/loginInput");
			Menu menu5=new Menu();
			menu5.setName("充值后台");
			menu5.setType("view");
			menu5.setUrl("http://121.41.57.44:8080/ymdata/backLogin/backMain");
			sub_button2.add(menu4);
			menu.setSub_button(sub_button2);
			list.add(menu);
			
			
			Menu menu2=new Menu();
			menu2.setName("短信");
			List<Menu> sub_button=new ArrayList<Menu>();
			Menu menu3=new Menu();
			menu3.setId(2);
			menu3.setName("短信群发");
			menu3.setType("view");
			menu3.setUrl("http://www.baidu.com");
			sub_button.add(menu3);
			menu2.setSub_button(sub_button);
			list.add(menu2);
			
			Map<String,List<Menu>> map=new HashMap<String, List<Menu>>();
			
			map.put("button", list);
			String json=JsonUtil.objToString(map);
			String s=WeixinKit.sendPost(menu_url, json);
			System.out.println(s);
		}
	}
}
