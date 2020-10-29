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
import javax.servlet.http.HttpSession;

import kr.or.ddit.member.vo.MemberVO;


@WebFilter("/*")
public class isLoginFilter implements Filter {

	
	
	
	public void init(FilterConfig fConfig) throws ServletException {
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		
		HttpServletRequest req = (HttpServletRequest) request;
		
		String uri = req.getRequestURI();
		if(uri.equals(req.getContextPath() + "/login") || uri.endsWith(".js") || uri.endsWith(".css") || uri.endsWith(".map")) {
			chain.doFilter(request, response);
		}else {
			HttpSession session = req.getSession();
			MemberVO member = (MemberVO)session.getAttribute("MEMBER");
			// 세션에 정보가 있을 경우
			if(member != null) {
				chain.doFilter(request, response);
			}else {
				// 로그인 화면으로 재요청 지시
				HttpServletResponse resp = (HttpServletResponse)response;
				resp.sendRedirect(req.getContextPath() + "/login");
			}
		}
		
	}
	
	
	public void destroy() {
	}



	

}
