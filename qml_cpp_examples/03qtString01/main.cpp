#include <QTextStream>

int main(void)
{
    QTextStream out(stdout);

    QString string {" love "};

    string.append("chess");
    string.prepend("I");

    out << string << endl;
    out << "The string has " << string.count()
        << " characters" << endl;

    out << string.toUpper() << endl;
    out << string.toLower() << endl;
    return 0;
}
