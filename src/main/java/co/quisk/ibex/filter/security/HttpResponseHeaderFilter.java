package co.quisk.ibex.filter.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class HttpResponseHeaderFilter extends OncePerRequestFilter  {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) 
			throws ServletException, IOException {
		
		response.addHeader("Content-Security-Policy","script-src 'self'");
        response.setHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
	    response.setHeader("host", "ibex.quisk.co");
	    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.addHeader("X-XSS-Protection", "1");
        response.addHeader("X-Content-Type-Options", "nosniff");
		filterChain.doFilter(request, response);
	}
}
