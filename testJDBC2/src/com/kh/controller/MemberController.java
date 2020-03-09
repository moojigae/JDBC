package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.service.MemberService;
import com.kh.model.vo.Member;
import com.kh.view.MemberMenu;

public class MemberController {
	private MemberMenu menu = new MemberMenu();
//	private MemberDAO md = new MemberDAO();
	private MemberService service = new MemberService();
	
	public void insertMember() {
		Member member = menu.insertMember();
		
		int result = service.insertMember(member);
		
		if(result > 0) {
			menu.displaySuccess(result + "개의 행이 추가되었습니다.");
		} else {
			menu.displayError("데이터 삽입 과정 중 오류 발생");
		}
	}

	public void selectAll() {
		ArrayList<Member> mList = service.selectAll();
		
		if(!mList.isEmpty()) {
			menu.displayMember(mList);
		} else {
			menu.displayError("조회 결과가 없습니다.");
		}
	}

	public void selectMember() {
//		검색 조건의 번호를 반환 받아 저장
		int sel = menu.selectMember();
		
		ArrayList<Member> mList = null;
		
		switch(sel){
			case 1:
				String id = menu.inputMemberId();
				mList = service.selectMemberId(id);
				break;
				
			case 2:
				char gender = menu.inputGender();
				mList = service.selectGender(gender);
				break;
			case 0: return;
		}
		if(!mList.isEmpty()) {
			menu.displayMember(mList);
		} else {
			menu.displayError("조회 결과가 없습니다.");
		}
		
	}

	public void updateMember() {
		// ID 받아오기
		String memberId = menu.inputMemberId();
		
		// 받아온 아이디가 존재하는 체크
		int check = service.checkMember(memberId);
		
		if(check < 0) {
			menu.displayError("입력한 아이디가 존재하지 않습니다");
		} else {
			int sel = menu.updateMember(); // 수정할 정보의 메뉴 번호 넘어옴
			
			if(sel == 0) return; // 0이면 종료
			
			String input = menu.inputUpdate(); // 수정할 값으로 받은 것을 input에 담기
			
			int result = service.updateMember(memberId, sel, input); // 성공하면 1 실패하면 0이기 때문에 int로 반환
			
			if(result > 0) {
				menu.displaySuccess(result + "개의 행이 수정되었습니다");
			} else {
				menu.displayError("데이터 수정 과정 중 오류 발생");
			}
		}
	}

	public void deleteMember() {
			String memberId = menu.inputMemberId();
			
			int check = service.checkMember(memberId);
			
			if(check < 0) {
				menu.displayError("입력한 아이디가 존재하지 않습니다");
			} else {
				char yn = menu.deleteMember();
				
				if(yn == 'N') return;
				
				int result = service.deleteMember(memberId);
				
				if(result > 0) {
					menu.displaySuccess(result + "개의 행이 삭제되었습니다.");
				} else {
					menu.displayError("데이터 삭제 과정 중 오류 발생");
				}
			}
	}
	
}