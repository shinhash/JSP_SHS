package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.List;

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
import kr.or.ddit.file.vo.FileVO;
import kr.or.ddit.reple.service.RepleService;
import kr.or.ddit.reple.service.RepleServiceI;
import kr.or.ddit.reple.vo.RepleVO;


@WebServlet("/boardInfo")
public class BoardInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final Logger logger = LoggerFactory.getLogger(BoardInfoServlet.class);
	private BoardServiceI boardService;
	private RepleServiceI repleService;
	@Override
	public void init() throws ServletException {
		repleService = new RepleService();
		boardService = new BoardService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardSeq = Integer.parseInt(request.getParameter("boardId"));
		logger.debug("boardId : {}", boardSeq);
		
		BoardVO boardVO = boardService.selectBoardInfo(boardSeq);
		List<FileVO> fileList = boardService.selectFileList(boardSeq);
		List<RepleVO> repleList = repleService.selectRepleList(boardVO);
		
		
		request.setAttribute("boardVO", boardVO);
		request.setAttribute("fileList", fileList);
		request.setAttribute("repleList", repleList);
		request.getRequestDispatcher("/pages/board/boardInfo.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
