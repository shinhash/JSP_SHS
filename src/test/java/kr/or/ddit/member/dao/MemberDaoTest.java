package kr.or.ddit.member.dao;

import static org.junit.Assert.*;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.db.MybatisUtil;
import kr.or.ddit.member.vo.MemberVO;

public class MemberDaoTest {

	private static final Logger logger = LoggerFactory.getLogger(MemberDaoTest.class);

	
	@Test
	public void selectMemberTest() {
		/***Given***/
		MemberDaoI memDao = new MemberDao();
		
		/***When***/
		MemberVO member = memDao.selectMember("brown");
		

		/***Then***/
		logger.debug("member info : {}", member);
		assertNotNull(member);
	}

}
