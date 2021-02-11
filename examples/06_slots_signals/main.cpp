#include <QApplication>

#include "clickme.h"

int main(int argc, char *argv[])
{
   QApplication app(argc, argv);

   ClickMe w;
   w.show();

   return(app.exec());
}
