package HTTPCLient.HTTPCLient;

import org.testng.annotations.Test;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpHost;
import org.apache.http.ProtocolException;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.RedirectStrategy;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultRedirectStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.spi.mapper.JsonSmartMappingProvider;

public class NewTest extends JsonPathFinder {

	@Test
	public void addEmployee() {
		String addURI = "https://reqres.in/api/users";
		String url = "https://gorest.co.in/public/v1/posts";
		String accessToken = "cf5e769f269d298864bb4770207981eadbabc90b3f28b753afcba8ffed9cdc65";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", "application/json");
		headers.add("Content-Type", "application/json");
		headers.add("Authorization", "Bearer " + accessToken);
		String jsonBody = "[\n" + "   {\n" + "      \"name\":\"Himu\",\n" + "      \"job\":\"Eng\"\n" + "   },\n"
				+ "   {\n" + "      \"name\":\"null\",\n" + "      \"job\":\"QA\",\n" + "      \"id\":\"999\"\n"
				+ "   }\n" + "]";
		HttpEntity<String> entity = new HttpEntity<String>(jsonBody, headers);
		System.out.println("entity is " + entity);

		//
		System.out.println("headers=" + entity.getHeaders());
		entity.getHeaders().getContentType();
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.postForEntity(addURI, entity, String.class);
		String responseBody = response.getBody().toString();
		System.out.println(responseBody);

		String jsonPath = "$[*].name";
		List<Object> o1 = getListofJsonValue(responseBody, jsonPath);
		System.out.println(o1);

		// Object ou = getSingleJSONValue(responseBody, jsonPath);
		// System.out.println(ou);

	}

	@Test
	public void getEmployee() {
		String addURI = "https://reqres.in/api/unknown";
		String jsonapth = "$.data[0].name";
		String responseBody = doGetcallResponseBody(addURI);
		System.out.println(getSingleJSONValue(responseBody, jsonapth));

	}

	@Test
	public void postcallExample() {
		String addURI = "https://reqres.in/api/users";

		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", "application/json");
		headers.add("Content-Type", "application/json");

		String jsonBody = "[\n" + "   {\n" + "      \"name\":\"Himu\",\n" + "      \"job\":\"Eng\"\n" + "   },\n"
				+ "   {\n" + "      \"name\":\"null\",\n" + "      \"job\":\"QA\",\n" + "      \"id\":\"999\"\n"
				+ "   }\n" + "]";

		HttpEntity<String> entity = doPostcall(addURI, doPostHttpEntity(jsonBody, headers));
		System.out.println(entity.getBody());
		String responseBody = entity.getBody();

		String jsonPath = "$[*].name";

		System.out.println(getListofJsonValue(responseBody, jsonPath));

	}

	@Test
	public void exchnageTemplate() {
		String addURI = "https://reqres.in/api/unknown";
		System.out.println(dorestTemplateCreater().exchange(addURI, HttpMethod.GET, null, String.class));
		ResponseEntity<String> response = dorestTemplateCreater().exchange(addURI, HttpMethod.GET, null, String.class);
		String responseBody = response.getBody();
		System.out.println(responseBody);
		String jsonPath = "$[*].name";
		List<Object> values = getListofJsonValue(responseBody, jsonPath);
		System.out.println(values);

	}

	@Test
	public void filrLogin() {
		String addURI = "https://blr8-110-113.apac.novell.com/rest/self";
		HttpHeaders headers = new HttpHeaders();
		// headers.add("Authorization", "Basic cmVzdDI6bm92ZWxsQDEyMw==");
		headers.setBasicAuth("admin", "novell");
		// headers.setBearerAuth("cf5e769f269d298864bb4770207981eadbabc90b3f28b753afcba8ffed9cdc65");
		headers.add("Accept", "*/*");
		HttpEntity<String> httpentity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = dorestTemplateCreater().exchange(addURI, HttpMethod.GET, httpentity,
				String.class);
		System.out.println(response.getBody());
	}

