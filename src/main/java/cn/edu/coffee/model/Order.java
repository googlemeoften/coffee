package cn.edu.coffee.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 訂單
 */
public class Order {
	private int orderId;
	private String extendId;
	private int totalPrice;
	private Date transactionTime;
	private int status;
	private String note;
	private int userId;

	private List<OrderItem> itemList;
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getExtendId() {
		return extendId;
	}

	public void setExtendId(String extendId) {
		this.extendId = extendId;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<OrderItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<OrderItem> itemList) {
		this.itemList = itemList;
	}

	public Date getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(Date transactionTime) {
		this.transactionTime = transactionTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public void addItem(OrderItem item) {
		if (this.itemList == null) {
			itemList = new ArrayList<OrderItem>();
		}

		itemList.add(item);
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", extendId=" + extendId
				+ ", totalPrice=" + totalPrice + ", transactionTime="
				+ transactionTime + ", status=" + status + ", note=" + note
				+ ", userId=" + userId + ", itemList=" + itemList + "]";
	}

}
