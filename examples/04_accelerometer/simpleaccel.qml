import QtQuick 2.0
import QtSensors 5.0

Rectangle {
    id: top
    anchors.fill: parent
    color: "black"

    Accelerometer {
        id: accel
        dataRate: 100
        active: true
        property real dx: 0
        property real dy: 0
        property real dz: 1

        onReadingChanged: {
            var length = Math.sqrt(reading.x*reading.x+
                                   reading.y*reading.y+
                                   reading.z*reading.z);

            dx = reading.x/length;
            dy = reading.y/length;
            dz = reading.z/length;
        }
    }

    Rectangle {
        property real size: parent.width<parent.height?parent.width:parent.height
        x: 0.5*parent.width-0.5*width-0.45*size*accel.dx
        y: 0.5*parent.height-0.5*height+0.45*size*accel.dy
        width: size/10
        height: width
        color: "gray"
        border.color: "white"
        border.width: 1
        radius: 0.5*width

        Behavior on x {
            SmoothedAnimation {
                easing.type: Easing.Linear
                duration: 50
            }
        }

        Behavior on y {
            SmoothedAnimation {
                easing.type: Easing.Linear
                duration: 50
            }
        }
    }
}