	@Test
	public void createLdapUSER() {
		String url = "https://blr8-100-178.apac.novell.com/rest/admin/user_sources";
		String jsonBody = "{\n" + "	\"url\": \"ldap://10.71.100.243:389\",\n"
				+ "	\"username_attribute\": \"cn=administrator,cn=users,dc=child2,dc=child1,dc=filr2016,dc=com\",\n"
				+ "	\"guid_attribute\": \"GUID\",\n" + "	\"directory_type\": \"Active Directory\",\n"
				+ "	\"username\": \"cn=administrator,cn=users,dc=child2,dc=child1,dc=filr2016,dc=com\",\n"
				+ "	\"password\": \"8EiT_pa$$\",\n" + "	\"user_contexts\": [{\n"
				+ "		\"base_dn\": \"OU=LDAPTestSyncOU,DC=child2,DC=child1,DC=Filr2016,DC=com\",\n"
				+ "		\"home_dir_config\": {\n" + "			\"type\": \"home_dir_attribute\"\n" + "		}\n"
				+ "	}],\n" + "	\"group_contexts\": [{\n" + "		\"base_dn\": \"\"\n" + "	}]\n" + "}";
		HttpHeaders httpheader = new HttpHeaders();
		httpheader.setBasicAuth("admin", "novell");
		httpheader.add("Content-Type", "application/json");
		httpheader.add("Accept", "*/*");
		HttpEntity<String> entity = new HttpEntity<String>(jsonBody, httpheader);
		MediaType media = entity.getHeaders().getContentType();
		System.out.println("Media Type=" + media);
		RestTemplate resttemplate = new RestTemplate();
		ResponseEntity<String> response = resttemplate.exchange(url, HttpMethod.POST, entity, String.class);
		System.out.println(response);
		System.out.println(response.getBody());
	}
	
	@Test
	public static void updateLdapSource() //put request
	{
		
		String url ="https://blr8-100-178.apac.novell.com/rest/admin/user_sources/sync_config";
		String JsonBody="{\n"
				+ "	\"schedule \": {\n"
				+ "		\"enabled\": true,\n"
				+ "		\"when\": \"...\",\n"
				+ "		\"selected_days\": {\n"
				+ "			\"sun\": true,\n"
				+ "			\"mon\": true,\n"
				+ "			\"tue\": true,\n"
				+ "			\"wed\": true,\n"
				+ "			\"thu\": true,\n"
				+ "			\"fri\": true,\n"
				+ "			\"sat\": true\n"
				+ "		},\n"
				+ "		\"at\": {\n"
				+ "			\"hour\": 12345,\n"
				+ "			\"minute\": 12345\n"
				+ "		},\n"
				+ "		\"every\": {\n"
				+ "			\"hour\": 12345,\n"
				+ "			\"minute\": 12345\n"
				+ "		}\n"
				+ "	}\n"
				+ "}";
		HttpHeaders headers = new HttpHeaders();
		headers.add("content-type", "application/json");
		headers.add("Accept", "*/*");
		headers.setBasicAuth("admin", "novell");
		RestTemplate rtp = new RestTemplate();
		HttpEntity<String> entity = new HttpEntity<String>(JsonBody,headers);
		ResponseEntity<String> response=rtp.exchange(url, HttpMethod.PUT, entity, String.class);
		String reponseBody = response.getBody();
		System.out.println(reponseBody);
		
		
	}

	@Test
	public static void listLDAPsources() {

		String url = "https://blr8-110-113.apac.novell.com/rest/admin/user_sources";
		HttpHeaders header = new HttpHeaders();
		header.setBasicAuth("admin", "novell");
		header.add("Accept", "*/*");
		HttpEntity<String> entity = new HttpEntity<String>(header);
		System.out.println(entity);
		RestTemplate rtp = new RestTemplate();
		ResponseEntity<String> rp = rtp.exchange(url, HttpMethod.GET, entity, String.class);
		System.out.println(rp.getBody());

	}

