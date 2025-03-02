//
// Created by jonas on 22.02.2025.
//

#ifndef HTTPREQUESTHANDLER_H
#define HTTPREQUESTHANDLER_H
#include <iostream>
#include "httplib.h"

class httpRequestHandler {
public:
    static auto sendGet(std::string base_url,httplib::Params params, httplib::Headers headers) {
        httplib::Client cli(base_url);
        return cli.Get(base_url, params,headers);
    }
    static auto sendGet(std::string base_url,std::string newurl,httplib::Params params) {
        httplib::Headers headers;
        httplib::Client cli(base_url);
        return cli.Get(newurl, params,headers);
    }
    static auto sendGet(std::string base_url,std::string endpoint,httplib::Headers headers) {
        httplib::Params params;
        httplib::Client cli(base_url);
        return cli.Get(endpoint, params,headers);
    }
    static auto sendGet(std::string base_url, std::string newurl) {
        httplib::Params params;
        httplib::Headers headers;
        httplib::Client cli(base_url);
        return cli.Get(newurl);
    }

    static void sendFile(std::string base_url,std::string exact_url, std::string file_path) {
        httplib::Client cli(base_url);


        std::ifstream file(file_path, std::ios::binary);
        if (!file) {
            std::cerr << "Fehler beim Öffnen der Datei!" << std::endl;

        }


        std::string file_content((std::istreambuf_iterator<char>(file)), std::istreambuf_iterator<char>());


        httplib::MultipartFormDataItems items = {
            {"file", file_content, getRawName(file_path), "application/octet-stream"}
        };

        auto res = cli.Post("/upload", items);

        if (res && res->status == 200) {
            std::cout << "Upload erfolgreich!" << std::endl;
        } else {
            std::cerr << "Fehler beim Upload! Status: " << (res ? std::to_string(res->status) : "keine Antwort") << std::endl;
        }

    }

    static std::string getRawName(std::string path) {
        return path.substr(path.find_last_of("/\\") + 1);
    }
};



#endif //HTTPREQUESTHANDLER_H
