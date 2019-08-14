package com.ecommerce.app.dao;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.ecommerce.app.entity.OrderEntity;
import com.ecommerce.app.utility.RandomNumberGenrator;

@Repository
public class EcommerceOrdersDaoImpl implements EcommerceOrdersDao {
	Set<Integer> randomNumbersSet = new HashSet<Integer>();
	private Set<OrderEntity> orderDetails = new HashSet<OrderEntity>();
	private static int count = 0;
	public static int getCount() {
		return count;
	}

	public static void setCount(int count) {
		EcommerceOrdersDaoImpl.count = count;
	}

	@Override
	public OrderEntity createOrder(Integer numberOfBricks) {
		// TODO Auto-generated method stub
		int genNumber = RandomNumberGenrator.numGenerator();
		do {
			if (randomNumbersSet.contains(genNumber)) {
				genNumber = RandomNumberGenrator.numGenerator();
			} else {
				randomNumbersSet.add(genNumber);
				break;
			}
		} while (true);
		OrderEntity orderEntity = new OrderEntity();
		//orderEntity.setOrderReferenceId(genNumber);
		orderEntity.setOrderReferenceId(++count);
		orderEntity.setNumberOfBricks(numberOfBricks);
		orderEntity.setOrderStatus("not dispathced");
		orderDetails.add(orderEntity);
		//return orderEntity.getOrderReferenceId();
		return orderEntity;
	}

	@Override
	public OrderEntity getCustomerOrderByReferenceId(Integer referenceId) {
		// TODO Auto-generated method stub
		OrderEntity orderEntity = null;
		for (OrderEntity entity : orderDetails) {
			if (entity.getOrderReferenceId().equals(referenceId)) {
				orderEntity = new OrderEntity();
				orderEntity.setOrderReferenceId(entity.getOrderReferenceId());
				orderEntity.setNumberOfBricks(entity.getNumberOfBricks());
				orderEntity.setOrderStatus(entity.getOrderStatus());
				break;
			}
		}
		return orderEntity;
	}

	@Override
	public Set<OrderEntity> getCustomersOrders() {
		// TODO Auto-generated method stub
		return orderDetails;
	}

	@Override
	public Object updateCustomerOrder(Integer referenceId, Integer numberOfBricks) {
		// TODO Auto-generated method stub
		Integer orderId = 0;
		for (OrderEntity entity : orderDetails) {
			if (entity.getOrderReferenceId().equals(referenceId)
					&& !entity.getOrderStatus().equalsIgnoreCase("dispatched")) {
				orderId = entity.getOrderReferenceId();
				entity.setNumberOfBricks(numberOfBricks);
				break;
			}
		}
		return orderId;
	}

	@Override
	public Object fullfilOrder(Integer referenceId) {
		// TODO Auto-generated method stub
		Integer orderId = 0;
		for (OrderEntity entity : orderDetails) 
		{
			if (entity.getOrderReferenceId().equals(referenceId)) 
			{
				orderId = entity.getOrderReferenceId();
				entity.setOrderStatus("dispatched");
				break;
			}
		}
		return orderId;
		//return	orderDetails.stream().filter(x -> referenceId.equals(x.getOrderReferenceId())).findAny().orElse(null);
	}

}
