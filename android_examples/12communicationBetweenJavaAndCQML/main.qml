import QtQuick 2.12
import QtQuick.Window 2.12
import QtQuick.Controls 2.12
import QtQuick.Layouts 1.12

ApplicationWindow {
    id: window
    visible: true

    ColumnLayout {
        anchors.fill: parent
        Text {
            id: messengerLog
            text: qsTr("")
            clip: true
            Layout.fillHeight: true
            Layout.fillWidth: true
            transformOrigin: Item.Center
            Layout.alignment: Qt.AlignHCenter | Qt.AlignTop
        }

        RowLayout {
            id: rowlayout
            Layout.bottomMargin: 10
            Layout.alignment: Qt.AlignHCenter | Qt.AlignBottom
            anchors.bottom: window.bottom
            spacing: 10

            Button {
                text: qsTr("Send to Java via JNI")
                onClicked: {
                    var message = qsTr("QML sending to Java: Hello from QML")
                    messengerLog.text += "\n" + message
                    JniMessenger.printFromJava(message)
                }
            }

            Button {
                text: "Clear"
                onClicked: messengerLog.text = ""
            }
        }
    }

    Connections {
        target: JniMessenger
        function onMessageFromJava(message) {
            System.out.println("onMessageFromJava BEGIN");
            var output = qsTr("QML received a message: %1").arg(message)
            print(output)
            messengerLog.text += "\n" + output
            System.out.println("onMessageFromJava END");
        }
    }
}
