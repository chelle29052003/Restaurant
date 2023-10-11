package JavaFX;

public class Admins extends Users {

	private int salary;

	public Admins(String id, String name, String gender, String phone, String email, String password, int salary) {
		super(id, name, gender, phone, email, password);
		this.salary = salary;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

}
