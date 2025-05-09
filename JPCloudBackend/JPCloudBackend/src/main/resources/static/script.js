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
        document.getElementById("overlay").style.display = "block";
    } else {
        document.getElementById("overlay").style.display = "none";
    }
    overlay = on;

}

async function createFolder(name) {
    let token = gettoken();
    let path = getpath();

    const formData = new FormData();
    formData.append("path", path);
    formData.append("token", token);
    formData.append("name", name);

    try {
        const response = await fetch("/file/createfolder", {
            method: "GET",
            body: formData,
        });

        if (response.ok) {
            alert(`Folder "${name}" created successfully.`);
            window.location.reload();
        } else {
            alert("Failed to create folder.");
        }
    } catch (error) {
        console.error("Error creating folder:", error);
        alert("An error occurred while creating the folder.");
    } finally {
        showOverlay(false);
    }
}
