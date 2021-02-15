#include <QApplication>
#include <QWidget>

int main(int argc, char *argv[])
{
    QApplication app(argc, argv);

    QWidget window;

    window.resize(600, 300);
    window.setWindowTitle("Window Title");
    window.show();

    return app.exec();
}
