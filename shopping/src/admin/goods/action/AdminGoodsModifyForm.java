package admin.goods.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.goods.db.*;

public class AdminGoodsModifyForm implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
	{
		ActionForward forward = new ActionForward(); //객체 생성
		
		AdminGoodsDAO agoodsdao = new AdminGoodsDAO();
		GoodsBean agb = new GoodsBean();
		
		String num = request.getParameter("goods_num"); //url에서 get방식으로 넘어온 goods_num(상품번호)을 받음
		agb = agoodsdao.getGoods(Integer.parseInt(num)); //String이므로 정수타입으로 변경 -> AdminGoodsDAO의 getGoods()메소드 실행
		
		request.setAttribute("agb", agb); //jsp로 보내기위해 request 객체에 값 세팅
		
		forward.setPath("./admingoods/admin_goods_modify.jsp");
		forward.setRedirect(false);
		
		return forward; //객체 반환
	}
}