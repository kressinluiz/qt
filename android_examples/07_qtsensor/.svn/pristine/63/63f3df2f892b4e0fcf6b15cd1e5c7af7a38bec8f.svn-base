#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QtGlobal>

#if (QT_VERSION >= QT_VERSION_CHECK(5, 0, 0))
#include <QtWidgets>
#else
#include <QtGui>
#endif

#include <QCompass>
#include <QCompassFilter>

class CompassInfo: public QObject, public QCompassFilter
{
   Q_OBJECT

public:

   CompassInfo(QObject* parent = NULL)
      : QObject(parent), QCompassFilter(),
        m_azimuth(0)
   {
      m_sensor = new QCompass(this);
      m_sensor->addFilter(this);
      m_sensor->start();
   }

private:

   virtual bool filter(QCompassReading *reading)
   {
      qreal a = reading->azimuth();

      if (a != m_azimuth)
      {
         emit azimuth(a);
         m_azimuth = a;
      }

      return(true);
   }

private:

   QCompass* m_sensor;
   qreal m_azimuth;

signals:

   void azimuth(qreal a);
};

class MainWindow: public QWidget
{
   Q_OBJECT

public:

   explicit MainWindow(QWidget *parent = 0);
   virtual ~MainWindow();

protected:

   CompassInfo *info_;

   QLabel *compassLabel_;

   void keyReleaseEvent(QKeyEvent *event);

protected slots:

   void azimuth(qreal a);
};

#endif // MAINWINDOW_H
