package admin.order.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.order.db.AdminOrderDAO;

public class AdminOrderListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AdminOrderDAO orderdao = new AdminOrderDAO();
		List orderlist = new ArrayList();
		int page = 1;
		int limit = 10;
		
		if(request.getParameter("page")!= null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		int ordercount = orderdao.getOrderCount();
		orderlist = orderdao.getOrderList(page, limit);
		
		int maxpage = (int) ((double)ordercount/limit+0.95);
		int startpage = (((int) ((double)page/10 +0.9)) -1) * 10 +1;
		int endpage = maxpage;
		if(endpage>startpage+10-1) endpage = startpage+10-1;
		
		request.setAttribute("page", page);
		request.setAttribute("maxpage", maxpage);
		request.setAttribute("startpage", startpage);
		request.setAttribute("ordercount", endpage);
		request.setAttribute("orderlist", orderlist);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./adminorder/admin_order_list.jsp");
		return forward;
	}
}
