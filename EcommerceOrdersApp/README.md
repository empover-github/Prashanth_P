# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/{bootVersion}/reference/htmlsingle/#using-boot-devtools)
* [Spring Web Starter](https://docs.spring.io/spring-boot/docs/{bootVersion}/reference/htmlsingle/#boot-features-developing-web-applications)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)



Online Purchase Bricks
---------------------
To Run and Test the Application follow the steps below

Step 1: Required PC with Java8, STS 3.1 /Eclipse and Tomcat 8 or above versions installation
Step 2: Download application and Import in IDE
Step 3: Open application.properties file and give Tomcat port Number 
Step 3: Run Main Method Class (EcommerceOrdersAppApplication.java)
		( Right click on java file and select Run as -> Spring Boot App)
Step 4: Right Click on Test Class EcommerceOrdersAppApplicationTests.java and select Run as -> Junit Test
		(Will get all Test Cases pass with sample data)
Step 5: Open Postman and Send requests as shown Below 
		
		Create Order Request
		--------------------
		
		Url : localhost:8089/ecommerceOrders/createOrder
		Method : Post
		Payload Body : Json type
				
				{
					"numberOfBricks":"100"
				}
		Response
		--------
				{
					"orderReferenceId": 1,
					"numberOfBricks": 100,
					"orderStatus": "not dispathced"
				}
				
		Get Order Request
		-----------------
		
		Url : localhost:8089/ecommerceOrders/getOrderByReferenceId/1
		Method : Get
		
		Response
		--------
		{"orderReferenceId":1,"numberOfBricks":100,"orderStatus":"not dispathced"}
		
		Get Orderes Request
		-------------------
		Url : localhost:8089/ecommerceOrders/getCustomersOrders
		Method : Get
		
		Response
		--------
		[
			{
				"orderReferenceId": 1,
				"numberOfBricks": 100,
				"orderStatus": "not dispathced"
			},
			{
				"orderReferenceId": 2,
				"numberOfBricks": 200,
				"orderStatus": "not dispathced"
			}
		]
		
		Update Order Quantity Request
		-----------------------------
		
		Url : localhost:8089/ecommerceOrders/updateCustomerOrder
		Method : Put
		Payload Body : Json Type
						{
							"orderReferenceId": 1,
							"numberOfBricks": 750
						}
		Response
		--------
		1
		
		FulFill Order Request
		---------------------
		Url : localhost:8089/ecommerceOrders/fullfilOrder/1
		Method : Put
		
		Response
		--------
		1
