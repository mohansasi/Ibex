package co.quisk.ibex.model;


public class AhAccountRequest {
  
  private String accountId;
  private String adminId;
  private String adminEmail;
  
  public String getAccountId() {
    return accountId;
  }
  
  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }
  
  public String getAdminId() {
    return adminId;
  }
  
  public void setAdminId(String adminId) {
    this.adminId = adminId;
  }
  
  public String getAdminEmail() {
    return adminEmail;
  }
  
  public void setAdminEmail(String adminEmail) {
    this.adminEmail = adminEmail;
  }
  
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder(128);
    builder.append("AhAccountRequest [accountId=").append(accountId).append(", adminId=").append(adminId)
        .append(", adminEmail=").append(adminEmail).append(']');
    return builder.toString();
  }
}
