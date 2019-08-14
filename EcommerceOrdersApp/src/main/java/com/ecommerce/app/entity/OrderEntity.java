package com.ecommerce.app.entity;

public class OrderEntity {

	private Integer orderReferenceId;
	private Integer numberOfBricks;
	private String orderStatus;
	public Integer getOrderReferenceId() {
		return orderReferenceId;
	}
	public void setOrderReferenceId(Integer orderReferenceId) {
		this.orderReferenceId = orderReferenceId;
	}
	public Integer getNumberOfBricks() {
		return numberOfBricks;
	}
	public void setNumberOfBricks(Integer numberOfBricks) {
		this.numberOfBricks = numberOfBricks;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	@Override
	public String toString() {
		return "OrderEntity [orderReferenceId=" + orderReferenceId + ", numberOfBricks=" + numberOfBricks
				+ ", orderStatus=" + orderStatus + "]";
	}
	
	
	
}
