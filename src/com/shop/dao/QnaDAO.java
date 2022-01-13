package com.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.shop.dto.AnswerVO;
import com.shop.dto.QnaVO;
import com.shop.dto.QuestionVO;
import com.shop.utility.DBManager;

public class QnaDAO {
	private static QnaDAO instance = new QnaDAO();
	private QnaDAO () {}
	public static QnaDAO getInstance() {
		return instance;
	}
	
	public int getRef() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int ref = 0;
		String sql = "SELECT MAX(ref) as max FROM question";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
		
			rs = pstmt.executeQuery(sql); 
		
			if(rs.next()){ 
				ref=rs.getInt("max")+1; 
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return ref;
	}
	
	public void insertQuestion(QuestionVO qvo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int ref = getRef();
		
		String sql = "insert into question(qbno, title, content, writer, category, pseq, ref) "
				+ "values(question_seq.nextval, ?, ?, ?, ?, ?, ?)";
		try {
		
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, qvo.getTitle());
			pstmt.setString(2, qvo.getContent());
			pstmt.setString(3, qvo.getWriter());
			pstmt.setInt(4, qvo.getCategory());
			pstmt.setInt(5, qvo.getPseq());
			pstmt.setInt(6, ref);
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	//상품별 질문 + 답변 전체 출력
	public List<QnaVO> getQnAListByProduct(String pseq){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from qna_view where pseq=?";
		
		List<QnaVO> QnAlist = new ArrayList<QnaVO>();
		
		QnaVO qvo = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pseq);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				qvo = new QnaVO();
				
				qvo.setRef(rs.getInt("ref"));
				qvo.setQbno(rs.getInt("qbno"));
				qvo.setPseq(rs.getInt("pseq"));
				qvo.setQtitle(rs.getString("qtitle"));
				qvo.setQcontent(rs.getString("qcontent"));
				qvo.setQwriter(rs.getString("qwriter"));
				qvo.setQregdate(rs.getString("qregdate").substring(0, 10));
				qvo.setCategory(rs.getInt("category"));
				
				qvo.setAbno(rs.getInt("abno"));
				qvo.setAtitle(rs.getString("atitle"));
				qvo.setAcontent(rs.getString("acontent"));
				qvo.setAwriter(rs.getString("awriter"));
				qvo.setAregdate(rs.getString("aregdate"));
				
				QnAlist.add(qvo);
				
				
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return QnAlist;
	}
	
	public void insertAnswer(AnswerVO avo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "insert into answer(abno, qbno, title, content, writer, ref) "
				+ "values(answer_seq.nextval, ?, ?, ?, ?, ?)";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, avo.getQbno());
			pstmt.setString(2, avo.getTitle());
			pstmt.setString(3, avo.getContent());
			pstmt.setString(4, avo.getWriter());
			pstmt.setInt(5, avo.getRef());
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			
		}finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	//질문 수정 셀렉트
	public QuestionVO getQuestionModify(int qbno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from question where qbno = ?";
		
		QuestionVO qvo = new QuestionVO();
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qbno);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				qvo.setQbno(rs.getInt("qbno"));
				qvo.setPseq(rs.getInt("pseq"));
				qvo.setTitle(rs.getString("title"));
				qvo.setContent(rs.getString("content"));
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return qvo;
	}
	
	//질문 수정 업데이트
	public void modifyQuestion(QuestionVO qvo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "update question set title=?, content=?, category=? "
				+ "where qbno = ?";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, qvo.getTitle());
			pstmt.setString(2, qvo.getContent());
			pstmt.setInt(3, qvo.getCategory());
			pstmt.setInt(4, qvo.getQbno());
				
			pstmt.executeUpdate();
		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	public int QnaPwdCheck(int qbno, String pwd) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select pwd from qna_view where qbno =?";
		
		int result = 0;
		String selectpwd = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qbno);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				selectpwd = rs.getString("pwd");
			}
			if(selectpwd.equals(pwd)) {
				result = 1;
			}else {
				result = 0;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return result;
	}

	
	public AnswerVO getAnswerModify(int abno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from answer where abno = ?";
		
		AnswerVO avo = new AnswerVO();
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, abno);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				avo.setTitle(rs.getString("title"));
				avo.setContent(rs.getString("content"));
				avo.setWriter(rs.getString("writer"));
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return avo;
	}
	
	public void modifyAnswer(AnswerVO avo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql =  "update answer set title=?, content=?, writer=? "
				+ "where abno = ?";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
				
			pstmt.setString(1, avo.getTitle());
			pstmt.setString(2, avo.getContent());
			pstmt.setString(3, avo.getWriter());
			pstmt.setInt(4, avo.getAbno());
			
			pstmt.executeUpdate();
		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	public void deleteQuestion(int qbno, int abno) {
		
		if(abno != 0) {
			deleteAnswer(abno);
		}
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "delete from question where qbno=?";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, qbno);
			
			pstmt.executeUpdate();
					
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	public void deleteAnswer(int abno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "delete from answer where abno=?";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, abno);
			
			pstmt.executeUpdate();
					
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
	}
}
