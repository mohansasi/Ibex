package co.quisk.ibex.login.controller;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import co.quisk.ibex.domain.FullRegistrationInfo;
import co.quisk.ibex.exception.QuiskApplicationException;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy({
	@ContextConfiguration(locations = { "classpath*:servlet-context.xml" }),
})
@WebAppConfiguration
public class RegistrationControllerTest {
	@Resource
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;
	RestTemplate restTemplate = new RestTemplate();
	private MockRestServiceServer mockServer;
	@Value("${generic.service.call.failed.msg}") private String failureMessage;


	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		mockServer = MockRestServiceServer.createServer(restTemplate);
	}
	
	String fullRegistrationSuccessBody="{\"givenName\" : \"jai\",\"familyName\" : \"test name\",\"email\" : \"test@gmail.com\",\"telephone\" : \"8822663377\","
			+ "\"countryPhoneCode\" :\"91\",\"question\" : null,\"answer\" : null,\"idType\" : \"id type\",\"idNumber\" : \"11223351\","
			+ "\"nationalId\" : \"6636599\",\"streetAddress\" :\"address 1\",\"addressLocality\" :\"city\",\"addressRegion\" :\"state\","
			+ "\"addressCountry\" :\"INDIA\",\"postalCode\" :\"54321\",\"birthDate\" :\"4/4/1990\",\"nationality\" :\"IN\",\"alias\" : null,"
			+ "\"idExprDate\" :\"05/28/2019\",\"gender\" :\"Male\",\"kycComplete\" :\"true\",\"initialFundedAmount\" :100.0,\"isPartnerRegistration\": false,"
			+ "\"accountToken\" :[ ],\"accountAlias\" : null}";


	@Test
	public void fullRegistrationInfo_Success()throws QuiskApplicationException{
		String uri = "register";
		try {
			mockServer.expect(requestTo(uri))
			.andExpect(method(HttpMethod.POST))
			.andRespond(withSuccess(fullRegistrationSuccessBody, MediaType.APPLICATION_JSON));
			String responseData = fullRegistrationInfoResponse();
			mockServer.verify();
			assertThat(
					responseData.toString(),
					allOf(containsString("givenName"),
							containsString("familyName")));
		} catch (Exception e) {
			fail();
			e.printStackTrace();
		}
	}

	@Test
	public void fullRegistrationInfo_Failure() throws QuiskApplicationException{
		String uri = "register";
		try {
			String responseData;
			mockServer.expect(requestTo(uri))
			.andExpect(method(HttpMethod.POST))
			.andRespond(withStatus(HttpStatus.NOT_FOUND));
			responseData = fullRegistrationInfoResponse();
			mockServer.verify();
			assertThat(
					responseData.toString(),
					allOf(containsString("Exception"),
							containsString("404")));
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void fullRegistrationInfo_Internal_Server_error() {
		String uri = "register";
		mockServer.expect(requestTo(uri))
		.andExpect(method(HttpMethod.POST))
		.andRespond(withServerError());
		String responseData;
		try {
			responseData = fullRegistrationInfoResponse();
			mockServer.verify();
			assertThat(responseData.toString(), allOf(containsString("Internal Server Error"), containsString("500")));
		} catch (Exception e) {
			fail();

		}
	}

	public String fullRegistrationInfoResponse() {
		String result;
		final String url = "register";
		try {
			FullRegistrationInfo fullRegistrationInfoRequest = getFullRegistrationInfoRequest();
			FullRegistrationInfo responseData = restTemplate.postForObject(url,
					fullRegistrationInfoRequest, FullRegistrationInfo.class);
			result = responseData.toString();
		} catch (Exception e) {
			result = "Get FAILED\n" + ExceptionUtils.getFullStackTrace(e);
		}
		return result;
	}

	FullRegistrationInfo getFullRegistrationInfoRequest(){
		FullRegistrationInfo registrationForm=new FullRegistrationInfo();
		registrationForm.setEmail("abc@gmail.com");
		registrationForm.setFamilyName("funny");
		registrationForm.setNationalId(String.valueOf(Math.round(Math.random()*10000000)));
		registrationForm.setCountryPhoneCode("91");
		registrationForm.setIdType("abc");
		registrationForm.setIdNumber("98754");
		registrationForm.setIdExprDate("5/01/2017");
		registrationForm.setStreetAddress("xxxxxxx");
		registrationForm.setAddressLocality("abc");
		registrationForm.setAddressRegion("def");
		registrationForm.setAddressCountry("India");
		registrationForm.setPostalCode("800064");
		registrationForm.setBirthDate("5/01/1992");
		registrationForm.setNationality("Indian");
		registrationForm.setKycComplete("xyz");
		registrationForm.setInitialFundedAmount(BigDecimal.valueOf(1000));
		registrationForm.setGender("Male");
		return registrationForm;
	}

}
