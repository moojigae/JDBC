package com.kh.board.controller;

import java.util.ArrayList;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Board;
import com.kh.view.View;

public class BoardController {
	private View view = new View();
	private BoardService bservice = new BoardService();

	public void selectAll() {
		ArrayList<Board> bList = bservice.selectAll();
		
		if(!bList.isEmpty()) {
			view.selectAll(bList);
		} else {
			view.displayError("조회 결과가 없습니다");
		}
	}

	public void selectOne() {
//		ArrayList<Board> bList = null;
		int no = view.inputBNO();
		
		int check = bservice.checkNo(no);
		Board board = null; 
		
		if(check < 0) {
			view.displayError("입력한 게시번호의 글이 존재하지 않습니다");
		} else {
			board = bservice.selectOne(no);
			if(board != null) {
				view.selectOne(board);
			} else {
				view.displayError("조회 결과가 없습니다.");
			}
		}
	}

	public void insertBoard() {
		Board board = view.insertBoard();
		
		int result = bservice.insertBoard(board);
		
		if(result > 0) {
			view.displaySuccess(result + "개의 행이 추가되었습니다.");
		} else {
			view.displayError("데이터 삽입 과정 중 오류 발생");
		}
	}

	public void updateBoard() {
		int no = view.inputBNO();
		int check = bservice.checkNo(no);
		String memId = view.getMemberId();
		String checkUser = bservice.checkAccount(no);
		if(no < 0) {
			view.displayError("입력한 게시번호의 글이 존재하지 않습니다");
		} else {
			if(memId.equals(checkUser)) {
				int sel = view.dateMenu();
				String input = null;
				if(sel == 1) {
					input = view.updateTitle();
				} else if (sel == 2) {
					input = view.updateContent();
				} else {
					return;
				}
				int result = bservice.updateBoard(no,sel,input);
				
				if(result > 0) {
					view.displaySuccess(result + "개의 행이 수정되었습니다.");
				} else {
					view.displayError("데이터 수정 과정 중 오류 발생");
				}
			} else {
				view.displayError("실행 권한이 없습니다");
			}
		}
	}

	public void deleteBoard() {
		int no = view.inputBNO();
		int check = bservice.checkNo(no);
		String checkUser = bservice.checkAccount(no);
		String memId = view.getMemberId();
		if(no < 0) {
			view.displayError("입력한 게시번호의 글이 존재하지 않습니다");
		}
		if(memId.equals(checkUser)) {
			char yn = view.deleteBoard();
			if(yn == 'Y') {
				int result = bservice.deleteBoard(no);
				if(result > 0) {
					view.displaySuccess(result + "개의 글이 삭제되었습니다.");
				} else {
					view.displayError("데이터 삭제 과정 중 오류 발생");
				}
			} else {
				return;
			}
		} else {
			view.displayError("실행 권한이 없습니다");
		}
	}

}
