TARGET = modelview
TEMPLATE = app

SOURCES += main.cpp
HEADERS += model.h view.h

QT += core

message(QT_VERSION = $$QT_VERSION)
greaterThan(QT_MAJOR_VERSION, 4): QT += widgets

DISTFILES += \
    android/AndroidManifest.xml \
    android/build.gradle \
    android/gradle/wrapper/gradle-wrapper.jar \
    android/gradle/wrapper/gradle-wrapper.properties \
    android/gradlew \
    android/gradlew.bat \
    android/res/values/libs.xml

ANDROID_PACKAGE_SOURCE_DIR = $$PWD/android

OTHER_FILES += android/AndroidManifest.xml
