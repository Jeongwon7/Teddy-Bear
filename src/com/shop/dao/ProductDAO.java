package com.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.shop.dto.ProductDTO;
import com.shop.utility.DBManager;

public class ProductDAO {
	
	private static ProductDAO Instance = new ProductDAO();
	
	private ProductDAO() {}
	
	public static ProductDAO getInstance() {
		return Instance;
	}
	//신상품
		public List<ProductDTO> listNewProduct(){
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String sql ="select * from new_pro_view";//가상 뷰 테이블
			
			List<ProductDTO> list = new ArrayList<ProductDTO>();
			
			try {
				conn = DBManager.getConnection();
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					ProductDTO pvo = new ProductDTO();
					
					pvo.setPseq(rs.getInt("pseq"));
					pvo.setName(rs.getString("name"));
					pvo.setImage(rs.getString("image"));
					pvo.setPrice2(rs.getInt("price2"));
					
					list.add(pvo);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				DBManager.close(conn, pstmt, rs);
			}
			return list;
		}
		
		//전체상품 신상품순으로
		public List<ProductDTO> listAllProduct(){
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String sql ="select pseq, name, image, price2 from product "
					+ "where useyn='y' order by indate desc";//가상 뷰 테이블
			
			List<ProductDTO> list = new ArrayList<ProductDTO>();
			
			try {
				conn = DBManager.getConnection();
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					ProductDTO pvo = new ProductDTO();
					
					pvo.setPseq(rs.getInt("pseq"));
					pvo.setName(rs.getString("name"));
					pvo.setImage(rs.getString("image"));
					pvo.setPrice2(rs.getInt("price2"));
					
					list.add(pvo);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				DBManager.close(conn, pstmt, rs);
			}
			return list;
		}
		//베스트 상품
		public List<ProductDTO> listBestProduct(){
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String sql ="select * from best_pro_view";//가상 뷰 테이블
			
			List<ProductDTO> list = new ArrayList<ProductDTO>();
			
			try {
				conn = DBManager.getConnection();
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					ProductDTO pvo = new ProductDTO();
					
					pvo.setPseq(rs.getInt("pseq"));
					pvo.setName(rs.getString("name"));
					pvo.setImage(rs.getString("image"));
					pvo.setPrice2(rs.getInt("price2"));
					
					list.add(pvo);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				DBManager.close(conn, pstmt, rs);
			}
			return list;
		}
	
		//상품디테일
		public ProductDTO getProduct(String pseq){
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String sql ="select * from product where pseq = ?";
			ProductDTO pvo = null;
			
			try {
				conn = DBManager.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, pseq);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					pvo = new ProductDTO();
					
					pvo.setPseq(rs.getInt("pseq"));
					pvo.setName(rs.getString("name"));
					pvo.setImage(rs.getString("image"));
					pvo.setPrice2(rs.getInt("price2"));
					pvo.setContent(rs.getString("content"));
					
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				DBManager.close(conn, pstmt, rs);
			}
			return pvo;
		}	
		
	//관리자 모드
	public int productInsert(ProductDTO pvo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		String sql = "insert into product(pseq, kind, name, price1, price2, price3, content, image) "
				+ "values (product_seq.nextval, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, pvo.getKind());
			pstmt.setString(2, pvo.getName());
			pstmt.setInt(3, pvo.getPrice1());
			pstmt.setInt(4, pvo.getPrice2());
			pstmt.setInt(5, pvo.getPrice3());
			pstmt.setString(6, pvo.getContent());
			pstmt.setString(7, pvo.getImage());
			
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
		return result;
	}
	
	public List<ProductDTO> productList(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql ="select * from product order by pseq desc";
		
		List<ProductDTO> list = new ArrayList<ProductDTO>();
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ProductDTO pvo = new ProductDTO();
				
				pvo.setPseq(rs.getInt("pseq"));
				pvo.setName(rs.getString("name"));
				pvo.setImage(rs.getString("image"));
				pvo.setPrice1(rs.getInt("price1"));
				pvo.setPrice2(rs.getInt("price2"));
				pvo.setIndate(rs.getString("indate").substring(0, 10));
				pvo.setUseyn(rs.getString("useyn"));
				
				list.add(pvo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}
	
	
	

	
}
