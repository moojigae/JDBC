package com.kh.model.dao;

import static com.kh.common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.model.vo.Member;

public class MemberDAO {
	
	private Properties prop = null; // 쿼리문 자체도 property 파일로 분리해서 관리할 수 있음
	
	public MemberDAO() {
		try {
			prop = new Properties();
			prop.load(new FileReader("query.properties")); // 맴버 객체 생성할 때마다 새로 생성됨
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int insertMember(Connection conn, Member member) {
		/*
		 * testJDBC1 프로젝트에서 DAO가 했던 업무
		 * 	1) JDBC 드라이버 등록
		 * 	2) DB 연결(Connection 객체 생성)
		 * 	3) SQL 실행
		 * 	4) 트랜잭션 처리
		 * 	5) 자원 반납
		 * 
		 * 실제로 DAO가 처리해야 하는 업무 : 3번(SQL문을 DB로 전달하여 실행하고 반환 값 받아오기)
		 * -> 1, 2, 4, 5번 업무 분담 : com.kh.common.JDBCTemplate을 만들어서 가지고 올 것임
		 * -> 1, 2 : MemberService에서 함 
		 * */
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertMember");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPwd());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getGender()+"");
			pstmt.setString(5, member.getEmail());
			pstmt.setString(6, member.getPhone());
			pstmt.setString(7, member.getAddress());
			pstmt.setInt(8, member.getAge());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			close(pstmt);
		}
		return result;
	}

	public ArrayList<Member> selectAll(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;	// 셀렉트 문이기 때문에 ResultSet 필요. 완성된 쿼리
		ArrayList<Member> mList = null;
		
		String query = prop.getProperty("selectAll");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			mList = new ArrayList<Member>();
			
			Member member = null;
			while(rset.next()) {
				// 테이블에 있는 컬럼명이 아니고 select해서 오는 컬럼명으로 값을 가지고 옴
				String memberId = rset.getString("member_id");
				String memberPwd = rset.getString("member_pwd");
				String memberName = rset.getString("member_name");
				char gender = rset.getString("gender").charAt(0);
				String email = rset.getString("email");
				String phone = rset.getString("phone");
				int age = rset.getInt("age");
				String address = rset.getString("address");
				Date enrollDate = rset.getDate("enroll_date");
				
				member = new Member(memberId, memberPwd, memberName, gender, email, phone, age, address, enrollDate);
				
				mList.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return mList;
	}

//	입력된 아이디가 포함된 모든 회원 정보 조회
	public ArrayList<Member> selectMemberId(Connection conn, String id) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Member> mList = null; // 포함된 것을 가지고 오기 때문에 여러개가 되서 ArrayList 사용
		
		String query = prop.getProperty("selectMemberId");
//		SELECT * FROM MEMBER WHERE MEMBER_ID LIKE 
		
		try {
			stmt = conn.createStatement();
			query += " '%" + id + "%'";
			rset = stmt.executeQuery(query);
			
			mList = new ArrayList<Member>();
			while(rset.next()) {
				String memberId = rset.getString("member_id");
				String memberPwd = rset.getString("member_pwd");
				String memberName = rset.getString("member_name");
				char gender = rset.getString("gender").charAt(0);
				String email = rset.getString("email");
				String phone = rset.getString("phone");
				int age = rset.getInt("age");
				String address = rset.getString("address");
				Date enrollDate = rset.getDate("enroll_date");
				
				Member member = new Member(memberId, memberPwd, memberName, gender,
											email, phone,age,address,enrollDate);
				mList.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return mList;
	}
	
	public ArrayList<Member> selectGender(Connection conn, char gender) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Member> mList = null;
		
		String query = prop.getProperty("selectGender");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, gender+"");
			
			rset = pstmt.executeQuery();
			
			mList = new ArrayList<Member>();
			
			while(rset.next()) {
				String memberId = rset.getString("member_id");
				String memberPwd = rset.getString("member_pwd");
				String memberName = rset.getString("member_name");
				char gen = rset.getString("gender").charAt(0);
				String email = rset.getString("email");
				String phone = rset.getString("phone");
				int age = rset.getInt("age");
				String address = rset.getString("address");
				Date enrollDate = rset.getDate("enroll_date");
				
				Member member = new Member(memberId, memberPwd, memberName, gen, email, phone, age, address);
				
				mList.add(member);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return mList;
	}
}
