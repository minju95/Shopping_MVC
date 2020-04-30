package member.action;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.db.MemberDAO;

public class MemberZipcodeAction implements Action {
	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		request.setCharacterEncoding("euc-kr");
		ActionForward forward =  new ActionForward();
		MemberDAO memberdao = new MemberDAO();
		List zipcodeList = new ArrayList();
		
		String searchdong = request.getParameter("dong"); //팝업창에서 입력한 ..동을 파라미터로 받음
		zipcodeList = memberdao.searchZipcode(searchdong);
		//사용자가 입력한 ..동을 List에 저장
		
		request.setAttribute("zipcodelist", zipcodeList);
		
		forward.setRedirect(false);
		forward.setPath("./member/member_zipcode.jsp");
		return forward;
	}

}
