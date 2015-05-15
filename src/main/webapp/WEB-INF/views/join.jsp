<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctxPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Demo 게시판-회원가입</title>
<jsp:include page="/WEB-INF/views/include/common-js.jsp"></jsp:include>
<jsp:include page="/WEB-INF/views/include/jqgrid.jsp"></jsp:include>
<style type="text/css">
.zipcode-elem {
  border: 1px solid #ccc;
  padding: 4px;
  cursor: pointer;
  font-size: 0.8em;
}

.zipnum0, .zipnum1 {
	color: chocolate;
}

.addr-main {
	display: none;
}
</style>
<script type="text/javascript" src="${ctxPath}/static/js/join.js"></script>
<script type="text/javascript">
var ctxpath = ctxpath || '${ctxPath}';
</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<h3>회원가입</h3>
<table>
<tr><td colspan="2"><b>기본정보</b></td></tr>
<tr><td>아이디</td><td><input type="text" id="userId" name="userId"/></td></tr>
<tr><td>닉네임</td><td><input type="text" id="userId" name="userId"/></td></tr>
<tr><td>비밀번호</td><td><input type="password" id="password" name="password"/></td></tr>
</table>
<br/>
<table>
<tr><td colspan="2"><b>연락처</b></td></tr>
<tr><td>이메일</td><td><input type="text" id="userId" name="userId"/></td></tr>
<tr>
	<td>우편번호</td>
	<td>
		<div><input type="text" id="zip0" name="zip0" size="4" readonly="readonly"/> -
		<input type="text" id="zip1" name="zip1" size="4" readonly="readonly"/></div>
	</td>
</tr>
<tr>
	<td>주소 입력</td>
	<td>
		<div><input type="text" id="addr-main" size="30" readonly="readonly"/></div>
		<div><input type="text" id="addr_sub" size="30" name="addr_sub"/></div>
	</td>
</tr>
</table>
<div id="zipcodeWin" title="addr" style="display:none">
	<input type="text" id="umd" /><input type="button" value="검색"/>
	<div id="result"></div>
</div>
<div id="template-zipcode-elem">
	<div class="zipcode-elem" id="{seq}">
		<span class="zipnum0">{zip0}</span> - <span class="zipnum1">{zip1}</span>
		<span class="addr-main">{mainaddr}</span>
		<span class="addr-detail">{fulladdr}</span>
	</div>
</div>
</body>
</html>