package com.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.shop.dto.CartVO;
import com.shop.dto.OrderVO;
import com.shop.utility.DBManager;

public class OrderDAO {
	private static OrderDAO instance = new OrderDAO();
	private OrderDAO () {}
	public static OrderDAO getInstance() {
		return instance;
	}
	
	public int insertOrder(List<CartVO> cartlist, String id) {
		int maxOseq = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			String insertOrder = "insert into orders(oseq, id) values(orders_seq.nextval, ?)";
			pstmt = conn.prepareStatement(insertOrder);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			pstmt.close();
			
			String selectMaxOseq = "select max(oseq) from orders";
			pstmt = conn.prepareStatement(selectMaxOseq);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				maxOseq = rs.getInt(1);
				
			}
			
			pstmt.close();
			
			for(CartVO cartVO : cartlist) {
				insertOrderDetail(cartVO, maxOseq);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
		return maxOseq;
	}
	
	public void insertOrderDetail(CartVO cartVO, int maxOseq) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBManager.getConnection();
			String insertOrderDetail = "insert into order_detail(odseq, oseq, pseq, quantity) values(order_detail_seq.nextval, ?, ?, ?)";
			pstmt = conn.prepareStatement(insertOrderDetail);
			pstmt.setInt(1, maxOseq);
			pstmt.setInt(2, cartVO.getPseq());
			pstmt.setInt(3, cartVO.getQuantity());
			pstmt.executeUpdate();
			pstmt.close();
			
			String updateCartResult = "update cart set result = 2 where cseq=?";
			pstmt = conn.prepareStatement(updateCartResult);
			pstmt.setInt(1, cartVO.getCseq());
			pstmt.executeUpdate();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	public List<OrderVO> listOrderByID(String id, String result, int oseq){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from order_view where id = ? and result like '%'||?||'%' and oseq=?";
		
		List<OrderVO> orderlist = new ArrayList<OrderVO>();
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, result);
			pstmt.setInt(3, oseq);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				OrderVO ovo = new OrderVO();
				
				ovo.setOdseq(rs.getInt("odseq"));
				ovo.setOseq(rs.getInt("oseq"));
				ovo.setId(rs.getString("id"));
				ovo.setIndate(rs.getString("indate"));
				ovo.setMname(rs.getString("mname"));
				ovo.setZipNum(rs.getString("zip_num"));
				ovo.setAddress1(rs.getString("address1"));
				ovo.setAddress2(rs.getString("address2"));
				ovo.setPhone(rs.getString("phone"));
				ovo.setPseq(rs.getInt("pseq"));
				ovo.setQuantity(rs.getInt("quantity"));
				ovo.setPname(rs.getString("pname"));
				ovo.setPrice2(rs.getInt("price2"));
				ovo.setResult(rs.getString("result"));
				
				orderlist.add(ovo);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return orderlist;
	}
	
	//현재 진행 중인 주문 내역만 조회
	public List<Integer> selectSeqOrderIng(String id){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<Integer> oseqList = new ArrayList<Integer>();
		
		String sql = "select distinct oseq from order_view where id=? and result='1' order by oseq desc";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				oseqList.add(rs.getInt(1));//rs.getInt(1): 첫번째 컬럼
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return oseqList;
	}
	
	//전체 주문 내역 조회
	public List<Integer> selectSeqOrder(String id){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<Integer> oseqList = new ArrayList<Integer>();
		
		String sql = "select distinct oseq from order_view where id=? order by oseq desc";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				oseqList.add(rs.getInt(1));//rs.getInt(1): 첫번째 컬럼
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return oseqList;
	}
	
	
	//관리자
	public List<OrderVO> listOrder(String name){//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<OrderVO> orderList = new ArrayList<OrderVO>();
		
		String sql ="select * from order_view where mname like '%'||?||'%' order by result, oseq desc";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			if(name == "") {
				pstmt.setString(1, "%");
			}else {
				pstmt.setString(1, name);
			}
			rs = pstmt.executeQuery();
			while(rs.next()) {
				OrderVO ovo = new OrderVO();
				
				ovo.setOdseq(rs.getInt("odseq"));
				ovo.setOseq(rs.getInt("oseq"));
				ovo.setId(rs.getString("id"));
				ovo.setIndate(rs.getString("indate"));
				ovo.setMname(rs.getString("mname"));
				ovo.setZipNum(rs.getString("zip_num"));
				ovo.setAddress1(rs.getString("address1"));
				ovo.setAddress2(rs.getString("address2"));
				ovo.setPhone(rs.getString("phone"));
				ovo.setPseq(rs.getInt("pseq"));
				ovo.setQuantity(rs.getInt("quantity"));
				ovo.setPname(rs.getString("pname"));
				ovo.setPrice2(rs.getInt("price2"));
				ovo.setResult(rs.getString("result"));
				
				orderList.add(ovo);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return orderList;
	}
	
	public void updateOrderResult(String odseq) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "update order_detail set result = '2' where odseq=?";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, odseq);
			
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
	}
}
