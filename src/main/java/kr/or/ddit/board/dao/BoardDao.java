package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.board.vo.BoardKindVO;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.db.MybatisUtil;
import kr.or.ddit.page.PageVO;

public class BoardDao implements BoardDaoI {

	
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
	public int selectBoardKindId(String BOARD_KIND_TITLE, SqlSession sqlSession) {
		int boardKindId = 0;
		try {
			boardKindId = sqlSession.selectOne("board.selectBoardKindId", BOARD_KIND_TITLE);			
		}catch(Exception e) { }
		return boardKindId;
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


	
	// 여기서부터 진행시작
	@Override
	public int updateBoardKind(BoardKindVO bkVO) {
		return 0;
	}

}
