package co.quisk.ibex.exception.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author enlume
 * @version 1.0.0
 * @desc QuiskErrorHandler used to handle all the 404 Not_FOUND, 
 * 500 Internal server errors across the platform
 * 
 */

@Controller
@SessionAttributes("adminUserData")
public class QuiskErrorHandler {
  
  private static final Logger LOG = LoggerFactory.getLogger(QuiskErrorHandler.class);

  @Value("${system.errormessage}") private String systemErrorMessage;
  @Value("${not.found.errormessage}") private String notFoundErrorMessage;
  @Value("${not.available.errormessage}") private String notAvailableErrorMessage;
  @Value("${access.denied.errormessage}") private String accessDeniedErrorMessage;
  @Value("${method.not.allowed.errormessage}") private String methodNotAllowedErrormessage;
  
  
  /**
   * Description: This error handler method is default handler for 404 (Not Found)
   * @param request
   * @param model
   * @return view
   */
  @RequestMapping("/NOT_FOUND")
  public String notFoundErrorHandler(HttpServletRequest request, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
    LOG.info("Inside notFoundErrorHandler method.");
   
    LOG.error("Not Found Error Handler error Message: {}",notFoundErrorMessage);
    if(session.getAttribute("adminUserData")!=null){
    	redirectAttributes.addFlashAttribute("errorMessage",notFoundErrorMessage);
      return "redirect:/PAGE_NOT_FOUND";
    }else{
    	model.addAttribute("errorMessage", notFoundErrorMessage);
      return "errors/error_notfound";
    }
  }
  
  
  /**
   * Description: This error handler method is default handler for 500 (Internal Server error)
   * @param request
   * @param model
   * @return view
   */
  @RequestMapping("/INTERNAL_SERVER_ERROR")
  public String internalServerErrorHandler(HttpServletRequest request, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
    LOG.info("Inside internalServerErrorHandler method.");
    
    LOG.error("Internal Server Error Handler error Message: {}",systemErrorMessage);
   
    if(session.getAttribute("adminUserData")!=null){
    	redirectAttributes.addFlashAttribute("errorMessage",systemErrorMessage);
      return "redirect:/SERVER_ERROR";
    }else{
    	model.addAttribute("errorMessage", systemErrorMessage);
      return "errors/system_error";
    }
    
  }
  
  /**
   * Description: This error handler method is default handler for 405 (Internal Server error)
   * @param request
   * @param model
   * @return view
   */
  @RequestMapping("/METHOD_NOT_ALLOWED")
  public String methodNotAllowedErrorHandler(HttpServletRequest request, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
    LOG.info("Inside methodNotAllowedErrorHandler method.");
    
    LOG.warn("methodNotAllowedErrorHandler Handler error Message: {}",methodNotAllowedErrormessage);
   
    if(session.getAttribute("adminUserData")!=null){
    	redirectAttributes.addFlashAttribute("errorMessage",methodNotAllowedErrormessage);
      return "redirect:/NOT_ALLOWED";
    }else{
    	model.addAttribute("errorMessage", methodNotAllowedErrormessage);
      return "errors/system_error";
    }
  }
  
  /**
   * Description: This error handler method is invoked when URL is tampered
   * @param request
   * @param model
   * @param session
   * @return view
   */
  @RequestMapping("/pageNotAvailable")
  public String pageNotAvailableErrorHandler(HttpServletRequest request, Model model, HttpSession session) {
    LOG.info("Inside pageNotAvailableErrorHandler method.");
    model.addAttribute("errorMessage", notAvailableErrorMessage);
    LOG.warn("pageNotAvailableErrorHandler Handler error Message: {}",notAvailableErrorMessage);
    if(session.getAttribute("adminUserData")!=null){
      return "errors/system_error_menu";
    }else{
      return "errors/system_error";
    }
  }

  /** Description: This error handler method is default handler for 403 (Forbidden)
   * @param request
   * @param model
   * @return view
  */
 @RequestMapping("/ACCESS_DENIED")
 public String accessDeniedErrorHandler(HttpServletRequest request, Model model, HttpSession session) {
	 LOG.info("Inside accessDeniedErrorHandler method.");
	 model.addAttribute("errorMessage", accessDeniedErrorMessage);
	 LOG.warn("accessDeniedErrorHandler Handler error Message: {}",accessDeniedErrorMessage);
	 if(session.getAttribute("adminUserData")!=null){
	     return "errors/system_error_menu";
	 }else{
	     return "errors/system_error";
	 }
  }
 
 /**
  * Description: This error handler method is default handler for 404 (Not Found)
  * @param request
  * @param model
  * @return view
  */
 @RequestMapping("/PAGE_NOT_FOUND")
 public String pageNotFoundErrorHandler(HttpServletRequest request, Model model, HttpSession session) {
   LOG.info("Inside pageNotFoundErrorHandler method.");
   LOG.error("Not Found Error Handler error Message: {}",notFoundErrorMessage);
   
   if(session.getAttribute("adminUserData")!=null){
     return "errors/error_notfound_menu";
   }else{
     return "errors/error_notfound";
   }
 }
 
 /**
  * Description: This error handler method is default handler for 500 (Internal Server error)
  * @param request
  * @param model
  * @return view
  */
 @RequestMapping("/SERVER_ERROR")
 public String internalServerErrorPageHandler(HttpServletRequest request, Model model, HttpSession session) {
   LOG.info("Inside internalServerErrorPageHandler method.");
   LOG.error("Internal Server Error Handler error Message: {}",systemErrorMessage);
  
   if(session.getAttribute("adminUserData")!=null){
     return "errors/system_error_menu";
   }else{
     return "errors/system_error";
   }
   
 }
 
 /**
  * Description: This error handler method is default handler for 405 (Internal Server error)
  * @param request
  * @param model
  * @return view
  */
 @RequestMapping("/NOT_ALLOWED")
 public String methodNotAllowedErrorPageHandler(HttpServletRequest request, Model model, HttpSession session) {
   LOG.info("Inside methodNotAllowedErrorPageHandler method.");
   LOG.warn("methodNotAllowedErrorHandler Handler error Message: {}",notFoundErrorMessage);
  
   if(session.getAttribute("adminUserData")!=null){
     return "errors/system_error_menu";
   }else{
     return "errors/system_error";
   }
 }

 
}
