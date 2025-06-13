function selectFile() {
    var input = document.createElement('input');
    input.type = 'file';

    input.onchange = e => {
        var file = e.target.files[0];
        if (!file) {
            return;
        }

        var formData = new FormData();
        formData.append('file', file);

        let currentPath = "";
        const params = new URLSearchParams(window.location.search);
        if (params.has('path')) {
            currentPath = params.get('path');
        }
        formData.append('path', currentPath);

        // CSRF-Token auslesen
        const csrfToken = document.querySelector("meta[name='_csrf']").getAttribute("content");
        const csrfHeaderName = document.querySelector("meta[name='_csrf_header']").getAttribute("content");

        const headers = {};
        if (csrfToken && csrfHeaderName) {
            headers[csrfHeaderName] = csrfToken;
        }


        fetch('/file/upload', {
            method: 'POST',
            headers: headers, // CSRF-Token hier einfügen
            body: formData
        })
            .then(response => {
                if (response.ok) {

                    window.location.reload();
                } else {
                    response.text().then(text => {
                        console.error('Fehler beim Hochladen der Datei:', text);
                        alert('Fehler beim Hochladen: ' + text);
                    });
                }
            })
            .catch(error => {
                console.error('Netzwerkfehler oder anderer Fehler:', error);
                alert('Netzwerkfehler oder anderer Fehler beim Hochladen.');
            });
    }

    input.click();
}
function downloadFile(element) {
    const filePath = element.getAttribute('data-path');
    if (filePath) {
        window.location.href = '/file/download?path=' + encodeURIComponent(filePath);
    }
}
function showDirOverlay(on) {
    if (on == true) {
        document.getElementById("overlay").style.display = "flex";
        document.getElementById("overlay").classList.add("active");
    } else {
        document.getElementById("overlay").style.display = "none";
        document.getElementById("overlay").classList.remove("active");
    }
    overlay = on;
}
function createDirectory(name) {
    showDirOverlay(false);
}
function toggleCreateButtons() {
    const createSection = document.querySelector('.create-new-section');
    createSection.classList.toggle('active');
}