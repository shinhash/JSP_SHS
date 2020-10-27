package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.BoardServiceI;
import kr.or.ddit.board.vo.BoardKindVO;
import kr.or.ddit.db.MybatisUtil;


@WebServlet("/boardKindUpdate")
public class BoardKindUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(BoardKindUpdateServlet.class);
	
	private BoardServiceI boardService;
	@Override
	public void init() throws ServletException {
		boardService = new BoardService();
	}
	
	


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		List<BoardKindVO> bkList = boardService.selectAllBoardKind(sqlSession);
		
//		ServletContext sc = getServletContext();
//		sc.setAttribute("bkList", bkList);
		
		request.getSession().setAttribute("bkList", bkList);
		
		response.sendRedirect(request.getContextPath() + "/boardCreate");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bordKindId = Integer.parseInt(request.getParameter("upBoardKindId"));
		String boardUse = request.getParameter("boardUse");
		
		logger.debug("bordKindId : {}", bordKindId);
		logger.debug("boardUse : {}", boardUse);
		
		BoardKindVO bkVO = new BoardKindVO();
		bkVO.setBOARD_KIND_ID(bordKindId);
		bkVO.setBOARD_KIND_STATUS(boardUse);
		
		int updateCnt = boardService.updateBoardKind(bkVO);
		
		if(updateCnt == 1) {
			SqlSession sqlSession = MybatisUtil.getSqlSession();
			List<BoardKindVO> bkList = boardService.selectAllBoardKind(sqlSession);
			
//			ServletContext sc = getServletContext();
//			sc.setAttribute("bkList", bkList);
			
			request.getSession().setAttribute("bkList", bkList);
		}
		
		
		
		// update에 성공 또는 실패를 하면 boardCreate 서블릿으로 돌아간다.
		response.sendRedirect(request.getContextPath() + "/boardCreate");
	}

}
