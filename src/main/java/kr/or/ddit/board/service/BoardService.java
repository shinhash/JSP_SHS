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
	public Map<String, Object> selectBoardPageList(String BOARD_KIND_TITLE, int pageNum, int pageSize) {
		
		Map<String, Object> boardInfoMap = new HashMap<String, Object>();
		
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		logger.debug("BOARD_KIND_TITLE : {}", BOARD_KIND_TITLE);
		
		int BOARD_KIND_ID = boardDao.selectBoardKindId(BOARD_KIND_TITLE, sqlSession);
		int totalCnt = 0;
		try {
			totalCnt = boardDao.selectBoardCnt(BOARD_KIND_ID, sqlSession);			
		}catch(Exception e) { }

		
		PageVO pageVO = new PageVO(pageNum, pageSize, BOARD_KIND_ID);
		logger.debug("BOARD_KIND_ID : {}", BOARD_KIND_ID);
		
		List<BoardVO> boardList = boardDao.selectBoardPageList(pageVO, sqlSession);
		
		int pageCnt = (int)(Math.ceil(totalCnt / (float)pageVO.getPageSize()));
		
		boardInfoMap.put("pageCnt", pageCnt);
		boardInfoMap.put("boardList", boardList);
		
		sqlSession.close();
		return boardInfoMap;
	}


	


}