	@Test
	public static void httpClientTest() {
		// 1. Build httpclient
		CloseableHttpClient httpclient = HttpClientBuilder.create().build();
		// 2.Need to use get or post or put or delete methods

		String url = "https://gorest.co.in/public/v1/posts";
		HttpGet get = new HttpGet(url);

		// 3 add headers
		get.addHeader("Accept", "application/json");
		get.addHeader("Authorization", "Bearer cf5e769f269d298864bb4770207981eadbabc90b3f28b753afcba8ffed9cdc65");

		// 4. Execute the method
		try {
			CloseableHttpResponse response = httpclient.execute(get);
			org.apache.http.HttpEntity httpEntity = response.getEntity();
			System.out.println(">>" + httpEntity);
			String responseBody = EntityUtils.toString(httpEntity);
			String path = "$.data[0].title";
			System.out.println(responseBody);
			Object values = JsonPath.read(responseBody, path);
			System.out.println(values);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public static void testFilrBasicAuth() throws ClientProtocolException, IOException
	{

		/*
		 * 1. Get the URL
		 * 2.Set the authentication
		 * 	a.using BasicCredentialsProvider class
		 * 	b.Define the Auth Scope
		 * 	c.provide username and password using "UsernamePassword Credentials"
		 * 3.Create a Custom Http client
		 * 4.set the default credentials to the custom http client
		 * 5. 
		 */
		
		String addURI = "https://blr8-110-113.apac.novell.com/rest/self";
		String endPoints="blr8-110-113.apac.novell.com";
		HttpHost host = new HttpHost(endPoints);//"https://blr8-110-113.apac.novell.com/rest/self";
		CredentialsProvider credentialsPovider = new BasicCredentialsProvider();
		AuthScope scope = new AuthScope(host);
		System.out.println("authScope="+scope);
		Credentials cred = new UsernamePasswordCredentials("admin", "novell");
		credentialsPovider.setCredentials(scope, cred);
		HttpClientBuilder clientbuilder = HttpClients.custom();
		clientbuilder=	clientbuilder.setDefaultCredentialsProvider(credentialsPovider);
		CloseableHttpClient httpclient=	clientbuilder.build();
		System.out.println(">>>>"+httpclient);
		
		HttpGet httpget = new HttpGet(addURI);
		System.out.println("httpget="+httpget);
		httpget.setHeader("accept", "*/*");
		CloseableHttpResponse response=	httpclient.execute(httpget);
		System.out.println(response);
		System.out.println(EntityUtils.toString(response.getEntity()));
		
		
		
	}
	@Test
	public static void generateQueryParam()
	{
		JSONObject req = new JSONObject();
		//req.put("Username", "Himanshu");
		//System.err.println(req);
		HttpHeaders headers = new HttpHeaders();
		Object msisdn = "cn=administrator,cn=users,dc=child2,dc=child1,dc=filr2016,dc=com";
		Object email= "";
		Object clientVersion= null;
		Object clientType= null;
		Object issuerName= null;
		Object applicationName= null;
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://blr8-110-113.apac.novell.com/rest/self")
		        .queryParam("msisdn", msisdn)
		        .queryParam("email", email)
		        .queryParam("clientVersion", clientVersion)
		        .queryParam("clientType", clientType)
		        .queryParam("issuerName", issuerName)
		        .queryParam("applicationName", applicationName);
		System.out.println(builder.toUriString());
		
		
	}
	@Test
	
	public static Object getFolderID()
	{
		Object path="/Home Workspace/Personal Workspaces/admin (admin)/SIEM_Folder_Trash";
		String getFolderIDUrl="https://blr8-110-113.apac.novell.com/rest/self/my_files/library_folders";
		Object FolderID=null;
		HttpHeaders header = new HttpHeaders();
		header.add("Accept", "*/*");
		header.add("content-type", "application/json");
		header.setBasicAuth("admin", "novell");
		String JsonBody="[{id: 6272, type: \"folder\"}]";
		HttpEntity<String> entity= new HttpEntity<String>(null,header);
		RestTemplate rtp = new RestTemplate();
		ResponseEntity<String> response=rtp.exchange(getFolderIDUrl, HttpMethod.GET, entity, String.class);
		String responseBody = response.getBody();
		System.out.println(responseBody);
	
		
	
		List<Object> job = null;
		int i=0;
		
		String jsonpath1 = "$.items[*].path";
		job = JsonPath.read(responseBody, jsonpath1);
		System.out.println("Size of List is" + job.size());
		//System.out.println(job.toString());
		Object obj1=null;

		for(i=0;i<job.size();i++)
		{
			String jsonpath = "$.items["+i+"].path";
			obj1=JsonPath.read(responseBody, jsonpath);
			System.out.println("obj1======="+obj1);
			if(obj1.toString().equals(path))
			{
				System.out.println("Eureka---Found the match");
				System.out.println("index value at which the value is available is=="+i);
				String newjsonPath="$.items["+i+"].id";
				 FolderID=JsonPath.read(responseBody, newjsonPath);
				System.out.println("Folder ID is===="+FolderID);
			
			}
		}
		
		
		return FolderID;
		
		
	}
	
	@Test
	public static void zownloadAsZip()
	{
		String url="https://blr8-110-113.apac.novell.com/rest/files/validate_zipit?recursive=true";
		Object FolderID=getFolderID();
		System.out.println("Folder ID is =========="+FolderID);
		HttpHeaders header = new HttpHeaders();
		
		header.add("Accept", "*/*");
		//header.add("content-type", "application/json");
		//header.setContentType(MediaType.APPLICATION_JSON);
		header.setContentType(MediaType.APPLICATION_JSON_UTF8);
		header.setBasicAuth("admin", "novell");
		String JsonBody="[{\n"
				+ "	\"id\": "+FolderID+",\n"
				+ "	\"type\": \"folder\"\n"
				+ "}]";
																					
		
		HttpEntity<String> entity= new HttpEntity<String>(JsonBody,header);
		System.out.println("Why Header Localtion----->"+entity.getHeaders().getLocation());
	//	header.getName().contains(HttpHeaders.LOCATION);
	
		MediaType media= entity.getHeaders().getContentType();
		System.out.println("Media Type is "+media);
		
		
		RestTemplate rtp = new RestTemplate();
		//ResponseEntity<String> response=
		System.out.println(rtp.exchange(url, HttpMethod.POST, entity, String.class));
		//String responseBody=response.getBody();
		
		//System.out.println(responseBody);
	}
	
	
	@Test
	
	public void test()
	{
		String payload ="{\n"
				+ "  \"service_name\" : \"...\",\n"
				+ "  \"service_admin\" : \"...\",\n"
				+ "  \"redirect_uris\" : [ \"...\", \"...\" ],\n"
				+ "  \"enabled\" : true\n"
				+ "}";
		ServiceName srv = new ServiceName();
		System.out.println(srv.getService_name());
		
		
	}
	
	@Test
	public static void getPublicDownloadLink()
	{
		String url="https://blr8-110-113.apac.novell.com";
		String restEndPoints="/rest/self/my_files/library_files";
		String hostURL = url+restEndPoints;
		System.out.println("HostURL is ======="+hostURL);
		HttpHeaders header = new HttpHeaders();
		header.add("Accept","*/*");
		header.add("content-type", "application/json");
		header.setBasicAuth("admin", "novell");
		HttpEntity<String> entity = new HttpEntity<String>(null,header);
		RestTemplate rtp = new RestTemplate();
		ResponseEntity<String> response=rtp.exchange("https://blr8-110-113.apac.novell.com/rest/self/my_files/library_files", HttpMethod.GET, entity, String.class);
		System.out.println("RAW Response is ====== "+response);
		String responseBody=response.getBody();
		System.out.println("Resposne ========="+responseBody);
	}
	@Test
	public static void testRedirectURL()
	{

			CloseableHttpClient httpclient=HttpClientBuilder.create().setRedirectStrategy(new DefaultRedirectStrategy()
					{
				
				 public boolean isRedirected(org.apache.http.HttpRequest request, CloseableHttpResponse response,
		                    HttpContext context) throws ClientProtocolException {

		                System.out.println(response);

		                // If redirect intercept intermediate response.
		                try {
							if (super.isRedirected(request, response, context)){
							    int statusCode  = response.getStatusLine().getStatusCode();
							    String redirectURL = response.getFirstHeader("Location").getValue();
							    System.out.println("redirectURL: " + redirectURL);
							    return true;
							}
						} catch (ProtocolException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		                return false;
		            }
				
					}).build();
		
	}
	
	@Test
	public void testSample()
	{

		
		DefaultRedirectStrategy dfs = new DefaultRedirectStrategy();
		
		HttpContext context;
		
		
		
		
		
		
		
		
	}
	
	
	
	
}
