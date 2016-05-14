<%@ page import="fixmycity.backend.Issue" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>FixMyCity <%="Hello GSP!"%></title>

</head>

<body>

<!--<center>
    <asset:image src="1 Splash.png" class="grails-logo"/>
</center>-->

<div id="map"></div>

<script>

    function initMap() {

        var issues = <%=Issue.list().collect{it.position()}%>;

        console.log(issues.length)




        var myLatLng = {lat : 57.709, lng : 11.97456};

        var map = new google.maps.Map(document.getElementById('map'), {
            zoom : 12,
            center : myLatLng
        });

        for (i = 0; i < issues.length; i++) {

            var image = 'http://192.81.222.241:8080/assets/problem.png';

            new google.maps.Marker({
                position : {lat : issues[i].lat, lng : issues[i].lng},
                map : map,
                icon: image,
                title : 'Hello World!'
            });
        }
    }
</script>
<script async defer
        src="https://maps.googleapis.com/maps/api/js?callback=initMap">
</script>

</body>
</html>
