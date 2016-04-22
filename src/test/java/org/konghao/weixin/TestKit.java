package org.konghao.weixin;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.konghao.weixin.json.AccessToken;
import org.konghao.weixin.kit.WeixinFinalValue;
import org.konghao.weixin.kit.WeixinKit;

public class TestKit {

	@Test
	public void testSha1() {
		byte a = 5;
		//System.out.println(Integer.toHexString(a&0xff));
		System.out.println(String.format("%02x", a));
	}
	
	@Test
	public void testSendGet() {
		try {
			CloseableHttpClient client = HttpClients.createDefault();
			HttpGet get = new HttpGet("http://www.konghao.org/");
			CloseableHttpResponse resp = client.execute(get);
			int status = resp.getStatusLine().getStatusCode();
			if(status>=200&&status<300) {
				HttpEntity entity = resp.getEntity();
				if(entity!=null) {
					String content = EntityUtils.toString(entity);
					System.out.println(content);
				}
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetAccessToken() {
		String str = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+WeixinFinalValue.APPID+"&secret="+WeixinFinalValue.APPSECRET;
		String content = WeixinKit.sendGet(str);
		AccessToken at = (AccessToken)org.konghao.weixin.json.JsonUtil.stringToObj(content, AccessToken.class);
		System.out.println(at.getAccess_token()+","+at.getExpires_in());
	}
}
