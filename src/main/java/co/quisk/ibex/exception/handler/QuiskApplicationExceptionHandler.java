package co.quisk.ibex.exception.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import co.quisk.ibex.exception.QuiskApplicationException;
/**
 * @author enlume
 * @version 1.0.0
 * @desc A controller advice allows  us to use exactly the same exception handling techniques but apply them
	across the whole application
 */

@ControllerAdvice
public class QuiskApplicationExceptionHandler {

  private static final Logger LOG = LoggerFactory.getLogger(QuiskApplicationExceptionHandler.class);

 /**
  * Description: This exception handler method is default handler for any exception that return "error" view in {@link ModelAndView} object
  * @param quiskApplicationException
  * @param request
  * @return modelAndView // returns error.html page when a error has occured
  */
  @ExceptionHandler(value = QuiskApplicationException.class)
  public ModelAndView handleApplicationException(QuiskApplicationException quiskApplicationException, WebRequest request) {
	  LOG.info("Inside handleApplicationException handler method : {}", quiskApplicationException);
	  ModelAndView modelAndView = new ModelAndView("error");
	  return modelAndView;
  }

}
