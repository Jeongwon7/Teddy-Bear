package com.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.shop.dto.MemberVO;
import com.shop.utility.DBManager;

public class MemberDAO {
	private static MemberDAO instance = new MemberDAO();
	private MemberDAO () {}
	public static MemberDAO getInstance(){
		return instance;
	}
	public void memberJoin(MemberVO dto) {
		Connection conn=null;
		PreparedStatement pstmt = null;
		
		String query = "insert into member (id, name, pwd, email, phone,"
				+ " zip_num, address1, address2) "
				+ "values (?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getPwd());
			pstmt.setString(4, dto.getEmail());
			pstmt.setString(5, dto.getPhone());
			pstmt.setString(6, dto.getZip_num());
			pstmt.setString(7, dto.getAddress1());
			pstmt.setString(8, dto.getAddress2());
			
			pstmt.executeUpdate();
					
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);;
		}
	}
	
	
	
	
	public MemberVO getMember(String id) {
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "select * from member where id = ?";
		//정렬 하고싶으면 조회순, 번호순 쿼리 만들어놓고 매개변수 받아서 if문으로 돌리면 됌
		
		MemberVO mvo = null;
		try {
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {//true: id 있음
				mvo = new MemberVO();
				
				mvo.setId(rs.getString("id"));
				mvo.setPwd(rs.getString("pwd"));
				mvo.setName(rs.getString("name"));
				mvo.setEmail(rs.getString("email"));
				mvo.setZip_num(rs.getString("zip_num"));
				mvo.setAddress1(rs.getString("address1"));
				mvo.setAddress2(rs.getString("address2"));
				mvo.setUseyn(rs.getString("useyn"));
				mvo.setIndate(rs.getString("indate").substring(0, 10));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return mvo;
	}
	
	
	  public int memberIdPwCheck(String id, String pwd) {
	      Connection conn=null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      
	      String query = "select id, pwd from member where id = ? and pwd = ?";
	      //정렬 하고싶으면 조회순, 번호순 쿼리 만들어놓고 매개변수 받아서 if문으로 돌리면 됌
	      
	      int result=0;
	      try {
	         conn=DBManager.getConnection();
	         pstmt=conn.prepareStatement(query);
	         pstmt.setString(1, id);
	         pstmt.setString(2, pwd);
	         rs = pstmt.executeQuery();
	         
	         if(rs.next()) {//true: id 있음
	            if(rs.getString("pwd") != null && rs.getString("pwd").equals(pwd)) {
	               result=1;//id와 pw가 같을 때
	            }else {
	               result=0;//pw가 같지 않을 때
	            }
	         }else {
	            result=-1;//id가 없을 때(회원가입한 사람(아이디)이 없을 때
	         }
	      }catch(Exception e){
	         e.printStackTrace();
	      }finally {
	         DBManager.close(conn, pstmt, rs);
	      }
	      return result;
	   }
	
}
