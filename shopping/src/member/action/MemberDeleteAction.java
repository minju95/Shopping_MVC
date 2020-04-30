package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.db.MemberDAO;

//member_out.jsp에서 넘어옴
public class MemberDeleteAction implements Action { //회원탈퇴
	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		if(id ==  null) {
			forward.setRedirect(true);
			forward.setPath("./MemberLogin.me");
			return forward;
		}
		
		MemberDAO memberdao = new MemberDAO();
		String pass = request.getParameter("MEMBER_PW"); //password가 맞으면 탈퇴처리(check=1)
		
		try {
			int check = memberdao.deleteMember(id, pass);
			if(check == 1) { //사용자가 입력한 비밀번호와 DB의 비밀번호가 동일한 경우
				session.invalidate();
				//invalidate() : 현재 생성된 세션을 무효화하는 메소드로, 리턴은 없음
				
				/* session.removeAtrribute() vs session.invalidate()
				 * 1) session.removeAtrribute() : 해당 변수명으로 지정된 세션 값 삭제
				 * 2) session.invalidate() : 세션에 저장된 값 모두 삭제
				 * */
				forward.setRedirect(false);
				forward.setPath("./member/member_out_ok.jsp");
				return forward;
			} else { //사용자가 입력한 비밀번호가 DB의 비밀번호와 일치하지 않는 경우
				response.setContentType("text/html; charset=euc-kr");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('비밀번호가 맞지 않습니다.');");
				out.println("history.go(-1);");
				out.println("</script>");
				out.close();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}