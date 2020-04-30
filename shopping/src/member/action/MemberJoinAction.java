package member.action;

import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.db.*;

public class MemberJoinAction implements Action { //회원가입
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("euc-kr"); //인코딩
		
		MemberDAO memberdao = new MemberDAO();
		MemberBean dto = new MemberBean();
		ActionForward forward = null;
		
		dto.setMEMBER_ID(request.getParameter("MEMBER_ID"));
		dto.setMEMBER_PW(request.getParameter("MEMBER_PW"));
		dto.setMEMBER_NAME(request.getParameter("MEMBER_NAME"));
		dto.setMEMBER_JUMIN1(Integer.parseInt(request.getParameter("MEMBER_JUMIN1")));
		dto.setMEMBER_JUMIN2(Integer.parseInt(request.getParameter("MEMBER_JUMIN2")));
		dto.setMEMBER_EMAIL(request.getParameter("MEMBER_EMAIL1")+"@"+
				request.getParameter("MEMBER_EMAIL2")); //이메일 파라미터를 총 3개받음(아이디, @, 도메인)
		dto.setMEMBER_EMAIL_GET(request.getParameter("MEMBER_EMAIL_GET")); //이메일 수신 여부
		dto.setMEMBER_MOBILE(request.getParameter("MEMBER_MOBILE"));
		dto.setMEMBER_PHONE(request.getParameter("MEMBER_PHONE"));
		dto.setMEMBER_ZIPCODE(request.getParameter("MEMBER_ZIPCODE1")+" - "+
				request.getParameter("MEMBER_ZIPCODE2"));//우편번호 파라미터를 총 3개받음(우편번호 앞, -, 우편번호 뒤)
		dto.setMEMBER_ADDR1(request.getParameter("MEMBER_ADDR1")); //집 주소
		dto.setMEMBER_ADDR2(request.getParameter("MEMBER_ADDR2")); //상세 주소
		dto.setMEMBER_ADMIN(0); //일반사용자
		dto.setMEMBER_JOIN_DATE(new Timestamp(System.currentTimeMillis())); //가입일
		
		memberdao.insertMember(dto); //회원가입 처리
		
		response.setContentType("text/html; charset=euc-kr");
		
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('회원가입에 성공하였습니다.');");
		out.println("location.href='./MemberLogin.me';"); //로그인 창으로 이동
		out.println("</script>");			
		out.close();
		
		return forward; //null 리턴
		
	}

}
