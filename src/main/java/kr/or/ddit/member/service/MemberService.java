package kr.or.ddit.member.service;

import kr.or.ddit.member.dao.MemberDao;
import kr.or.ddit.member.dao.MemberDaoI;
import kr.or.ddit.member.vo.MemberVO;

public class MemberService implements MemberServiceI {
	
	private MemberDaoI memDao;
	public MemberService() {
		memDao = new MemberDao();
	}

	@Override
	public MemberVO selectMember(String userid) {
		return memDao.selectMember(userid);
	}

}
