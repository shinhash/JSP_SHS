package kr.or.ddit.board.dao;

import static org.junit.Assert.*;

import java.util.List;
import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.controller.FileUploadUtil;
import kr.or.ddit.board.vo.BoardKindVO;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.db.MybatisUtil;
import kr.or.ddit.file.vo.FileVO;
import kr.or.ddit.page.PageVO;

public class BoardDaoTest {

	private static final Logger logger = LoggerFactory.getLogger(BoardDaoTest.class);

	
	
	
	@Test
	public void selectAllBoardKindTest() {
		
		/***Given***/
		BoardDaoI boardDao = new BoardDao();
		SqlSession sqlSession = MybatisUtil.getSqlSession();

		/***When***/
		List<BoardKindVO> bkList = boardDao.selectAllBoardKind(sqlSession);
		sqlSession.close();
		
		/***Then***/
//		assertEquals(1, bkList.size());
	}
	
	
	
	@Test
	public void selectAddedBoardKindTest() {
		
		/***Given***/
		BoardDaoI boardDao = new BoardDao();
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		String userid = "brown";

		/***When***/
		List<BoardKindVO> memBkList = boardDao.selectAddedBoardKind(userid, sqlSession);
		sqlSession.close();

		/***Then***/
		logger.debug("======================================================memBkList.get(0) : {}", memBkList.get(0));
//		assertEquals(4, memBkList.size());
	}
	
	
	
	
	
	
	
	@Test
	public void insertBoardKindTest() {

		/***Given***/
		BoardKindVO bkVO = new BoardKindVO("test", "Y", "brown");
		BoardDaoI boardDao = new BoardDao();
		
		/***When***/
		int cnt = boardDao.insertBoardKind(bkVO);
		
		/***Then***/
//		assertEquals(1, cnt);
	}
	
	
	@Test
	public void selectBoardKindTitleTest() {
		
		/***Given***/
		BoardDaoI boardDao = new BoardDao();
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		int bkNum = 1;

		/***When***/
		String bkTitle = boardDao.selectBoardKindTitle(bkNum, sqlSession);

		/***Then***/
//		assertEquals("셀리의 게시판", bkTitle);
	}
	
	
	@Test
	public void selectBoardPageListTest() {
		
		/***Given***/
		PageVO pageVO = new PageVO(1, 10, 1);
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		BoardDaoI boardDao = new BoardDao();
		
		/***When***/
		List<BoardVO> boardList = boardDao.selectBoardPageList(pageVO, sqlSession); 

		/***Then***/
//		assertEquals(1, boardList.size());
	}
	
	
	@Test
	public void selectBoardTotalTest() {
		
		/***Given***/
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		BoardDaoI boardDao = new BoardDao();
		int bkId = 1;

		/***When***/
		int boardTotalCnt = boardDao.selectBoardTotal(bkId, sqlSession);
		
		/***Then***/
//		assertEquals(1, boardTotalCnt);
	}
	
	@Test
	public void updateBoardKindTest() {
		
		/***Given***/
		BoardDaoI boardDao = new BoardDao();
		BoardKindVO bkVO = new BoardKindVO();
		int bkId = 1;
		String bkST = "Y";
		bkVO.setBOARD_KIND_ID(bkId);
		bkVO.setBOARD_KIND_STATUS(bkST);
		
		/***When***/
		int updateBKCnt = boardDao.updateBoardKind(bkVO);

		/***Then***/
//		assertEquals(1, updateBKCnt);
	}
	
	
	
	
	@Test
	public void selectBoardInfoTest() {
		
		/***Given***/
		BoardDaoI boardDao = new BoardDao();
		int boardSeq = 1;

		/***When***/
		BoardVO boardVO = boardDao.selectBoardInfo(boardSeq);

		/***Then***/
//		assertEquals("첫 게시글 작성", boardVO.getBOARD_TITLE());
	}
	
	
	
	
	
	@Test
	public void selectBoardGnVOTest() {
		
		/***Given***/
		BoardDaoI boardDao = new BoardDao();
		int boardPSeq = 1;

		/***When***/
		List<BoardVO> boardList = boardDao.selectBoardGnVO(boardPSeq);

		/***Then***/
//		assertEquals(1, boardList.size());
	}
	
	
	
	
	@Test
	public void selectFileListTest() {
		
		/***Given***/
		BoardDaoI boardDao = new BoardDao();
		int boardSeq = 1;

		/***When***/
		List<FileVO> fileList = boardDao.selectFileList(boardSeq);

		/***Then***/
//		assertEquals(2, fileList.size());
	}
	
	
	
