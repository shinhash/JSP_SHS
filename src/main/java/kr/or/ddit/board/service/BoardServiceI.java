package kr.or.ddit.board.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.board.vo.BoardKindVO;

public interface BoardServiceI {

	List<BoardKindVO> selectAllBoardKind(SqlSession sqlSession);
	
	List<BoardKindVO> selectAddedBoardKind(String userid, SqlSession sqlSession);	
	
	int insertBoardKind(BoardKindVO bkVO);
	
	Map<String, Object> selectBoardPageList(String BOARD_KIND_TITLE, int pageNum, int pageSize);
}
