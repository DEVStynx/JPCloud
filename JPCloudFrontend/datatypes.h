//
// Created by jonas on 23.02.2025.
//

#ifndef DATATYPES_H
#define DATATYPES_H
#include <string>



class FileInformation {
    public:
        const std::string filename;
        const std::string path;
        const long size;
        const bool isDirectory; // Fixed `boolean` to `bool`
        FileInformation(const std::string &filename, const std::string &path, long size, bool is_directory)
            : filename(filename),
              path(path),
              size(size),
              isDirectory(is_directory) {
        }
};

#endif //DATATYPES_H
