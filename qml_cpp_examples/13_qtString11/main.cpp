#include <QTextStream>

int main(void) {

   QTextStream out(stdout);

   QString str { "Lovely" };
   str.append(" season");

   out << str << endl;

   //remove 3 characters from the string starting at position 10
   str.remove(10, 3);
   out << str << endl;

   //replace 3 characters starting at position 7
   str.replace(7, 3, "girl");
   out << str << endl;

   //clears the string!
   str.clear();

   if (str.isEmpty()) {
     out << "The string is empty" << endl;
   }

   return 0;
}
