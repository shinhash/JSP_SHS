package kr.or.ddit.member.dao;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.db.MybatisUtil;
import kr.or.ddit.member.vo.MemberVO;

public class MemberDao implements MemberDaoI {

	@Override
	public MemberVO selectMember(String userid) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		MemberVO memVO = sqlSession.selectOne("member.selectMember", userid);
		sqlSession.close();
		return memVO;
	}
}
