#include <QApplication>
#include <QQuickView>
#include <QQuickWidget>

#include <QtWebView/QtWebView>

int main(int argc, char *argv[])
{
   QApplication app(argc, argv);

#ifndef QQUICKWIDGET
   QQuickView v;
   v.setResizeMode(QQuickView::SizeRootObjectToView);
   v.setSource(QUrl("qrc:///simplegps.qml"));
   v.resize(800,640);
   v.show();
#else
   QQuickWidget w;
   w.setSource(QUrl("qrc:///simplegps.qml"));
   w.setResizeMode(QQuickWidget::SizeRootObjectToView);
   w.resize(800,640);
   w.show();
#endif

   return(app.exec());
}
