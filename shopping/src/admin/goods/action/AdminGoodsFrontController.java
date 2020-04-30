package admin.goods.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminGoodsFrontController extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.processRequest(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		ActionForward forward=new ActionForward();
		Action action=null;
		
		if(command.equals("/GoodsAddAction.ag")){ 
			action= new AdminGoodsAddAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}		
		}else if(command.equals("/GoodsList.ag")){ // 관리자 전용 상품 목록 페이지
			action=new AdminGoodsListAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/GoodsAdd.ag")){ //관리자 전용 상품 등록 페이지
			forward=new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./admingoods/admin_goods_write.jsp");
		}else if(command.equals("/GoodsDelete.ag")){ //관리자 전용 상품 리스트에 등록된 상품 삭제
			action=new AdminGoodsDeleteAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/GoodsModify.ag")){ //관리자 전용 상품 수정 페이지
			action=new AdminGoodsModifyForm();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/GoodsModifyAction.ag")){
			action=new AdminGoodsModifyAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(forward.getPath());
		
		if(forward.isRedirect()){			
			response.sendRedirect(forward.getPath());			
		}else{			
			RequestDispatcher dispatcher=
				request.getRequestDispatcher(forward.getPath());
			dispatcher.forward(request, response);			
		}
	}
}