package co.quisk.ibex.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.quisk.ap.support.util.Constants;
import co.quisk.ibex.exception.QuiskApplicationException;
/**
 * All the methods for date operations
 * 
 * @author enlume
 *
 */
public class DateUtils {

	private static final Logger LOG = LoggerFactory
			.getLogger(DateUtils.class);

	public static String getMinMaxDates(int year,int month,Boolean flag) throws QuiskApplicationException{
		SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT_DD_MMM_YYYY);
		String formattedDate=null;
		try{
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.MONTH, month);
			calendar.set(Calendar.YEAR, year);
			if(flag){
				calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
			}else{
				calendar.set(Calendar.DATE, 1);
			}
			formattedDate=dateFormat.format(calendar.getTime());
		}catch(Exception e){
			LOG.error("Exception in getMinMaxDates: {}",
					e.getMessage());
			throw new QuiskApplicationException(e.getMessage());
		}
		return formattedDate;
	}
	
	public static String getTodayDate(){
		SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT_DD_MMM_YYYY);
		Calendar calendar=Calendar.getInstance();
		String formattedDate=dateFormat.format(calendar.getTime());
		return formattedDate;
	}
	
	public static String covertDateFormat(String originalDate,String originalDateFormat,String targetDateFormat){
		String formattedDate =null;
		try {
			DateFormat originalFormat = new SimpleDateFormat(originalDateFormat);
			DateFormat targetFormat = new SimpleDateFormat(targetDateFormat);
			Date convertedDate = originalFormat.parse(originalDate);
			formattedDate = targetFormat.format(convertedDate);
		} catch (ParseException e) {
			LOG.error("Exception in covertDateFormat: {}",e.getMessage());
		}
		return formattedDate;
	}
	
}
