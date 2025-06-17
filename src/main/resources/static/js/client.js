function getCurrentBranchId() {
    const params = new URLSearchParams(window.location.search);
    return params.has('branch') ? params.get('branch') : '-1';
}
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

        // Branch-ID hinzufügen
        formData.append('branch', getCurrentBranchId());

        // CSRF-Token auslesen
        const csrfToken = document.querySelector("meta[name='_csrf']").getAttribute("content");
        const csrfHeaderName = document.querySelector("meta[name='_csrf_header']").getAttribute("content");

        const headers = {};
        if (csrfToken && csrfHeaderName) {
            headers[csrfHeaderName] = csrfToken;
        }

        fetch('/file/upload', {
            method: 'POST',
            headers: headers,
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
        window.location.href = '/file/download?path=' + encodeURIComponent(filePath) + '&branch=' + getCurrentBranchId();
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
    var formData = new FormData();
    let currentPath = "";
    const params = new URLSearchParams(window.location.search);
    if (params.has('path')) {
        currentPath = params.get('path');
    }
    formData.append("path",currentPath);
    formData.append("branch",getCurrentBranchId());
    formData.append("name",name);

    const csrfToken = document.querySelector("meta[name='_csrf']").getAttribute("content");
    const csrfHeaderName = document.querySelector("meta[name='_csrf_header']").getAttribute("content");

    const headers = {};
    if (csrfToken && csrfHeaderName) {
        headers[csrfHeaderName] = csrfToken;
    }

    fetch('/file/createfolder', {
        method: 'POST',
        headers: headers, // CSRF-Token hier einfügen
        body: formData
    })
        .then(response => {
            if (response.ok) {
                window.location.reload();
            } else {
                response.text().then(text => {
                    console.error('Issue at creating folder', text);
                    alert('Ussue at folder creation: ' + text);
                });
            }
        })
        .catch(error => {
            console.error('Error: ', error);
            alert('Error');
        });

}

function setpath() {
    var input = document.getElementById("path-input");
    var path = input.value;
    document.location.href = '/dashboard?path=' + path;
}

function setBranch(branchId) {
    const params = new URLSearchParams(window.location.search);
    let path = params.has('path') ? params.get('path') : '';
    window.location.href = '/dashboard?path=' + path + '&branch=' + branchId;
}

window.onload = () => {
    var pathInput = document.getElementById("path-input");
    const params = new URLSearchParams(window.location.search);
    if (params.has('path')) {
        pathInput.value = params.get('path');
    }
}
function toggleCreateButtons() {
    const createSection = document.querySelector('.create-new-section');
    createSection.classList.toggle('active');
}