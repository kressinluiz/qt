#include <QApplication>
#include <QQuickView>
#include <QQuickWidget>

int main(int argc, char *argv[])
{
   QApplication app(argc, argv);

#ifndef QQUICKWIDGET
   QQuickView v;
   v.setResizeMode(QQuickView::SizeRootObjectToView);
   v.setSource(QUrl("qrc:///simpleqml.qml"));
   v.show();
#else
   QQuickWidget w;
   w.setSource(QUrl("qrc:///simpleqml.qml"));
   w.setResizeMode(QQuickWidget::SizeRootObjectToView);
   w.show();
#endif

   return(app.exec());
}
