QT += quick androidextras

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
android: include(/home/kressin/Android/Sdk/android_openssl/openssl.pri)

ANDROID_PACKAGE_SOURCE_DIR = $$PWD/android

DISTFILES += \
    $$PWD/android/AndroidManifest.xml \
    $$PWD/android/build.gradle \
    $$PWD/android/gradle/wrapper/gradle-wrapper.jar \
    $$PWD/android/gradle/wrapper/gradle-wrapper.properties \
    $$PWD/android/gradlew \
    $$PWD/android/gradlew.bat \
    $$PWD/android/res/values/libs.xml \
    $$PWD/android/src/ExampleActivity.java \
    $$PWD/android/libs/armeabi-v7a/uartVideo.aar \
    android/res/xml/device_filter.xml
