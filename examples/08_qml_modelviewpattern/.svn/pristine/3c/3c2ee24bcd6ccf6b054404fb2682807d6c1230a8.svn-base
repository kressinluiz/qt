#include <QApplication>

#include "model.h"
#include "view.h"

int main(int argc, char *argv[])
{
   QApplication app(argc, argv);

   Model m;
   View v;
   v.setModel(&m);
   v.show();

   return(app.exec());
}
