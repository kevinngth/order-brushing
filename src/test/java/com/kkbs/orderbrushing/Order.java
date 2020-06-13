package com.kkbs.orderbrushing;

public class Order implements Comparable{

	private long orderId;
	private long shopId;
	private long userId;
	private String eventTime;

	public Order(long orderId, long shopId, long userId, String eventTime) {
		this.orderId = orderId;
		this.shopId = shopId;
		this.userId = userId;
		this.eventTime = eventTime;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getShopId() {
		return shopId;
	}

	public void setShopId(long shopId) {
		this.shopId = shopId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getEventTime() {
		return eventTime;
	}

	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}

	@Override
	public String toString() {
		return "Order{" +
			"orderId=" + orderId +
			", shopId=" + shopId +
			", userId=" + userId +
			", eventTime='" + eventTime + '\'' +
			'}';
	}

	@Override
	public int compareTo(Object o) {
		Order order = (Order) o;
		return this.eventTime.compareTo( order.eventTime );
	}
}