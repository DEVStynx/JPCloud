let overlay = false;
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

    filediv.onclick = function () {
        if (file.dir == true) {
            path = file.path.replace('\\', '/');
            if (window.location.href.includes('main.html')) {
                window.location.href = `/main.html?token=${gettoken()}&path=${path}`;

            } else {
                window.location.href = `/file-view.html?token=${gettoken()}&path=${path}`;
            }
        } else {
            download(file);
        }
    };

    var dropdown = document.createElement('div');
    dropdown.className = "dropdown";

    var expandbtn = document.createElement('button');
    expandbtn.className = "btn btn-primary dropdown-toggle";
    expandbtn.type = "button";
    expandbtn.textContent = "⇓";
    expandbtn.onclick = function (event) {
        event.stopPropagation(); // Prevent click from propagating to parent elements
        const isActive = dropdownmenu.style.display === "block";
        closeAllDropdowns(); // Close other dropdowns
        dropdownmenu.style.display = isActive ? "none" : "block"; // Toggle visibility
    };

    dropdown.appendChild(expandbtn);

    var dropdownmenu = document.createElement('ul');
    dropdownmenu.className = "dropdown-menu";
    dropdownmenu.style.display = "none"; // Initially hidden

    var open = document.createElement('li');
    open.textContent = "Open";
    open.className = "drop-file-option";

    open.onclick = function (event) {
        event.stopPropagation(); // Prevent click from propagating to parent elements
        console.log(`Open clicked for file: ${file.name}`);
        // Add any additional logic for "Open" here
        window.location.href = `/fileopener.html?token=${gettoken()}&filePath=${getpath() + "/" + file.name}`
    };
    dropdownmenu.appendChild(open);

    dropdown.appendChild(dropdownmenu);
    filediv.appendChild(dropdown);

    fileList.appendChild(filediv);
}

// Helper function to close all dropdowns
function closeAllDropdowns() {
    const dropdowns = document.querySelectorAll('.dropdown-menu');
    dropdowns.forEach(menu => {
        menu.style.display = "none";
    });
}

// Close dropdowns when clicking outside
document.addEventListener('click', closeAllDropdowns);
function download(file) {
    console.log("download()");
    path = file.path.replace('\\','/');

    window.location.href = `/download?path=${path}&token=${gettoken()}`;
}
function getpath() {
    const urlParams = new URLSearchParams(window.location.search);
    const path = urlParams.get('path') == null ? "/" : urlParams.get('path');
    return path;
}
function showOverlay(on) {
    if (on == true) {
        document.getElementById("overlay").style.display = "flex";
        document.getElementById("overlay").classList.add("active");
    } else {
        document.getElementById("overlay").style.display = "none";
        document.getElementById("overlay").classList.remove("active");
    }
    overlay = on;
}

async function createFolder(folderName) {
    try {
        const token = gettoken();
        const currentPath = getpath();

        const response = await fetch(`/files/createFolder?token=${token}&path=${encodeURIComponent(currentPath)}&name=${encodeURIComponent(folderName)}`, {
            method: 'POST'
        });

        if (response.ok) {
            showOverlay(false);
            window.location.reload();
        } else {
            alert(`Fehler beim Erstellen des Ordners: ${response.statusText}`);
        }
    } catch (error) {
        alert(`Fehler beim Erstellen des Ordners: ${error.message}`);
    }
}