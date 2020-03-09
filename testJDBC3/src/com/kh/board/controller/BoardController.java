package com.kh.board.controller;

import java.util.ArrayList;

import com.kh.board.model.dao.BoardDAO;
import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Board;
import com.kh.view.View;

public class BoardController {
	private View view = new View();
	private BoardDAO bdao = new BoardDAO();
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
		
	}

	public void insertBoard() {
		
	}

	public void updateBoard() {
		
	}

	public void deleteBoard() {
		
	}

}
