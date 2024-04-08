<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="http://code.jquery.com/jquery-latest.js"></script>

<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	$.ajax({
		type : "GET",
		url : "apitest",
		datatype : "json",
		success : function(data){
			console.log("성공");
			$.each(data, function(index, item){
			$("#apitest").append((index+1) + "<br>"); // index가 끝날때까지 
			$("#apitest").append("지역번호= " + item.stnid + ",<br>");
			$("#apitest").append("지역명(국문)= " + item.stnko + ",<br>");
			$("#apitest").append("기압-평균현지= " + item.pa + ",<br>");
			$("#apitest").append("기압-평균해면= " + item.ps + ",<br>");
			$("#apitest").append("기온-최고평균=" + item.avgtamax + ",<br>");
			$("#apitest").append("기온-최저평균=" + item.avgtamin + "<br>");
			$("#apitest").append("기온-평균=" + item.taavg + "<br>");
			$("#apitest").append("기온-최고-최고=" + item.tamin + "<br>");
			$("#apitest").append("기온-최저-최저=" + item.avgtgmin + "<br>");
			});
		},
		error: function(xhr, status, error) {
        	console.error("AJAX 요청 실패:", status, error); 
        	alert("서버에서 데이터를 가져오는 중 오류가 발생했습니다.");
    }
	});
</script>
</head>
<body>
<div id="apitest"></div>
<div id="map" style="width:100%;height:350px;"></div>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=3ac1a052250440a894c10831b4af0aa7"></script>
<script>
var mapContainer = document.getElementById('map'), // 지도를 표시할 div  
    mapOption = { 
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

// 마커를 표시할 위치와 title 객체 배열입니다 
var positions = [
    {
        title: '카카오', 
        latlng: new kakao.maps.LatLng(33.450705, 126.570677)
    },
    {
        title: '생태연못', 
        latlng: new kakao.maps.LatLng(33.450936, 126.569477)
    },
    {
        title: '텃밭', 
        latlng: new kakao.maps.LatLng(33.450879, 126.569940)
    },
    {
        title: '근린공원',
        latlng: new kakao.maps.LatLng(33.451393, 126.570738)
    }
];

// 마커 이미지의 이미지 주소입니다
var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; 
    
for (var i = 0; i < positions.length; i ++) {
    
    // 마커 이미지의 이미지 크기 입니다
    var imageSize = new kakao.maps.Size(24, 35); 
    
    // 마커 이미지를 생성합니다    
    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize); 
    
    // 마커를 생성합니다
    var marker = new kakao.maps.Marker({
        map: map, // 마커를 표시할 지도
        position: positions[i].latlng, // 마커를 표시할 위치
        title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
        image : markerImage // 마커 이미지 
    });
}
</script>

</body>
</html>
