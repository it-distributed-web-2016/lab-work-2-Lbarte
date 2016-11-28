<%@ page import="core.*" %>
<%@ page import="java.util.ArrayList" %>
<% DataManager dataManageInstMap = new DataManager();
ArrayList<ArrayList<String>> dataListsMap = new ArrayList<ArrayList<String>>();
dataListsMap = dataManageInstMap.getData();
if(dataListsMap.size()>0) {
 %>

<div class="map" id="map"></div>

<script>
function initMap() {
  var myLatLng = {lat: 0.0, lng: 0.0};

  var map = new google.maps.Map(document.getElementById('map'), {
    zoom: 1,
    center: myLatLng
  });

  setMarkers(map);
}

// Data for the markers consisting of a name, a LatLng and a zIndex for the
// order in which these markers should display on top of each other.
var beaches = [
<% for(int index = 0; index < dataListsMap.size(); index++) {%>
	['<%=dataListsMap.get(index).get(0)%>', '<%=dataListsMap.get(index).get(1)%>','<%=dataListsMap.get(index).get(2)%>','<%=dataListsMap.get(index).get(3)%>',<%=dataListsMap.get(index).get(4)%>, <%=dataListsMap.get(index).get(5)%>]
	<%if(index != dataListsMap.size()) {
		%>,<%
	}
}%>];

function setMarkers(map) {
  // Adds markers to the map.

  // Marker sizes are expressed as a Size of X,Y where the origin of the image
  // (0,0) is located in the top left of the image.

  for (var i = 0; i < beaches.length; i++) {
    var beach = beaches[i];
    var contentString = '<div id="content">' + '<div id="siteNotice">'+'</div>' + '<h3 id="header" class="header">Sample</h3>' + '<div id="bodyContent">' + '<p><b>'+beach[0]+'</b> ~ '+beach[1]+' ~ '+beach[2]+' ~ '+beach[3]+'</p>'+'</div>'+'</div>';
    var marker = new google.maps.Marker({
      position: {lat: beach[4], lng: beach[5]},
      map: map,
      //shape: shape,
      title: beach[0]//,
      //zIndex: beach[3]
    });
    attachMarker(marker, contentString);
  }
  
  function attachMarker(marker, contentString) {
  	var infowindow = new google.maps.InfoWindow({
    		content: contentString
    	});
    	marker.addListener('click', function() {
    		infowindow.open(marker.get('map'), marker);
    	});
  }
}
</script>
<script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDZR1p9OfY-reQIv42OsZB_9_ZDq7TkOF0&callback=initMap"></script>
<script type="text/javascript">
</script>
<%} else {%>
<div id="content" class="content">
	<h3>There is no data</h3>
</div>
<%}%>