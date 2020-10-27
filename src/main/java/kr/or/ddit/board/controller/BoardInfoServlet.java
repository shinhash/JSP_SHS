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
import kr.or.ddit.board.vo.BoardVO;


@WebServlet("/boardInfo")
public class BoardInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final Logger logger = LoggerFactory.getLogger(BoardInfoServlet.class);
	private BoardServiceI boardService;
	@Override
	public void init() throws ServletException {
		boardService = new BoardService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardSeq = Integer.parseInt(request.getParameter("boardId"));
		logger.debug("boardId : {}", boardSeq);
		
		BoardVO boardVO = boardService.selectBoardInfo(boardSeq);
		
		request.setAttribute("boardVO", boardVO);
		request.getRequestDispatcher("/pages/board/boardInfo.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
