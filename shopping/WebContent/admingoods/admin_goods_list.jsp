<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import ="java.util.*" %>
<%@ page import ="admin.goods.db.*" %>
<%
	Collection list = (Collection) request.getAttribute("list"); //AdminGoodsListAction에서 세팅한 리스트를 얻어옴
	Object obj[] = list.toArray(); //list를 배열로 바꿈
	GoodsBean agb = null;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쇼핑몰</title>
<script>
function goodsdelete(goods_num) { //url에 표기되는 내용으로, get방식으로 goods_num을 받아서 삭제되는 페이지로 넘김
	document.goodsform.action ="./GoodsDelete.ag?goods_num="+goods_num;
	document.goodsform.submit();
}

function goodsmodify(goods_num) { //url에 표기되는 내용으로, get방식으로 goods_num을 받아서 수정되는 페이지로 넘김
	document.goodsform.action ="./GoodsModify.ag?goods_num="+goods_num;
	document.goodsform.submit();
}
</script>
</head>
<body>
<table width="960" cellspacing="0" cellpadding="0" border="0"  align="center">
<!-- 내용 전체를 감싸는 큰 박스 -->
	<tr>
		<td colspan="2">
		
		<table border="0" width="80%">
			<tr>
				<td height="22" bgcolor="#6699FF">
					<p align="center"><font size=2>상품목록</font></p>
				</td>
			</tr>
			
			<tr>
				<td>
					<p align="right"><font size=2><a href="./GoodsAdd.ag">상품등록</a></font></p>
				</td>
			</tr>
			
			<tr>
			<td>
			<form name="goodsform" method="post">
			<table border="1" cellspacing="0">
				<tr>
					<td width="50">
						<p align="center"><font size=2>번호</font></p>
					</td>
					<td width="141">
						<p align="center"><font size=2>카테고리</font></p>
					</td>
					<td width="100">
						<p align="center"><font size=2>사진</font></p>
					</td>
					<td width="141">
						<p align="center"><font size=2>상품명</font></p>
					</td>
					<td width="141">
						<p align="center"><font size=2>단가</font></p>
					</td>
					<td width="80">
						<p align="center"><font size=2>수량</font></p>
					</td>
					<td width="141">
						<p align="center"><font size=2>등록일자</font></p>
					</td>
					<td width="100">
						<p align="center"><font size=2>수정 및 삭제</font></p>
					</td>
				</tr>
				<%
					 for(int i=0; i<list.size();i++) {//등록된 상품 개수만큼 
						 agb = (GoodsBean) obj[i]; //상품들(배열인 obj)을 agb(GoodsBean)에 객체로 넣음
				
				%>
				<tr>
					<td>
						<p align="center">
							<font size=2><%=agb.getGOODS_NUM() %></font>
						</p>
					</td>
					<td>
						<p align="center">
							<font size=2><%=agb.getGOODS_CATEGORY() %></font>
						</p>
					</td>
					<td>
						<p align="center">
							<img src="./upload/<%=agb.getGOODS_IMAGE().split(",")[0] %>"
							width="50" height="50" border="0">
							<!-- 이미지가 저장될 폴더명은 위에서 기재된 바와 같이 upload로 설정 -->
						</p>
					</td>
					<td>
						<p align="center">
							<font size=2><%=agb.getGOODS_NAME() %></font>
						</p>
					</td>
					<td>
						<p align="center">
							<font size=2><%=agb.getGOODS_PRICE() %></font>
						</p>
					</td>
					<td>
						<p align="center">
							<font size=2><%=agb.getGOODS_AMOUNT() %></font>
						</p>
					</td>
					<td>
						<p align="center">
							<font size=2><%=agb.getGOODS_DATE().substring(0,10) %></font>
						</p>
					</td>					
					<td>
						<p align="center">
						<a href="javascript:goodsmodify(<%=agb.getGOODS_NUM()%>);">
							<font size=2>수정</font>
							<!-- url에 get방식으로 goods_num을 넘길 것 -->
						</a>
						
						<a href="javascript:goodsdelete(<%=agb.getGOODS_NUM()%>);">
							<font size=2>삭제</font>
							<!-- url에 get방식으로 goods_num을 넘길 것 -->
						</a>
						</p>
					</td>
				</tr>
					<%
					}
					%>
			</table>
			</td>
			</tr>
			</form>
		</table>
		</td>
	</tr>
</table>
</body>
</html>