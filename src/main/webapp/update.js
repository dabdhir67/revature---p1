document.getElementById("updateButton").addEventListener("click", updateTicket);

function updateTicket(){

    // let idInput = document.getElementById("inputReId").value;
    // let reimbursement 
    const xhr = new XMLHttpRequest();
    xhr.open("PUT", "http://localhost:8080/Project_1/reimbursements");
    xhr.onreadystatechange = function() {
        if(xhr.readyState===4){
            console.log("resolved reimbursement successfully");
        } else {
            console.log("error resolving ...")
        }
    }

    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send()
}