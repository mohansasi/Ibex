package co.quisk.ibex.model;

public class UserProfileRequest {
	private String accountId;
	private String adminEmailId;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAdminEmailId() {
		return adminEmailId;
	}

	public void setAdminEmailId(String adminEmailId) {
		this.adminEmailId = adminEmailId;
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("UserProfile [ accountId=").append(accountId)
				.append(", adminEmailId=").append(adminEmailId);
		return sb.toString();
	}
}
