/**
 * 
 */
package co.quisk.ibex.util;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author enlume
 *
 */
public class CommonUtil {

	private static final Logger LOG = LoggerFactory.getLogger(CommonUtil.class);
	
	 public static String encodeString(String username) {
		 LOG.info("Entered  encodeString method.");
	    	byte[] encoded = Base64.encodeBase64(username.getBytes()); 
			return new String(encoded);
		}
	 
	 public static String decodeString(String encodedUsername) {
	    LOG.info("Entered  decodeString method.");
	    	byte[] decoded = Base64.decodeBase64(encodedUsername.getBytes());
			return new String(decoded);
		}
}
