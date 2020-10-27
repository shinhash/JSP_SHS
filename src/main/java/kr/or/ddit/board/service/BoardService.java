package kr.or.ddit.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.dao.BoardDao;
import kr.or.ddit.board.dao.BoardDaoI;
import kr.or.ddit.board.vo.BoardKindVO;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.db.MybatisUtil;
import kr.or.ddit.file.vo.FileVO;
import kr.or.ddit.page.PageVO;

public class BoardService implements BoardServiceI {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardService.class);

	
	private BoardDaoI boardDao;
	public BoardService() {
		boardDao = new BoardDao();
	}
	

	@Override
	public List<BoardKindVO> selectAllBoardKind(SqlSession sqlSession) {
		return boardDao.selectAllBoardKind(sqlSession);
	}
	
	@Override
	public List<BoardKindVO> selectAddedBoardKind(String userid, SqlSession sqlSession) {
		return boardDao.selectAddedBoardKind(userid, sqlSession);
	}

	

	@Override
	public int insertBoardKind(BoardKindVO bkVO) {
		return boardDao.insertBoardKind(bkVO);
	}


	@Override
	public Map<String, Object> selectBoardPageList(int boardKindId, int pageNum, int pageSize) {
		
		Map<String, Object> boardInfoMap = new HashMap<String, Object>();
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		
		
		String boardKindTitle = boardDao.selectBoardKindTitle(boardKindId, sqlSession);
		logger.debug("service => boardKindTitle : {}", boardKindTitle);
		
		
		
		// 리스트 전체 길이
		int totalCnt = 0;
		try {
			totalCnt = boardDao.selectBoardCnt(boardKindId, sqlSession);			
		}catch(Exception e) { }

		
		PageVO pageVO = new PageVO(pageNum, pageSize, boardKindId);
		logger.debug("BOARD_KIND_ID : {}", boardKindId);
		
		
		// 계층, 페이징 처리된 리스트
		List<BoardVO> boardList = boardDao.selectBoardPageList(pageVO, sqlSession);
		
		// 페이지 정보
		int pageCnt = (int)(Math.ceil(totalCnt / (float)pageVO.getPageSize()));
		
		boardInfoMap.put("pageCnt", pageCnt);
		boardInfoMap.put("boardList", boardList);
		boardInfoMap.put("boardKindTitle", boardKindTitle);
		
		sqlSession.close();
		return boardInfoMap;
	}


	@Override
	public int updateBoardKind(BoardKindVO bkVO) {
		return boardDao.updateBoardKind(bkVO);
	}


	@Override
	public BoardVO selectBoardInfo(int boardSeq) {
		return boardDao.selectBoardInfo(boardSeq);
	}


	@Override
	public int insertBoard(BoardVO boardVO) {
		return boardDao.insertBoard(boardVO);
	}


	@Override
	public int insertBoardFile(FileVO fileVO) {
		return boardDao.insertBoardFile(fileVO);
	}


	@Override
	public int selectBoardSeq() {
		return boardDao.selectBoardSeq();
	}




}
