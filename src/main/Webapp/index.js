document.getElementById("app").innerHTML = `
<h1> User Query & File Upload Example </h1>
<div>
<input type="file" accept=".csv, .xls, .xlsx" id="fileInput" />
</div>
`;

const fileInput = document.querySelector("#fileInput");

const uploadFile = file => {
    console.log("Uploading file...");
    const API_ENDPOINT = "/swe/api/upload";
    const request = new XMLHttpRequest();
    const formData = new FormData();

    request.open("POST", API_ENDPOINT, true);
    request.onreadystatechange = () => {
        if (request.readyState === 4 && request.status === 200) {
            console.log(request.responseText);
        }
    };
    formData.append("file", file);
    request.send(formData);
};

fileInput.addEventListener("change", event => {
    const files = event.target.files;
    uploadFile(files[0]);
});