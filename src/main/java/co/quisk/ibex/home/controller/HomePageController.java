package co.quisk.ibex.home.controller;


import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import co.quisk.ap.support.model.AdminUserData;
import co.quisk.ibex.exception.QuiskApplicationException;

@Controller
@SessionAttributes("adminUserData")
public class HomePageController {

  private static final Logger LOG = LoggerFactory.getLogger(HomePageController.class);
  @Value("${generic.service.call.failed.msg}")
  private String failureMessage;
  @Value("${default.timeout.value}")
  private Integer defaultTimeoutValue;
  
  @RequestMapping(value = "/home", method = RequestMethod.GET)
  String index(Model model, @ModelAttribute("adminUserData") AdminUserData adminUserData, HttpSession session) throws QuiskApplicationException {
    LOG.info(
        "Entered HomePageController index handler method");
	if (adminUserData == null) {
      LOG.warn("Empty adminUserData");
      return "redirect:/login";
    }
    LOG.debug(adminUserData.toString());

    return "home";
  }
  
  /**
     * Description: when the action(URI) is passwordExpiry 
	 * this handler method is invoked which will return the view for passwordexpired
	 * This method is invoked when the user clicks on Yes on password expiry notification
	 * which in turn closes the session
	 * 
     * @param model
     * @return modelview of passwordexpired
     * @throws QuiskApplicationException 
     * @since 1.0.0
     */
	@RequestMapping(value = "/passwordExpiry", method = RequestMethod.GET)
	String passwordExpired(Model model,
			@ModelAttribute("adminUserData") AdminUserData adminUserData,
			SessionStatus status) throws QuiskApplicationException {
		LOG.info("Entered passwordExpired method handler.");
			if(adminUserData.getEmail()!= null){
				String email = adminUserData.getEmail();
				model.addAttribute("adminEmail", email);
				status.setComplete();
				return "passwordexpired";
			}
		return "redirect:/login";
	}
	
	 /**
     * Description: when the action(URI) is passwordReset 
	 * this handler method is invoked which will return the view for passwordexpired page
	 * This method is invoked when the user clicks on Reset password from profile view
	 * 
     * @param model
     * @return modelview of passwordexpired
     * @throws QuiskApplicationException 
     * @since 1.0.0
     */
	@RequestMapping(value = "/passwordReset", method = RequestMethod.GET)
	String passwordReset(Model model,
			@ModelAttribute("adminUserData") AdminUserData adminUserData,
			SessionStatus status) throws QuiskApplicationException {
		LOG.info("Entered passwordReset method handler.");
			if(adminUserData.getEmail()!= null){
				String email = adminUserData.getEmail();
				model.addAttribute("adminEmail", email);
				model.addAttribute("fromHomePage", true);
				return "passwordexpired";
			}
		return "redirect:/login";
	}
	
	 /**
     * Description: when the action(URI) is logout 
	 * this handler method is invoked which will return the view for login
	 * 
	 * This method is invoked when the user clicks on logout
	 * which in turn clears the session
	 * 
     * @param model
     * @return modelview of passwordexpired
     * @throws QuiskApplicationException 
     * @since 1.0.0
     */
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	String logout(Model model,
			@ModelAttribute("adminUserData") AdminUserData adminUserData,
			SessionStatus status) throws QuiskApplicationException {
		LOG.info("Entered logout method handler.");
			if(adminUserData!=null){
				status.setComplete();
			}
		return "redirect:/login";
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	String test(Model model,
			@ModelAttribute("adminUserData") AdminUserData adminUserData,
			SessionStatus status) throws QuiskApplicationException {
		LOG.info("Entered logout method handler.");
			if(adminUserData!=null){
				status.setComplete();
			}
		return "redirect:/login";
	}
	

}
