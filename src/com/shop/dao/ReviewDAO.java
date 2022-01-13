package com.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.shop.dto.CartVO;
import com.shop.dto.ReviewVO;
import com.shop.utility.DBManager;

public class ReviewDAO {

	private static ReviewDAO instance = new ReviewDAO();
	private ReviewDAO() {}
	public static ReviewDAO getInstance() {
		return instance;
	}
	
	public void insertReview(ReviewVO rvo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "insert into review (rbno, writer, title, content, star, pseq) "
				+ "values(review_seq.nextval, ?, ?, ?, ?, ?)";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, rvo.getWriter());
			pstmt.setString(2, rvo.getTitle());
			pstmt.setString(3, rvo.getContent());
			pstmt.setInt(4, rvo.getStar());
			pstmt.setInt(5, rvo.getPseq());
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	public List<ReviewVO> getReviewList(String pseq){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from review where pseq = ? order by rbno desc";
		
		List<ReviewVO> rlist = new ArrayList<ReviewVO>();
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pseq);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ReviewVO rvo = new ReviewVO();
				
				rvo.setRbno(rs.getInt("rbno"));
				rvo.setTitle(rs.getString("title"));
				rvo.setWriter(rs.getString("writer"));
				rvo.setContent(rs.getString("content"));
				rvo.setStar(rs.getInt("star"));
				rvo.setRegdate(rs.getString("regdate").substring(0, 10));
				rvo.setPseq(rs.getInt("pseq"));
				
				rlist.add(rvo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return rlist;
	}
	
	public ReviewVO getReview(int rbno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from review where rbno = ?";
		
		ReviewVO rvo = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rbno);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				rvo = new ReviewVO();
				
				rvo.setRbno(rs.getInt("rbno"));
				rvo.setTitle(rs.getString("title"));
				rvo.setWriter(rs.getString("writer"));
				rvo.setContent(rs.getString("content"));
				rvo.setStar(rs.getInt("star"));
				rvo.setRegdate(rs.getString("regdate").substring(0, 10));
				rvo.setPseq(rs.getInt("pseq"));
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return rvo;
	}
	
	public void modifyReview(ReviewVO rvo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "update review set title = ?, content = ?, star = ? where rbno = ?";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, rvo.getTitle());
			pstmt.setString(2, rvo.getContent());
			pstmt.setInt(3, rvo.getStar());
			pstmt.setInt(4, rvo.getRbno());
			
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	public void deleteReview(int rbno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "delete from review where rbno=?";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, rbno);

			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
		
	}
}
