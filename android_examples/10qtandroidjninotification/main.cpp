#include <QGuiApplication>
#include <QQmlApplicationEngine>

#include <QtAndroidExtras>

int main(int argc, char *argv[])
{
    qDebug() << "Going in";
#if QT_VERSION < QT_VERSION_CHECK(6, 0, 0)
    QCoreApplication::setAttribute(Qt::AA_EnableHighDpiScaling);
#endif

    QAndroidJniObject brasil("org.kressin.example.Notifier");
    qDebug() << "Objeto notifier criado";

    QString s("hi there");

    QAndroidJniEnvironment env;
    jstring jarg = env->NewStringUTF(s.toStdString().c_str());

    brasil.callMethod<void>("notify", "(Ljava/lang/String;)V", jarg);
    qDebug() << "Método notify chamado";

    env->DeleteLocalRef(jarg);

    QGuiApplication app(argc, argv);

    QQmlApplicationEngine engine;
    const QUrl url(QStringLiteral("qrc:/main.qml"));
    QObject::connect(&engine, &QQmlApplicationEngine::objectCreated,
                     &app, [url](QObject *obj, const QUrl &objUrl) {
        if (!obj && url == objUrl)
            QCoreApplication::exit(-1);
    }, Qt::QueuedConnection);
    engine.load(url);

    return app.exec();
}
