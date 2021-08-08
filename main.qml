import QtQuick 2.15
import QtQuick.Window 2.15
import QtQuick.Controls 2.12
import io.qt.examples.backend 1.0

Window {
    width: 640
    height: 480
    visible: true
    title: qsTr("Integrando QML com C++")


    BackEnd {
        id: backend
    }

    TextField {
        text: backend.userName
        placeholderText: qsTr("User name")
        anchors.centerIn: parent

        onEditingFinished: backend.userName = text
    }
}
