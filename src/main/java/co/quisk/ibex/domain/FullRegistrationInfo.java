package co.quisk.ibex.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mobibucks.common.domain.AddMoneyToken;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FullRegistrationInfo implements Serializable {

  private static final long serialVersionUID = 6864700433683167439L;

  private String givenName;
  private String familyName;
  private String email;
  private String telephone;
  private String countryPhoneCode;
  private String question;
  private String answer;
  private String idType;
  private String idNumber;
  private String nationalId;
  private String streetAddress;
  private String addressLocality;
  private String addressRegion;
  private String addressCountry;
  private String postalCode;
  private String birthDate;
  private String nationality;
  private String alias;
  private String idExprDate;
  private String gender;
  private String kycComplete;
  private BigDecimal initialFundedAmount;
  private boolean isPartnerRegistration;
  private ArrayList<AddMoneyToken> accountToken = new ArrayList<AddMoneyToken>();

  public String getNationality() {
    return nationality;
  }

  public void setNationality(String nationality) {
    this.nationality = nationality;
  }

  public String getAccountAlias() {
    return alias;
  }

  public void setAccountAlias(String alias) {
    this.alias = alias;
  }

  public String getGivenName() {
    return givenName;
  }

  public void setGivenName(String givenName) {
    this.givenName = givenName;
  }

  public String getFamilyName() {
    return familyName;
  }

  public void setFamilyName(String familyName) {
    this.familyName = familyName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  public String getCountryPhoneCode() {
    return countryPhoneCode;
  }

  public void setCountryPhoneCode(String countryCode) {
    this.countryPhoneCode = countryCode;
  }

  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }

  public String getIdType() {
    return idType;
  }

  public void setIdType(String idType) {
    this.idType = idType;
  }

  public String getIdNumber() {
    return idNumber;
  }

  public void setIdNumber(String idNumber) {
    this.idNumber = idNumber;
  }
  
  public String getNationalId() {
    return nationalId;
  }

  public void setNationalId(String nationalId) {
    this.nationalId = nationalId;
  }

  public String getStreetAddress() {
    return streetAddress;
  }

  public void setStreetAddress(String streetAddress) {
    this.streetAddress = streetAddress;
  }

  public String getAddressLocality() {
    return addressLocality;
  }

  public void setAddressLocality(String addressLocality) {
    this.addressLocality = addressLocality;
  }

  public String getAddressRegion() {
    return addressRegion;
  }

  public void setAddressRegion(String addressRegion) {
    this.addressRegion = addressRegion;
  }

  public String getAddressCountry() {
    return addressCountry;
  }

  public void setAddressCountry(String addressCountry) {
    this.addressCountry = addressCountry;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public String getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(String birthDate) {
    this.birthDate = birthDate;
  }

    public String getIdExprDate() {
        return idExprDate;
    }

    public void setIdExprDate(String idExprDate) {
        this.idExprDate = idExprDate;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
    
    public String getGender() {
      return gender;
  }

  public void setGender(String gender) {
      this.gender = gender;
  }

  public String getKycComplete() {
    return kycComplete;
  }

  public void setKycComplete(String kycComplete) {
    this.kycComplete = kycComplete;
  }
  public BigDecimal getInitialFundedAmount() {
    return initialFundedAmount;
  }

  public void setInitialFundedAmount(BigDecimal initialFundedAmount) {
    this.initialFundedAmount = initialFundedAmount;
  }

  public boolean getIsPartnerRegistration() {
	return isPartnerRegistration;
  }

  public void setIsPartnerRegistration(boolean isPartnerRegistration) {
	this.isPartnerRegistration = isPartnerRegistration;
  }

  public ArrayList<AddMoneyToken> getAccountToken() {
	return accountToken;
  }

  public void setAccountToken(ArrayList<AddMoneyToken> accountToken) {
	this.accountToken = accountToken;
  }

	@Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("givenName", givenName)
                .append("familyName", familyName)
                .append("email", email)
                .append("telephone", telephone)
                .append("countryPhoneCode", countryPhoneCode)
                .append("question", question)
                .append("answer", answer)
                .append("idType", idType)
                .append("idNumber", idNumber)
                .append("nationalId", nationalId)
                .append("streetAddress", streetAddress)
                .append("addressLocality", addressLocality)
                .append("addressRegion", addressRegion)
                .append("addressCountry", addressCountry)
                .append("postalCode", postalCode)
                .append("birthDate", birthDate)
                .append("nationality", nationality)
                .append("alias", alias)
                .append("idExprDate", idExprDate)
                .append("kycComplete", kycComplete)
                .append("initialFundedAmount", initialFundedAmount)
                .append("isPartnerRegistration", isPartnerRegistration)
                .append("accountToken", accountToken)
                .toString();
    }

}