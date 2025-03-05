cmake_minimum_required(VERSION 3.30)
project(JPCloudFrontend)

set(CMAKE_CXX_STANDARD 20)
set(CMAKE_AUTOMOC ON)
set(CMAKE_AUTORCC ON)
set(CMAKE_AUTOUIC ON)



find_package(Qt6 COMPONENTS
        Core
        Gui
        Widgets
        REQUIRED)

qt_wrap_ui(UI_FILES mainwindow.ui
        cloudwindow.ui
)
add_executable(JPCloudFrontend main.cpp

        ui_mainwindow.h
        ui_cloudwindow.h
        httplib.h
        httpRequestHandler.cpp
        DataHolder.cpp
        httpRequestHandler.h
        DataHolder.h
        FrontendSendFunctions.h
        datatypes.h
        resources.qrc
        DataHolder.cpp
)
set(ASSETS_SOURCE_DIR ${CMAKE_SOURCE_DIR}/assets)
set(ASSETS_TARGET_DIR ${CMAKE_BINARY_DIR}/assets)





# Kopiere die Assets in den Build-Ordner
add_custom_command(
        TARGET JPCloudFrontend POST_BUILD
        COMMAND ${CMAKE_COMMAND} -E copy_directory
        ${ASSETS_SOURCE_DIR} ${ASSETS_TARGET_DIR}
)

# Füge den manuellen Include Pfad für nlohmann hinzu
target_include_directories(JPCloudFrontend
        PRIVATE ${CMAKE_CURRENT_SOURCE_DIR}/includes
)

target_link_libraries(JPCloudFrontend
        Qt::Core
        Qt::Gui
        Qt::Widgets
)
# Winsock Lib für httplib
target_link_libraries(JPCloudFrontend ws2_32)


if (WIN32 AND NOT DEFINED CMAKE_TOOLCHAIN_FILE)
    set(DEBUG_SUFFIX)
    if (MSVC AND CMAKE_BUILD_TYPE MATCHES "Debug")
        set(DEBUG_SUFFIX "d")
    endif()
    set(QT_INSTALL_PATH "${CMAKE_PREFIX_PATH}")
    if(NOT EXISTS "${QT_INSTALL_PATH}/bin")
        set(QT_INSTALL_PATH "${QT_INSTALL_PATH}/..")
        if(NOT EXISTS "${QT_INSTALL_PATH}/bin")
            set(QT_INSTALL_PATH "${QT_INSTALL_PATH}/..")
        endif()
    endif()
    if(EXISTS "${QT_INSTALL_PATH}/plugins/platforms/qwindows${DEBUG_SUFFIX}.dll")
        add_custom_command(TARGET ${PROJECT_NAME} POST_BUILD
                COMMAND ${CMAKE_COMMAND} -E make_directory
                "$<TARGET_FILE_DIR:${PROJECT_NAME}>/plugins/platforms/")
        add_custom_command(TARGET ${PROJECT_NAME} POST_BUILD
                COMMAND ${CMAKE_COMMAND} -E copy
                "${QT_INSTALL_PATH}/plugins/platforms/qwindows${DEBUG_SUFFIX}.dll"
                "$<TARGET_FILE_DIR:${PROJECT_NAME}>/plugins/platforms/")
    endif()
    foreach(QT_LIB Core Gui Widgets )
        add_custom_command(TARGET ${PROJECT_NAME} POST_BUILD
                COMMAND ${CMAKE_COMMAND} -E copy
                "${QT_INSTALL_PATH}/bin/Qt6${QT_LIB}${DEBUG_SUFFIX}.dll"
                "$<TARGET_FILE_DIR:${PROJECT_NAME}>")
    endforeach(QT_LIB)
endif()