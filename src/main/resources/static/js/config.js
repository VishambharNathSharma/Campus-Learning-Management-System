const BASE_URL="http://localhost:8080/api";

function getToken(){
    return localStorage.getItem("token");
}

function getHeaders(){
    return{
    "Content-Type":"application/json",
    "Authorization":"Bearer"+getToken
    };
}