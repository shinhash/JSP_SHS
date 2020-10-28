package kr.or.ddit.filter;

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

import kr.or.ddit.member.vo.MemberVO;


@WebFilter("/*")
public class isLoginFilter implements Filter {

	
	
	
	public void init(FilterConfig fConfig) throws ServletException {
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		
		HttpServletRequest req = (HttpServletRequest) request;
		
		MemberVO member = (MemberVO) req.getSession().getAttribute("MEMBER");
		
		if(member == null) {
			HttpServletResponse resp = (HttpServletResponse) response;
			resp.sendRedirect(req.getContextPath() + "/login");
			chain.doFilter(request, response);			
		}else {
			chain.doFilter(request, response);						
		}
		
	}
	
	
	public void destroy() {
	}



	

}
