var button = document.getElementById("loginButton");
    button.addEventListener("click", getHomePage);

function getHomePage(){
    
    let email = document.getElementById("inputEmail").value;
    let password = document.getElementById("inputPassword").value;
    console.log(`email: ${email}, password ${password}`); 
    


    const xhr = new XMLHttpRequest();
    xhr.open("POST", "/Project_1-1.0-SNAPSHOT/login");
    xhr.onreadystatechange = function(){
        if(xhr.readyState===4){
            // look at status code (either 401 or 200)
            // if the status code is 401 - indicate to the user that their credentials are invalid
             if (xhr.status===200){
                // if the status code is 200 - get auth token from response and store it in browser, navigate to another page
                const token = xhr.getResponseHeader("Authorization");
                console.log(token); //"2:ADMIN"
                sessionStorage.setItem("token", token);
                const tokenArr = token.split(":");
                if(tokenArr[1]=="Manager"){
                    window.location.href="/manager.html";
                } else {
                    window.location.href="/employee.html";
                }
             }
        }
    }   
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    // send request, with the username and password in the request body
    const requestBody = `email=${email}&pass_word=${password}`;
    xhr.send(requestBody); // ready state changes 1->2, 2->3, 3-4
}
