#include <QApplication>

#include "threebuttonwidget.h"

int main(int argc, char *argv[])
{
   QApplication app(argc, argv);

   ThreeButtonWidget w;
   w.show();

   return(app.exec());
}
