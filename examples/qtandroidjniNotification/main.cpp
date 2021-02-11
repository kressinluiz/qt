#include <QGuiApplication>
#include <QQmlApplicationEngine>

#include <QtAndroidExtras>

int main(int argc, char *argv[])
{
#if QT_VERSION < QT_VERSION_CHECK(6, 0, 0)
    QCoreApplication::setAttribute(Qt::AA_EnableHighDpiScaling);
#endif

    QGuiApplication app(argc, argv);

    QAndroidJniObject brasil("org.kressin.example.Notifier");
    qDebug() << "Objeto notifier criado";

    QString s("hi there");

    QAndroidJniEnvironment env;
    jstring jarg = env->NewStringUTF(s.toStdString().c_str());

    brasil.callMethod<void>("notify", "(Ljava/lang/String;)V", jarg);
    qDebug() << "Método notify chamado";

    env->DeleteLocalRef(jarg);

    return app.exec();
}
