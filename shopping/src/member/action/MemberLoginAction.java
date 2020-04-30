package member.action;

import java.io.PrintWriter; //PrinterWriter의 부모 클래스는 Writer

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.db.MemberDAO;

public class MemberLoginAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession(); //아래에서 생성한 세션 받기
		ActionForward forward = new ActionForward();
		MemberDAO memberdao = new MemberDAO();
		
		//member_login.jsp에서 입력된 값을 받아서 변수에 저장
		String id =request.getParameter("MEMBER_ID");
		String pass  = request.getParameter("MEMBER_PW");
		
		int check = memberdao.userCheck(id, pass); //사용자가 입력한 아이디와 비밀번호가 정확한지 확인
		if(check==1) { //로그인 성공시
			session.setAttribute("id", id); //세션 생성
			if(memberdao.isAdmin(id)) { //member_admin이 1이면 관리자
				forward.setRedirect(true);
				forward.setPath("./GoodsList.ag"); //관리자 전용 상품 등록 페이지로 이동
				return forward;
			} else { //member_admin이 0이면 일반사용자
				forward.setRedirect(true);
				forward.setPath("./GoodsList.go?item=new_item"); //일반 상품 페이지로 이동
				return forward;
			}		
			
		}else if(check == 0) { //아이디는 있으나 비밀번호가 틀린 경우
			response.setContentType("text/html; charset=euc-kr");
			PrintWriter out = response.getWriter();
			//alert창 띄우기
			out.println("<script>");
			out.println("alert('비밀번호가 일치하지 않습니다.');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
		} else { //아이디가 존재하지 않을 경우
			response.setContentType("text/html; charset=euc-kr");
			PrintWriter out = response.getWriter();
			//alert창 띄우기
			out.println("<script>");
			out.println("alert('아이디가 존재하지 않습니다.');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
		}
		return null;
	}
	
}
