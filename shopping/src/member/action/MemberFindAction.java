package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.db.MemberBean;
import member.db.MemberDAO;

// 회원 아이디/비번찾기(member_find.jsp에서 넘어옴)
public class MemberFindAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		request.setCharacterEncoding("euc-kr");
				
		ActionForward forward=new ActionForward();
		MemberDAO memberdao=new MemberDAO();
		MemberBean member=new MemberBean();
				
		String name=request.getParameter("MEMBER_NAME");
		String jumin1=request.getParameter("MEMBER_JUMIN1");
		String jumin2=request.getParameter("MEMBER_JUMIN2");
				
		member= memberdao.findId(name, jumin1, jumin2); //인수에 3개를 집어넣음
				
		if(member!=null){ //리턴 되는 값인 member가 null이 아니면 회원정보 찾기 성공
			request.setAttribute("id", member.getMEMBER_ID());
			request.setAttribute("passwd", member.getMEMBER_PW());
			
			forward.setRedirect(false);
			forward.setPath("./member/member_find_ok.jsp"); 
			return forward; //경로 반환
			
		}else{ //리턴 되는 값인 member가 null이면
			response.setContentType("text/html; charset=euc-kr");
			PrintWriter out = response.getWriter();
			
			//alert창 띄우기
			out.println("<script>");
			out.println("alert('입력한 정보가 일치하지 않습니다.');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
		}
		return null;
		
	}
}
