package com.ecommerce.app.dao;

import java.util.Set;

import com.ecommerce.app.entity.OrderEntity;

public interface EcommerceOrdersDao {

	public OrderEntity createOrder(Integer numberOfBricks);

	public OrderEntity getCustomerOrderByReferenceId(Integer referenceId);

	public Set<OrderEntity> getCustomersOrders();

	public Object updateCustomerOrder(Integer referenceId, Integer numberOfBricks);

	public Object fullfilOrder(Integer referenceId);

}
