package kr.or.ddit.reple.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.db.MybatisUtil;
import kr.or.ddit.reple.dao.RepleDao;
import kr.or.ddit.reple.dao.RepleDaoI;
import kr.or.ddit.reple.vo.RepleVO;

public class RepleService implements RepleServiceI {
	
	
	private static final Logger logger = LoggerFactory.getLogger(RepleService.class);

	
	private RepleDaoI repleDao;
	public RepleService() {
		repleDao = new RepleDao();
	}
	
	@Override
	public List<RepleVO> selectRepleList(BoardVO boardVO) {
		return repleDao.selectRepleList(boardVO);
	}

	@Override
	public int insertReple(RepleVO repleVO) {
		return repleDao.insertReple(repleVO);
	}

	@Override
	public int deleteReple(int repleId) {
		
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		
		RepleVO delRepVO = new RepleVO();
		delRepVO.setREPLE_SEQ(repleId);
		delRepVO.setREPLE_CONTENT("[삭제된 댓글입니다.]");
		delRepVO.setREPLE_STATUS("N");
		
		int delreCnt = repleDao.deleteReple(delRepVO, sqlSession);
		if(delreCnt == 1) {
			sqlSession.commit();
			
		}else {
			sqlSession.rollback();
		}
		sqlSession.close();
		
		return delreCnt;
	}

	@Override
	public int selectRepBoardId(int repleId) {
		return repleDao.selectRepBoardId(repleId);
	}

	

}
