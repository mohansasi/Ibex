package co.quisk.ibex.interceptor;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.quisk.ibex.utils.ApplicationConstants;

/**
 * @author enlume
 * @version 1.0.0
 * @desc AuthenticationInterceptor method will check the user active session
 *  If not available it will redirect to login page
 */

public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
 
  private static final Logger LOG = LoggerFactory.getLogger(AuthenticationInterceptor.class);
  
  private static final String[] excludePaths =new String[]{"/ibex/register","/ibex/PAGE_NOT_FOUND","/ibex/SERVER_ERROR","/ibex/METHOD_NOT_ALLOWED","/ibex/NOT_ALLOWED",
            "/register","/ibex/registerSuccess","/registerSuccess","/NOT_ALLOWED", "/PAGE_NOT_FOUND", "/SERVER_ERROR", "/METHOD_NOT_ALLOWED", "/INTERNAL_SERVER_ERROR",
            "/NOT_FOUND","/ibex/"};
  
  private static final String[] excludeAjaxPaths =new String[]{"ajaxGetFIs"};
  
  /**
   * To inject values stored in properties file into the class values include
   * service names, success and error messages
   */
  @Value("${session.expired.message}")
  private String sessionExpiredMessage;
  
	@Override
    public boolean preHandle(
        HttpServletRequest request, HttpServletResponse response, 
        Object handler)
        throws Exception {
		try{
      String uri = request.getRequestURI();
      String[] ajaxUri = request.getRequestURI().split("/");
      LOG.info("Entered AuthenticationInterceptor: preHandle method {} and referer is:{}",uri,request.getHeader("referer"));
     
      if(!Arrays.asList(excludePaths).contains(uri) && uri.indexOf(".js")<0 && 
                uri.indexOf(".css")<0 && uri.indexOf(".png")<0 && uri.indexOf(".ico")<0 && uri.indexOf(".woff2")<0
                && uri.indexOf(".jpg")<0 && !uri.equalsIgnoreCase(ApplicationConstants.DEFAULT_PATH)){ 
    	LOG.info("allowed uri {} and referer is:{}",uri,request.getHeader("referer"));
        if(request.getSession().getAttribute("adminUserData")==null){
          request.setAttribute("loginFailure",sessionExpiredMessage);
          request.getRequestDispatcher("login").forward(request, response);
          return false;
        }
        /**
         * if referer URl is null, this means the URl has been tampered and it will redirect the User to error page
         */
        if(request.getHeader("referer") == null){
        	LOG.debug("requestUri in session when referer is null is :{}", request.getSession().getAttribute("requestUri").toString());
        	if(request.getSession().getAttribute("requestUri").toString().equalsIgnoreCase(uri)){
        		return true;
        	}
        	if(uri.equalsIgnoreCase("/pageNotAvailable")){
        		LOG.debug("This message is logged when URL has been tampered and request URI is 'pageNotAvailable'.");
        		 return true;
        	}
        	request.getRequestDispatcher("pageNotAvailable").forward(request, response);
        	return false;
        }
        LOG.info("ajaxUri length is :{} and ajaxUri is:{}",ajaxUri.length,ajaxUri);
        if(ajaxUri!= null && ajaxUri.length==2){
        	if(Arrays.asList(excludeAjaxPaths).indexOf(ajaxUri[1]) < 0){
        		request.getSession().setAttribute("requestUri", uri);
        		LOG.debug("requestUri value in session is :{}", request.getSession().getAttribute("requestUri"));
        	}
        }
        if(ajaxUri!= null && ajaxUri.length==3){
        	if(Arrays.asList(excludeAjaxPaths).indexOf(ajaxUri[2]) < 0){
        		request.getSession().setAttribute("requestUri", uri);
        		LOG.debug("requestUri value in session is :{}", request.getSession().getAttribute("requestUri"));
        	}
        }
      }
		}catch(Exception e){
			LOG.error("Error inside AuthenticationInterceptor : preHandle method {}",e);
		}
     return true;
      
    }
    
}
