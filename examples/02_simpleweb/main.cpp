#include <QApplication>
#include <QQuickView>
#include <QQuickWidget>

#include <QtWebView/QtWebView>

int main(int argc, char *argv[])
{
   QApplication app(argc, argv);
   QtWebView::initialize();

#ifndef QQUICKWIDGET
   QQuickView v;
   v.setResizeMode(QQuickView::SizeRootObjectToView);
   v.setSource(QUrl("qrc:///simpleweb.qml"));
   v.resize(800,640);
   v.show();
#else
   QQuickWidget w;
   w.setSource(QUrl("qrc:///simpleweb.qml"));
   w.setResizeMode(QQuickWidget::SizeRootObjectToView);
   w.resize(800,640);
   w.show();
#endif

   return(app.exec());
}
