package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.BoardServiceI;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.file.vo.FileVO;
import kr.or.ddit.member.vo.MemberVO;


@WebServlet("/boardRegist")
@MultipartConfig
public class BoardRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final Logger logger = LoggerFactory.getLogger(BoardRegistServlet.class);
	
	private BoardServiceI boardService;
	@Override
	public void init() throws ServletException {
		boardService = new BoardService();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardKindId = Integer.parseInt(request.getParameter("boardKindId"));
		
		
		request.setAttribute("boardKindId", boardKindId);
		request.getRequestDispatcher("/pages/board/boardRegist.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String boardTitle = request.getParameter("boardTitle");
		String editordata = request.getParameter("editordata");
		int boardKindId = Integer.parseInt(request.getParameter("BOARD_KIND_ID"));
	
		logger.debug("editordata : {}", editordata);
		
		MemberVO memVO = (MemberVO)request.getSession().getAttribute("MEMBER");
		int boardSeq = boardService.selectBoardSeq();
		
		
		BoardVO boardVO = new BoardVO();
		boardVO.setBOARD_SEQ(boardSeq);
		boardVO.setBOARD_TITLE(boardTitle);
		boardVO.setBOARD_CONTENT(editordata);
		boardVO.setUSERID(memVO.getUserid());
		boardVO.setBOARD_KIND_ID(boardKindId);
		boardVO.setBOARD_STATUS("Y");
		
		int insertCnt = boardService.insertBoard(boardVO);
		
		
		int boardFileAddCnt = 0;
		
		
		FileVO fileVO = null;
		Collection<Part> parts = request.getParts();
		for(Part part : parts) {
			
			Part partTemp  = part;
			String partName = partTemp.getName();
			
			String fileRealName = "";
			
			if(partName.equals("fileInput")) {
				
				String attchHeader = partTemp.getHeader("Content-disposition");
				logger.debug("attchHeader : {}", attchHeader);
				
				String[] headerInfo = attchHeader.split("; ");
				
				for(String headerSplit : headerInfo) {
					
					String[] temp = headerSplit.split("=");
					
					if("filename".equals(temp[0])) {
						fileRealName = temp[1].split("\"")[1];
						
						
						// 파일 업로드
						partTemp.write("D:\\upload\\" + fileRealName);
						partTemp.delete();
						
						
						// 파일경로를 db에 저장
						String tempName = UUID.randomUUID().toString();
						fileVO = new FileVO();
						fileVO.setFILE_NAME(tempName);
						fileVO.setREAL_FILE_NAME(fileRealName);
						fileVO.setBOARD_KIND_ID(boardKindId);
						fileVO.setBOARD_SEQ(boardSeq);
						boardFileAddCnt = boardService.insertBoardFile(fileVO);
					}
				}
			}
			logger.debug("fileName : {}", fileRealName);
		}
		
		
		
		if(insertCnt == 1 && boardFileAddCnt == 1) {
			response.sendRedirect(request.getContextPath() + "/boardInfo?boardId=" + boardSeq);
			
		}
		
	}

}