	@Test
	public void selectBoardSeqTest() {
		
		/***Given***/
		BoardDaoI boardDao = new BoardDao();

		/***When***/
		int boardSeq = boardDao.selectBoardSeq();
		
		/***Then***/
//		assertEquals(2, boardSeq);
	}
	
	
	@Test
	public void insertBoardTest() {
		
		/***Given***/
		BoardDaoI boardDao = new BoardDao();
		BoardVO boardVO = new BoardVO();
		
		boardVO.setBOARD_SEQ(2);
		boardVO.setBOARD_TITLE("두번째 게시글 작성");
		boardVO.setBOARD_CONTENT("<p>안녕하세요 하하 2</p>");
		boardVO.setUSERID("brown");
		boardVO.setBOARD_KIND_ID(1);
		boardVO.setBOARD_STATUS("Y");

		/***When***/
		int insertBoardCnt = boardDao.insertBoard(boardVO);

		/***Then***/
//		assertEquals(1, insertBoardCnt);
	}
	
	
	
	
	@Test
	public void insertBoardFileTest() {
		
		/***Given***/
		BoardDaoI boardDao = new BoardDao();
		
		String tempName = UUID.randomUUID().toString();
		FileVO fileVO = null;
		
		String fileEx = FileUploadUtil.getExtension("test.png");
		
		String filename = "D:\\upload\\" + tempName + "." + fileEx;
		
		fileVO = new FileVO();
		fileVO.setFILE_NAME(filename);
		fileVO.setREAL_FILE_NAME("test.png");
		fileVO.setBOARD_KIND_ID(1);
		fileVO.setBOARD_SEQ(1);
		fileVO.setFILE_STATUS("Y");

		/***When***/
		int FileAddCnt = boardDao.insertBoardFile(fileVO);
		
		/***Then***/
//		assertEquals(1, FileAddCnt);
	}
	
	
	
	@Test
	public void updateBoardInfoTest() {
		
		/***Given***/
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		BoardDaoI boardDao = new BoardDao();
		BoardVO upBoardVO = new BoardVO();
		
		upBoardVO.setBOARD_SEQ(2);
		upBoardVO.setBOARD_KIND_ID(1);
		upBoardVO.setBOARD_TITLE("수정");
		upBoardVO.setBOARD_CONTENT("<p>다시 작성 합니다. 작성자 셀리</p>");
		
		/***When***/
		int upBoardCnt = boardDao.updateBoardInfo(upBoardVO, sqlSession);
		if(upBoardCnt == 1) {
			sqlSession.commit();
		}else {
			sqlSession.rollback();
		}
		sqlSession.close();
		
		/***Then***/
//		assertEquals(1, upBoardCnt);
	}
	
	
	
	
	@Test
	public void updateFileInfoTest() {
		
		/***Given***/
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		BoardDaoI boardDao = new BoardDao();
		FileVO upfileVO = new FileVO();
		
		upfileVO.setFILE_SEQ(1);
		upfileVO.setFILE_STATUS("Y");

		/***When***/
		int upFileCnt = boardDao.updateFileInfo(upfileVO, sqlSession);
		if(upFileCnt == 1) {
			sqlSession.commit();
		}else {
			sqlSession.rollback();
		}
		sqlSession.close();

		/***Then***/
//		assertEquals(1, upFileCnt);
	}
	
	
	
	
	
	@Test
	public void updateInsertFileInfoTest() {
		
		/***Given***/
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		BoardDaoI boardDao = new BoardDao();
		
		String tempName = UUID.randomUUID().toString();
		FileVO fileVO = null;
		
		String fileEx = FileUploadUtil.getExtension("test2.png");
		
		String filename = "D:\\upload\\" + tempName + "." + fileEx;
		
		fileVO = new FileVO();
		fileVO.setFILE_SEQ(3);
		fileVO.setFILE_NAME(filename);
		fileVO.setREAL_FILE_NAME("test2.png");
		fileVO.setBOARD_KIND_ID(1);
		fileVO.setBOARD_SEQ(1);
		fileVO.setFILE_STATUS("N");

		/***When***/
		int upFileCnt = boardDao.updateInsertFileInfo(fileVO, sqlSession);
		if(upFileCnt == 1) {
			sqlSession.commit();
		}else {
			sqlSession.rollback();
		}
		sqlSession.close();

		/***Then***/
//		assertEquals(1, upFileCnt);
	}
	
	
	
	@Test
	public void delBoardStatusTest() {
		
		/***Given***/
		BoardDaoI boardDao = new BoardDao();
		BoardVO delBoardVO = new BoardVO();
		
		delBoardVO.setBOARD_SEQ(2);
		delBoardVO.setBOARD_TITLE("[삭제된 게시글 입니다.]");
		delBoardVO.setBOARD_STATUS("N");

		/***When***/
		int delBoardCnt = boardDao.delBoardStatus(delBoardVO);

		/***Then***/
//		assertEquals(1, delBoardCnt);
	}
	

}
