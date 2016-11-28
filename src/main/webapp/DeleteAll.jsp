<%@ page import="core.*" %>
<% 
DataManager dataManageInst = new DataManager();
dataManageInst.deleteData();
%>
<jsp:include page="Title.jsp"/>
<body onload="setTimeout(redirect, 3000)">
<jsp:include page="Body.jsp"/>
<div class="afterEffect">
	<h3><a href="Results.jsp">Action "Delete all data" received</a></h3>
</div>
<script>
	function redirect() {
		window.location = "Results.jsp";
	}
</script>
</body>
</html>