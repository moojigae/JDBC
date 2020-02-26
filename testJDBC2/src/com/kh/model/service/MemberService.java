package com.kh.model.service;

// static메소드 이름만 사용하기 위한 방법 중 하나
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.model.dao.MemberDAO;
import com.kh.model.vo.Member;

public class MemberService {
// controller와 dao사이에 있음 

	public int insertMember(Member member) {
		Connection conn = getConnection();
		
		MemberDAO mDAO = new MemberDAO(); // 필드에 넣지 않고 메소드 안에 넣어놔야 수정이 생겼을 때 그 때 그 때 변경됨
		
		int result = mDAO.insertMember(conn, member); // dao에서 conn사용하라고 conn을 매개변수로 넣어줌
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}

	public ArrayList<Member> selectAll() {
		Connection conn = getConnection();
		
		MemberDAO mDAO = new MemberDAO();
		
		ArrayList<Member> mList = mDAO.selectAll(conn);
		
		
		return mList;
	}

	public ArrayList<Member> selectMemberId(String id) {
		Connection conn = getConnection();
		MemberDAO mDAO = new MemberDAO();
		
		ArrayList<Member> mList = mDAO.selectMemberId(conn, id);
		
		return mList;
	}

	public ArrayList<Member> selectGender(char gender) {
		Connection conn = getConnection();
		MemberDAO mDAO = new MemberDAO();
		
		ArrayList<Member> mList = mDAO.selectGender(conn, gender);
		
		return mList;
		
	}
}
