#include <QTextStream>

int main(void)
{
    QTextStream out(stdout);

    QString string {"Eagle"};

    out << string[0] << endl;
    out << string[4] << endl;

    out << string.at(0) << endl;

    //string.at(index position)
    //The position must be a valid index position ( 0 <= position < size() )
    if (!string.at(4).isNull()) {
        out << "inside the range of the string" << endl;
    }

    return 0;
}
