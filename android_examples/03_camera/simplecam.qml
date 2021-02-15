import QtQuick 2.0
import QtMultimedia 5.3

Rectangle {
    width: 800
    height: 480

    Camera {
        id: camera
        videoRecorder {
            resolution: "800x480"
            frameRate: 30
        }
    }

    VideoOutput {
        anchors.fill: parent
        source: camera
        autoOrientation: true

        MouseArea {
            anchors.fill: parent;
            onClicked: camera.searchAndLock()
            onDoubleClicked: camera.imageCapture.capture()
        }
    }
}
