window.onload = getReimbursements();



// var button = document.getElementById("viewPending");
// button.addEventListener("click", getReimbursements);
    

function getReimbursements(){
    
    const xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:8080/Project_1/reimbursements");
    xhr.onreadystatechange = function() {

        let reimbursementJson = xhr.responseText;
        let reimbursements = JSON.parse(reimbursementJson); //turns json into java objects
        // console.log(reimbursements);

        let tableBody = document.getElementById("reimbursementTable");
        const token = sessionStorage.getItem("token");
        console.log(token);

        const tokenArr = token.split(":");
        for(let reimbursement of reimbursements){
            if(tokenArr[0]==reimbursement.employee){
                let tableRow = document.createElement("tr");
                tableRow.innerHTML = `<td>${reimbursement.reimbursement_id}</td><td>${reimbursement.amount}</td><td>${reimbursement.description}</td><td>${reimbursement.employee}</td>`
                tableBody.appendChild(tableRow);
            }   
        }

    }
    xhr.send();   
}







document.getElementById("ticketButton").addEventListener("click", createTicket);

function createTicket(){

    let type = "create";

    let amountInput = document.getElementById("inputAmount").value;
    let descInput = document.getElementById("inputDescription").value;
    let e_id = document.getElementById("inputId").value;
    // create an object to represent our new reimbursement
    let reimbursement = `type=${type}&amount=${amountInput}&description=${descInput}&employee_id=${e_id}`;
    
    // configure HTTP request using xhr
    let xhr = new XMLHttpRequest();
    xhr.open("POST","http://localhost:8080/Project_1/reimbursements");
    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4){
            // process response
            if(xhr.status === 200 || xhr.status===201){
                console.log("added in new reimbursement");
                window.location.reload();
                getReimbursements();
            } else {
                console.log("there was an issue creating reimbursement --- status code: "+xhr.status+" "+xhr.statusText)
            }
        }
    }

    
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send(reimbursement); //

}









// window.onload = function(){
//     const xhr = new XMLHttpRequest();
//     xhr.open("GET", "http://localhost:8080/Project_1/employees");
//     xhr.onreadystatechange = function() {

//         let employeeJson = xhr.responseText;
//         let employees = JSON.parse(employeeJson); //turns json into java objects
//         console.log(employees);

//         let tableBody = document.getElementById("profileTable");
//         const token = sessionStorage.getItem("token");
//         console.log(token);

//         const tokenArr = token.split(":");
//         for(let employee of employees){
//             if(tokenArr[0]==employee.employeeId){
//                 let tableRow = document.createElement("tr");
//                 tableRow.innerHTML = `<td>${employee.firstName}</td><td>${employee.lastName}</td><td>${employee.email}</td><td>${employee.userRole}</td><td>${reimbursement.is_completed}</td>`
//                 tableBody.appendChild(tableRow);
//             }   
//         }

//     }
//     xhr.send();   
// }


// var submit = document.getElementById("ticketbutton");
// submit.addEventListener("click", addReimbursement);

// function addReimbursement(){
//     let amountInput = document.getElementById("inputAmount").value;
//     let descInput = document.getElementById("inputDescription").value;
//     let reimbursement = {amount: amountInput, description: descInput}
//     const xhr = new XMLHttpRequest();
//     xhr.open("POST", "http://localhost:8080/Project_1/reimbursements");
//     xhr.onreadystatechange = function() {
//         if(xhr.status === 201){
//             console.log("added in new ticket");
//             let row = document.createElement("tr");
//             let reimbursementId = document.createElement("td");
//             reimbursementId.innerHTML = reimbursement.reimbursement_id;
//             row.appendChild(reimbursementId);
    
//             let amount = document.createElement("td");
//             amount.innerHTML = reimbursement.amount;
//             row.appendChild(amount);
    
//             let description = document.createElement("td");
//             description.innerHTML = reimbursement.description;
//             row.appendChild(description);
    
//             let resolved = document.createElement("td");
//             resolved.innerHTML = reimbursement.is_completed;
//             row.appendChild(resolved);
//             reimbursementTable.appendChild(row);
//             }
//         }
//         xhr.send();
//     }