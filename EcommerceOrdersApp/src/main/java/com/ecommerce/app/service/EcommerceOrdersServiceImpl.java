package com.ecommerce.app.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ecommerce.app.dao.EcommerceOrdersDao;
import com.ecommerce.app.entity.OrderEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class EcommerceOrdersServiceImpl implements EcommerceOrdersService {

	@Autowired
	private EcommerceOrdersDao ecommerceOrdersDao;
	@Autowired
	ObjectMapper objectMapper;

	@Override
	public OrderEntity createOrder(Integer numberOfBricks) {
		// TODO Auto-generated method stub
		return ecommerceOrdersDao.createOrder(numberOfBricks);
	}

	@Override
	public String getCustomerOrderByReferenceId(Integer referenceId) {
		// TODO Auto-generated method stub

		String responseString = "";

		OrderEntity orderEntity = ecommerceOrdersDao.getCustomerOrderByReferenceId(referenceId);
		if (orderEntity != null) {
			try {
				ObjectNode objectNode = objectMapper.createObjectNode();
				objectNode.put("orderReferenceId", orderEntity.getOrderReferenceId());
				objectNode.put("numberOfBricks", orderEntity.getNumberOfBricks());
				objectNode.put("orderStatus", orderEntity.getOrderStatus());
				responseString = objectMapper.writeValueAsString(objectNode);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			responseString = "No order details";
		}
		return responseString;

	}

	@Override
	public Set<ObjectNode> getCustomersOrders() {
		// TODO Auto-generated method stub
		Set<OrderEntity> orderDetails = ecommerceOrdersDao.getCustomersOrders();
		Set<ObjectNode> responseSet = new HashSet<ObjectNode>();
		for (OrderEntity orderEntity : orderDetails) {
			ObjectNode objectNode = objectMapper.createObjectNode();
			objectNode.put("orderReferenceId", orderEntity.getOrderReferenceId());
			objectNode.put("numberOfBricks", orderEntity.getNumberOfBricks());
			objectNode.put("orderStatus", orderEntity.getOrderStatus());
			responseSet.add(objectNode);
		}
		return responseSet;

	}

	@Override
	public Object updateCustomerOrder(Integer referenceId, Integer numberOfBricks) {
		// TODO Auto-generated method stub
		Object referenceID;
		if ((String.valueOf(referenceId).length() == 1 || String.valueOf(referenceId).length()==2 || String.valueOf(referenceId).length()==3)  && numberOfBricks != 0) {
			return referenceID = ecommerceOrdersDao.updateCustomerOrder(referenceId, numberOfBricks);
		} else {
			return "Invalid Input";
		}
		/*
		 * System.out.println("referenceID==" + referenceID); if (referenceID == 0) {
		 * return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); } return
		 * referenceID;
		 */
	}

	@Override
	public Object fullfilOrder(Integer referenceId) {
		// TODO Auto-generated method stub
		return ecommerceOrdersDao.fullfilOrder(referenceId);
	}

}
