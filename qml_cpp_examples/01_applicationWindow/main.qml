//import modules
import QtQuick 2.12
import QtQuick.Controls 2.12

//window containing the application
/*  ApplicationWindow basic layout:
  * Menu Bar
  * Tool Bar
  * Content Area
  * Status Area
*/
ApplicationWindow{


    title:qsTr("Title of the application");
    width: 640
    height: 480

    //menu containing two menu items
    menuBar: MenuBar {
        Menu {
            title: qsTr("File")
            MenuItem {
                text: qsTr("&Open")
                onTriggered: console.log("Open action triggered");
            }
            MenuItem {
                text: qsTr("Exit")
                onTriggered: Qt.quit();
            }
        }
    }

    //content area
    Button {
        text: qsTr("Hello World")
        anchors.horizontalCenter: parent.horizontalCenter
        anchors.verticalCenter: parent.verticalCenter
    }


}
