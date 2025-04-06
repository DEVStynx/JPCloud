function gettoken() {
    const urlParams = new URLSearchParams(window.location.search);
    const token = urlParams.get('token');
    return token;
}

function upload() {
    var fileInput = document.getElementById("file-upload"); // Korrigierte ID
    if (!fileInput || fileInput.files.length === 0) {
        alert("Please select a file to upload.");
        return;
    }


    const files = fileInput.files;
    const token = gettoken();

    for (let i = 0; i < files.length; i++) {
        uploadfile(files[i], token);
    }

}
function getpath() {
    const urlParams = new URLSearchParams(window.location.search);
    const path = urlParams.get('path');
    return path;
}

function renameFile(originalFile, newName) {
    return new File([originalFile], newName, {
        type: originalFile.type,
        lastModified: originalFile.lastModified,
    });
}

async function uploadfile(file, token) {
    const path = getpath(); // Hole den aktuellen Pfad
    const newFileName = `${path}/${file.name}`; // Erstelle den neuen Dateinamen
    const renamedFile = renameFile(file, newFileName); // Erstelle eine neue Datei mit dem neuen Namen

    const formData = new FormData();
    formData.append("file", renamedFile); // Füge die umbenannte Datei hinzu
    formData.append("token", token);

    try {
        const response = await fetch("/upload", {
            method: "POST",
            body: formData,
        });

        if (response.ok) {
            alert(`File "${file.name}" uploaded successfully to "${path}".`);
            window.location.reload();
        } else {
            alert(`Failed to upload file "${file.name}" to "${path}".`);
        }
    } catch (error) {
        console.error("Error uploading file:", error);
        alert(`An error occurred while uploading file "${file.name}" to "${path}".`);
    }
}