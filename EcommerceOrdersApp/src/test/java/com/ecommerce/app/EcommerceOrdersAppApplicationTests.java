package com.ecommerce.app;

import java.io.IOException;
import java.util.Set;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.ecommerce.app.dao.EcommerceOrdersDaoImpl;
import com.ecommerce.app.entity.OrderEntity;
import com.ecommerce.app.service.EcommerceOrdersService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EcommerceOrdersAppApplicationTests 
{

	
	@Autowired
	private EcommerceOrdersService ecommerceOrdersService;
	
	/*
	 *  To Execute Test methods in sequence, written the methods names ascending order 
	 */
	
	// Create Order Success  
	@Test
	public void bricksOrderTestA()
	{
		Integer expected = 1;
		OrderEntity order = ecommerceOrdersService.createOrder(78);
		System.out.println("\n Create Order\n");
		System.out.println("_____________\n");
		System.out.println("Order Created Successfully. \n Refrenece Id: "+order.getOrderReferenceId()+"\n");
		Assert.assertEquals(expected, order.getOrderReferenceId());	
	}
	
	// Sequence No Uniqueness
	@Test
	public void bricksOrderTestB()
	{
		Integer expected = 2;
		EcommerceOrdersDaoImpl e = new EcommerceOrdersDaoImpl();
		final Integer actual = (e.getCount()+1);
		System.out.println("Order Reference Id Uniqueness\n");
		System.out.println("_____________________________\n");
		System.out.println("Next Order Sequence No is "+actual+"\n");
		Assert.assertEquals(expected, actual);	
	}
	
	
	// Get Order Details
	@Test
	public void bricksOrderTestC()
	{
		Integer expected = 78;
		String orderString = ecommerceOrdersService.getCustomerOrderByReferenceId(1);
		ObjectMapper objectMapper = new ObjectMapper();
		OrderEntity order;
		try {
			order = objectMapper.readValue(orderString, OrderEntity.class);
			System.out.println("Get Order \n");
			System.out.println("__________\n");
			System.out.println("Order Reference No:"+order.getOrderReferenceId()+"\nQuantity :"+order.getNumberOfBricks()+"\n");
			Assert.assertEquals(expected, order.getNumberOfBricks());	
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Get Order Details Failure Case
	@Test
	public void bricksOrderTestD()
	{
		Integer expected = 78;
		String orderString = ecommerceOrdersService.getCustomerOrderByReferenceId(256);
		if(orderString.equals("No order details"))
		{
			System.out.println("Get Order for Order Reference Id :256");
			System.out.println("_______________________________________\n");
			System.out.println("No Order Details \n");
			Assert.assertEquals("No order details", orderString);
		}
		else
		{
			ObjectMapper objectMapper = new ObjectMapper();
			OrderEntity order;
			try {
				order = objectMapper.readValue(orderString, OrderEntity.class);
				System.out.println("Order details:: Order Ref. No:"+order.getOrderReferenceId()+" \n Quantity ::"+order.getNumberOfBricks()+"\n");
				Assert.assertEquals(expected, order.getNumberOfBricks());	
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	 
	
	//Update Order Quantity
	@Test
	public void bricksOrderTestE()
	{
		//String expected = "{\"orderReferenceId\":1,\"numberOfBricks\":500,\"orderStatus\":\"not dispathced\"}";
		String expected = "1";
		Object order = ecommerceOrdersService.updateCustomerOrder(1,500);
		System.out.println("Update Order\n");
		System.out.println("_____________\n");
		System.out.println("Order Reference Id :  "+order.toString()+ " Updated Succesfully \n");
		Assert.assertEquals(expected, order.toString());	
	}
	//Update Order Quantity Failure Case
	@Test
	public void bricksOrderTestF()
	{
		String expected = "Invalid Input";
		Object order = ecommerceOrdersService.updateCustomerOrder(80000,500);
		System.out.println("Update Order Reference Id 80000\n");
		System.out.println("_______________________________\n");
		System.out.println("Invalid Order Refernce Id \n");
		Assert.assertEquals(expected, order.toString());	
	}
	
	//Fulfill Order
	@Test
	public void bricksOrderTestG()
	{
		String expected = "1";
		//OrderEntity order = ecommerceOrdersService.fullfilOrder(1);
		Object order = ecommerceOrdersService.fullfilOrder(1);
		System.out.println("FulFill Order\n");
		System.out.println("_____________\n");
		System.out.println("Order Dispatched Successfully \n");
		Assert.assertEquals(expected, order.toString());	
	}
	
	//Fulfill Order With Invalid Reference Id
		@Test
		public void bricksOrderTestH()
		{
			String expected = "0";
			Object order = ecommerceOrdersService.fullfilOrder(125);
			System.out.println("FulFill Order With Invalid Reference Id: 125\n");
			System.out.println("___________________________________________\n");
			System.out.println("400 Bad Request\n");
			Assert.assertEquals(expected, order.toString());	
		}
	
	//Update Order Quantity After Order Dispatched
	@Test
	public void bricksOrderTestI()
	{
		String expected = "0";
		Object order = ecommerceOrdersService.updateCustomerOrder(1,250);
		System.out.println("Update Order After Dispatch\n");
		System.out.println("___________________________\n");
		System.out.println("Order has been dispatched.\n");
		Assert.assertEquals(expected, order.toString());
	}
	
	//Get All Orders
	@Test
	public void bricksOrderTestK()
	{
		int expected = 1;
		Set<ObjectNode> orders = ecommerceOrdersService.getCustomersOrders();
		System.out.println("Get All Orders \n");
		System.out.println("______________\n");
		System.out.println(orders.toString()+"\n");
		Assert.assertEquals(expected,orders.size());	
	}
	
	// Create Order Success 
	@Test
	public void bricksOrderTestL()
	{
		Integer expected = 2;
		OrderEntity order = ecommerceOrdersService.createOrder(423);
		System.out.println("\n Create Order\n");
		System.out.println("_____________\n");
		System.out.println("Order Created Successfully. \n Refrenece Id: "+order.getOrderReferenceId()+"\n");
		Assert.assertEquals(expected, order.getOrderReferenceId());	
	}
	
	// Sequence No Uniqueness
	@Test
	public void bricksOrderTestM()
	{
		Integer expected = 3;
		EcommerceOrdersDaoImpl e = new EcommerceOrdersDaoImpl();
		final Integer actual = (e.getCount()+1);
		System.out.println("Order Reference Id Uniqueness\n");
		System.out.println("_____________________________\n");
		System.out.println("Next Order Sequence No is "+actual+"\n");
		Assert.assertEquals(expected, actual);	
	}
}
