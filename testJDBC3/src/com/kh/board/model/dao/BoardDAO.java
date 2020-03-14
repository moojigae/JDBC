package com.kh.board.model.dao;

import static com.kh.board.common.JDBCTemplate.close;

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

import com.kh.board.model.vo.Board;

public class BoardDAO {
	private Properties prop = null;
	
	public BoardDAO() {
		try {
			prop = new Properties();
			prop.load(new FileReader("query.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public ArrayList<Board> selectAll(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		
		ArrayList<Board> bList = null;
		
		String query = prop.getProperty("selectAll");
		
		try {
			stmt =conn.createStatement();
			rset = stmt.executeQuery(query);
			
			bList = new ArrayList<Board>();
			
			Board board = null;
			
			while(rset.next()) {
				int bNo = rset.getInt("BNO");
				String title = rset.getString("TITLE");
				String content = rset.getString("CONTENT");
				Date createDate = rset.getDate("CREATE_DATE");
				String writer = rset.getString("WRITER");
				
				board = new Board(bNo,title,content,createDate,writer);
				bList.add(board);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return bList;
	}
	public int checkNo(Connection conn, int no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int check = 0;
		
		String query = prop.getProperty("checkNo");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				check = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return check;
	}
	public Board selectOne(Connection conn, int no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Board board = null;
		
		String query = prop.getProperty("selectOne");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				int bNo = rset.getInt("bno");
				String title = rset.getString("title");
				String content = rset.getString("content");
				Date createDate = rset.getDate("create_date");
				String writer = rset.getString("writer");
				
				board = new Board(bNo,title,content,createDate,writer);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		return board;
	}
	public int insertBoard(Connection conn, Board board) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertBoard");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setString(3, board.getWriter());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	public String checkAccount(Connection conn, int no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String checkUser = null;
		
		String query = prop.getProperty("checkAccount");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				checkUser = rset.getString("writer");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return checkUser;
	}
	
	public int updateBoard(Connection conn, int no, int sel, String input) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateBoard" + sel);
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, input);
			pstmt.setInt(2, no);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	public int deleteBoard(Connection conn, int no) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("deleteBoard");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	
	
	
	
}






















