package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
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
		String boardPseq = request.getParameter("boardPseq");
		
		if(boardPseq == null) {
			request.setAttribute("boardKindId", boardKindId);
			request.getRequestDispatcher("/pages/board/boardRegist.jsp").forward(request, response);			
		}else {
			int boardPseqNum = Integer.parseInt(boardPseq);
			
			request.setAttribute("boardPseq", boardPseq);
			request.setAttribute("boardKindId", boardKindId);
			request.getRequestDispatcher("/pages/board/boardRegist.jsp").forward(request, response);	
		}
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
		
		String boardPseqText = request.getParameter("boardPseq"); 
		logger.debug("boardPseqText : {}", boardPseqText);
		
		
		// 답글작성 ==> 부모글이 있는지 확인
		int boardPseqNum = 0;
		try{
			boardPseqNum = Integer.parseInt(boardPseqText);
			logger.debug("boardPseqNum : {}", boardPseqNum);
		}catch(Exception e) { }
		
		// 부모글의 정보를 가져오기 및 셋팅
		BoardVO boardGnVO = boardService.selectBoardGnVO(boardPseqNum);
		boardVO.setBOARD_PSEQ(boardGnVO.getBOARD_SEQ());
		boardVO.setBOARD_GN(boardGnVO.getBOARD_GN());
		
		FileVO fileVO = null;
		Collection<Part> parts = request.getParts();
		
		
		// 게시글 작성(insert)
		int insertBoardCnt = boardService.insertBoard(boardVO);
//		int insertBoardCnt = 1;
		
		int FileAddCnt = 0;
		
		int boardFileAllCnt = 0; // 첨부파일의 전체 수
		int boardInsertFileCnt = 0; // 성공적으로 insert한 파일의 수
		
		if(insertBoardCnt == 1) {
			
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
						
						if(!temp[0].equals("form-data")) {
							
							
							if("filename".equals(temp[0])) {
								
								if(!"\"\"".equals(temp[1])) {
									
									fileRealName = temp[1].split("\"")[1];
									
									
									boardFileAllCnt++;
									
									// uuid
									String tempName = UUID.randomUUID().toString();
									
									// file 확장자
									String fileEx = FileUploadUtil.getExtension(fileRealName);
									
									// db에 저장할 파일의 경로와 파일의 이름 + 확장자
									String filename = "D:\\upload\\" + tempName + "." + fileEx;
									
									// 파일 업로드
									partTemp.write(filename);
									partTemp.delete();
									
									
									// 파일경로를 db에 저장
									fileVO = new FileVO();
									fileVO.setFILE_NAME(filename);
									fileVO.setREAL_FILE_NAME(fileRealName);
									fileVO.setBOARD_KIND_ID(boardKindId);
									fileVO.setBOARD_SEQ(boardSeq);
									fileVO.setFILE_STATUS("Y");
									
									FileAddCnt = boardService.insertBoardFile(fileVO);
								
									if(FileAddCnt == 1) {
										boardInsertFileCnt++;
									}
								}
							}
							
							
							
						}
						
						
					}
				}
//				logger.debug("fileName : {}", fileRealName);
			}
		}
		
		
		if(insertBoardCnt == 1 && boardFileAllCnt == boardInsertFileCnt) {
			response.sendRedirect(request.getContextPath() + "/boardInfo?boardId=" + boardSeq);
		}
		
	}

}
