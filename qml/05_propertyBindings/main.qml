import QtQuick 2.12

Rectangle {
    width: 400
    height: 200

    Rectangle {
        color: "red"
        width: parent.width / 2
        height: parent.height
    }

    Rectangle {
        color: "blue"
        width: parent.width / 2
        height: parent.height
        x: parent.width / 2
    }
}
