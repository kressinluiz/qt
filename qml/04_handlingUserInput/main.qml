import QtQuick 2.12

Rectangle {
    width: 200
    height: 100
    color: "red"

    Text {
        anchors.centerIn: parent
        text: "Hello, World!"
    }

    //by accepting focus,
    //the color can be changed whenever the enter key is pressed
    focus: true
    Keys.onPressed: {
        if (event.key === Qt.Key_Return) {
            color = "red";
            event.accepted = true;
        }
    }

    //whenever the user clicks anywhere in the window, color changes to blue
    TapHandler {
        onTapped: parent.color = "blue"
    }
}
