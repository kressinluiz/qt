// application.qml
import QtQuick 2.12

Column {
    width: 180
    height: 180
    padding: 1.5
    topPadding: 10.0
    bottomPadding: 10.0
    spacing: 5

    MessageLabel{
        width: parent.width - 2
        msgType: "debug"
    }
    MessageLabel {
        width: parent.width - 2
        message: "This is a warning!"
        msgType: "warning"
    }
    MessageLabel {
        width: parent.width - 2
        message: "A critical warning!"
        msgType: "critical"
    }
}



