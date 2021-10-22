document.getElementById("viewInfo").addEventListener("click", getEmployees);

function getEmployees(){
    const xhr = new XMLHttpRequest();
    xhr.open("GET", "/employees");
    xhr.onreadystatechange = function() {

        let employeeJson = xhr.responseText;
        let employees = JSON.parse(employeeJson); //turns json into java objects
        // console.log(employees);

        let tableBody = document.getElementById("profileTable");

        const token = sessionStorage.getItem("token");
        // console.log(token);

        const tokenArr = token.split(":");

        for(let employee of employees){
            if(tokenArr[0]==employee.employeeId){
                let tableRow = document.createElement("tr");
                tableRow.innerHTML = `<td>${employee.employeeId}</td><td>${employee.firstName}</td><td>${employee.lastName}</td><td>${employee.email}</td><td>${employee.userRole}</td>`
                tableBody.appendChild(tableRow);     
            }                    
        }

    }
    xhr.send();   
}
