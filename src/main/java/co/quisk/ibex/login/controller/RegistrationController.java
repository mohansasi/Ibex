package co.quisk.ibex.login.controller;
 
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mobibucks.common.domain.QuiskServiceError;
import com.mobibucks.common.domain.QuiskServiceResponse;
import com.mobibucks.common.util.RestClient;
import com.mobibucks.common.workqueue.ResendWelcomeQueue;
import com.quisk.ibex.utils.ApplicationConstants;

import co.quisk.ap.support.model.AdminUserData;
import co.quisk.ap.support.util.Constants;
import co.quisk.ap.support.valueobject.ChangeAdminPasswordRequest;
import co.quisk.ibex.domain.FullRegistrationInfo;
import co.quisk.ibex.domain.RegistrationForm;
import co.quisk.ibex.exception.QuiskApplicationException;



@Controller
class RegistrationController {

	private static final Logger LOG = LoggerFactory.getLogger(RegistrationController.class);
    /**
     * To inject values stored in properties file into the class 
     * values include service names, success and error messages
     */
	@Value("${register.service}") private String registerService;
	@Value("${quisk.key}") private String quiskKey;
	@Value("${generic.service.call.failed.msg}") private String genericServiceException;
	@Value("${footer.year}") private String footerYear;
	@Value("${generic.service.call.failed.msg}") private String failureMessage;
	
	
	/**
     * Description: when the action(URI) is default 
     * this handler will load registration page
     * 
     * @param model
     * @return modelview of login
     * @since 1.0.0
     */
    @RequestMapping("/")
    String defaultIndex(Model model,HttpServletRequest request, HttpSession session) {
        LOG.info("Entered defaultIndex method handler.");
        
        
        
        
        return "register";
    }
	
	
	/**
     * Description: when the action(URI) is register 
	 * this handler method is invoked which will return the view for registration view
	 * 
     * @param model
     * @return modelview of login
	 * @throws QuiskApplicationException 
     * @since 1.0.0
     */
    @RequestMapping("register")
    String register(@ModelAttribute FullRegistrationInfo registrationForm ,@ModelAttribute("fiId") String fiId, Model model, RedirectAttributes redirectAttributes) throws QuiskApplicationException {
    	LOG.info("Entered register method handler.");
    	MultivaluedMap<String, Object> headerMap = new MultivaluedHashMap<String, Object>();
    	QuiskServiceResponse successResponse=null;
		QuiskServiceError errorResponse=null;
    	try{
    		if(registrationForm != null){
    			
    			
    			registrationForm.setEmail(ApplicationConstants.EMAIL);
    			registrationForm.setFamilyName(ApplicationConstants.FAMILY_NAME);
    			registrationForm.setNationalId(String.valueOf(Math.round(Math.random()*10000000)));
    			registrationForm.setCountryPhoneCode(ApplicationConstants.COUNTRY_CODE);
    			registrationForm.setIdType(ApplicationConstants.ID_TYPE);
    			registrationForm.setIdNumber(ApplicationConstants.ID_NUMBER);
    			registrationForm.setIdExprDate(ApplicationConstants.ID_EXP_DATE);
    			registrationForm.setStreetAddress(ApplicationConstants.STREET_ADDRESS);
    			registrationForm.setAddressLocality(ApplicationConstants.Address_Locality);
    			registrationForm.setAddressRegion(ApplicationConstants.Address_Region);
    			registrationForm.setAddressCountry(ApplicationConstants.Address_Country);
    			registrationForm.setPostalCode(ApplicationConstants.Postal_Code);
    			registrationForm.setBirthDate(ApplicationConstants.Birth_Date);
    			registrationForm.setNationality(ApplicationConstants.Nationality);
    			registrationForm.setKycComplete(ApplicationConstants.Kyc_Complete);
    			registrationForm.setInitialFundedAmount(BigDecimal.valueOf(ApplicationConstants.Initial_FundedAmount));
    			registrationForm.setGender(ApplicationConstants.Gender);
    			
    			
    			SimpleDateFormat f = new SimpleDateFormat(
    					ApplicationConstants.DATE_FORMATE);
    			f.setTimeZone(TimeZone.getTimeZone("UTC"));

    			headerMap.add(ApplicationConstants.DATE,f.format(new Date()));
				headerMap.add(ApplicationConstants.QUISK_KEY, quiskKey);
				
				LOG.info("Json Request {}",RestClient.objectToJson(registrationForm,FullRegistrationInfo.class));
				
				Response serviceResponse =	RestClient.callServiceWithPost(registerService,headerMap,
						RestClient.objectToJson(registrationForm ,FullRegistrationInfo.class));
				if(serviceResponse != null && serviceResponse.getEntity() != null){
					String responseJson = serviceResponse.getEntity().toString();
					if (serviceResponse.getStatus() != Status.OK.getStatusCode()){
						LOG.warn(
								"Registration service request was not successful:{}",
								serviceResponse.getStatus());
						LOG.debug(
								"Registration Service Error message object JSON: {}",
								responseJson);
						errorResponse=(QuiskServiceError) RestClient.jsonToObject(responseJson, QuiskServiceError.class);
						LOG.info("Json Error message {}",RestClient.jsonToObject(responseJson, QuiskServiceError.class));
						model.addAttribute("SuccessMessage", errorResponse.getErrorMessage());
						return "register";
					}
					else{
						successResponse = (QuiskServiceResponse) RestClient.jsonToObject(responseJson,QuiskServiceResponse.class);
						LOG.info("Json succcess Message {}",RestClient.jsonToObject(responseJson,QuiskServiceResponse.class));
						return "redirect:/registerSuccess";
					}
				}
					
    		}
    		else{
    			LOG.warn("no items available for Register  in RegistrationController ");
    		}
    	} catch (Exception e) {
			LOG.error("Exception in register : {}",
					e.getMessage());
			throw new QuiskApplicationException(failureMessage);
		}

        return "register-success";
    }
    
    @RequestMapping(value = "/registerSuccess", method = RequestMethod.GET)
	String registerationSucess(Model model, HttpSession session)
			throws QuiskApplicationException {
		LOG.info("Entered registerationSucess method handler.");
		try {
			
		} catch (Exception e) {
			LOG.error("Exception in registerationSucess : {}",e.getMessage());
			throw new QuiskApplicationException(e.getMessage());
		}
		return ApplicationConstants.REGISTER_SUCCESS;
	}
    
}
