<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- List를 순회하기 위해서 태그 라이브러리 설정 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- List의 데이터가 있는지 없는지 확인하기 위해서 list의 길이를 확인하기 위해서 태그 라이브러리를 설정 -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring MVC</title>
<!-- css 링크 설정 -->
<!-- 이 파일은 webapp 디렉토리의 css 디렉토리에 style.css 
이런 이유로 외부자원의 링크는 절대 경로로 설정하는 경우가 많습니다 -->
<link rel="stylesheet" href="./css/style.css" />
</head>
<body>
	<ul>
		<li><a href="hello" class="menu">처음 만들어보는 요청</a>
		<li><a href="/detail/1025" class="menu">상세보기</a>
		<li><a href="/param" class="menu">파라미터 입력</a>
		<li><a href="/forward" class="menu">forwarding - 데이터 전달</a>
		<li><a href="/redirect" class="menu">redirect - 데이터 전달</a>
		<li><a href="fileview" class="menu">파일 목록 보기</a>
		<li><a href="excel.xls" class="menu">엑셀 다운로드</a>
		<li><a href="excelread" class="menu">엑셀 읽기</a>
		<li><a href="item.pdf" class="menu">PDF 출력</a>
		<li><a href="item.json" class="menu">JSON 출력</a>
		<li><a href="item.csv" class="menu">텍스트 출력</a>
		<li><a href="itemrest.json" class="menu">RESTController -
				JSON 출력</a>
		<li><a href="#" class="menu" id="ajax">ajax 요청(json)</a>
		<li><a href="#" class="menu" id="ajaxxml">ajax 요청(xml)</a>
		
		<li><a href="exception" class="menu">예외발생</a>
		
		<li><a href="message" class="menu">스프링 메시지 출력</a>
		
		<li><a href="fileupload" class="menu">파일 업로드</a>
	</ul>

	<div id="disp"></div>

	<div align="center" class="body">
		<h2>상품 목록</h2>
		<table border="1">
			<tr class="header">
				<th align="center" width="100">상품아이디</th>
				<th align="center" width="320">상품이름</th>
				<th align="center" width="100">상품가격</th>
			</tr>

			<!-- list에 데이터가 없는 경우 -->
			<c:if test="${fn:length(list) == 0}">
				<tr>
					<td colspan="3">데이터가 없습니다</td>
			</c:if>
			<!-- list에 데이터가 있는 경우 -->
			<c:if test="${fn:length(list) != null}">
				<c:forEach var="item" items="${list}">
					<tr class="record">
						<td align="center">${item.itemid}</td>
						<td align="left">&nbsp; <!-- 파라미터를 이용해서 데이터를 전달하는 방식 --> <!-- 
			<a href="/detail.html?itemid=${item.itemid}">${item.itemname}</a></td>
			--> <!-- URL을 이용해서 데이터를 전달하는 방식 --> <a
							href="/detail.html/${item.itemid}">${item.itemname}</a>
						<td align="right">${item.price}원</td>
						<!-- ${item} -->
				</c:forEach>
			</c:if>
		</table>
	</div>

</body>

<script>
	//위치에 관계 없이 스크립트를 사용하기 위해서 window에 load 이벤트가 발생한 후 작업
	window.addEventListener("load", function() {
		//DOM 객체 찾아오기
		var ajax = document.getElementById("ajax");
		var disp = document.getElementById("disp");
		//alert(ajax);
		//alert(disp);

		//클릭 이벤트 처리
		ajax.addEventListener("click", function(e) {
			//ajax 요청 객체 생성
			var request = new XMLHttpRequest();
			//요청 생성
			request.open('get', '/itemrest.json')
			//요청 전송
			request.send('');
			//응답이 오면 수행
			request.addEventListener('load', function(e) {
				//alert(request.responseText);

				//json 데이터 파싱
				var list = JSON.parse(request.responseText);

				//배열 순회하면서 출력 내용 만들기
				var output = '';
				for (i in list) {
					//alert(list[i]);
					var item = list[i];

					output += "<h3>" + item.itemname + "</h3>";
					output += "<p>" + item.description + "</p>";
				}
				//disp에 출력
				disp.innerHTML = output;
			});
		});
		//xml 요청
		var ajaxxml = document.getElementById("ajaxxml");
		ajaxxml.addEventListener('click', function(e) {
			//ajax 요청 객체 생성
			var request = new XMLHttpRequest();
			//요청 생성
			request.open('get', '/item.xml')
			//요청 전송
			request.send('');
			//응답이 오면 수행
			request.addEventListener('load', function(e) {
				//가져온 문자열을 XML로 변환
				var list = request.responseXML;
				//alert(list)

				//원하는 태그를 가져옵니다
				var itemnames = list.getElementsByTagName("itemname");
				var descriptions = list.getElementsByTagName("description");

				var output = '';
				for (var i = 0; i < itemnames.length; i = i + 1) {
					var itemname = itemnames[i].childNodes[0].nodeValue;
					//alert(itemname);
					var description = descriptions[i].childNodes[0].nodeValue;
					output += '<h3>' + itemname + '</h3>';
					output += '<p>' + description + '</p>';
				}
				disp.innerHTML = output;

			});
		})
	});
</script>
</html>