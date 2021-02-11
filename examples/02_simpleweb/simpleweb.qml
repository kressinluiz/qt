import QtQuick 2.0
import QtWebView 1.0
import QtPositioning 5.3

Rectangle {
    id: top
    anchors.fill: parent
    color: "black"

    PositionSource {
        id: gps
        preferredPositioningMethods: PositionSource.SatellitePositioningMethods
        updateInterval: 1000 // ms
        active: true
        onPositionChanged: {
            active = false;
        }
    }

    Text {
        anchors.centerIn: parent
        font.pointSize: 10
        color: "white"
        text: "Location is unknown"
        visible: !gps.position.latitudeValid || !gps.position.longitudeValid
    }

    WebView {
        id: webview
        anchors.fill: parent

        url: "https://www.google.com/maps/@?api=1&map_action=map&center=" +
             gps.position.coordinate.latitude + "," +
             gps.position.coordinate.longitude +
             "&zoom=12&basemap=terrain"

        visible: gps.position.latitudeValid && gps.position.longitudeValid
    }
}
