#include <QWidget>
#include <QVBoxLayout>
#include <QPushButton>
#include <QLabel>
#include <QKeyEvent>

class ClickMe: public QWidget
{
   Q_OBJECT

public:

   ClickMe(QWidget *parent = NULL)
      : QWidget(parent),
        counter(0)
   {
      QPushButton *button = new QPushButton("Click me - pleeease!");
      button->setStyleSheet("QPushButton { color: red; }");
      connect(button, SIGNAL(clicked()), this, SLOT(click()));
      label = new QLabel;

      QVBoxLayout * layout = new QVBoxLayout;
      layout->addWidget(button);
      layout->addWidget(label);
      setLayout(layout);
   }

protected:

   QLabel *label;
   int counter;

protected slots:

   void click()
   {
      counter++;
      if (counter <= 1)
         label->setText("OMG - you clicked it!");
      else
         if (counter % 2 == 0)
            label->setText("You did it again!");
         else
            label->setText("And again!");
   }

private:

   void keyReleaseEvent(QKeyEvent *event)
   {
      if (event->key() == Qt::Key_Back)
         exit(0);
      else
         QWidget::keyPressEvent(event);
   }

};
