package kr.or.ddit.member.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.member.vo.MemberVO;

public class MemberServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(MemberServiceTest.class);

	
	@Test
	public void selectMemberTest() {
		/***Given***/
		MemberServiceI memService = new MemberService();

		/***When***/
		MemberVO memVO = memService.selectMember("brown");
		
		
		/***Then***/
		logger.debug("member info : {}", memVO);
		assertNotNull(memVO);
		
		
	}

}
