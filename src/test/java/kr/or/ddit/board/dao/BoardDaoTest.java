package kr.or.ddit.board.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.vo.BoardKindVO;
import kr.or.ddit.db.MybatisUtil;

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
		BoardKindVO bkVO = new BoardKindVO("test", 'Y', "brown");
		BoardDaoI boardDao = new BoardDao();
		
		/***When***/
		int cnt = boardDao.insertBoardKind(bkVO);
		
		/***Then***/
		assertEquals(1, cnt);
	}
	
	
	
	
	
	

}
