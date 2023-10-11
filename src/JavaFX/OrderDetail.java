package JavaFX;

public class OrderDetail {

	private String orderID, menuID;
	private int qty;

	public OrderDetail(String orderID, String menuID, int qty) {
		super();
		this.orderID = orderID;
		this.menuID = menuID;
		this.qty = qty;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public String getMenuID() {
		return menuID;
	}

	public void setMenuID(String menuID) {
		this.menuID = menuID;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

}
