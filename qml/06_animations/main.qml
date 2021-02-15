import QtQuick 2.12

Rectangle {
    color: "lightgray"
    width: 200
    height: 200

    property int animatedValue: 0
    SequentialAnimation on animatedValue {
        loops: Animation.Infinite
        PropertyAnimation { to: 150; duration: 10000 }
        PropertyAnimation { to: 0; duration: 10000 }
    }

    Text {
        anchors.centerIn: parent
        text: parent.animatedValue
    }
}
