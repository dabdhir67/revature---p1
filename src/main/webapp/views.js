document.getElementById("viewEmployees").addEventListener("click", getEmployees);

function getEmployees(){
    const xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:8080/Project_1/employees");
    xhr.onreadystatechange = function() {

        let employeeJson = xhr.responseText;
        let employees = JSON.parse(employeeJson); //turns json into java objects
        console.log(employees);

        let tableBody = document.getElementById("employeeTable");

        for(let employee of employees){
                let tableRow = document.createElement("tr");
                tableRow.innerHTML = `<td>${employee.employeeId}</td><td>${employee.firstName}</td><td>${employee.lastName}</td><td>${employee.email}</td><td>${employee.userRole}</td>`
                tableBody.appendChild(tableRow);          
        }

    }
    xhr.send();   
}
