package JavaFX;

public class Customers extends Users {

	private String address;

	public Customers(String id, String name, String gender, String phone, String email, String password,
			String address) {
		super(id, name, gender, phone, email, password);
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
