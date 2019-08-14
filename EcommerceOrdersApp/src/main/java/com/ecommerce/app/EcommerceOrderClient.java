package com.ecommerce.app;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.Scanner;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EcommerceOrderClient {

	private static final String webServiceURI = "http://localhost:8089/ecommerceOrders";

	@Autowired
	ObjectMapper objectMapper;
	
	@SuppressWarnings("resource")
	public static void main(String[] args) 
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("Choose Option ::\n 1: Create \n 2: Get \n 3: Get All\n 4: Update\n 5: Fulfill \n Order " );
		String input = scanner.nextLine();
		processRequest(input);
	}
	
	public static void processRequest(String inputOption)
	{
		try
		{
			Scanner scanner = new Scanner(System.in);
			if("".equals(inputOption))
			{
				System.out.println("Please Enter Input Value");
			}
			else
			{
				if("1".equals(inputOption))
				{
					 try {
						 	System.out.println("Please Enter Quantity");
							String qty = scanner.nextLine();
							
							DefaultHttpClient httpClient = new DefaultHttpClient();
							HttpPost postRequest = new HttpPost(webServiceURI.concat("/createOrder"));
							StringEntity values = new StringEntity("{\"numberOfBricks\":"+qty+"}");
							values.setContentType("application/json");
							postRequest.setEntity(values);

							HttpResponse response = httpClient.execute(postRequest);
							
							HttpEntity entity = response.getEntity();
							String responseString = EntityUtils.toString(entity, "UTF-8");
							System.out.println("Order Details:\n"+responseString);
							 
						/*
						 * if (response.getStatusLine().getStatusCode() != 201) { throw new
						 * RuntimeException("Failed : HTTP error code : " +
						 * response.getStatusLine().getStatusCode()); }
						 * 
						 * BufferedReader br = new BufferedReader( new
						 * InputStreamReader((response.getEntity().getContent())));
						 * 
						 * String output; System.out.println("Output from Server .... \n"); while
						 * ((output = br.readLine()) != null) { System.out.println(output); }
						 */

							//httpClient.getConnectionManager().shutdown();

						  } catch (MalformedURLException e) {

							e.printStackTrace();
						
						  } catch (IOException e) {

							e.printStackTrace();

						  }
				}
				else if("2".equals(inputOption))
				{
					try
					{
					System.out.println("Please Enter Reference Id");
					String refId = scanner.nextLine();
					DefaultHttpClient httpClient = new DefaultHttpClient();
					HttpGet getRequest = new HttpGet(webServiceURI.concat("/getOrderByReferenceId").concat("/").concat(refId));
					getRequest.addHeader("accept", "application/json");

					HttpResponse response = httpClient.execute(getRequest);
					
					HttpEntity entity = response.getEntity();
					String responseString = EntityUtils.toString(entity, "UTF-8");
					System.out.println("Order Details:\n"+responseString);
					} catch (MalformedURLException e) {

						e.printStackTrace();
					
					  } catch (IOException e) {

						e.printStackTrace();

					  }
				}
				else if("3".equals(inputOption))
				{
				try {
						DefaultHttpClient httpClient = new DefaultHttpClient();
						HttpGet getRequest = new HttpGet(
							"http://localhost:8089/ecommerceOrders/getCustomersOrders");
						getRequest.addHeader("accept", "application/json");

						HttpResponse response = httpClient.execute(getRequest);

						/*
						 * if (response.getStatusLine().getStatusCode() != 200) { throw new
						 * RuntimeException("Failed : HTTP error code : " +
						 * response.getStatusLine().getStatusCode()); }
						 * 
						 * 
						 * BufferedReader br = new BufferedReader( new
						 * InputStreamReader((response.getEntity().getContent())));
						 * 
						 * String output; System.out.println("Output from Server .... \n"); while
						 * ((output = br.readLine()) != null) { System.out.println(output); }
						 */
						
						HttpEntity entity = response.getEntity();
						String responseString = EntityUtils.toString(entity, "UTF-8");
						System.out.println("Order Details:\n"+responseString);
						
						//httpClient.getConnectionManager().shutdown();

					  } catch (ClientProtocolException e) {
					
						e.printStackTrace();

					  } catch (IOException e) {
					
						e.printStackTrace();
					  }
				}
				else if("4".equals(inputOption))
				{
					try
					{
					System.out.println("Please Enter Order Reference Id");
					String refId = scanner.nextLine();
					System.out.println("Please Enter Quantity");
					String qty = scanner.nextLine();
					
					DefaultHttpClient httpClient = new DefaultHttpClient();
					HttpPost postRequest = new HttpPost(webServiceURI.concat("/updateCustomerOrder"));
					StringEntity values = new StringEntity("{\"orderReferenceId\":"+refId+",\"numberOfBricks\":\" "+qty+" \"}");
					values.setContentType("application/json");
					postRequest.setEntity(values);

					HttpResponse response = httpClient.execute(postRequest);
					
					HttpEntity entity = response.getEntity();
					String responseString = EntityUtils.toString(entity, "UTF-8");
					System.out.println("Updated Order:\n"+responseString);
					
					//httpClient.getConnectionManager().shutdown();
					} catch (ClientProtocolException e) {
						
						e.printStackTrace();

					  } catch (IOException e) {
					
						e.printStackTrace();
					  }
				}
				else if("5".equals(inputOption))
				{
					try
					{
					System.out.println("Please Enter Reference Id");
					String refId = scanner.nextLine();
					DefaultHttpClient httpClient = new DefaultHttpClient();
					HttpGet getRequest = new HttpGet(webServiceURI.concat("/fullfilOrder").concat("/").concat(refId));
					getRequest.addHeader("accept", "application/json");

					HttpResponse response = httpClient.execute(getRequest);
					
					HttpEntity entity = response.getEntity();
					String responseString = EntityUtils.toString(entity, "UTF-8");
					System.out.println("Order Dispatched:\n"+responseString);
					}
					catch (ClientProtocolException e) {
					
					e.printStackTrace();

				  } catch (IOException e) {
				
					e.printStackTrace();
				  }
				}
				else
				{
					System.out.println("Please Enter Valid Input");
				}

			}
			
			System.out.println("Choose Option ::\n 1: Create \n 2: Get \n 3: Get All\n 4: Update\n 5: Fulfill \n Order " );
			inputOption = scanner.nextLine();
		  
			  if(inputOption != null && !inputOption.equals("6") ) {
				  processRequest(inputOption);
			  }
			  else
			  {
				  System.out.println("\n Completed"); 
			  }
		}
		catch(Exception e)
		{
			
		}
	}
	
}
