package com.ecommerce.app.service;

import java.util.Set;

import com.ecommerce.app.entity.OrderEntity;
import com.fasterxml.jackson.databind.node.ObjectNode;

public interface EcommerceOrdersService {

	public OrderEntity createOrder(Integer numberOfBricks);

	public String getCustomerOrderByReferenceId(Integer referenceId);

	public Set<ObjectNode> getCustomersOrders();

	public Object updateCustomerOrder(Integer referenceId, Integer numberOfBricks);

	public Object fullfilOrder(Integer referenceId);

}
