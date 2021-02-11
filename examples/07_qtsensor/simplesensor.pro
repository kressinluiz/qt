TARGET = simplesensor
TEMPLATE = app

SOURCES += main.cpp mainwindow.cpp
HEADERS += mainwindow.h

QT += core network sensors

message(QT_VERSION = $$QT_VERSION)
greaterThan(QT_MAJOR_VERSION, 4): QT += widgets
!lessThan(QT_VERSION, "5.3.0"): QT += sensors

unix:QMAKE_CXXFLAGS_WARN_ON += -Wall -Wno-unused-parameter -Wno-parentheses

ANDROID_PACKAGE_SOURCE_DIR = $$PWD/android

OTHER_FILES += android/AndroidManifest.xml

DISTFILES += \
    android/build.gradle \
    android/gradle/wrapper/gradle-wrapper.jar \
    android/gradle/wrapper/gradle-wrapper.properties \
    android/gradlew \
    android/gradlew.bat \
    android/res/values/libs.xml
