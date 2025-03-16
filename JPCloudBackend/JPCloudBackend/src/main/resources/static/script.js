async function loadFiles() {
    console.log("loadFiles()");
    const urlParams = new URLSearchParams(window.location.search);
    const token = urlParams.get('token');
    const path = urlParams.get('path') || '';
    document.getElementById('path-input').value = path;

    const response = await fetch(`/files/list?path=${path}&token=${token}`);
    const files = await response.json();

    const fileList = document.getElementById('file-list');


    if (files) {
        files.forEach(file => {
            console.log(file);
            

            addFileToSite(file);
            
        });
    } else {
        fileList.textContent = 'No files found or access denied.';
    }
}

function gettoken() {
    const urlParams = new URLSearchParams(window.location.search);
    const token = urlParams.get('token');
    return token;
}

function setpath() {
    path = document.getElementById('path-input').value;
    window.location.href = `/main.html?token=${gettoken()}&path=${path}/`;
}

function addFileToSite(file) {
    
    const fileList = document.getElementById('file-grid');
    var filediv = document.createElement('div');
    filediv.className = "file";
    
    var icondiv = document.createElement('div');
    icondiv.className = "icon";
    if (file.dir == true) {
        icondiv.innerHTML = '📁';
    } else {
        icondiv.innerHTML = '📄';
    }
    filediv.appendChild(icondiv);
    var namediv = document.createElement('div');
    namediv.className = "name";
    namediv.textContent = file.name;
    filediv.appendChild(namediv);
    filediv.onclick = function() {
        if (file.dir == true) {
            path = file.path.replace('\\','/');
            window.location.href = `/main.html?token=${gettoken()}&path=${path}/`;
        } else {
            download(file);
    
        }
    }
    fileList.appendChild(filediv);
}
function download(file) {
    console.log("download()");
    path = file.path.replace('\\','/');

    window.location.href = `/download?path=${path}&token=${gettoken()}`;
}