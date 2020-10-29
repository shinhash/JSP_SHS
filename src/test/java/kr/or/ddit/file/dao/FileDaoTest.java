package kr.or.ddit.file.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import kr.or.ddit.file.vo.FileVO;

public class FileDaoTest {

	@Test
	public void selectFileVOTest() {
		
		/***Given***/
		FileDaoI fileDao = new FileDao();
		int fileId = 1;

		/***When***/
		FileVO fileVO = fileDao.selectFileVO(fileId);

		/***Then***/
//		assertNotNull(fileVO);
	}
}
