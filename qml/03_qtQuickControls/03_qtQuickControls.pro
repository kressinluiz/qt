QT += quick quickcontrols2

CONFIG += c++11

# You can make your code fail to compile if it uses deprecated APIs.
# In order to do so, uncomment the following line.
#DEFINES += QT_DISABLE_DEPRECATED_BEFORE=0x060000    # disables all the APIs deprecated before Qt 6.0.0

SOURCES += \
        main.cpp

RESOURCES += qml.qrc

# Additional import path used to resolve QML modules in Qt Creator's code model
QML_IMPORT_PATH =

# Additional import path used to resolve QML modules just for Qt Quick Designer
QML_DESIGNER_IMPORT_PATH =

# Default rules for deployment.
qnx: target.path = /tmp/$${TARGET}/bin
else: unix:!android: target.path = /opt/$${TARGET}/bin
!isEmpty(target.path): INSTALLS += target

DISTFILES += \
    icons/gallery/20x20/back.png \
    icons/gallery/20x20/drawer.png \
    icons/gallery/20x20/menu.png \
    icons/gallery/20x20@2/back.png \
    icons/gallery/20x20@2/drawer.png \
    icons/gallery/20x20@2/menu.png \
    icons/gallery/20x20@3/back.png \
    icons/gallery/20x20@3/drawer.png \
    icons/gallery/20x20@3/menu.png \
    icons/gallery/20x20@4/back.png \
    icons/gallery/20x20@4/drawer.png \
    icons/gallery/20x20@4/menu.png \
    icons/gallery/index.theme \
    images/arrow.png \
    images/arrow@2x.png \
    images/arrow@3x.png \
    images/arrow@4x.png \
    images/arrows.png \
    images/arrows@2x.png \
    images/arrows@3x.png \
    images/arrows@4x.png \
    images/qt-logo.png \
    images/qt-logo@2x.png \
    images/qt-logo@3x.png \
    images/qt-logo@4x.png \
    pages/BusyIndicatorPage.qml \
    pages/ButtonPage.qml \
    pages/CheckBoxPage.qml \
    pages/ComboBoxPage.qml \
    pages/DelayButtonPage.qml \
    pages/DelegatePage.qml \
    pages/DialPage.qml \
    pages/DialogPage.qml \
    pages/FramePage.qml \
    pages/GroupBoxPage.qml \
    pages/PageIndicatorPage.qml \
    pages/ProgressBarPage.qml \
    pages/RadioButtonPage.qml \
    pages/RangeSliderPage.qml \
    pages/ScrollBarPage.qml \
    pages/ScrollIndicatorPage.qml \
    pages/ScrollablePage.qml \
    pages/SliderPage.qml \
    pages/SpinBoxPage.qml \
    pages/StackViewPage.qml \
    pages/SwipeViewPage.qml \
    pages/SwitchPage.qml \
    pages/TabBarPage.qml \
    pages/TextAreaPage.qml \
    pages/TextFieldPage.qml \
    pages/ToolTipPage.qml \
    pages/TumblerPage.qml
