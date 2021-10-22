let reimbursementList2 = "";



// var button = document.getElementById("viewAll");
// button.addEventListener("click", getReimbursements);
window.onload = getReimbursements();

function getReimbursements(){
    
    const xhr = new XMLHttpRequest();
    xhr.open("GET", "/reimbursements");
    xhr.onreadystatechange = function() {

        let reimbursementJson = xhr.responseText;
        let reimbursements = JSON.parse(reimbursementJson); //turns json into java objects
        reimbursementList2=JSON.parse(reimbursementJson);
        // console.log(reimbursements);

        let tableBody = document.getElementById("reimbursementTable");
        // const token = sessionStorage.getItem("token");
        // console.log(token);
        // const tokenArr = token.split(":");


        for(let reimbursement of reimbursements){
            // console.log(reimbursement.completed);
                if(reimbursement.completed !== true){
                    let tableRow = document.createElement("tr");
                    tableRow.innerHTML = `<td>${reimbursement.reimbursement_id}</td><td>${reimbursement.amount}</td><td>${reimbursement.description}</td><td>${reimbursement.employee}</td>`
                    tableBody.appendChild(tableRow); 
                }  
            }
        }
    xhr.send();   
}



document.getElementById("updateButton").addEventListener("click", updateTicket);

function updateTicket(){

    let idInput = document.getElementById("inputReId").value;
    let type = "update";
    console.log(idInput);

    const xhr = new XMLHttpRequest();
    xhr.open("POST", "/reimbursements");
    xhr.onreadystatechange = function() {
        if(xhr.readyState===4){ 
            console.log("resolved reimbursement successfully");
            window.location.reload();
            getReimbursements();
        } else {
            console.log("error resolving ...")
        }
    }

    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    const reqBody = `id=${idInput}&type=${type}`
    console.log(reqBody);
    xhr.send(reqBody);
}