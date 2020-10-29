package kr.or.ddit.file.service;

import static org.junit.Assert.*;

import org.junit.Test;

import kr.or.ddit.file.dao.FileDao;
import kr.or.ddit.file.dao.FileDaoI;
import kr.or.ddit.file.vo.FileVO;

public class FileServiceTest {

	@Test
	public void selectFileVOTest() {
		
		/***Given***/
		FileServiceI fileService = new FileService();
		int fileId = 1;

		/***When***/
		FileVO fileVO = fileService.selectFileVO(fileId);

		/***Then***/
//		assertNotNull(fileVO);
	}
}
