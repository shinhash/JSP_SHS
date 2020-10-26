package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.board.vo.BoardKindVO;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.page.PageVO;

public interface BoardDaoI {

	List<BoardKindVO> selectAllBoardKind(SqlSession sqlSession);
	
	List<BoardKindVO> selectAddedBoardKind(String userid, SqlSession sqlSession);	
	
	int insertBoardKind(BoardKindVO bkVO);
	
	int selectBoardKindId(String BOARD_KIND_TITLE, SqlSession sqlSession);
	
	List<BoardVO> selectBoardPageList(PageVO pageVO, SqlSession sqlSession);
	
	int selectBoardCnt(int BOARD_KIND_ID, SqlSession sqlSession);
	
	int updateBoardKind(BoardKindVO bkVO);
	
}
