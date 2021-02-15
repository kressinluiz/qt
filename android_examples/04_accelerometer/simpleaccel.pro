QT += core gui quick quickwidgets multimedia
greaterThan(QT_MAJOR_VERSION, 4): QT += widgets

TARGET = simpleaccel
TEMPLATE = app

SOURCES += main.cpp

CONFIG += mobility

RESOURCES += simpleaccel.qrc

ANDROID_PACKAGE_SOURCE_DIR = $$PWD/android
OTHER_FILES += android/AndroidManifest.xml

DISTFILES += \
    android/AndroidManifest.xml \
    android/build.gradle \
    android/gradle/wrapper/gradle-wrapper.jar \
    android/gradle/wrapper/gradle-wrapper.properties \
    android/gradlew \
    android/gradlew.bat \
    android/res/values/libs.xml

