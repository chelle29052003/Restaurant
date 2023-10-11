package JavaFX;

public class OrderHeader {

	private String orderID, customerID, adminID, orderDate;

	public OrderHeader(String orderID, String customerID, String adminID, String orderDate) {
		super();
		this.orderID = orderID;
		this.customerID = customerID;
		this.adminID = adminID;
		this.orderDate = orderDate;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getAdminID() {
		return adminID;
	}

	public void setAdminID(String adminID) {
		this.adminID = adminID;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

}
