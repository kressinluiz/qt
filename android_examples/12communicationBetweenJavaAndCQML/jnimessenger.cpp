#include "jnimessenger.h"

#include <QAndroidJniObject>
#include <QAndroidJniEnvironment>
#include <QtAndroid>
#include <QMessageLogger>

JniMessenger *JniMessenger::m_instance = nullptr;

static void callFromJava(JNIEnv *env, jobject /*thiz*/, jstring value)
{
    qDebug("callFromJava cpp BEGIN");
    const char * message = env->GetStringUTFChars(value, nullptr);
    qDebug("Message from Java: %s ", message);
    emit JniMessenger::instance()->messageFromJava(env->GetStringUTFChars(value, nullptr));
    qDebug("callFromJava cpp END");
}

JniMessenger::JniMessenger(QObject *parent) : QObject(parent)
{
    m_instance = this;

    JNINativeMethod methods[] {{"callFromJava", "(Ljava/lang/String;)V", reinterpret_cast<void *>(callFromJava)}};
    QAndroidJniObject javaClass("org/qtproject/example/jnimessenger/JniMessenger");

    QAndroidJniEnvironment env;
    jclass objectClass = env->GetObjectClass(javaClass.object<jobject>());
    env->RegisterNatives(objectClass,
                         methods,
                         sizeof(methods) / sizeof(methods[0]));
    env->DeleteLocalRef(objectClass);
}

void JniMessenger::printFromJava(const QString &message)
{
    qDebug("printFromJava cpp BEGIN");
    QAndroidJniObject javaMessage = QAndroidJniObject::fromString(message);
    QAndroidJniObject::callStaticMethod<void>("org/qtproject/example/jnimessenger/JniMessenger",
                                       "printFromJava",
                                       "(Ljava/lang/String;)V",
                                        javaMessage.object<jstring>());
    qDebug("printFromJava cpp END");
}
