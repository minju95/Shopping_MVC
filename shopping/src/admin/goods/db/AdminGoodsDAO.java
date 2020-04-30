package admin.goods.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class AdminGoodsDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public AdminGoodsDAO() {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource) envCtx.lookup("jdbc/OracleDB");
			con = ds.getConnection();
		} catch(Exception ex) {
			System.out.println("DB 연결 실패 :" +ex);
			return;
		} 
	}
	
	public List getGoodsList() {
		List goodslist = new ArrayList();
		try {
			String sql="SELECT * FROM GOODS ORDER BY GOODS_NUM ";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				GoodsBean agb = new GoodsBean();
				
				agb.setGOODS_NUM(rs.getInt("goods_num"));
				agb.setGOODS_CATEGORY(rs.getString("goods_category"));
				agb.setGOODS_NAME(rs.getString("goods_name"));
				agb.setGOODS_CONTENT(rs.getString("goods_content"));
				agb.setGOODS_SIZE(rs.getString("goods_size"));
				agb.setGOODS_COLOR(rs.getString("goods_color"));
				agb.setGOODS_AMOUNT(rs.getInt("goods_amount"));
				agb.setGOODS_PRICE(rs.getInt("goods_price"));
				agb.setGOODS_IMAGE(rs.getString("goods_image"));
				agb.setGOODS_BEST(rs.getInt("goods_best"));
				agb.setGOODS_DATE(rs.getString("goods_date"));
				
				goodslist.add(agb);
			}
		}
		catch(Exception e) {
			e.getStackTrace();
		}
		return goodslist;
	}
	
	public GoodsBean getGoods(int num) {
		try {
			
			String sql ="SELECT * FROM goods WHERE goods_num =?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			rs.next();
			
			GoodsBean agb = new GoodsBean();
			
			agb.setGOODS_NUM(rs.getInt("goods_num"));
			agb.setGOODS_CATEGORY(rs.getString("goods_category"));
			agb.setGOODS_NAME(rs.getString("goods_name"));
			agb.setGOODS_CONTENT(rs.getString("goods_content"));
			agb.setGOODS_SIZE(rs.getString("goods_size"));
			agb.setGOODS_COLOR(rs.getString("goods_color"));
			agb.setGOODS_AMOUNT(rs.getInt("goods_amount"));
			agb.setGOODS_PRICE(rs.getInt("goods_price"));
			agb.setGOODS_IMAGE(rs.getString("goods_image"));
			agb.setGOODS_BEST(rs.getInt("goods_best"));
			
			return agb;
			
		}  catch(SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	public int insertGoods(GoodsBean agb) {
		int result = 0;
		int num = 0;
		
		String sql = "SELECT max(goods_num) FROM goods ";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			num = rs.getInt(1)+1;
			
			sql = "INSERT INTO goods VALUES(?,?,?,?,?,?,?,?,?,?,sysdate) ";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			pstmt.setString(2, agb.getGOODS_CATEGORY());
			pstmt.setString(3, agb.getGOODS_NAME());
			pstmt.setString(4, agb.getGOODS_CONTENT());
			pstmt.setString(5, agb.getGOODS_SIZE());
			pstmt.setString(6, agb.getGOODS_COLOR());
			pstmt.setInt(7, agb.getGOODS_AMOUNT());
			pstmt.setInt(8, agb.getGOODS_PRICE());
			pstmt.setString(9, agb.getGOODS_IMAGE());
			pstmt.setInt(10, agb.getGOODS_BEST());
			
			result = pstmt.executeUpdate();
			return result;
		} catch(Exception e) {
			e.printStackTrace();
		} 
		return 0;
	}
	
	//등록된 상품을 삭제하는 경우
	public int deleteGoods(GoodsBean agb) {
		int result = 0; //삭제된 행의 개수 = 초기에 0으로 세팅ㄴ
		try {
			String sql ="DELETE FROM goods WHERE goods_num =?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, agb.getGOODS_NUM());
			
			result = pstmt.executeUpdate(); //삭제 sql문을 실행하여 결과값(삭제된 행의 개수)을 result값에 저장
			
			return result; //삭제된 행의 개수인 result값 반환
		} catch(Exception e) {
			e.getStackTrace();
		}
		return 0; //삭제되지 않은경우 초기값 그대로 반환
	}
	
	public int modifyGoods(GoodsBean agb) {
		int result = 0;
		
		try {
			String sql="update goods set "+
			"goods_category=?, goods_name=?, goods_price=? ,"+
			"goods_color=? ,goods_amount=? ,goods_size=? ,"+
			"goods_content=?,goods_best=? where goods_num=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, agb.getGOODS_CATEGORY());
			pstmt.setString(2, agb.getGOODS_NAME());
			pstmt.setInt(3, agb.getGOODS_PRICE());
			pstmt.setString(4, agb.getGOODS_COLOR());
			pstmt.setInt(5, agb.getGOODS_AMOUNT());
			pstmt.setString(6, agb.getGOODS_SIZE());
			pstmt.setString(7, agb.getGOODS_CONTENT());
			pstmt.setInt(8, agb.getGOODS_BEST());
			pstmt.setInt(9, agb.getGOODS_NUM());
			result = pstmt.executeUpdate();
			
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
