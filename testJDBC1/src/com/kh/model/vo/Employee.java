package com.kh.model.vo;

import java.sql.Date;

public class Employee {
	/* - empNo : int
	   - empName : String
	   - job : String
	   - mgr : int
	   - hireDate : date
	   - sal : int
	   - comm : int
	   - deptNo : int
	   
	    + 기본생성자
	    + Employee(empno:int, empName:String, job:String, mgr:int, hireDate:date, sal: int, comm: int, deptNo:int)
	    + Employee(empno:int, empName:String, job:String, mgr:int, sal: int, comm: int, deptNo:int)
	    + Employee(empno:int, empName:String, job:String, sal: int, comm: int)
	    + Employee(job:String, sal : int, comm: int)
	    + getter/setter
	    + toString():String
	    	반환형식 empNo / empName / job / mgr / hireDate / sal / comm / deptNo
	*/
	
	private int empNo;
	private String empName;
	private String job;
	private int mgr;
	private Date hireDate;
	private int sal;
	private int comm;
	private int deptNo;
	
	public Employee(){}
	public Employee(int empNo, String empName, String job, int mgr, Date hireDate, int sal, int comm, int deptNo) {
		this.empNo = empNo;
		this.empName = empName;
		this.job = job;
		this.mgr = mgr;
		this.hireDate = hireDate;
		this.sal = sal;
		this.comm = comm;
		this.deptNo = deptNo;
	}
	public Employee(int empNo, String empName, String job, int mgr, int sal, int comm, int deptNo) {
		this.empNo = empNo;
		this.empName = empName;
		this.job = job;
		this.mgr = mgr;
		this.sal = sal;
		this.comm = comm;
		this.deptNo = deptNo;
	}
	public Employee(int empNo, String empName, String job, int sal, int comm) {
		this.empNo = empNo;
		this.empName = empName;
		this.job = job;
		this.sal = sal;
		this.comm = comm;
	}
	public Employee(String job, int sal, int comm) {
		this.job = job;
		this.sal = sal;
		this.comm = comm;
	}
	
	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}
	public int getEmpNo() {
		return empNo;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpName() {
		return empName;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getJob() {
		return job;
	}
	public void setMgr(int mgr) {
		this.mgr = mgr;
	}
	public int getMgr() {
		return mgr;
	}
	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
	public Date getHireDate() {
		return hireDate;
	}
	public void setSal(int sal) {
		this.sal = sal;
	}
	public int getSal() {
		return sal;
	}
	
	public void setComm(int comm) {
		this.comm = comm;
	}
	public int getComm() {
		return comm;
	}
	public void setDeptNo(int deptNo) {
		this.deptNo = deptNo;
	}
	public int getDeptNo() {
		return deptNo;
	}
	@Override
	public String toString() {
		return "사번 : " + empNo + " 이름 : " + empName + " 직급 : " + job + " 직속상사 : " + mgr + " 입사일 : " 
				+ hireDate + " 급여 : " + sal + " 커미션 : " + comm + " 부서 : " + deptNo;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}