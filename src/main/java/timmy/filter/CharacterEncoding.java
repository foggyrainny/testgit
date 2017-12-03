package timmy.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


/**
 * Servlet Filter implementation class CharacterEncoding
 */
//@WebFilter("/CharacterEncoding")
public class CharacterEncoding implements Filter {

    private boolean ignore;
    
    private String encod;
    
    public CharacterEncoding() {
    	
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		ignore = false;
		encod = null;
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		if(this.ignore && encod!= null){
		request.setCharacterEncoding(this.encod);	
		response.setCharacterEncoding(this.encod);
		response.setContentType("text/html;charset="+this.encod);
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		ignore=Boolean.valueOf(fConfig.getInitParameter("ignore"));
		encod=fConfig.getInitParameter("encoding");
	}

}
