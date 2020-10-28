package kr.or.ddit.reple.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.db.MybatisUtil;
import kr.or.ddit.reple.vo.RepleVO;

public class RepleDao implements RepleDaoI {
	
	private static final Logger logger = LoggerFactory.getLogger(RepleDao.class);

	
	@Override
	public List<RepleVO> selectRepleList(BoardVO boardVO) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		List<RepleVO> repleList = sqlSession.selectList("reple.selectRepleList", boardVO);
		sqlSession.close();
		return repleList;
	}

	@Override
	public int insertReple(RepleVO repleVO) {
		
		logger.debug("write start");
		
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		int insertRepleCnt = 0;
		try {
			insertRepleCnt = sqlSession.insert("reple.insertReple", repleVO);
			logger.debug("write end");
			
		}catch(Exception e) { }
		
		if(insertRepleCnt == 1) {
			sqlSession.commit();
		}else {
			sqlSession.rollback();
		}
		sqlSession.close();
		return insertRepleCnt;
	}
	

	@Override
	public int deleteReple(RepleVO repleVO, SqlSession sqlSession) {
		return sqlSession.update("reple.deleteReple", repleVO);
	}
	


	@Override
	public int selectRepBoardId(int repleId) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		int reBoardSeq = 0;
		try{
			reBoardSeq = sqlSession.selectOne("reple.selectRepBoardId", repleId);
		}catch(Exception e) { }
		
		if(reBoardSeq == 1) {
			sqlSession.commit();
		}else {
			sqlSession.rollback();
		}
		sqlSession.close();
		
		return reBoardSeq;
	}

	

	

}
