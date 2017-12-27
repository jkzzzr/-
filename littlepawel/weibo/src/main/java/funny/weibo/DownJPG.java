package funny.weibo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class DownJPG {

	public static int COUNT = 0;
	public static void main(String[] args) throws Exception {
		downloadBatch();

	}
	
	public static void downloadBatch() throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("uid-clear_picSrc_1"));
		String line = "";
		while ((line = br.readLine())!=null){
			String[] strings = line.split("[\t]");
			String uri = "http:"+strings[2];
			System.out.print(uri+"\t");
			downLoadSingle(uri);
			System.err.println(++COUNT);
		}
		br.close();
	}
	
	
	
	public static void downLoadSingle(String uri) throws IOException, Exception{
		HttpClient client = new DefaultHttpClient();
//		String uri = "http://wx3.sinaimg.cn/mw1024/afc3fc8bgy1fmv1tz3y37j20qo0zkjvv.jpg";
		HttpGet get = new HttpGet(uri);
		get.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		get.setHeader("Accept-Encoding", "gzip, deflate");
//		get.setHeader("Host", "wx3.sinaimg.cn");
		get.setHeader("Cache-Control", "no-cache");
		get.setHeader("ccept-Language", "zh-CN,zh;q=0.8");
		get.setHeader("Connection", "keep-alive");
		get.setHeader("Cache-Control", "no-cache");
		get.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");
		
		HttpResponse response = client.execute(get);
		HttpEntity entity = response.getEntity();
//		System.out.println(entity.getContentType());
		InputStream is = entity.getContent();
		byte[] bs = new byte[1024];
		int length = 0;
		String fileName = uri.substring(uri.lastIndexOf('/')+1);
		FileOutputStream fos = new FileOutputStream(new File("F:\\wanwan\\pic\\"+ fileName));
		
		while ((length = is.read(bs))>0){
			fos.write(bs, 0, length);
		}
		is.close();
		fos.close();
//		System.out.println(response.getProtocolVersion().getProtocol());
	}
	

}
