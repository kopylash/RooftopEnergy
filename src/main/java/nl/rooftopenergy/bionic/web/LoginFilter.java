package nl.rooftopenergy.bionic.web;

import java.io.IOException;

import javax.inject.Named;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Named
public class LoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession(false);
		String url = request.getRequestURI();
		LoginBean login =null;
		if (session != null) login = (LoginBean) session.getAttribute("loginBean");
        if (url.contains("/loginPage.xhtml")){
            chain.doFilter(req, res);
        } else
		if ((session == null || login == null) ) { 
			if (url.contains("javax.faces.resource")) {
				chain.doFilter(req, res);						
			} else {				
				response.sendRedirect(request.getContextPath() + "/loginPage.xhtml");
			} // No logged-in user found, so redirect to login page.
            }
        else if (url.contains("javax.faces.resource")) {
            chain.doFilter(req, res);
		} else if (((url.contains("loggedPage.xhtml"))) &&
				"User".equals(login.user)){
				chain.doFilter(req, res);			
		} else {
	    	response.sendRedirect(request.getContextPath() + "/loginPage.xhtml");} // No logged-in user found, so redirect to login page.
	}	

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

	@Override
	public void destroy() {

	}

}
//&& !url.contains("javax.faces.resource")

