package com.kh.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kh.model.vo.Employee;

public class EmployeeDao {
	public ArrayList<Employee> selectAll() {
		
		Connection conn = null;  // 연결정보를 담은 객체
		
		Statement stmt = null;  // Connection 객체를 통해 DB에 sql문을 전달하여 실행시키고 결과 값을 반환 받는 역할
		
		ResultSet rset = null;  // SELECT문을 사용한 질의 성공 시 반환되는 객체
		
		ArrayList<Employee> empList = null; 
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "SCOTT");
			// jdbc : oracle:thin
			//		  JDBC드라이버가 thin 타입
			// @localhost
			//		오라클이 설치된 서버의 ip가 자신의 컴퓨터임 == @ 127.0.0.1
			// 1521
			//		오라클 listener 포트번호
			// xe
			//		접속할 오라클 명
			
//			 System.out.println(conn);
//			 접속 실패 시 null
			 
			 String query = "SELECT * FROM EMP";
			 
			 stmt = conn.createStatement();
			 rset = stmt.executeQuery(query);
			
			 empList = new ArrayList<Employee>(); // 조회 결과를 저장할 ArrayList
			 Employee emp = null;	// 조회 결과의 한 행의 값을 저장할 임시 vo 선언
			 
			 while(rset.next()) {
				 int empNo = rset.getInt("empno");	// 매개변수 안에 컬럼 이름이 들어오는 것이기 때문에 대소문 구분 안함
				 String empName = rset.getString("ename");
				 String job = rset.getString("job");
				 int mgr = rset.getInt("mgr");
				 Date hireDate = rset.getDate("hiredate");
				 int sal = rset.getInt("sal");
				 int comm = rset.getInt("comm");
				 int deptNo = rset.getInt("deptNo");
				 
				 emp = new Employee(empNo, empName, job, mgr, hireDate, sal, comm, deptNo);
				 empList.add(emp);	// emp는 한 명만 있는 것이 아니기 때문에 ArrayList에 넣어줘야 함
			 }
			 
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close(); // 가장 먼저 연 것은 나중에 닫음
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return empList;
	}

	public Employee selectEmployee(int empNo) {
		Connection conn = null;
//		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Employee emp = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","SCOTT","SCOTT");
			
//			String query = "SELECT * FROM EMP WHERE EMPNO = " + empNo;
			String query = "SELECT * FROM EMP WHERE EMPNO = ?"; 
// 			statement는 ''을 포함하고 있지 않음
//			ex) String query = "SELECT * FROM EMP WHERE EMPNO = '" + empName + "'";
//			prepareStatement는 ''를 자동으로 포함하고 있기 때문에 따로 안붙여 줘도 됨
			
//          stmt = conn.createStatement();
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1,empNo);
            
//			rset = stmt.executeQuery(query);	// 0~1개의 값이 들어가 있음
            rset = pstmt.executeQuery();
			
			
			
			if(rset.next()) {
				
				String empName = rset.getString("ename");
				String job = rset.getString("job");
				int mgr = rset.getInt("mgr");
				Date hireDate = rset.getDate("hireDate");
				int sal = rset.getInt("sal");
				int comm = rset.getInt("comm");
				int deptNo = rset.getInt("deptNo");
				
				emp = new Employee(empNo, empName, job, mgr, hireDate, sal, comm, deptNo);


			}
		}catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
//				stmt.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return emp;
	}

	public int insertEmployee(Employee emp) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		    conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","SCOTT","SCOTT");
		    
		    String query = "INSERT INTO EMP VALUES(?,?,?,?, SYSDATE,?,?,?)";
		    
		    pstmt = conn.prepareStatement(query);
		    
//		    db 컬럼 순으로 넣어야 함
		    pstmt.setInt(1,emp.getEmpNo());
		    pstmt.setString(2, emp.getEmpName());
		    pstmt.setString(3, emp.getJob());
		    pstmt.setInt(4, emp.getMgr());
		    pstmt.setInt(5, emp.getSal());
		    pstmt.setInt(6, emp.getComm());
		    pstmt.setInt(7, emp.getDeptNo());
		    
		    result = pstmt.executeUpdate();	// 제대로 삽입이 되었으면 1이 반환됨
		    
		    if (result > 0) {	// 제대로 삽입이 되었다면 커밋하라
		    	conn.commit();
		    } else {
		    	conn.rollback();
		    }
		    
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int updateEmployee(Employee emp) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SCOTT","SCOTT");
			
			String query = "UPDATE EMP SET JOB = ?, SAL = ?, COMM = ? WHERE EMPNO = ?";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, emp.getJob());
			pstmt.setInt(2, emp.getSal());
			pstmt.setInt(3, emp.getComm());
			pstmt.setInt(4, emp.getEmpNo());
			
			result = pstmt.executeUpdate(); 
			
			if (result > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}
			
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int deleteEmployee(int empNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "SCOTT");
			
			String query = "DELETE FROM EMP WHERE EMPNO = ?";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, empNo);
			result = pstmt.executeUpdate();
			
			if(result > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally{
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
