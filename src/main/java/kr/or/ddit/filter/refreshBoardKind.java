package kr.or.ddit.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.BoardServiceI;
import kr.or.ddit.board.vo.BoardKindVO;
import kr.or.ddit.db.MybatisUtil;


@WebFilter("/*")
public class refreshBoardKind implements Filter {

	
	private BoardServiceI boardService;
	public void init(FilterConfig fConfig) throws ServletException {
		boardService = new BoardService();
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;

		SqlSession sqlSession = MybatisUtil.getSqlSession();
		List<BoardKindVO> bkList = boardService.selectAllBoardKind(sqlSession);
		sqlSession.close();
		
		req.getSession().setAttribute("bkList", bkList);
		chain.doFilter(req, response);
	}
	
	

	public void destroy() {
	}


	


	
}
