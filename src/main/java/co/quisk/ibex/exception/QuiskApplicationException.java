package co.quisk.ibex.exception;

import javax.ws.rs.core.Response.Status;

import co.quisk.ibex.exception.handler.QuiskApplicationExceptionHandler;

/**
 * @author enlume
 * @version 1.0.0
 * @see QuiskApplicationExceptionHandler
 * 
 * @desc A custom exception class with httpStatus and errorMessage, when request with error is generated
 *   it throws "QuiskApplicationException", and the handleApplicationException() method will be fired.
 */
public class QuiskApplicationException extends Exception {

  private static final long serialVersionUID = -2672529858660692576L;

  private String errorMessage;
  private Status httpStatus = Status.INTERNAL_SERVER_ERROR;
  
  
  public QuiskApplicationException() {
	super();
  }

  public QuiskApplicationException(String message) {
    super(message);
    this.errorMessage = message;
  }
  
  public QuiskApplicationException(String errorMessage, Status httpStatus) {
	super();
	this.errorMessage = errorMessage;
	this.httpStatus = httpStatus;
  }
  
  public Status getHttpStatus() {
	return httpStatus;
  }
  
  public String getErrorMessage() {
    return this.errorMessage;
  }
  
}
