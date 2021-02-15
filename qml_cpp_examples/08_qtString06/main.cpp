#include <QTextStream>

int main(void)
{
    QTextStream out(stdout);

    QString str = { "The night train" };

    //return the five rightmost characters of the string
    out << str.right(5) << endl;
    //return the nine leftmost characters of the string
    out << str.left(9) << endl;
    //return five characters of the string starting from position 4
    out << str.mid(4, 5) << endl;

    //
    QString str2("The big apple");
    QStringRef sub(&str2, 0, 7);

    out << sub.toString() << endl;

    return 0;
}
