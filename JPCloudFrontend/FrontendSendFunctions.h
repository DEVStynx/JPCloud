//
// Created by jonas on 23.02.2025.
//
#include <iostream>
#include "httpRequestHandler.h"
#include <QJsonArray>
#include <QJsonObject>
#include <QJsonDocument>
#include <vector>
#include <filesystem>
#include <QFileDialog>
#include <QMainWindow>

#include "datatypes.h"
#include <vector>
#ifndef FRONTENDSENDFUNCTIONS_H
#define FRONTENDSENDFUNCTIONS_H

#endif //FRONTENDSENDFUNCTIONS_H

namespace FrontendSendFunctions {
    std::string getExtension(const std::string& filename);
    std::string getFileName(const std::string& path);
    std::vector<std::string> ImageFileExtensions() {
        return {
            ".jpg", ".jpeg", ".png", ".bmp", ".gif", ".tiff", ".tif", ".ico", ".svg"
        };
    }


    void sendFile(std::string path) {
        std::cout << "Uploading File: " << path << std::endl;
        std::string formString = path;
        httpRequestHandler::sendFile(
        "http://localhost:8080",
        "/upload",
        path
    );
    }

    auto downloadFile(std::string path, QMainWindow* mainWindow) {
        std::stringstream input;
        input << httpRequestHandler::sendGet( // sendGet gibt möglicherweise die falsche Antwort zurück
                "http://localhost:8080",
                "/download?path=" + path)->body;
        std::string option = "File (*"+FrontendSendFunctions::getExtension(path)+");;All Files (*.*)";
        std::string ppath = QFileDialog::getSaveFileName(mainWindow, "Save File", "", option.c_str()).toStdString();
        std::ofstream file(ppath, std::ios::binary);

        if (!file.is_open()) {
            std::cout << "File is open";
            return;
        }
        file << input.rdbuf();
        file.close();
    }

    void registerUser(std::string username, std::string password) {
        std::cout << "Registering User: " << username << std::endl;
        std::cout << httpRequestHandler::sendGet(
            "http://localhost:8080",
            "/users/register2/" + username + "/" + password + "/true/true");
    }

    std::vector<FileInformation> getFiles(std::string path) {

        httplib::Params params;
        std::string endpoint = "/files/list?path=" + path;

        auto result = httpRequestHandler::sendGet("http://localhost:8080",endpoint)->body;

        QByteArray ba = result.c_str();
        QJsonDocument jsondoc = QJsonDocument::fromJson(ba);

        if (!jsondoc.isArray()) {
            std::cout << "No Array";
            return std::vector<FileInformation>{};
        }

        QJsonArray array = jsondoc.array();
        std::vector<FileInformation> files;

        for (const QJsonValue &value : array) {
            QJsonObject obj = value.toObject();

            std::string filename = obj.value("name").toString().toStdString();
            std::string path = obj.value("path").toString().toStdString();
            //std::cout << filename << std::endl;
            long size = obj.value("size").toInt();
            bool isDirectory = obj.value("dir").toBool();
            FileInformation fileInformation = FileInformation(filename, path, size, isDirectory);
            files.push_back(fileInformation);
        }

        return files;


    }
    std::string getExtension(const std::string& filename) {
        std::filesystem::path pathObj(filename);
        return pathObj.extension().string();
    }
    std::string getFileName(const std::string& path) {
        std::filesystem::path pathObj(path);
        return pathObj.filename().string();
    }
    std::string GetDefaultIconPath(const FileInformation file) {
        if (file.isDirectory) {
            return "assets/folderIcon.png";
        }
        std::string extension = getExtension(file.filename);
        for (const std::string& pextension : ImageFileExtensions()) {
            if (extension == pextension) {
                return "assets/ImageIcon.png";
            } else {continue;}
        }
        return "assets/txtfileIcon.png";


    }




}
