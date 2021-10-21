package HTTPCLient.HTTPCLient;

import java.util.List;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import javax.xml.transform.Templates;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.jayway.jsonpath.JsonPath;


/**
 * @author HiKumar
 *
 */
public class JsonPathFinder {

	public static void main(String[] args) {

		
		String jsonPath = "$.data[*].name";
	
	
	}
	/**
	 * @param jsonResponse
	 * @param jsonPath
	 * @return LIST of json values
	 */
	public static List<Object> getListofJsonValue(String jsonResponse,String jsonPath)
	{
		List<Object> obj=JsonPath.read(jsonResponse,jsonPath);
		return obj;
	}
	
	/**
	 * @param jsonResponse
	 * @param jsonPath
	 * @return single json value
	 */
	public static Object getSingleJSONValue(String jsonResponse,String jsonPath)
	{
		Object obj=JsonPath.read(jsonResponse, jsonPath);
		return obj;
	}
	
	
	/**
	 * @param url
	 * @return HttpEntity<String>
	 */
	public static HttpEntity<String> dogetHttpEntity(String url)
	{
		return dorestTemplateCreater().getForEntity(url, String.class);
		
	}
	
	/*
	 * for Get call we don't need any Entity 
	 * i.e. headers + Json Body
	 * We need to caputre only the response what we receive from server
	 */
	
	/**
	 * @return rest template ref
	 */
	public static RestTemplate dorestTemplateCreater()
	{
		RestTemplate rtp = new RestTemplate();
		return rtp;
	}
	/**
	 * @param url
	 * @return
	 */
	public static String doGetcallResponseBody(String url)
	{
		String responseBody=dogetHttpEntity(url).getBody();
		return responseBody;
	}
	
	
	
	public static HttpEntity<String> doPostHttpEntity(String jsonBody,HttpHeaders headers)
	{
		HttpEntity<String> entity = new HttpEntity<String>(jsonBody, headers);
		return entity;
	}
	
	public static HttpEntity<String> doPostcall(String url,HttpEntity<String> entity)
	{
		return dorestTemplateCreater().postForEntity(url,entity,String.class);
	}
	
	public static void execute(){
		  TrustManager[] trustAllCerts = new TrustManager[] {
		        new X509TrustManager() {
		          public java.security.cert.X509Certificate[] getAcceptedIssuers() {
		           return null;
		          }
		          @Override
		          public void checkClientTrusted(X509Certificate[] arg0, String arg1)
		           throws CertificateException {}
		 
		          @Override
		          public void checkServerTrusted(X509Certificate[] arg0, String arg1)
		            throws CertificateException {}
		          }
		     };
		  SSLContext sc=null;
		  try {
		   sc = SSLContext.getInstance("SSL");
		  } catch (NoSuchAlgorithmException e) {
		   e.printStackTrace();
		  }
		  try {
		   sc.init(null, trustAllCerts, new java.security.SecureRandom());
		  } catch (KeyManagementException e) {
		   e.printStackTrace();
		  }
		  HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		  // Create all-trusting host name verifier
		  HostnameVerifier validHosts = new HostnameVerifier() {
		  @Override
		  public boolean verify(String arg0, SSLSession arg1) {
		   return true;
		  }
		  };
		  // All hosts will be valid
		  HttpsURLConnection.setDefaultHostnameVerifier(validHosts);
		 }


}
