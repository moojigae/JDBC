package com.kh.board.model.service;

import static com.kh.board.common.JDBCTemplate.*;
import java.sql.Connection;
import java.util.ArrayList;

import com.kh.board.model.dao.BoardDAO;
import com.kh.board.model.vo.Board;

public class BoardService {
	public ArrayList<Board> selectAll() {
		Connection conn = getConnection();
		BoardDAO bDAO= new BoardDAO();
		
		ArrayList<Board> bList = bDAO.selectAll(conn);
		
		return bList;
	}

	public int checkNo(int no) {
		Connection conn = getConnection();
		BoardDAO bDAO = new BoardDAO();
		int check = bDAO.checkNo(conn,no);
		return check;
	}

	public Board selectOne(int no) {
		Connection conn = getConnection();
		BoardDAO bDAO = new BoardDAO();
		
		Board board = bDAO.selectOne(conn, no);
		return board;
	}

	public int insertBoard(Board board) {
		Connection conn = getConnection();
		BoardDAO bDAO = new BoardDAO();
		int result = bDAO.insertBoard(conn,board);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}
	
	public String checkAccount(int no) {
		Connection conn = getConnection();
		BoardDAO bDAO = new BoardDAO();
		String checkUser = bDAO.checkAccount(conn,no);
		return checkUser;
	}
	
	public int updateBoard(int no, int sel, String input) {
		Connection conn = getConnection();
		BoardDAO bDAO = new BoardDAO();
		int result = bDAO.updateBoard(conn, no, sel, input);
		return result;
	}

	public int deleteBoard(int no) {
		Connection conn = getConnection();
		BoardDAO bDAO = new BoardDAO();
		int result = bDAO.deleteBoard(conn,no);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		return result;
		
	}

	
	
	
}
