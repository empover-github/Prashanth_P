package com.ecommerce.app.controller;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ecommerce.app.entity.OrderEntity;
import com.ecommerce.app.service.EcommerceOrdersService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping(value = "/ecommerceOrders")
public class EcommerceOrdersController {

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	private EcommerceOrdersService ecommerceOrdersService;

	@PostMapping(value = "/createOrder")
	public Object createOrder(@RequestBody OrderEntity orderEntity) {
		OrderEntity referenceID;
		if (orderEntity.getNumberOfBricks() != 0) 
		{
			referenceID = ecommerceOrdersService.createOrder(orderEntity.getNumberOfBricks());
		} else {
			return "Invalid Input";
		}
		return referenceID;
	}

	@GetMapping(value = "/getOrderByReferenceId/{referenceId}")
	public String getOrderByReferenceId(@PathVariable("referenceId") Integer referenceId) {
		return ecommerceOrdersService.getCustomerOrderByReferenceId(referenceId);
	}

	@GetMapping(value = "/getCustomersOrders")
	public Set<ObjectNode> getCustomerOrders() {
		return ecommerceOrdersService.getCustomersOrders();
	}

	@PutMapping(value = "/updateCustomerOrder")
	public Object updateCustomerOrder(@RequestBody OrderEntity orderEntity) 
	{
		Object referenceID = 0;
		referenceID = ecommerceOrdersService.updateCustomerOrder(orderEntity.getOrderReferenceId(),orderEntity.getNumberOfBricks());
		if("0".equals(referenceID))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Can not Update");
		else
			return referenceID;
	}

	@PutMapping(value = "/fullfilOrder/{referenceId}")
	public Object fullfilOrder(@PathVariable("referenceId") Integer referenceId) {
		Object referenceID = 0;
		if (String.valueOf(referenceId).length() == 1 || String.valueOf(referenceId).length() == 2 || String.valueOf(referenceId).length() == 3) 
		{
			referenceID = ecommerceOrdersService.fullfilOrder(referenceId);
			if("0".equals(referenceID.toString()))
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("400 Bad Request");
			else
				return referenceID;
		} 
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("400 Bad Request");
		}
		/*
		 * if (referenceID == 0) return
		 * ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		 * 
		 * return ResponseEntity.status(HttpStatus.OK).body(null);
		 */
	}

}
