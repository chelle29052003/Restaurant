package JavaFX;

public class Carts {

	private String id, name;
	private int price, qty, total;

	public Carts(String id, String name, int price, int qty, int total) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.qty = qty;
		this.total = total;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}
