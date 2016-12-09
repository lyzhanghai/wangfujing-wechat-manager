<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en"
	class="app js no-touch no-android chrome no-firefox no-iemobile no-ie no-ie10 no-ie11 no-ios no-ios7 ipad">
<head>
<%@include file="/common/common.jspf"%>
	<script type="text/javascript" src="${ctx}/js/system/user/userAuthorizationStore.js"></script>
	<style>
		.divcss5{height:50px;width:80%;border:1px solid #E6E6E6;
			margin:0 auto;

		}

		.divCube:hover{
			background: #ecf8ff;


		}
		.divCube{
			font-family:"微软雅黑";
			color: #3abbb9;
			font-size:20px;
			line-height:47px;
		}


		.tableclass {
			 margin-right: 20px;
			 margin-left: 20px;
			margin-top: 20px;
			margin-bottom: 20px;
		 }
		.tdclass{
			margin-top: 10px;
			margin-bottom: 10px;
		}
		.divcenter{
			margin-top: 70px;
			margin-right: 25%;
			margin-left: 25%;
		}
		.divl{
					 position:absolute; height:400px; overflow:auto
				 }
	</style>
<script src="${pageContext.request.contextPath}/echarts/esl/esl.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/echarts/echarts-all.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/system/monitor/systemInfo.js"></script>
	<script type="text/javascript">

	</script>
</head>
<body class="" style="" >
	<section >
		<div class="row" style="padding-right: 8px; padding-left: 8px; padding-top: 8px; padding-bottom: 30px;">


			<div class="col-md-6 divcenter" >
				<section class="panel panel-info portlet-item">
					<header class="panel-heading">
						<i class="fa fa-th-list"></i> 请选择门店
					</header>
					<%--data-url="/monitor/systemInfo.shtml" style="padding: 0px"--%>
					<div  class="panel-body  "  style=" height:600px; overflow-y :auto" overflow:scroll; >

						<table class="tableclass" width="98%" align="center" cellspacing="10">

							<c:forEach items="${userAuthorizatioStoreList}" var="userAuthorization"  varStatus="status">

								<c:choose>
									<c:when test="${(status.index + 1)%2==1}">    <!--如果 -->
										<tr>
										<td ><div  class="col-md-6 divcss5 tdclass divCube" onclick="onuser(${userAuthorization.storeCode},${userAuthorization.userId})">${userAuthorization.businessName} </div></td>
									</c:when>

									<c:otherwise>  <!--否则 -->
										<td ><div  class="col-md-6 divcss5 tdclass divCube" onclick="onuser(${userAuthorization.storeCode},${userAuthorization.userId})">${userAuthorization.businessName}</div></td>
										</tr>
									</c:otherwise>
								</c:choose>

							</c:forEach>
								</table>





							<%--<tr><td ><div  class="col-md-6 divcss5 tdclass" onclick="onuser('201011','35')">	门店名 	<br>	工号：1111111</div></td>      <td ><div  class="col-md-6 divcss5 tdclass">门店名 	<br>	工号：1111111</div></td></tr>--%>
							<%--<tr><td ><div  class="col-md-6 divcss5 tdclass" >	门店名 	<br>	工号：1111111</div></td>      <td><div class="col-md-6 divcss5 tdclass" >	门店名 	<br>	工号：1111111</div></td></tr>
							<tr><td ><div class="col-md-6 divcss5 tdclass" >	门店名 	<br>	工号：1111111</div></td>      <td><div  class="col-md-6 divcss5 tdclass">	门店名 	<br>	工号：1111111</div></td></tr>
							<tr><td ><div class="col-md-6 divcss5 tdclass" >	门店名 	<br>	工号：1111111</div></td>      <td><div  class="col-md-6 divcss5 tdclass">	门店名 	<br>	工号：1111111</div></td></tr>
							<tr><td ><div  class="col-md-6 divcss5 tdclass">	门店名 	<br>	工号：1111111</div></td>      <td ><div  class="col-md-6 divcss5 tdclass">门店名 	<br>	工号：1111111</div></td></tr>

							<tr><td ><div  class="col-md-6 divcss5 tdclass" >	门店名 	<br>	工号：1111111</div></td>      <td><div class="col-md-6 divcss5 tdclass" >	门店名 	<br>	工号：1111111</div></td></tr>
							<tr><td ><div class="col-md-6 divcss5 tdclass" >	门店名 	<br>	工号：1111111</div></td>      <td><div  class="col-md-6 divcss5 tdclass">	门店名 	<br>	工号：1111111</div></td></tr>
							<tr><td ><div class="col-md-6 divcss5 tdclass" >	门店名 	<br>	工号：1111111</div></td>      <td><div  class="col-md-6 divcss5 tdclass">	门店名 	<br>	工号：1111111</div></td></tr>
							<tr><td ><div  class="col-md-6 divcss5 tdclass">	门店名 	<br>	工号：1111111</div></td>      <td ><div  class="col-md-6 divcss5 tdclass">门店名 	<br>	工号：1111111</div></td></tr>--%>

						</table>














						</div>




				</section>
			</div>

			<!-- /.span -->
		</div>
	</section>
</body>
</html>
