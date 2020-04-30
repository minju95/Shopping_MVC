package admin.goods.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.goods.db.*;

//addmin_goods_modify.jsp에서 넘어옴
public class AdminGoodsModifyAction implements Action { //상품 정보 수정 처리

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("euc-kr");
		ActionForward forward = new ActionForward(); //객체 생성
		AdminGoodsDAO agoodsdao = new AdminGoodsDAO();
		GoodsBean agb = new GoodsBean();
		
	
		agb.setGOODS_CATEGORY(request.getParameter("goods_category"));
		agb.setGOODS_NAME(request.getParameter("goods_name"));
		agb.setGOODS_CONTENT(request.getParameter("goods_content"));
		agb.setGOODS_SIZE(request.getParameter("goods_size"));
		agb.setGOODS_COLOR(request.getParameter("goods_color"));
		agb.setGOODS_AMOUNT(Integer.parseInt(request.getParameter("goods_amount")));
		agb.setGOODS_PRICE(Integer.parseInt(request.getParameter("goods_price")));
		agb.setGOODS_BEST(Integer.parseInt(request.getParameter("goods_best")));
		agb.setGOODS_NUM(Integer.parseInt(request.getParameter("goods_num")));
		
		int result = agoodsdao.modifyGoods(agb); //수정처리 메소드를 수행하여 수행된 결과의 개수를 int타입의 변수로 저장
		if(result<=0) { //수정된 결과값이 없다면
			System.out.println("상품 수정 실패");
			return null; //null값 반환
		}  
		
		//수정처리가 잘 이루어졌다면
		forward.setPath("./GoodsList.ag"); 
		forward.setRedirect(true);
		
		return forward; //객체 반환
	}
}
