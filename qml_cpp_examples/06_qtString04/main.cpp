#include <QTextStream>

int main(void)
{
    QTextStream out(stdout);

    QString s1 = "Eagle";
    QString s2 = "Eagle\n";
    QString s3 = "Eagle ";
    QString s4 = "opeл";


    //There are three methods to get the lenght of a string:
    //the size, the count and the length method.
    out << "length method" << endl;
    out << s1.length() << endl;
    out << s2.length() << endl;
    out << s3.length() << endl;
    out << s4.length() << endl;

    out << "count method" << endl;
    out << s1.count() << endl;
    out << s2.count() << endl;
    out << s3.count() << endl;
    out << s4.count() << endl;

    out << "size method" << endl;
    out << s1.size() << endl;
    out << s2.size() << endl;
    out << s3.size() << endl;
    out << s4.size() << endl;




    return 0;
}
