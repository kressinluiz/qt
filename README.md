# The files and projects are for personal reference.

# Qt for Android

- Use Qt Creator to compile/debug projects and to install a specific version of Qt and its libraries. 
- Use Android Studio to setup NDK (Native Dev Kit), SDK (Software Dev Kit) and ADB (Android Debug Bridge) (Android dependecies and debugger).

# Cute?

Design and implementation of multi-platform user interfaces.

# How to create a project for Android?
- Create a new project of Qt Quick Application with Qt Creator.
- Go to Build Android APK (Projects -> Build Settings -> Build Steps) and click in Create Templates (Application -> Android Customization). Choose SDK according to Qt version (for Qt 5.12.6 -> SDK 28).
- Add android directory (gradle, res, java files, manifest) to the .pro file of the project
```
1 ANDROID_PACKAGE_SOURCE_DIR = $$PWD/android
```

```
1 >OTHER_FILES += \
2    android/AndroidManifest.xml\
3    android/src/org/kressin/Main.java \
4    android/src/org/kressin/Notifier.java
```
- Add any Qt module used in the project to the .pro file of the project
```
QT+= quick androidextras widgets ... etc ...
```
- Add any permissions required by the app in the android manifest file
```
1   <uses-permission android:name=“android.permission.CAMERA”/>
2   <uses-permission android:name=“android.permission.FLASHLIGHT”/>
```


# How to generate a UI?

## use QML to define the properties and dependencies of ui elements (.qml file)

- QML is based on JavaScript
- To instantiate a UI that was declared with QML, it is necessary a QML/JavaScript interpreter. We can run an interpreter by creating
    - QQuickView window: QML content occupies the entire window
    - QuickWidget object: QML content is part of Qt widget hierarchy


## Implement native Qt widget classes (C++) and layout them in a hierarchy


## use the Qt Designer to design the widget hierarchy and implement functionality (click and drag approach - limited by complexity)

# How to run java code on Qt for Android?
- JNI (Java Native Interface) is used to call java code from native C/C++ code.
- The Qt Android Extras module has a wrapper for JNI: [QAndroidJNIEnvironment](https://doc.qt.io/qt-5/qandroidjnienvironment.html), [QAndroidJNIExceptionCleaner](https://doc.qt.io/qt-5/qandroidjniexceptioncleaner.html), [QAndroidJNIObject](https://doc.qt.io/qt-5/qandroidjniobject.html)
- Edit .pro file of the project to add the Android Extras Module: QT += androidextras
- Include QtAndroidExtras header file: #include <QtAndroidExtras>

## How to call a static java method with QtAndroidExtras?
```
QAndroidJNIObject::callStaticMethod<T>("path/to/java/class", "method name", "signature", parameters)
```
- T corresponds to the java return type (jint for a Java integer)
- Signature specified in JNI style: "(parameter-types)result-type"
    - Example: "(II)I" for two Java integer parameters (function arguments) and an integer result (return)


# References
- [Qt on Android by Stefan Rottger](http://schorsch.efi.fh-nuernberg.de/roettger/index.php/Lectures/QtOnAndroid)
- [Qt 5.12.2 Android Extras](https://doc.qt.io/qt-5/qtandroidextras-index.html)