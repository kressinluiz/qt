#include <QTextStream>

int main(void)
{

    QTextStream out(stdout);

    QString string = "There are %1 white roses";
    int number = 12;

    //Dynamic string building: replace specific characters with actual values
    //arg method is used to do the interpolation
    out << string.arg(number) << endl;

    QString s2 = "The tree is %1 m high";
    double h = 5.65;

    out << s2.arg(h) << endl;

    QString s3 = "We have %1 lemons and %2 oranges";
    int ln = 12;
    int on = 4;

    out << s3.arg(ln).arg(on) << endl;

    return 0;
}
