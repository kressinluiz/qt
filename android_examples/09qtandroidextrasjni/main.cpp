#include <QGuiApplication>
#include <QQmlApplicationEngine>

#include <QtAndroidExtras>

int main(int argc, char *argv[])
{
#if QT_VERSION < QT_VERSION_CHECK(6, 0, 0)
    QCoreApplication::setAttribute(Qt::AA_EnableHighDpiScaling);
#endif

    QGuiApplication app(argc, argv);

    bool avail = QAndroidJniObject::isClassAvailable("org/kressin/example/ExampleActivity");

    if (avail)
       QAndroidJniObject::callStaticMethod<void>("org/kressin/example/ExampleActivity", "doSomething");
    else
       qDebug() << "class not available";

    return app.exec();
}
