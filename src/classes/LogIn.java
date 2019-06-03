package classes;

public class LogIn {

	private String name;
	private String password;
	private String clientType;
	
	public LogIn() {
		super();
	}

	public LogIn(String name, String password, String clientType) {
		super();
		this.name = name;
		this.password = password;
		this.clientType = clientType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getClientType() {
		return clientType;
	}

	public void setClientType(String clientType) {
		this.clientType = clientType;
	}

	@Override
	public String toString() {
		return "LogIn [name=" + name + ", password=" + password + ", clientType=" + clientType + "]";
	}
	
}
