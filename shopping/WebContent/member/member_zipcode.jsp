<%@ page contentType="text/html; charset=euc-kr"%>
<%@ page import="java.util.*"%>
<%
	String addr=""; //팝업창에 뜬 주소
	String zipcode=""; //사용자가 입력한 '..동' 이름
	String zip1=""; //팝업창에 뜬 우편번호 앞자리
	String zip2=""; //팝업창에 뜬 우편번호 뒷자리
	
	List zipcodeList=(ArrayList)request.getAttribute("zipcodelist"); //MemberZipCodeAction 클래스에서 설정한 값이 이동
%>
<!DOCTYPE html>
<html>
<head>
<title>쇼핑몰-회원주소찾기</title>
<!-- 회원가입(member_join.jsp)에서 우편번호 검색 버튼 클릭 시 이동되는  페이지  -->
<script>
function setZipcode(zip1,zip2,addr){ //자식창(팝업창)에서 부모창(회원가입창)으로 데이터 전달
	//forms: 부모창의 form 이름
	opener.document.forms[0].MEMBER_ZIPCODE1.value=zip1; //자식 창의 우편번호 앞자리를 부모창의 우편번호 앞자리 박스로 이동
	opener.document.forms[0].MEMBER_ZIPCODE2.value=zip2; //자식 창의 우편번호 뒷자리를 부모창의 우편번호 뒷자리 박스로 이동
	opener.document.forms[0].MEMBER_ADDR1.value=addr; // 자식 창의 주소를 부모창의 주소 박스로 이동
	self.close(); //창 닫기
}
</script>
</head>

<body bgcolor="#f5f5f5">
 <table width="370" border="0" cellspacing="0" cellpadding="5" align="center">
	<tr align="center">
		<td align="center">
			<font color="#ff4500">-우편번호 검색-</font>
			<br/>
		</td>
	</tr>
	
 </table>
 <form action="./MemberZipcodeAction.me" method="post" name="form">
 <table width="370" border="0" cellspacing="0" cellpadding="5">
	<tr align="center">
		<td align="center">
			<font size="2">지역명 : </font>
			<input type="text" name="dong"/>
			<input type="submit" value="검색"><br>
			<font size="2">동을 입력하세요.(예:방배, 원천, 2글자 이상 입력)</font>
		</td>
	</tr>
 </table>
 </form>
 
 <br/>
 <table width="370" border="1" cellspacing="0" cellpadding="5">
 	<tr height="35">
		<td align="center" colspan="2">-검색결과-</td>
	</tr>
<%	
	if(zipcodeList != null && zipcodeList.size() != 0) { //사용자가 동이름을 정확하게 입력했다면
		for(int i=0;i<zipcodeList.size();i++) { //zipcodeList를 for문으로 돌림
			String data=(String)zipcodeList.get(i);
			
			StringTokenizer st = new StringTokenizer(data,",");
			zipcode=st.nextToken();
			addr=st.nextToken();
			
			zip1=zipcode.split("-")[0];
			zip2=zipcode.split("-")[1];
%>
	<tr>
	 <td width="20%"><!-- 우편번호 -->
		 <a href="#" onclick="setZipcode(<%=zip1%>,<%=zip2%>,'<%=addr %>')">
		 <!-- onclick 안의 내용 : 우편번호를 클릭하면 우편번호(zip1, zip2)와 주소(addr) 데이터를 뽑아냄-->
			<font size="2"><%=zipcode%></font>
		 </a>
	 </td>
	 <td width="80%"><!--주소  -->
	 	<font size="2"><%=addr %></font></td>
	</tr>
	
	<%	
		}
	} else { //사용자가 동이름을 정확하게 입력하지 않은 경우
	%>
	<tr>
	 <td colspan="2" align="center">검색 결과가 없습니다.</td>
	</tr>
	<%	
		}
	%>
 </table>
</body>
</html>