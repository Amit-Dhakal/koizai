package com.koizai.commonservice.sharepoint.service;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonToken;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.koizai.commonservice.sharepoint.dto.SharePointDetail;
import com.koizai.commonservice.sharepoint.util.ConstantUtil;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.util.ArrayList; 
import java.util.List;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SharepointFileServiceImpl implements SharepointFileService{
	
	
	    private String token;
	    private String siteURL;
	    
	    public SharepointFileServiceImpl() {
	        this.token = ConnectionService.getToken();
	        this.siteURL ="https://" + ConstantUtil.domain + ".sharepoint.com" + ConstantUtil.site;
	        
	    }

	    public void download(String downloadFileDir, String siteFolderUrl) {

	        try {
	            System.out.println("-----First downloading files from base folder-----.");

	            downloadAllFilesFromFolder(downloadFileDir, siteURL, siteFolderUrl);
	            List<String> folderNames = getListOfFolders(siteURL, siteFolderUrl);

	            for (String folder : folderNames) {
	                download(downloadFileDir + File.separator + folder, siteFolderUrl + "/" + folder);
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println("The file could not be downloaded/Token error/File IO : " + new Timestamp(System.currentTimeMillis()));
	        }

	    }
	    
	    
/*
	    public InputStream downloadFile(String relativeUrl, String fileName) {

	        relativeUrl = relativeUrl.replaceAll("\\s", "%20");
	        String fileUrl = siteURL + "/_api/web/GetFolderByServerRelativeUrl('" + relativeUrl + "')/Files('" + fileName + "')/$value";

	        
	        URL url;
	        InputStream inputStream = null;
	        try {
	            url = new URL(fileUrl);
	            URLConnection urlConnection = url.openConnection();
	            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
	            httpURLConnection.setRequestMethod("GET");
	            httpURLConnection.setRequestProperty("Authorization", "Bearer " + token);
	             inputStream = httpURLConnection.getInputStream();
	             System.out.println("Input Stream::"+inputStream);
	          return inputStream;
	        } catch (MalformedURLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        
	        return inputStream;
     
	    }
	    */
	    

	    // private void
	    private void downloadAllFilesFromFolder(String downloadFileDir, String siteURL, String siteFolderUrl) throws IOException {

	    	  Response nameFileArray = getFilesNameFromFolder(siteURL,siteFolderUrl);
  
	           String fileUrl= siteURL + "/_api/web/GetFolderByServerRelativeUrl('" + siteFolderUrl + "')/Files";
			    OkHttpClient client = new OkHttpClient();
			    Request request = new Request.Builder()
						    .header("Authorization", "Bearer " + token)
						    .header("accept", "application/json;odata=verbose")
						    .url(fileUrl)
						    .build();
		               	
	              	String res = null;

			 		try {
						Response response = client.newCall(request).execute();
						//res= response.peekBody(2048).toString();
						//int code=response.code();
									
						  String httpFResponseStr0 = getHttpResponse(fileUrl);  
						 //res=httpFResponseStr0;
						  
			              System.out.println("Response is::-"+httpFResponseStr0);

				         String fileName0 = "";
				         	       
				         JsonFactory factory = new JsonFactory();
				         com.fasterxml.jackson.core.JsonParser  parser  = factory.createParser(httpFResponseStr0);
				         
			              while(parser.nextToken()!=null)         
				         {  	         
				      	   JsonToken jsonToken= parser.nextToken();
				        	    if(JsonToken.FIELD_NAME.equals(jsonToken)){
				        	        String fieldName = parser.getCurrentName();
				        	        
				        	    	System.out.println("Field name:"+fieldName);

				        	     jsonToken = parser.nextToken();

				        	        if("FileLeafRef".equals(fieldName)){

				        	        	String folderName=parser.getValueAsString();
				        	        		   System.out.println("File name is::"+folderName);     	 
				        	        	//InputStream inputStream = response.body().byteStream();
				        	        		    InputStream inputStream = new ByteArrayInputStream(httpFResponseStr0.getBytes());
				        	     	   fileUrl= siteURL + "/_api/web/GetFolderByServerRelativeUrl('" + siteFolderUrl + "')/Files('" + folderName + "')/$value";

				        	            convertToFileAndDownload(downloadFileDir,folderName,inputStream);			        	             	            
				        	        }
				        	    }
				        	    }									     
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			       
	    }

	    
	    private void convertToFileAndDownload(String downloadFileDir, String fileName, InputStream inputStream) throws IOException {
	    	   SharePointDetail sharePointDetail = new SharePointDetail();

		          String siteURL = "https://" + ConstantUtil.domain + ".sharepoint.com" + sharePointDetail.getSiteName();//1 part
			 //   String fUrl = siteURL + "/_api/web/GetFolderByServerRelativeUrl('" +  + "')/Files";
			      String fUrl= siteURL + "/_api/web/Lists/GetByTitle('Documents')/Items?$expand=Folder&$select=Title,FileLeafRef,Folder/ServerRelativeUrl";
	
		    	 OkHttpClient client = new OkHttpClient();
		    	    Request request = new Request.Builder()
		    	    		.header("Authorization", "Bearer " + ConnectionService.getToken())
		    			    .header("accept", "application/json;odata=verbose")
		    			    .url(fUrl)
		    			    .build();	    
		    	    Response response = client.newCall(request).execute();
		    	   
		    	    if (!response.isSuccessful()) {
		    	        throw new IOException("Failed to download file: " + response);
		    	    }
			       // String saveFilePath = downloadFileDir + fileName;
			        String saveFilePath = downloadFileDir;

		    	    FileOutputStream fos = new FileOutputStream(saveFilePath);
		    	    fos.write(response.body().bytes());
		    	    fos.close();
		                
		           System.out.println("File {} downloaded");
	    }
	    
	    

	    private Response getFilesNameFromFolder(String siteURL, String siteFolderUrl) throws IOException {
	    	
	        String fUrl = siteURL + "/_api/web/GetFolderByServerRelativeUrl('" + siteFolderUrl + "')/Files";
	  
	        System.out.println("fUrl:::"+fUrl);
	        
	        OkHttpClient client = new OkHttpClient();
			Request request = new Request.Builder()
				    .header("Authorization", "Bearer " + token)
				    .header("accept", "application/json;odata=verbose")
				    .url(fUrl)
				    .build();
               	String res = null;
               	Response response=null;
	 		try {
				 response = client.newCall(request).execute();
			//res= response.peekBody(2048).string();
				//int code=response.code();
			     
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return response;                
	    }

	    
	    
	    public List<String> getListOfFolders(String siteURL, String siteFolderUrl) throws IOException {

	        List<String> folderNames = new ArrayList<>();
	        String fUrl0 = siteURL + "/_api/web/GetFolderByServerRelativeUrl('" + siteFolderUrl + "')/Folders";
	        System.out.println("getListOfFolders:" + fUrl0);
	        String httpFResponseStr0 = getHttpResponse(fUrl0);
	        
	        
	        String fileName0="";        	       
	         JsonFactory factory = new JsonFactory();
	         com.fasterxml.jackson.core.JsonParser  parser  = factory.createParser(httpFResponseStr0);
	              	         
	        
             while(!parser.isClosed())         
	         {  	         
	      	   JsonToken jsonToken= parser.nextToken();
	        	    if(JsonToken.FIELD_NAME.equals(jsonToken)){
	        	        String fieldName = parser.getCurrentName();      	        
	        	    	System.out.println("Field name:"+fieldName);

	        	     jsonToken = parser.nextToken();

	        	        if("FileLeafRef".equals(fieldName)){

	        	        	String folderName=parser.getValueAsString();
	        	        	System.out.println("Folders Name are :-"+folderName);        	        	 

	        	         	folderNames.add(folderName);
     	
	        	     }
	        	        
	        	    }
	         }
			return folderNames;
	        
	    }

	    
	    
	    
	    //OK HTTP URL CONNECTION
	    private String getHttpResponse(String fUrl0) throws IOException {
	    	OkHttpClient client = new OkHttpClient();

			Request request = new Request.Builder()
				    .header("Authorization", "Bearer " + token)
				    .header("accept", "application/json;odata=verbose")
				    .url(fUrl0)
				    .build();
               	String res = null;
	 		try {
				Response response = client.newCall(request).execute();
				res= response.peekBody(2048).string();
			//	int code=response.code();
				
			    StringBuilder httpFResponseStr0 = new StringBuilder();
		        
		        InputStreamReader inputStreamReader = getInputStreamReader(response);
		        
		        BufferedReader fin0 = new BufferedReader(inputStreamReader);
		        
		        String streamLine = "";
		        
		        while ((streamLine = fin0.readLine()) != null) {
		            httpFResponseStr0.append(streamLine);
		            System.out.println("streamLine==" + streamLine);
		        }
		        res= httpFResponseStr0.toString();	
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Response ok :::-"+res);

			return res;
	    }

	    
 private InputStreamReader getInputStreamReader(Response res) throws IOException {
	    	
	        InputStreamReader inputStreamReader = null;
	        if (res.code() == 200) {	        	
	            inputStreamReader = new InputStreamReader(res.body().byteStream());            		            		
	        } else 
	        {
	        	System.out.println("Inputr stream reader error");
	        }
	        return inputStreamReader;                
	    }
	     
	  
	    
 
 private String getHttpURLConnection(String token,URL fileLstUrl0) throws IOException {
 	OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
			    .header("Authorization", "Bearer " + token)
			    .header("accept", "application/json;odata=verbose")
			    .url(fileLstUrl0)
			    .build();
        	String res = null;
		try {
			Response response = client.newCall(request).execute();
			res= response.peekBody(2048).string();
			//int code=response.code();
		     
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;                
 }
 
 
 
 /*
 private InputStreamReader getInputStreamReader(HttpURLConnection httpFConn0) throws IOException {
     InputStreamReader inputStreamReader = null;
     if (httpFConn0.getResponseCode() == 200) {
         inputStreamReader = new InputStreamReader(httpFConn0.getInputStream());
     } else {
         inputStreamReader = new InputStreamReader(httpFConn0.getErrorStream());
     }
     return inputStreamReader;
 }
*/
 
 
 

	    /*
	    private HttpURLConnection getHttpURLConnection(URL fileLstUrl0) throws IOException {
	    	
	        URLConnection fConnection0 = fileLstUrl0.openConnection();
	        HttpURLConnection httpFConn0 = (HttpURLConnection) fConnection0;
	        httpFConn0.setRequestMethod("GET");
	        httpFConn0.setRequestProperty("Authorization", "Bearer " + token);
	        httpFConn0.setRequestProperty("accept", "application/json;odata=verbose");
	        httpFConn0.connect();
	        return httpFConn0;	        
	        
	    }
	    */
 

 
	    public static void main(String[] args) {
	    	
	    	SharePointData sharepointFileService=new SharePointData();
	    	SharePointDetail sharePointDetail=new SharePointDetail();
	        String siteFolderUrl = sharePointDetail.getSiteName()+"/Shared%20Documents";
	    	sharepointFileService.download(sharePointDetail.getDownloadDirectory(),siteFolderUrl);
	    	

		}

		@Override
		public void init() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public InputStream downloadFile(String relativeUrl, String siteFolderUrl) {
			// TODO Auto-generated method stub
			return null;
		}

}
