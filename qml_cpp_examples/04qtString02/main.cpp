#include <QTextStream>

int main()
{
    QTextStream out(stdout);

    //traditional way
    QString str1 = "The night train";
    out << str1 << endl;

    //object way
    QString str2("A yellow rose");
    out << str2 << endl;

    //brace way
    QString str3 {"An old falcon"};
    out << str3 << endl;

    //c_str method generate a null-terminated sequence of characters
    std::string s1 = "A blue sky";
    QString str4 = s1.c_str();
    out << str4 << endl;

    //convert a standard C++ string to a QString
    std::string s2 = "A thick fog";
    QString str5 = QString::fromLatin1(s2.data(), s2.size());
    out << str5 << endl;

    //A C string it is an array of chars.
    //One of the QString constructors can take an array of chars
    char s3[] = "A deep forest";
    QString str6(s3);
    out << str6 << endl;

    return 0;
}
