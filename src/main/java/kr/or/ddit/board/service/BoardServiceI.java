package kr.or.ddit.board.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.board.vo.BoardKindVO;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.file.vo.FileVO;

public interface BoardServiceI {

	List<BoardKindVO> selectAllBoardKind(SqlSession sqlSession);
	
	List<BoardKindVO> selectAddedBoardKind(String userid, SqlSession sqlSession);	
	
	int insertBoardKind(BoardKindVO bkVO);
	
	Map<String, Object> selectBoardPageList(int boardKindId, int pageNum, int pageSize);
	
	int updateBoardKind(BoardKindVO bkVO);
	
	BoardVO selectBoardInfo(int boardSeq);
	
	int insertBoard(BoardVO boardVO);
	
	int insertBoardFile(FileVO fileVO);
	
	int selectBoardSeq();
	
}
