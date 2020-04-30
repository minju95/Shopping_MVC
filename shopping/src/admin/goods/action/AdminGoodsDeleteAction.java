package admin.goods.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.goods.db.*;

//admin_goods_list.jsp에서 '삭제' 클릭 시 이동되는 클래스
public class AdminGoodsDeleteAction implements Action { //삭제 처리

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward(); //객체 생성
		AdminGoodsDAO agoodsdao = new AdminGoodsDAO();
		GoodsBean agb = new GoodsBean();
		
		agb.setGOODS_NUM(
				Integer.parseInt(request.getParameter("goods_num")));
		
		int check = agoodsdao.deleteGoods(agb);
		//AdminGoodsDAO의 deleteGoods() 메소드를 실행하여 처리 결과값을 check라는 변수에 담음
		
		if(check>0) { //삭제가 잘 이루어졌으면
			forward.setRedirect(true);
			forward.setPath("GoodsList.ag"); //GoodsList.ag 페이지로 이동
		}
		return forward; //삭제가 잘 이루어지지 않았으면 그냥 객체 반환
	}
}
