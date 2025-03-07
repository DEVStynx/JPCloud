/********************************************************************************
** Form generated from reading UI file 'cloudwindowquZhqS.ui'
**
** Created by: Qt User Interface Compiler version 6.8.2
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef CLOUDWINDOWQUZHQS_H
#define CLOUDWINDOWQUZHQS_H

#include <QtCore/QVariant>
#include <QtGui/QAction>
#include <QtGui/QIcon>
#include <QtWidgets/QApplication>
#include <QtWidgets/QGridLayout>
#include <QtWidgets/QListWidget>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenu>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QTextEdit>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_CloudWindow
{
public:
    QAction *actionconnect;
    QAction *action_disconnect;
    QAction *action_exit;
    QAction *actionopensettings;
    QAction *actionRefresh;
    QAction *action_refresh;
    QWidget *centralwidget;
    QListWidget *cloudFileList;
    QPushButton *UploadButton;
    QTextEdit *pathInput;
    QPushButton *SelectFile;
    QPushButton *RefreshButton;
    QTextEdit *currentServerpathInput;
    QPushButton *backpathButton;
    QWidget *gridLayoutWidget;
    QGridLayout *gridLayout;
    QMenuBar *menubar;
    QMenu *menuJpCloud;
    QMenu *menusettings;
    QMenu *RefreshDropdown;
    QStatusBar *statusbar;

    void setupUi(QMainWindow *MainWindow)
    {
        if (MainWindow->objectName().isEmpty())
            MainWindow->setObjectName("MainWindow");
        MainWindow->resize(781, 601);
        actionconnect = new QAction(MainWindow);
        actionconnect->setObjectName("actionconnect");
        action_disconnect = new QAction(MainWindow);
        action_disconnect->setObjectName("action_disconnect");
        action_exit = new QAction(MainWindow);
        action_exit->setObjectName("action_exit");
        actionopensettings = new QAction(MainWindow);
        actionopensettings->setObjectName("actionopensettings");
        actionRefresh = new QAction(MainWindow);
        actionRefresh->setObjectName("actionRefresh");
        action_refresh = new QAction(MainWindow);
        action_refresh->setObjectName("action_refresh");
        centralwidget = new QWidget(MainWindow);
        centralwidget->setObjectName("centralwidget");
        cloudFileList = new QListWidget(centralwidget);
        cloudFileList->setObjectName("cloudFileList");
        cloudFileList->setGeometry(QRect(0, 30, 781, 511));
        QFont font;
        font.setPointSize(10);
        cloudFileList->setFont(font);
        cloudFileList->setVerticalScrollBarPolicy(Qt::ScrollBarPolicy::ScrollBarAlwaysOn);
        cloudFileList->setHorizontalScrollBarPolicy(Qt::ScrollBarPolicy::ScrollBarAlwaysOff);
        cloudFileList->setEditTriggers(QAbstractItemView::EditTrigger::CurrentChanged|QAbstractItemView::EditTrigger::DoubleClicked|QAbstractItemView::EditTrigger::EditKeyPressed|QAbstractItemView::EditTrigger::SelectedClicked);
        UploadButton = new QPushButton(centralwidget);
        UploadButton->setObjectName("UploadButton");
        UploadButton->setGeometry(QRect(210, 0, 101, 24));
        pathInput = new QTextEdit(centralwidget);
        pathInput->setObjectName("pathInput");
        pathInput->setGeometry(QRect(0, 0, 211, 31));
        QFont font1;
        font1.setPointSize(7);
        font1.setBold(true);
        pathInput->setFont(font1);
        SelectFile = new QPushButton(centralwidget);
        SelectFile->setObjectName("SelectFile");
        SelectFile->setGeometry(QRect(180, 0, 31, 31));
        QIcon icon(QIcon::fromTheme(QIcon::ThemeIcon::DocumentSaveAs));
        SelectFile->setIcon(icon);
        RefreshButton = new QPushButton(centralwidget);
        RefreshButton->setObjectName("RefreshButton");
        RefreshButton->setGeometry(QRect(710, 0, 71, 24));
        QIcon icon1(QIcon::fromTheme(QIcon::ThemeIcon::ViewRefresh));
        RefreshButton->setIcon(icon1);
        currentServerpathInput = new QTextEdit(centralwidget);
        currentServerpathInput->setObjectName("currentServerpathInput");
        currentServerpathInput->setGeometry(QRect(310, 0, 211, 31));
        currentServerpathInput->setFont(font1);
        backpathButton = new QPushButton(centralwidget);
        backpathButton->setObjectName("backpathButton");
        backpathButton->setGeometry(QRect(520, 0, 32, 32));
        backpathButton->setMinimumSize(QSize(32, 32));
        backpathButton->setMaximumSize(QSize(32, 32));
        QIcon icon2(QIcon::fromTheme(QIcon::ThemeIcon::DocumentRevert));
        backpathButton->setIcon(icon2);
        gridLayoutWidget = new QWidget(centralwidget);
        gridLayoutWidget->setObjectName("gridLayoutWidget");
        gridLayoutWidget->setGeometry(QRect(290, 100, 160, 80));
        gridLayout = new QGridLayout(gridLayoutWidget);
        gridLayout->setObjectName("gridLayout");
        gridLayout->setContentsMargins(0, 0, 0, 0);
        MainWindow->setCentralWidget(centralwidget);
        menubar = new QMenuBar(MainWindow);
        menubar->setObjectName("menubar");
        menubar->setGeometry(QRect(0, 0, 781, 33));
        menuJpCloud = new QMenu(menubar);
        menuJpCloud->setObjectName("menuJpCloud");
        menusettings = new QMenu(menubar);
        menusettings->setObjectName("menusettings");
        RefreshDropdown = new QMenu(menubar);
        RefreshDropdown->setObjectName("RefreshDropdown");
        RefreshDropdown->setIcon(icon1);
        MainWindow->setMenuBar(menubar);
        statusbar = new QStatusBar(MainWindow);
        statusbar->setObjectName("statusbar");
        MainWindow->setStatusBar(statusbar);

        menubar->addAction(RefreshDropdown->menuAction());
        menubar->addAction(menuJpCloud->menuAction());
        menubar->addAction(menusettings->menuAction());
        menuJpCloud->addAction(actionconnect);
        menuJpCloud->addAction(action_disconnect);
        menuJpCloud->addAction(action_exit);
        menusettings->addAction(actionopensettings);
        RefreshDropdown->addAction(action_refresh);

        retranslateUi(MainWindow);

        QMetaObject::connectSlotsByName(MainWindow);
    } // setupUi

    void retranslateUi(QMainWindow *MainWindow)
    {
        MainWindow->setWindowTitle(QCoreApplication::translate("MainWindow", "MainWindow", nullptr));
        actionconnect->setText(QCoreApplication::translate("MainWindow", "connect", nullptr));
        action_disconnect->setText(QCoreApplication::translate("MainWindow", "disconnect", nullptr));
        action_exit->setText(QCoreApplication::translate("MainWindow", "exit", nullptr));
        actionopensettings->setText(QCoreApplication::translate("MainWindow", "opensettings", nullptr));
        actionRefresh->setText(QCoreApplication::translate("MainWindow", "wd", nullptr));
        action_refresh->setText(QCoreApplication::translate("MainWindow", "Refresh", nullptr));
        UploadButton->setText(QCoreApplication::translate("MainWindow", "Upload", nullptr));
        pathInput->setPlaceholderText(QCoreApplication::translate("MainWindow", "path", nullptr));
        SelectFile->setText(QString());
        RefreshButton->setText(QCoreApplication::translate("MainWindow", "Refresh", nullptr));
        currentServerpathInput->setPlaceholderText(QCoreApplication::translate("MainWindow", "cloud path", nullptr));
        backpathButton->setText(QString());
        menuJpCloud->setTitle(QCoreApplication::translate("MainWindow", "JpCloud", nullptr));
        menusettings->setTitle(QCoreApplication::translate("MainWindow", "settings", nullptr));
        RefreshDropdown->setTitle(QCoreApplication::translate("MainWindow", "Refresh", nullptr));
    } // retranslateUi

};

namespace Ui {
    class CloudWindow: public Ui_CloudWindow {};
} // namespace Ui

QT_END_NAMESPACE

#endif // CLOUDWINDOWQUZHQS_H
