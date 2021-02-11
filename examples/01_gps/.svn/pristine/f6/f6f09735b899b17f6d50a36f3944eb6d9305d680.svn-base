import QtQuick 2.0
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
    }

    Text {
        anchors.centerIn: parent
        font.pointSize: 10
        color: "white"
        text: "Lat/Lon = "+gps.position.coordinate.latitude + "/" + gps.position.coordinate.longitude
        visible: gps.valid && gps.position.latitudeValid && gps.position.longitudeValid
    }
}
