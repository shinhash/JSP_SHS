package kr.or.ddit.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.BoardServiceI;
import kr.or.ddit.board.vo.BoardVO;


@WebServlet("/boardCommentRegist")
public class BoardCommentInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private BoardServiceI boardService;
	@Override
	public void init() throws ServletException {
		boardService = new BoardService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int boardComPseq = Integer.parseInt(request.getParameter("boardPseq"));
		int boardKindId = Integer.parseInt(request.getParameter("boardKindId"));
		
		request.setAttribute("boardComPseq", boardComPseq);
		request.setAttribute("boardKindId", boardKindId);
		request.getRequestDispatcher("/pages/board/boardCommentRegist.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		// 해당 게시글의 gn 받아오기
//		boardService.selectBoardInfo(boardComPseq);
	}

}
