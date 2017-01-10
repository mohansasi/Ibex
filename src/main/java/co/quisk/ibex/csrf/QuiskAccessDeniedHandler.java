package co.quisk.ibex.csrf;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.csrf.InvalidCsrfTokenException;
import org.springframework.security.web.csrf.MissingCsrfTokenException;

public class QuiskAccessDeniedHandler implements AccessDeniedHandler {

  final static Logger LOG = LoggerFactory.getLogger(QuiskAccessDeniedHandler.class);

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response,
      AccessDeniedException accessDeniedException) throws IOException, ServletException {
    LOG.debug("Entering :: QuiskAccessDeniedHandler.handle() method");
    if(accessDeniedException instanceof MissingCsrfTokenException){
    	LOG.error("Got MissingCsrfTokenException. CSRF token should be included in Form data as HTTP parameter named _csrf");
    }
    if(accessDeniedException instanceof InvalidCsrfTokenException){
    	LOG.error("Got InvalidCsrfTokenException. CSRF token value is invalid, valid token should be sent in request as part of form data.");
    }
    response.sendRedirect("ACCESS_DENIED");   
    LOG.debug("Exiting :: QuiskAccessDeniedHandler.handle() method");
  }
}