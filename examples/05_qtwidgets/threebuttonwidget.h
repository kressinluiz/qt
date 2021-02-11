#ifndef THREEBUTTONWIDGET_H
#define THREEBUTTONWIDGET_H

#include <QWidget>
#include <QRadioButton>
#include <QBoxLayout>

class ThreeButtonWidget: public QWidget
{
   public:

      ThreeButtonWidget(QWidget *parent = NULL)
         : QWidget(parent)
      {
         QBoxLayout *layout = new QBoxLayout(QBoxLayout::TopToBottom);
         layout->setDirection(QBoxLayout::TopToBottom);


         QString textoteste = "Olaaaaa";
         layout->addWidget(new QRadioButton(textoteste));
         layout->addWidget(new QRadioButton(textoteste));
         layout->addWidget(new QRadioButton(textoteste));

         setLayout(layout);
      }

      ~ThreeButtonWidget()
      {}
};


#endif // THREEBUTTONWIDGET_H
