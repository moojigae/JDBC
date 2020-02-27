package com.kh.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.controller.MemberController;
import com.kh.model.vo.Member;

public class MemberMenu {
	private Scanner sc = new Scanner(System.in);
	
	public void mainMenu() {
		MemberController mc = new MemberController();
		
		while(true) {
		System.out.println("*** 회원 관리 프로그램 ***");
		System.out.println("1. 새 회원 등록");
		System.out.println("2. 모든 회원 조회");
		System.out.println("3. 특정 조건 회원 조회");
		System.out.println("4. 회원 정보 수정");
		System.out.println("5. 회원 탈퇴");
		System.out.println("0. 프로그램 종료");
		System.out.println("번호 선택 : ");
		int menu = sc.nextInt();
		sc.nextLine();
		
		switch(menu) {
			case 1: mc.insertMember(); break;
			case 2: mc.selectAll(); break;
			case 3: mc.selectMember(); break;
			case 4: break;
			case 5: break;
			case 0: System.out.println("프로그램을 종료합니다."); return;
			default : System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
		}
		}
	}

	public Member insertMember() {
		System.out.println("회원 아이디 : ");
		String memberId = sc.nextLine();
		
		System.out.println("비밀 번호 : ");
		String memberPwd = sc.nextLine();
		
		System.out.println("이름 : ");
		String memberName = sc.nextLine();
		
		System.out.println("성별(남:M, 여:F) : ");
		char gender = sc.nextLine().toUpperCase().charAt(0);
		
		System.out.println("이메일 : ");
		String email = sc.nextLine();
				
		System.out.println("전화번호(-포함)");
		String phone = sc.nextLine();
		
		System.out.println("나이 : ");
		int age = Integer.parseInt(sc.nextLine());
		
		System.out.println("주소 : ");
		String address = sc.nextLine();
		
		Member member = new Member(memberId, memberPwd, memberName, gender, email, phone, age, address);
		
		return member;
	}

	public void displaySuccess(String string) {
		System.out.println("서비스 요청 성공 : " + string);
	}

	public void displayError(String string) {
		System.out.println("서비스 요청 실패 : " + string);
	}

	public void displayMember(ArrayList<Member> mList) {
		System.out.printf("%-10s %-10s %-10s %-15s %-20s %-4s -%20s -%15s\n",
				"ID","PWD","NAME","GENDER","EMAIL","PHONE","AGE","ADDRESS","ENROLLDATE");
		for(Member m : mList) {
			System.out.printf("%-10s %-10s %-11s %-10s %-20s %-4s -%20s -%15s\n",
								m.getMemberId(), m.getMemberPwd(), m.getMemberName(),
								m.getGender(), m.getEmail(), m.getPhone(), m.getAge(),
								m.getAddress(), m.getEnrollDate());
		}
	}

	public int selectMember() {
		int sel = 0;
		
		while(true) {
			System.out.println("1. 아이디로 회원 조회");
			System.out.println("2. 성별로 회원 조회");
			System.out.println("0. 메인 메뉴로 돌아가기");
			System.out.println("번호 선택 : ");
			sel = Integer.parseInt(sc.nextLine());
			
			switch(sel) {
			case 1: case 2: case 0: return sel;
			default: System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
		}
	}

	public String inputMemberId() {
		System.out.println("회원 아이디 : ");
		String id = sc.nextLine();
		return id;
	}

	public char inputGender() {
		System.out.println("조회할 성별 입력(남: M / 여: F) : ");
		char gen = sc.nextLine().toUpperCase().charAt(0);
		return gen;
	}
	
	public Member updateMember() {
		System.out.println("수정할 정보를 입력하세요.");
		System.out.println("아이디 : ");
		String id = sc.nextLine();
		System.out.println("비밀번호 : ");
		String password = sc.nextLine();
		System.out.println("이름 : ");
		String name = sc.nextLine();
		System.out.println("성별(남: M / 여: F) : ");
		char gender = sc.nextLine().toUpperCase().charAt(0);
		System.out.println("이메일 : ");
		String email = sc.nextLine();
		System.out.println("휴대폰 번호 : ");
		String phone = sc.nextLine();
		System.out.println("나이 : ");
		int age = Integer.parseInt(sc.nextLine());
		System.out.println("주소 : ");
		String address = sc.nextLine();
		
		Member member = new Member(id, password, name, gender, email, phone, age, address);
		
		return member;
	}
	
	
}
