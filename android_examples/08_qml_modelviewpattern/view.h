#ifndef VIEW_H
#define VIEW_H

#include "model.h"

#include <QWidget>
#include <QVBoxLayout>
#include <QListView>
#include <QPushButton>
#include <QDateTime>

class View: public QWidget
{
   Q_OBJECT

public:

   View(QWidget *parent = NULL)
      : QWidget(parent)
   {
      view_ = new QListView();

      QPushButton *button = new QPushButton("Add list item");
      connect(button, SIGNAL(clicked()), this, SLOT(click()));

      QVBoxLayout * layout = new QVBoxLayout;
      layout->addWidget(view_);
      layout->addWidget(button);
      setLayout(layout);
   }

   void setModel(Model *model)
   {
      model_ = model;
      view_->setModel(model);
   }

protected:

   Model *model_;
   QListView *view_;

protected slots:

   void click()
   {
      model_->add("Added item at " + QDateTime::currentDateTime().toString());
   }

};

#endif
