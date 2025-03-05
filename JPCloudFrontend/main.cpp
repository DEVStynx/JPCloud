#include <iostream>

#include "FrontendSendFunctions.h"
#include "ui_mainwindow.h"
#include "ui_cloudwindow.h"
#include "httpRequestHandler.h"
#include "QFileDialog"
#include "DataHolder.h"

void showAllFilesAtDir(std::string path, Ui::CloudWindow &cloud_window);
void switchToCloudWindow(QMainWindow &w, Ui::CloudWindow &cloud_window) {
    cloud_window.setupUi(&w);
    w.show();
}

void registerButton(QMainWindow &w, Ui::CloudWindow &cloud_window) {
    QApplication::connect(cloud_window.UploadButton, &QPushButton::clicked, [&cloud_window]() {
        FrontendSendFunctions::sendFile(cloud_window.pathInput->toPlainText().toStdString());
        showAllFilesAtDir(DataHolder::path, cloud_window);
});
    QApplication::connect(cloud_window.SelectFile, &QPushButton::clicked, [&]() {
        std::string path = QFileDialog::getOpenFileName(&w, "Upload File", "", "All Files (*);;Text Files (*.txt)").toStdString();
        std::cout <<"opened File" <<path << std::endl;
        cloud_window.pathInput->setText(path.c_str());
        showAllFilesAtDir(DataHolder::path, cloud_window);
    });

    QApplication::connect(cloud_window.cloudFileList, &QListWidget::itemDoubleClicked, [&cloud_window,&w](QListWidgetItem* item) {
        std::vector<FileInformation> res = FrontendSendFunctions::getFiles(DataHolder::path + "/" + item->text().toStdString());

        if (res.size() > 1) {
            std::cout << "Directory" << std::endl;
            DataHolder::path = DataHolder::path + "/" + item->text().toStdString();
            showAllFilesAtDir(DataHolder::path, cloud_window);
        } else {
            std::cout << "Downloading File" << cloud_window.cloudFileList->currentItem()->text().toStdString() << std::endl;
            FrontendSendFunctions::downloadFile(DataHolder::path + "/" +cloud_window.cloudFileList->currentItem()->text().toStdString(),&w);
            showAllFilesAtDir(DataHolder::path, cloud_window);
        }

    });
    QApplication::connect(cloud_window.RefreshButton, &QPushButton::clicked, [&]() {
        showAllFilesAtDir(DataHolder::path, cloud_window);
    });
}

void showAllFilesAtDir(std::string path, Ui::CloudWindow &cloud_window) {
    std::vector<FileInformation> res = FrontendSendFunctions::getFiles(path);
    cloud_window.cloudFileList->clear();
    for ( FileInformation file : res) {
        QListWidgetItem* item = new QListWidgetItem();
        item->setText(file.filename.c_str());

        QIcon icon = QIcon::fromTheme("text-plain");
        QString iconPath = QString::fromStdString(FrontendSendFunctions::GetDefaultIconPath(file));
        if (!iconPath.isEmpty()) {
            icon = QIcon(iconPath);
        }

        item->setIcon(icon);


        cloud_window.cloudFileList->addItem(item);

    }
}
void logout() {
    std::cout << "LOGGING OUT" << std::endl;
    std::cout <<"Logout Successful? "+ FrontendSendFunctions::logout() << std::endl;
}

int main(int argc, char *argv[]) {
    std::atexit(logout);
    DataHolder::path = "";
    QApplication a(argc, argv);
    QMainWindow w;
    w.setWindowTitle("JPCloud");

    Ui::MainWindow main_window;
    main_window.setupUi(&w);
    
    Ui::CloudWindow cloud_window;


    QApplication::connect(main_window.pushButton, &QPushButton::clicked, [&]() {
        std::cout << main_window.usernameIn->toPlainText().toStdString() << std::endl;


        const std::string username = main_window.usernameIn->toPlainText().toStdString();
        const std::string password = main_window.textEdit_2->toPlainText().toStdString();
        const std::string url = main_window.textEdit_3->toPlainText().toStdString();
        DataHolder::username = username;
        DataHolder::password = password;
        DataHolder::url = url;

        std::cout << "Url:" << DataHolder::url << std::endl;
        FrontendSendFunctions::fetchToken();
        switchToCloudWindow(w, cloud_window);
        registerButton(w, cloud_window);
        showAllFilesAtDir("", cloud_window);
    });



    w.show();

    return QApplication::exec();
}
