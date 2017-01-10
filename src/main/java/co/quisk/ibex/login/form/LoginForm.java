package co.quisk.ibex.login.form;

public class LoginForm {
	
	private String email;
	private String password;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LoginForm [email=");
		builder.append(email);
		builder.append(", password=");
		builder.append("**************");
		builder.append("]");
		return builder.toString();
	}
}
