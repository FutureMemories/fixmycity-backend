<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>FixMyCity</title>

</head>

<body>
<br><br>
<!--<center>
    <asset:image src="1 Splash.png" class="grails-logo"/>
</center>-->

<div id="map"></div>

<script>

    function initMap() {

        var myLatLng = {lat : 57.709, lng : 11.97456};

        var map = new google.maps.Map(document.getElementById('map'), {
            zoom : 12,
            center : myLatLng
        });

        var marker = new google.maps.Marker({
            position : {lat : 58.709, lng : 11.97456},
            map : map,
            title : 'Hello World!'
        });
        var marker = new google.maps.Marker({
            position : myLatLng,
            map : map,
            title : 'Hello World!'
        });
    }
</script>
<script async defer
        src="https://maps.googleapis.com/maps/api/js?callback=initMap">
</script>

</body>
</html>
