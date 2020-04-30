package member.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public MemberDAO() {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup("java:comp/env");
			DataSource ds =  (DataSource)envCtx.lookup("jdbc/OracleDB");
			con= ds.getConnection();
			
		} catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public boolean insertMember(MemberBean mb) throws SQLException {
		String sql = null;
		
		try {
			sql="insert into member values(?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mb.getMEMBER_ID());
			pstmt.setString(2, mb.getMEMBER_PW());
			pstmt.setString(3, mb.getMEMBER_NAME());
			pstmt.setInt(4, mb.getMEMBER_JUMIN1());
			pstmt.setInt(5, mb.getMEMBER_JUMIN2());
			pstmt.setString(6, mb.getMEMBER_EMAIL());
			pstmt.setString(7, mb.getMEMBER_EMAIL_GET());
			pstmt.setString(8, mb.getMEMBER_MOBILE());
			pstmt.setString(9, mb.getMEMBER_PHONE());
			pstmt.setString(10, mb.getMEMBER_ZIPCODE());
			pstmt.setString(11, mb.getMEMBER_ADDR1());
			pstmt.setString(12, mb.getMEMBER_ADDR2());
			pstmt.setInt(13, mb.getMEMBER_ADMIN());
			pstmt.setTimestamp(14, mb.getMEMBER_JOIN_DATE());
			pstmt.executeUpdate();
			
			return true;
		} catch(Exception e){
			e.printStackTrace();
		}finally {
			if(pstmt != null) pstmt.close();
		}
		
		return false;
	}
	//로그인할 때 사용자가 입력한 아이디와 패스워드가 정확한지 확인하는 용도
	public int userCheck(String id, String pw) throws SQLException {
		String sql = null;
		int x = -1;
		
		try {
			sql ="select MEMBER_PW from MEMBER where MEMBER_ID=?";
			pstmt =  con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String memberpw = rs.getString("MEMBER_PW");
				if(memberpw.equals(pw)) {
					x=1; //로그인 성공
				}else {
					x=0; //비번틀림
				}
			}else {
				x= -1; //아이디오류
			}
			return x;
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) pstmt.close();
			if(rs != null) pstmt.close();
		}
		return -1;
	}
	
	//아이디 중복체크할 때
	public int confirmId(String id) throws SQLException {
		String sql=null;
		int x=-1;
		
		try{
			sql="select MEMBER_ID from member where MEMBER_ID=? ";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				x=1; //사용불가
			}else{
				x=-1; //해당 아이디사용가능
			}
			
			return x;
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) pstmt.close();
			if(rs != null) rs.close();			
		}		
		return -1;
	}
	
	//해당 유저의 정보를 불러옴
	public MemberBean getMember(String id) throws SQLException {
		MemberBean member=null;
		String sql=null;
		
		try{
			sql="select * from member where MEMBER_ID=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				member=new MemberBean();
				
				member.setMEMBER_ID(rs.getString("MEMBER_ID"));
				member.setMEMBER_NAME(rs.getString("MEMBER_NAME"));
				member.setMEMBER_JUMIN1(rs.getInt("MEMBER_JUMIN1"));
				member.setMEMBER_JUMIN2(rs.getInt("MEMBER_JUMIN2"));
				member.setMEMBER_EMAIL(rs.getString("MEMBER_EMAIL"));
				member.setMEMBER_EMAIL_GET(
						rs.getString("MEMBER_EMAIL_GET"));
				member.setMEMBER_MOBILE(
						rs.getString("MEMBER_MOBILE"));
				member.setMEMBER_PHONE(
						rs.getString("MEMBER_PHONE"));
				member.setMEMBER_ZIPCODE(
						rs.getString("MEMBER_ZIPCODE"));
				member.setMEMBER_ADDR1(rs.getString("MEMBER_ADDR1"));
				member.setMEMBER_ADDR2(rs.getString("MEMBER_ADDR2"));
				
				return member;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(pstmt != null) pstmt.close(); 
			if(rs != null) rs.close(); 
			
		}	
		return null;
	}
	
	//회원정보수정
	public void updateMember(MemberBean mb) throws SQLException {
		String sql= null;
		
		try {
			sql="update member set MEMBER_PW=?,MEMBER_NAME=?,"
				+" MEMBER_EMAIL=?,MEMBER_EMAIL_GET=?,MEMBER_MOBILE=?,"
				+" MEMBER_PHONE=?,MEMBER_ZIPCODE=?,MEMBER_ADDR1=?,"
				+" MEMBER_ADDR2=? where MEMBER_ID=?";
					
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, mb.getMEMBER_PW());
				pstmt.setString(2, mb.getMEMBER_NAME());
				pstmt.setString(3, mb.getMEMBER_EMAIL());
				pstmt.setString(4, mb.getMEMBER_EMAIL_GET());
				pstmt.setString(5, mb.getMEMBER_MOBILE());
				pstmt.setString(6, mb.getMEMBER_PHONE());
				pstmt.setString(7, mb.getMEMBER_ZIPCODE());
				pstmt.setString(8, mb.getMEMBER_ADDR1());
				pstmt.setString(9, mb.getMEMBER_ADDR2());
				pstmt.setString(10, mb.getMEMBER_ID());			
				pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) pstmt.close();
		}
	}
	
	//회원 탈퇴 (비밀번호 확인 후 삭제)
	public int deleteMember(String id, String pw) throws SQLException {
		String sql = null;
		int x =  -1; //기본값
		try {
			sql =  "select MEMBER_PW from MEMBER where MEMBER_ID=? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			if(rs.next()) {
				String memberpw = rs.getString("MEMBER_PW"); //DB에서 얻어온 비밀번호 값 = memberpw
				
				if(memberpw.equals(pw)) { //사용자가 입력한 비밀번호(pw)와 DB에서 얻어옪 비밀번호(memberpw)가 일치하면
					sql="delete from member where MEMBER_ID=?"; //삭제 SQL문
					pstmt= con.prepareStatement(sql);
					pstmt.setString(1, id);
					pstmt.executeUpdate(); //삭제 실행
					x=1;
				} else { //사용자가 입력한 비밀번호(pw)와 DB에서 얻어옪 비밀번호(memberpw)가 일치하지 않으면
					//비밀번호가 틀리면
					x=0; //0을 리턴
				}
				return x;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) pstmt.close();
		}
		return -1;
	}
	
	// 아이디/비밀번호 찾기(name, jumin1, jumin2를 받음) ->MemberFindAction
	public MemberBean findId(String name, String jumin1, String jumin2)
			throws SQLException {
		
		String sql=null;
		MemberBean member=new MemberBean();
				
		try{
			sql="select MEMBER_ID, MEMBER_PW, MEMBER_JUMIN1,"
				+" MEMBER_JUMIN2 from member where MEMBER_NAME=?";
					
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
					
			if(rs.next()){
				String memberjumin1= rs.getString("MEMBER_JUMIN1"); //DB에서 jumin1을 얻어옴
				String memberjumin2= rs.getString("MEMBER_JUMIN2"); //DB에서 jumin2를 얻어옴
				
				if(memberjumin1.equals(jumin1) && memberjumin2.equals(jumin2)){
					//사용자가 입력한 주민번호가 DB의 주민번호와 일치하는 경우
					member.setMEMBER_ID(rs.getString("MEMBER_ID")); //아이디 얻어오기
					member.setMEMBER_PW(rs.getString("MEMBER_PW")); //비밀번호 얻어오기
					
					return member;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(pstmt != null) pstmt.close(); 
			if(rs != null) rs.close(); 				
		}
		
		return null;
	}
	
	//관리자인지 체크 관리자면(1,true) 아니면 (0,false)
	public boolean isAdmin(String id){
		String sql="select MEMBER_ADMIN from MEMBER where MEMBER_ID=?";
		int member_admin=0;
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			rs.next();
			
			member_admin= rs.getInt("MEMBER_ADMIN");
			
			if(member_admin==1){
				return true; //관리자는 1번
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	//주소찾기 (**동을 검색하면 나오게)
	public List searchZipcode(String searchdong){
		String sql="select * from zipcode where dong like ?";
		List zipcodeList=new ArrayList();
		
		try{
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, "%"+searchdong+"%");
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				String sido=rs.getString("sido");
				String gugun=rs.getString("gugun");
				String dong=rs.getString("dong");  
				String ri=rs.getString("ri"); 
				String bunji=rs.getString("bunji");
				if(ri == null) ri="";
				if(bunji == null) bunji="";
				
				String zipcode=rs.getString("zipcode");
				String addr= sido+" "+gugun+" "+dong+" "+ri+" "+bunji;
				
				zipcodeList.add(zipcode+","+addr);
			}
			return zipcodeList;
		} catch (SQLException e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
}
