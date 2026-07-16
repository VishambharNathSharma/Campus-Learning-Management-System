async function login(email,password,expectedRole){
try{
const response=await fetch(`${BASE_URL}/auth/login`,{
method:"POST",
headers:{
    "Content-Type":"application/json"
},
body: JSON.stringify({
email: email,
password: password
})
});
 if (data.role !== expectedRole) {
        alert("Please use the correct login section.");
        return;
    }
if(!response.ok){
throw new Error("Invalid email or password");
}
const data= await response.json();
localStorage.setItem("token", data.token);
localStorage.setItem("role", data.role);
localStorage.setItem("userId", data.id);
 if(data.role=="TEACHER"){
 window.location.href=TeacherHome.html;
 }else{
 window.location.href=StudentHome.html;
 }
}catch(error){
alert(error.message);

}

document.getElementById("studentForm").addEventListener("submit", function(e){
    e.preventDefault();
    login(
    document.getElementById("studentEmail").value;
    document.getElementById("studentPassword").value,
    "STUDENT"
    );
});
document.getElementById("teacherForm").addEventListener("submit", function(e){
    e.preventDefault();
    login(
    document.getElementById("teacherEmail").value;
    document.getElementById("teacherPassword").value,
    "STUDENT"
    );
});
}