package com.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.shop.dto.NoticeVO;
import com.shop.utility.DBManager;

import utility.Criteria;

public class NoticeDAO {
	
	private static NoticeDAO instance = new NoticeDAO();
	private NoticeDAO() {}
	public static NoticeDAO getInstance() {
		return instance;
	}
	
	public void noticeInsert(NoticeVO nvo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String query ="insert into notice (bno, title, content, writer) " + 
					" values (notice_seq.nextval,?,?,?)";
		
		try {
			conn= DBManager.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, nvo.getTitle());
			pstmt.setString(2, nvo.getContent());
			pstmt.setString(3, nvo.getWriter());
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}

	}
	
	
	
	//Paging
	
	public List<NoticeVO> getNoticeListPaging(Criteria cri, String query ){
			
			Connection conn=null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String sql = null;
			//정렬 하고싶으면 조회순, 번호순 쿼리 만들어놓고 매개변수 받아서 if문으로 돌리면 됌
			
			List<NoticeVO> list = new ArrayList<NoticeVO>();
			
			
			 if(query != "") {//검색 키워드가 있을 때
		            sql = "select * from (select /*+ index_desc(notice notice_pk) */ "
		                  + "rownum rn, bno, title, content, writer, regdate, viewcount "
		                  + "from notice where (" + query + ") and rownum <= ? * ? )"
		                  + " where rn > (?-1) * ?";
		            
		         }else {//검색 안할 때
		            sql = "select * from (select /*+ index_desc(notice notice_pk) */ "
		                  + "rownum rn, bno, title, content, writer, regdate, viewcount from notice where rownum  <= ? * ?) "
		                  + " where rn > (?-1) * ?";
		         }
			
			try {
				conn=DBManager.getConnection();
				pstmt=conn.prepareStatement(sql);
				
				pstmt.setInt(1, cri.getPageNum());
				pstmt.setInt(2, cri.getAmount());
				pstmt.setInt(3, cri.getPageNum());
				pstmt.setInt(4, cri.getAmount());
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					NoticeVO nvo = new NoticeVO();
					
					nvo.setBno(rs.getInt("bno"));
					nvo.setTitle(rs.getString("title"));
					nvo.setContent(rs.getString("content"));
					nvo.setWriter(rs.getString("writer"));
					nvo.setRegdate(rs.getString("regdate").substring(0, 10));
					nvo.setViewcount(rs.getInt("viewcount"));
					
					list.add(nvo);
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				DBManager.close(conn, pstmt, rs);
			}
			return list;
		}
	
	
	
	
	//총 레코드 개수 구하기
	public int noticeCount(String query) {
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql="";
		
		if( query != "") {
			sql = "select count(*) as cnt from notice where "+query;
		}else {
			sql = "select count(*) as cnt from notice";
		}
		
		int count=0;//총 레코드 개수가 저장될 변수
		
		try {
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				count= rs.getInt("cnt");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return count;
		
	}
	
	public NoticeVO getNotice(int bno) {
		
		noticeViewcount(bno);
		//조회수 메서드 호출 이렇게 하면 "select * from notice where bno=?";에서 조회수+1된 것이 조회된다
		
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query="select * from notice where bno=?";
		
		NoticeVO nvo = null;
		
		try {
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, bno);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				nvo = new NoticeVO();
				nvo.setBno(rs.getInt("bno"));
				nvo.setTitle(rs.getString("title"));
				nvo.setContent(rs.getString("content"));
				nvo.setWriter(rs.getString("writer"));
				nvo.setRegdate(rs.getString("regdate").substring(0, 10));
				nvo.setViewcount(rs.getInt("viewcount"));
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return nvo;
	}

	//조회수 증가
	public void noticeViewcount(int bno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String query ="update notice set viewcount = viewcount+1 where bno=?";
		try {
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, bno);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}

	}
	
	//이전글
	public NoticeVO prevByBno(int bno) {
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query="select bno, title from notice where bno=(select max(bno) from notice where bno < ?)";
		
		NoticeVO nvo = null;
		
		try {
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, bno);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				nvo = new NoticeVO();
				nvo.setBno(rs.getInt("bno"));
				nvo.setTitle(rs.getString("title"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return nvo;
	}
	
	//다음글
	public NoticeVO nextByBno(int bno) {
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query="select bno, title from notice where bno=(select min(bno) from notice where bno > ?)";
		
		NoticeVO nvo = null;
		
		try {
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, bno);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				nvo = new NoticeVO();
				nvo.setBno(rs.getInt("bno"));
				nvo.setTitle(rs.getString("title"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return nvo;
	}
	
	public void noticeDelete(int bno) {
		Connection conn=null;
		PreparedStatement pstmt = null;
		
		String query = "delete from notice where bno=?";
		
		try {
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, bno);
			pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
		
	}

	
	public NoticeVO modifyNoticeForm(int bno) {
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query="select * from notice where bno=?";
		
		NoticeVO nvo = null;
		
		try {
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, bno);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				nvo = new NoticeVO();
				nvo.setBno(rs.getInt("bno"));
				nvo.setTitle(rs.getString("title"));
				nvo.setContent(rs.getString("content"));
				nvo.setWriter(rs.getString("writer"));
				nvo.setRegdate(rs.getString("regdate").substring(0,10));
				nvo.setViewcount(rs.getInt("viewcount"));
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return nvo;
	}
	
	public void modifyNotice(NoticeVO nvo) {
		Connection conn=null;
		PreparedStatement pstmt = null;
		
		String query="update notice set title=?, content=?, writer=? where bno=?";
		
		try {
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, nvo.getTitle());
			pstmt.setString(2, nvo.getContent());
			pstmt.setString(3, nvo.getWriter());
			pstmt.setInt(4, nvo.getBno());
			pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
		
	}
	
}
