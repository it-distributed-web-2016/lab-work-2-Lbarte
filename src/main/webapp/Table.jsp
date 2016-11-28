<%@ page import="core.*" %>
<%@ page import="java.util.ArrayList" %>
<% DataManager dataManageInstTable = new DataManager();
ArrayList<ArrayList<String>> dataListsTable = new ArrayList<ArrayList<String>>();
dataListsTable = dataManageInstTable.getData(); %>
<div class="table">
<%
if(dataListsTable.size() != 0) {
	%>
	<table border=2>
		<tr>
			<td>ID</td>
			<td>Number</td>
			<td>Measured data</td>
			<td>Time</td>
			<td>Latitude</td>
			<td>Longitude</td>
		<tr>
	<%
	for(int index = 0; index < dataListsTable.size(); index++) {
		%>
		<tr>
		<td><%= dataListsTable.get(index).get(0) %></td>
		<td><%= dataListsTable.get(index).get(1) %></td>
		<td><%= dataListsTable.get(index).get(2) %></td>
		<td><%= dataListsTable.get(index).get(3) %></td>
		<td><%= dataListsTable.get(index).get(4) %></td>
		<td><%= dataListsTable.get(index).get(5) %></td>
		<tr>
		<%
	}
	%>
	</table>
</div>
<% } %>