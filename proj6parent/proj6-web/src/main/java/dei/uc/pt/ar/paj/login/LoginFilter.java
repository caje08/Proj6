package dei.uc.pt.ar.paj.login;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter({"/index.xhtml", "/common/*"})
public class LoginFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp=(HttpServletResponse) response;
		
		if(req.getSession().getAttribute("sessionLoggedIn")==null){
			resp.sendRedirect(req.getContextPath() + "/login.xhtml");
		}

		if (req.getSession().getAttribute("sessionLoggedIn")!=null){
			boolean a=(boolean) req.getSession().getAttribute("sessionLoggedIn");
			if(!a){
				resp.sendRedirect(req.getContextPath() + "/login.xhtml");
			}else {
				chain.doFilter(request, response);
			}
		}
	}

	@Override
	public void destroy() {
	}

}