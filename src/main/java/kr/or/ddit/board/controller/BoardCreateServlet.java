package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.List;

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
import kr.or.ddit.member.vo.MemberVO;


@WebServlet("/boardCreate")
public class BoardCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private static final Logger logger = LoggerFactory.getLogger(BoardCreateServlet.class);
	
	private BoardServiceI boardService;
	
	
	@Override
	public void init() throws ServletException {
		boardService = new BoardService();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		
		MemberVO member = (MemberVO) request.getSession().getAttribute("MEMBER");
		String userid = member.getUserid();
		
		List<BoardKindVO> memBkList = boardService.selectAddedBoardKind(userid, sqlSession);
		sqlSession.close();
		
		request.getSession().setAttribute("memBkList", memBkList);
		request.getRequestDispatcher("/pages/board/boardCreate.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberVO member = (MemberVO) request.getSession().getAttribute("MEMBER");
		String addboardName = request.getParameter("addboardName");
		String board_use = request.getParameter("board_use");
		
//		logger.debug("board_use : {}", board_use);
		BoardKindVO bkVO = new BoardKindVO(addboardName, board_use, member.getUserid());
		
		int insertCnt = boardService.insertBoardKind(bkVO);
		if(insertCnt == 1) {
			
			SqlSession sqlSession = MybatisUtil.getSqlSession();
			
			List<BoardKindVO> bkList = boardService.selectAllBoardKind(sqlSession);
			sqlSession.close();
			
			request.getSession().setAttribute("bkList", bkList);
			response.sendRedirect(request.getContextPath() + "/pages/main/main.jsp");
		}else {
			doGet(request, response);
		}
		
	}

}
