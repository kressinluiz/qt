#include "mainwindow.h"

MainWindow::MainWindow(QWidget *parent)
   : QWidget(parent)
{
   QVBoxLayout *mainLayout = new QVBoxLayout;
   setLayout(mainLayout);

   compassLabel_ = new QLabel("Compass");
   mainLayout->addWidget(compassLabel_);

   QPushButton *quitButton = new QPushButton("Quit");
   connect(quitButton, SIGNAL(pressed()), this, SLOT(close()));
   mainLayout->addWidget(quitButton);

   info_ = new CompassInfo;
   connect(info_, SIGNAL(azimuth(qreal)), this, SLOT(azimuth(qreal)));
}

MainWindow::~MainWindow()
{
   delete info_;

   delete compassLabel_;
}

void MainWindow::azimuth(qreal a)
{
   compassLabel_->setText("Compass: "+QString::number(floor(a+0.5)));
}

void MainWindow::keyReleaseEvent(QKeyEvent *event)
{
#ifdef Q_OS_ANDROID

   if (event->key() == Qt::Key_Back)
      event->accept();

#endif
}
