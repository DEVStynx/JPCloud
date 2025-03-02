/********************************************************************************
** Form generated from reading UI file 'cloudwindow.ui'
**
** Created by: Qt User Interface Compiler version 6.8.2
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_CLOUDWINDOW_H
#define UI_CLOUDWINDOW_H

#include <QtCore/QVariant>
#include <QtGui/QAction>
#include <QtGui/QIcon>
#include <QtWidgets/QApplication>
#include <QtWidgets/QListWidget>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenu>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QTextEdit>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_MainWindow
{
public:
    QAction *actiondisconnect;
    QAction *actionexit;
    QAction *actionconnect;
    QAction *actionopensettings;
    QWidget *centralwidget;
    QListWidget *cloudFileList;
    QPushButton *UploadButton;
    QTextEdit *pathInput;
    QPushButton *SelectFile;
    QListWidget *cloudFileList_2;
    QPushButton *RefreshButton;
    QMenuBar *menubar;
    QMenu *menuJpCloud;
    QMenu *menusettings;
    QStatusBar *statusbar;

    void setupUi(QMainWindow *MainWindow)
    {
        if (MainWindow->objectName().isEmpty())
            MainWindow->setObjectName("MainWindow");
        MainWindow->resize(781, 601);
        actiondisconnect = new QAction(MainWindow);
        actiondisconnect->setObjectName("actiondisconnect");
        actionexit = new QAction(MainWindow);
        actionexit->setObjectName("actionexit");
        actionconnect = new QAction(MainWindow);
        actionconnect->setObjectName("actionconnect");
        actionopensettings = new QAction(MainWindow);
        actionopensettings->setObjectName("actionopensettings");
        centralwidget = new QWidget(MainWindow);
        centralwidget->setObjectName("centralwidget");
        cloudFileList = new QListWidget(centralwidget);
        cloudFileList->setObjectName("cloudFileList");
        cloudFileList->setGeometry(QRect(460, 30, 321, 511));
        QFont font;
        font.setPointSize(10);
        cloudFileList->setFont(font);
        cloudFileList->setVerticalScrollBarPolicy(Qt::ScrollBarPolicy::ScrollBarAlwaysOn);
        cloudFileList->setHorizontalScrollBarPolicy(Qt::ScrollBarPolicy::ScrollBarAlwaysOff);
        cloudFileList->setEditTriggers(QAbstractItemView::EditTrigger::CurrentChanged|QAbstractItemView::EditTrigger::DoubleClicked|QAbstractItemView::EditTrigger::EditKeyPressed|QAbstractItemView::EditTrigger::SelectedClicked);
        UploadButton = new QPushButton(centralwidget);
        UploadButton->setObjectName("UploadButton");
        UploadButton->setGeometry(QRect(690, 0, 101, 24));
        pathInput = new QTextEdit(centralwidget);
        pathInput->setObjectName("pathInput");
        pathInput->setGeometry(QRect(460, 0, 211, 31));
        QFont font1;
        font1.setPointSize(7);
        font1.setBold(true);
        pathInput->setFont(font1);
        SelectFile = new QPushButton(centralwidget);
        SelectFile->setObjectName("SelectFile");
        SelectFile->setGeometry(QRect(640, 0, 31, 31));
        QIcon icon(QIcon::fromTheme(QIcon::ThemeIcon::DocumentSaveAs));
        SelectFile->setIcon(icon);
        cloudFileList_2 = new QListWidget(centralwidget);
        cloudFileList_2->setObjectName("cloudFileList_2");
        cloudFileList_2->setGeometry(QRect(0, 30, 321, 511));
        cloudFileList_2->setFont(font);
        cloudFileList_2->setVerticalScrollBarPolicy(Qt::ScrollBarPolicy::ScrollBarAlwaysOn);
        cloudFileList_2->setHorizontalScrollBarPolicy(Qt::ScrollBarPolicy::ScrollBarAlwaysOff);
        cloudFileList_2->setEditTriggers(QAbstractItemView::EditTrigger::CurrentChanged|QAbstractItemView::EditTrigger::DoubleClicked|QAbstractItemView::EditTrigger::EditKeyPressed|QAbstractItemView::EditTrigger::SelectedClicked);
        cloudFileList_2->setDragDropMode(QAbstractItemView::DragDropMode::DragOnly);
        cloudFileList_2->setVerticalScrollMode(QAbstractItemView::ScrollMode::ScrollPerPixel);
        cloudFileList_2->setHorizontalScrollMode(QAbstractItemView::ScrollMode::ScrollPerPixel);
        RefreshButton = new QPushButton(centralwidget);
        RefreshButton->setObjectName("RefreshButton");
        RefreshButton->setGeometry(QRect(0, 0, 71, 24));
        QIcon icon1(QIcon::fromTheme(QIcon::ThemeIcon::ViewRefresh));
        RefreshButton->setIcon(icon1);
        MainWindow->setCentralWidget(centralwidget);
        menubar = new QMenuBar(MainWindow);
        menubar->setObjectName("menubar");
        menubar->setGeometry(QRect(0, 0, 781, 33));
        menuJpCloud = new QMenu(menubar);
        menuJpCloud->setObjectName("menuJpCloud");
        menusettings = new QMenu(menubar);
        menusettings->setObjectName("menusettings");
        MainWindow->setMenuBar(menubar);
        statusbar = new QStatusBar(MainWindow);
        statusbar->setObjectName("statusbar");
        MainWindow->setStatusBar(statusbar);

        menubar->addAction(menuJpCloud->menuAction());
        menubar->addAction(menusettings->menuAction());
        menuJpCloud->addAction(actiondisconnect);
        menuJpCloud->addAction(actionexit);
        menuJpCloud->addAction(actionconnect);
        menusettings->addAction(actionopensettings);

        retranslateUi(MainWindow);

        QMetaObject::connectSlotsByName(MainWindow);
    } // setupUi

    void retranslateUi(QMainWindow *MainWindow)
    {
        MainWindow->setWindowTitle(QCoreApplication::translate("MainWindow", "MainWindow", nullptr));
        actiondisconnect->setText(QCoreApplication::translate("MainWindow", "connect", nullptr));
        actionexit->setText(QCoreApplication::translate("MainWindow", "disconnect", nullptr));
        actionconnect->setText(QCoreApplication::translate("MainWindow", "exit", nullptr));
        actionopensettings->setText(QCoreApplication::translate("MainWindow", "opensettings", nullptr));
        UploadButton->setText(QCoreApplication::translate("MainWindow", "Upload", nullptr));
        pathInput->setPlaceholderText(QCoreApplication::translate("MainWindow", "path", nullptr));
        SelectFile->setText(QString());
        RefreshButton->setText(QCoreApplication::translate("MainWindow", "Refresh", nullptr));
        menuJpCloud->setTitle(QCoreApplication::translate("MainWindow", "JpCloud", nullptr));
        menusettings->setTitle(QCoreApplication::translate("MainWindow", "settings", nullptr));
    } // retranslateUi

};

namespace Ui {
    class MainWindow: public Ui_MainWindow {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_CLOUDWINDOW_H
