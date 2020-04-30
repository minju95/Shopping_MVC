package admin.goods.action;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
//WEB-INF -> lib 폴더에 cos.jar 파일이 존재해야만 사용할 수 있는 클래스
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
//DefaultFileRenamePolicy : 똑같은 이름을 가진 파일이 존재한다면 파일명(default)에 1, 2, 3 으로 붙게 해주는 클래스

import admin.goods.db.*;

//admin_goods_write.jsp에서 넘어옴
public class AdminGoodsAddAction implements Action { //상품 등록
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) {
		
		ActionForward forward = new ActionForward(); //객체 생성
		AdminGoodsDAO agoodsdao= new AdminGoodsDAO();
		GoodsBean agb = new GoodsBean();
		
		String realPath = "";
		String savePath = "upload"; //이미지가 저장된 폴더명
		int maxSize = 5 * 1024 * 1024;
		
		realPath = request.getRealPath(savePath);
		// getRealPath: 웹 컨텐츠 경로를 얻어내는 메소드
		// 서버 디스크 파일 시스템에서 확장 된 WAR 폴더 구조의 경로를 절대 디스크 파일 시스템 경로로 변환 
		
		System.out.println("경로 : "+realPath); 
		//c:\workspace\.metadata\.plugins\.org.eclipse.wst.server.core\tmp1\wtpwebapps\shoppin..이렇게 출력됨
		
		List savefiles=new ArrayList();
		
		try {

			MultipartRequest multi = null;
			multi = new MultipartRequest(request, realPath, maxSize, "euc-kr",
					new DefaultFileRenamePolicy());
			
			Enumeration files=multi.getFileNames();
			while(files.hasMoreElements()){
				String name=(String)files.nextElement();
				if(files.hasMoreElements()){
					savefiles.add(multi.getFilesystemName(name)+",");
				}else{
					savefiles.add(multi.getFilesystemName(name));
				}
			}
			
			StringBuffer fl=new StringBuffer();
			for(int i=0;i<savefiles.size();i++){
				fl.append(savefiles.get(i));	
			}
			
			//admin_goods_write.jsp에서 받은 데이터를 agb 객체의 변수에 세팅함
			agb.setGOODS_CATEGORY(multi.getParameter("goods_category"));
			agb.setGOODS_NAME(multi.getParameter("goods_name"));
			agb.setGOODS_CONTENT(multi.getParameter("goods_content"));
			agb.setGOODS_SIZE(multi.getParameter("goods_size"));
			agb.setGOODS_COLOR(multi.getParameter("goods_color"));
			agb.setGOODS_AMOUNT(
					Integer.parseInt(multi.getParameter("goods_amount")));
			agb.setGOODS_PRICE(
					Integer.parseInt(multi.getParameter("goods_price")));
			agb.setGOODS_IMAGE(fl.toString());
			agb.setGOODS_BEST(
					Integer.parseInt(multi.getParameter("goods_best")));
			
			int result = agoodsdao.insertGoods(agb); //agb 객체를 AdminGoodsDAO의 insertGoods메소드를 이용하여 DB에 삽입
			
			if (result <= 0){ //DB에 삽입된 결과값이 없으면 
				return null; //null값 리턴
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//DB에 삽입된 결과값이 있으면
		forward.setRedirect(true);
		forward.setPath("GoodsList.ag");
		return forward; //객체 반환
	}
}
