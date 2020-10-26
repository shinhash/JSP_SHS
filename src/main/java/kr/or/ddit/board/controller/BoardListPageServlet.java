package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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


@WebServlet("/boardListPage")
public class BoardListPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private static final Logger logger = LoggerFactory.getLogger(BoardListPageServlet.class);
	private BoardServiceI boardService;
	public BoardListPageServlet() {
		boardService = new BoardService();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String boardKindTitle = request.getParameter("boardKindTitle");
		logger.debug("boardTitle : {}", boardKindTitle);
		
		int pageNum = 1;
		try {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}catch(Exception e) { }
		
		Map<String, Object> boardInfoMap = boardService.selectBoardPageList(boardKindTitle, pageNum, 10);
		
		int pageCnt = (Integer) boardInfoMap.get("pageCnt");
		List<BoardVO> boardPageList = (List<BoardVO>) boardInfoMap.get("boardList");
		
		
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("pageCnt", pageCnt);
		request.setAttribute("boardPageList", boardPageList);
		request.setAttribute("boardKindTitle", boardKindTitle);
		request.getRequestDispatcher("/pages/board/boardPageList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
