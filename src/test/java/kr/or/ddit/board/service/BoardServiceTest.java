package kr.or.ddit.board.service;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import kr.or.ddit.board.controller.FileUploadUtil;
import kr.or.ddit.board.vo.BoardKindVO;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.db.MybatisUtil;
import kr.or.ddit.file.vo.FileVO;

public class BoardServiceTest {

	
	
	
	
	@Test
	public void selectAllBoardKindTest() {

		/***Given***/
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		BoardServiceI boardService = new BoardService();

		/***When***/
		List<BoardKindVO> bkList = boardService.selectAllBoardKind(sqlSession);

		/***Then***/
//		assertEquals(1, bkList.size());
	}
	
	
	
	@Test
	public void selectAddedBoardKindTest() {
		
		/***Given***/
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		BoardServiceI boardService = new BoardService();

		String userid = "sally";
		
		/***When***/
		List<BoardKindVO> bkList = boardService.selectAddedBoardKind(userid, sqlSession);

		/***Then***/
//		assertEquals(1, bkList.size());
	}
	
	
	
	
	
	
	@Test
	public void insertBoardKindTest() {
		
		/***Given***/
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		BoardServiceI boardService = new BoardService();

		BoardKindVO bkVO = new BoardKindVO();
		bkVO.setBOARD_KIND_ID(2);
		bkVO.setBOARD_KIND_TITLE("test");
		bkVO.setBOARD_KIND_CREATOR("brown");
		bkVO.setBOARD_KIND_STATUS("Y");

		/***When***/
		int bkCreateCnt = boardService.insertBoardKind(bkVO);
		
		/***Then***/
//		assertEquals(1, bkCreateCnt);
	}
	
	
	
	@Test
	public void selectBoardPageListTest() {
		
		/***Given***/
		BoardServiceI boardService = new BoardService();
		int bkId = 1;
		int pageNum = 1;
		int pageSize = 10;

		/***When***/
		Map<String, Object> selectBoardPageListMap = boardService.selectBoardPageList(bkId, pageNum, pageSize);

		/***Then***/
//		assertEquals(1, selectBoardPageListMap.get("pageCnt"));
	}
	
	
	
	
	@Test
	public void updateBoardKindTest() {
		
		/***Given***/
		BoardServiceI boardService = new BoardService();
		BoardKindVO bkVO = new BoardKindVO();
		
		bkVO.setBOARD_KIND_ID(1);
		bkVO.setBOARD_KIND_STATUS("N");

		/***When***/
		int upBK = boardService.updateBoardKind(bkVO);
		
		/***Then***/
//		assertEquals(1, upBK);
	}
	
	
	
	
	
	@Test
	public void selectBoardInfoTest() {
		
		/***Given***/
		BoardServiceI boardService = new BoardService();
		int boardSeq = 2;
		
		/***When***/
		BoardVO boardVO = boardService.selectBoardInfo(boardSeq);
		
		/***Then***/
//		assertEquals("수정", boardVO.getBOARD_TITLE());
	}
	
	
	
	
	@Test
	public void selectBoardGnVOTest() {
		
		/***Given***/
		BoardServiceI boardService = new BoardService();
		int boardPSeq = 2;

		/***When***/
		BoardVO boardVOGN = boardService.selectBoardGnVO(boardPSeq);
		
		/***Then***/
//		assertEquals(2, boardVOGN.getBOARD_GN());
	}
	
	
	
	@Test
	public void selectFileListTest() {
		
		/***Given***/
		BoardServiceI boardService = new BoardService();
		int boardSeq = 1;

		/***When***/
		List<FileVO> fileList = boardService.selectFileList(boardSeq);

		/***Then***/
//		assertEquals(2, fileList.size());
	}
	
	
	
	
	@Test
	public void insertBoardTest() {
		
		/***Given***/
		BoardServiceI boardService = new BoardService();
		BoardVO boardVO = new BoardVO();
		
		boardVO.setBOARD_SEQ(3);
		boardVO.setBOARD_TITLE("test three");
		boardVO.setBOARD_CONTENT("<p>test three</p>");
		boardVO.setBOARD_KIND_ID(1);
		boardVO.setBOARD_PSEQ(1);
		boardVO.setBOARD_GN(1);
		boardVO.setBOARD_STATUS("Y");
		boardVO.setUSERID("sally");
		
		/***When***/
		int insertBoardCnt = boardService.insertBoard(boardVO);

		/***Then***/
//		assertEquals(1, insertBoardCnt);
	}
	
	
	
	@Test
	public void insertBoardFileTest() {
		
		/***Given***/
		BoardServiceI boardService = new BoardService();
		FileVO fileVO = new FileVO();

		
		String fileRealName = "test123.jpg";
		// uuid
		String tempName = UUID.randomUUID().toString();
		// file 확장자
		String fileEx = FileUploadUtil.getExtension(fileRealName);
		// db에 저장할 파일의 경로와 파일의 이름 + 확장자
		String filename = "D:\\upload\\" + tempName + "." + fileEx;
		
		fileVO.setFILE_NAME(filename);
		fileVO.setREAL_FILE_NAME(fileRealName);
		fileVO.setBOARD_KIND_ID(1);
		fileVO.setBOARD_SEQ(1);
		fileVO.setFILE_STATUS("Y");

		/***When***/
		int insertBoardFileCnt = boardService.insertBoardFile(fileVO);

		/***Then***/
//		assertEquals(1, insertBoardFileCnt);
	}
	
	
	
	@Test
	public void selectBoardSeqTest() {
		
		/***Given***/
		BoardServiceI boardService = new BoardService();

		/***When***/
		int boardSeq = boardService.selectBoardSeq();
		
		/***Then***/
//		assertEquals(2, boardSeq);
	}
	
	
	
	@Test
	public void updateBoardInfoTest() {
		
		/***Given***/
		BoardServiceI boardService = new BoardService();
		Map<String, Object> updateInfoMap = new HashMap<String, Object>();
		
		BoardVO boardVO = new BoardVO();
		boardVO.setBOARD_SEQ(1);
		boardVO.setBOARD_TITLE("test 합니다.");
		boardVO.setBOARD_CONTENT("<p>this is rewrite test</p>");
		boardVO.setUSERID("sally");
		boardVO.setBOARD_KIND_ID(1);
		boardVO.setBOARD_STATUS("Y");
		
		
		List<Integer> fileIdList = null;
		List<FileVO> insertFileList = null;
		
		updateInfoMap.put("boardVO", boardVO);
		updateInfoMap.put("fileIdList", fileIdList);
		updateInfoMap.put("insertFileList", insertFileList);

		/***When***/
		int updateBoardCnt = boardService.updateBoardInfo(updateInfoMap);

		/***Then***/
//		assertEquals(1, updateBoardCnt);
	}
	
	
	
	@Test
	public void delBoardStatusTest() {
		
		/***Given***/
		BoardServiceI boardService = new BoardService();
		BoardVO boardVO = new BoardVO();
		boardVO.setBOARD_SEQ(1);
		boardVO.setBOARD_TITLE("[삭제된 게시글 입니다.]");
		boardVO.setBOARD_STATUS("N");
		
		/***When***/
		int delBoardCnt = boardService.delBoardStatus(boardVO);
		
		/***Then***/
//		assertEquals(1, delBoardCnt);
	}
}
