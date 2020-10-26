package kr.or.ddit.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.BoardServiceI;
import kr.or.ddit.board.vo.BoardKindVO;


@WebServlet("/boardKindUpdate")
public class BoardKindUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(BoardKindUpdateServlet.class);
	
	private BoardServiceI boardService;
	public BoardKindUpdateServlet() {
		boardService = new BoardService();
	}
	


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bordKindId = Integer.parseInt(request.getParameter("upBoardKindId"));
		char boardUse = request.getParameter("boardUse").charAt(0);
		
		logger.debug("bordKindId : {}", bordKindId);
		logger.debug("boardUse : {}", boardUse);
		
		BoardKindVO bkVO = new BoardKindVO();
		bkVO.setBOARD_KIND_ID(bordKindId);
		bkVO.setBOARD_KIND_STATUS(boardUse);
		
//		int updateCnt = boardService.
	
	
	}

}
