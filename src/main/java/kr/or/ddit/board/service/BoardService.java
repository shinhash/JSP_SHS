package kr.or.ddit.board.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Part;

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
import kr.or.ddit.reple.vo.RepleVO;

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
			totalCnt = boardDao.selectBoardTotal(boardKindId, sqlSession);			
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
	public BoardVO selectBoardGnVO(int boardPseqNum) {
		
		List<BoardVO> boardList = boardDao.selectBoardGnVO(boardPseqNum);
		BoardVO boardVO = null;
		if(boardList != null && boardList.size() > 0) {
			boardVO = boardList.get(0);
		}else{
			boardVO = new BoardVO();
		}
		return boardVO;
	}

	


	@Override
	public int insertBoardFile(FileVO fileVO) {
		return boardDao.insertBoardFile(fileVO);
	}


	@Override
	public int selectBoardSeq() {
		return boardDao.selectBoardSeq();
	}


	@Override
	public List<FileVO> selectFileList(int boardSeq) {
		return boardDao.selectFileList(boardSeq);
	}


	@Override
	public int updateBoardInfo(Map<String, Object> updateInfoMap) {
		
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		BoardVO updateBoardVO = (BoardVO) updateInfoMap.get("boardVO");
		List<Integer> delFileIdList = (List<Integer>) updateInfoMap.get("fileIdList");
		List<FileVO> insertFileList = (List<FileVO>) updateInfoMap.get("insertFileList");
		
		int updateBoardCnt = boardDao.updateBoardInfo(updateBoardVO, sqlSession);
		int updateFileInfoCnt = 0;
		int updateCnt = 0;
		
		int insertFileInfoCnt = 0;
		int insertCnt = 0;
		
		int updateResult = 0;
		if(updateBoardCnt == 1) {
			
			// 파일 수정
			if(delFileIdList != null) {
				for(int fileId : delFileIdList) {
					FileVO updateFileVO = new FileVO();
					updateFileVO.setFILE_SEQ(fileId);
					updateFileVO.setFILE_STATUS("N");
					
					updateFileInfoCnt = boardDao.updateFileInfo(updateFileVO, sqlSession);
					if(updateFileInfoCnt == 1) {
						updateCnt++;
					}
				}
			}else {
				delFileIdList = new ArrayList<Integer>();
			}
			
			// 파일 추가
			if(insertFileList != null) {
				for(FileVO insertFile : insertFileList) {
					int fileSeq = selectBoardSeq();
					insertFile.setFILE_SEQ(fileSeq);
					insertFileInfoCnt = boardDao.updateInsertFileInfo(insertFile, sqlSession);
					if(insertFileInfoCnt == 1) {
						insertCnt++;
					}
				}
			}else {
				insertFileList = new ArrayList<FileVO>();
			}
			
//			if(updateCnt == delFileIdList.size() && insertCnt == insertFileList.size()) { // 둘다 성공할 경우
//				updateResult = 1;
//				sqlSession.commit();
//				
//			)else if(updateCnt != delFileIdList.size() || insertCnt != insertFileList.size()) { // 둘중 하나가 실패할 경우
//				sqlSession.rollback();
//				
//			}
			
		}
		
		if(updateCnt == delFileIdList.size() && insertCnt == insertFileList.size()) { // 둘다 성공할 경우
			updateResult = 1;
			sqlSession.commit();
			
		}else if(updateBoardCnt != 1 || (updateCnt != delFileIdList.size() || insertCnt != insertFileList.size())) { // 둘중 하나가 실패할 경우이거나 게시판을 최신화 하지 못했을 경우
			sqlSession.rollback();
		}
		
		
		
		sqlSession.close();
		return updateResult;
	}


	@Override
	public int delBoardStatus(BoardVO boardVO) {
		return boardDao.delBoardStatus(boardVO);
	}




}
