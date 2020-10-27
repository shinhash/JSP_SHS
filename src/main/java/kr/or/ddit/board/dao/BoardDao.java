package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.vo.BoardKindVO;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.db.MybatisUtil;
import kr.or.ddit.file.vo.FileVO;
import kr.or.ddit.page.PageVO;

public class BoardDao implements BoardDaoI {

	private static final Logger logger = LoggerFactory.getLogger(BoardDao.class);

	
	@Override
	public List<BoardKindVO> selectAllBoardKind(SqlSession sqlSession) {
		return sqlSession.selectList("board.selectAllBoardKind");
	}
	
	
	@Override
	public List<BoardKindVO> selectAddedBoardKind(String userid, SqlSession sqlSession) {
		return sqlSession.selectList("board.selectAddedBoardKind", userid);
	}
	
	
	
	@Override
	public int insertBoardKind(BoardKindVO bkVO) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		int cnt = 0;
		try {			
			cnt = sqlSession.insert("board.insertBoardKind", bkVO);
		}catch(Exception e) { }
		if(cnt == 1) {
			sqlSession.commit();
		}else {
			sqlSession.rollback();
		}
		sqlSession.close();
		return cnt;
	}




	@Override
	public List<BoardVO> selectBoardPageList(PageVO pageVO, SqlSession sqlSession) {
		List<BoardVO> boardPageList = sqlSession.selectList("board.selectBoardPageList", pageVO);
		return boardPageList;
	}


	@Override
	public int selectBoardCnt(int BOARD_KIND_ID, SqlSession sqlSession) {
		return sqlSession.selectOne("board.selectBoardCnt", BOARD_KIND_ID);
	}


	
	@Override
	public int updateBoardKind(BoardKindVO bkVO) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		int updateCnt = 0;
		try{
			updateCnt = sqlSession.update("board.updateBoardKind", bkVO);
		}catch(Exception e) { }
		
		if(updateCnt == 1) {
			sqlSession.commit();
		}else {
			sqlSession.rollback();
		}
		sqlSession.close();
		return updateCnt;
	}


	@Override
	public BoardVO selectBoardInfo(int boardSeq) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		BoardVO boardVO = sqlSession.selectOne("board.selectBoardInfo", boardSeq);
		sqlSession.close();
		return boardVO;
	}


	@Override
	public int insertBoard(BoardVO boardVO) {
		
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		int insertCnt = 0;
		int insertUpdateCnt = 0;
		
		try {
			insertCnt = sqlSession.insert("board.insertNewBoard", boardVO);
		}catch(Exception e) { }
		
		
		
		if(insertCnt == 1) {
			try {
				insertUpdateCnt = sqlSession.update("board.updateNewInsertFile", boardVO);	
			}catch(Exception e) { }
			
			if(insertUpdateCnt == 1) {
				sqlSession.commit();
			}
			
		}else if(insertCnt != 1 || insertUpdateCnt != 1) {
			sqlSession.rollback();
		}
		
		sqlSession.close();
		
		logger.debug("insertCnt : {}", insertCnt);
		logger.debug("insertUpdateCnt : {}", insertUpdateCnt);
		return insertCnt;
	}


	@Override
	public String selectBoardKindTitle(int boardKindId, SqlSession sqlSession) {
		return sqlSession.selectOne("board.selectBoardKindTitle", boardKindId);
	}

	
	
	@Override
	public int insertBoardFile(FileVO fileVO) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		
		int fileSeq = sqlSession.selectOne("board.selectFileSeq");
		fileVO.setFILE_SEQ(fileSeq);
		
		int insertBoardFileCnt = 0;
		try {
			insertBoardFileCnt = sqlSession.insert("board.insertBoardFile", fileVO);			
		}catch(Exception e) { }
		
		if(insertBoardFileCnt == 1) {
			sqlSession.commit();
		}else {
			sqlSession.rollback();
		}
		sqlSession.close();
		
		return insertBoardFileCnt;
	}


	@Override
	public int selectBoardSeq() {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		int boardSeq = sqlSession.selectOne("board.selectFileSeq");
		sqlSession.close();
		return boardSeq;
	}

	
}
