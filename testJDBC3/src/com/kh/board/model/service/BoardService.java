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
	
	
}
