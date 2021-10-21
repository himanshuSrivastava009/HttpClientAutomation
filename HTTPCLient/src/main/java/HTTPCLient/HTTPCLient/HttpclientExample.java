package HTTPCLient.HTTPCLient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpclientExample {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		
		String addURI = "http://httpbin.org/get";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet get = new HttpGet(addURI);
	//	System.out.println(httpclient.execute(get));
		CloseableHttpResponse response =httpclient.execute(get);
		System.out.println(response.toString());
		BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
		InputStream ip=response.getEntity().getContent();
		System.out.println(ip.toString());
		System.out.println("===="+response.getStatusLine());
		System.out.println(response.getParams());
		//System.out.println(response.getAllHeaders().toString().indent(0));
		InputStreamReader ir = new InputStreamReader(ip);
	int data=	ir.read();
		 while (data != -1) {  
             System.out.print((char) data);  
             data = ir.read();  
         }
	
		
		

	}

}
