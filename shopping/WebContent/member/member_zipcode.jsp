<%@ page contentType="text/html; charset=euc-kr"%>
<%@ page import="java.util.*"%>
<%
	String addr=""; //�˾�â�� �� �ּ�
	String zipcode=""; //����ڰ� �Է��� '..��' �̸�
	String zip1=""; //�˾�â�� �� �����ȣ ���ڸ�
	String zip2=""; //�˾�â�� �� �����ȣ ���ڸ�
	
	List zipcodeList=(ArrayList)request.getAttribute("zipcodelist"); //MemberZipCodeAction Ŭ�������� ������ ���� �̵�
%>
<!DOCTYPE html>
<html>
<head>
<title>���θ�-ȸ���ּ�ã��</title>
<!-- ȸ������(member_join.jsp)���� �����ȣ �˻� ��ư Ŭ�� �� �̵��Ǵ�  ������  -->
<script>
function setZipcode(zip1,zip2,addr){ //�ڽ�â(�˾�â)���� �θ�â(ȸ������â)���� ������ ����
	//forms: �θ�â�� form �̸�
	opener.document.forms[0].MEMBER_ZIPCODE1.value=zip1; //�ڽ� â�� �����ȣ ���ڸ��� �θ�â�� �����ȣ ���ڸ� �ڽ��� �̵�
	opener.document.forms[0].MEMBER_ZIPCODE2.value=zip2; //�ڽ� â�� �����ȣ ���ڸ��� �θ�â�� �����ȣ ���ڸ� �ڽ��� �̵�
	opener.document.forms[0].MEMBER_ADDR1.value=addr; // �ڽ� â�� �ּҸ� �θ�â�� �ּ� �ڽ��� �̵�
	self.close(); //â �ݱ�
}
</script>
</head>

<body bgcolor="#f5f5f5">
 <table width="370" border="0" cellspacing="0" cellpadding="5" align="center">
	<tr align="center">
		<td align="center">
			<font color="#ff4500">-�����ȣ �˻�-</font>
			<br/>
		</td>
	</tr>
	
 </table>
 <form action="./MemberZipcodeAction.me" method="post" name="form">
 <table width="370" border="0" cellspacing="0" cellpadding="5">
	<tr align="center">
		<td align="center">
			<font size="2">������ : </font>
			<input type="text" name="dong"/>
			<input type="submit" value="�˻�"><br>
			<font size="2">���� �Է��ϼ���.(��:���, ��õ, 2���� �̻� �Է�)</font>
		</td>
	</tr>
 </table>
 </form>
 
 <br/>
 <table width="370" border="1" cellspacing="0" cellpadding="5">
 	<tr height="35">
		<td align="center" colspan="2">-�˻����-</td>
	</tr>
<%	
	if(zipcodeList != null && zipcodeList.size() != 0) { //����ڰ� ���̸��� ��Ȯ�ϰ� �Է��ߴٸ�
		for(int i=0;i<zipcodeList.size();i++) { //zipcodeList�� for������ ����
			String data=(String)zipcodeList.get(i);
			
			StringTokenizer st = new StringTokenizer(data,",");
			zipcode=st.nextToken();
			addr=st.nextToken();
			
			zip1=zipcode.split("-")[0];
			zip2=zipcode.split("-")[1];
%>
	<tr>
	 <td width="20%"><!-- �����ȣ -->
		 <a href="#" onclick="setZipcode(<%=zip1%>,<%=zip2%>,'<%=addr %>')">
		 <!-- onclick ���� ���� : �����ȣ�� Ŭ���ϸ� �����ȣ(zip1, zip2)�� �ּ�(addr) �����͸� �̾Ƴ�-->
			<font size="2"><%=zipcode%></font>
		 </a>
	 </td>
	 <td width="80%"><!--�ּ�  -->
	 	<font size="2"><%=addr %></font></td>
	</tr>
	
	<%	
		}
	} else { //����ڰ� ���̸��� ��Ȯ�ϰ� �Է����� ���� ���
	%>
	<tr>
	 <td colspan="2" align="center">�˻� ����� �����ϴ�.</td>
	</tr>
	<%	
		}
	%>
 </table>
</body>
</html>