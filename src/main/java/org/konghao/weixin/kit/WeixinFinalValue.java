package org.konghao.weixin.kit;

public class WeixinFinalValue {
	public static final String APPSECRET ="d4624c36b6795d1d99dcf0547af5443d";
	public static final String APPID = "wxc740d60d6a759aa1";
	public static final String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+WeixinFinalValue.APPID+"&secret="+WeixinFinalValue.APPSECRET;

	public static final String GetIp_Url="https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token=ACCESS_TOKEN";
	public static final String Menu_url="https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
}
