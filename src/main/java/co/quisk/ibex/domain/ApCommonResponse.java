package co.quisk.ibex.domain;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"httpStatus", "errorMessage", "errorCodes"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApCommonResponse {

  private Status httpStatus;
  private String errorMessage;
  private List<String> errorCodes = new ArrayList<String>(5);

  public ApCommonResponse(Status httpStatus, String code, String message) {
    errorCodes.add(code);
    this.errorMessage = message;
    this.httpStatus = httpStatus;
  }

  public Status getHttpStatus() {
    return httpStatus;
  }

  public void setHttpStatus(Status httpStatus) {
    this.httpStatus = httpStatus;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public List<String> getErrorCodes() {
    return errorCodes;
  }

  public void setErrorCodes(List<String> errorCodes) {
    this.errorCodes = errorCodes;
  }

}
