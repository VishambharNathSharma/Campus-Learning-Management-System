document.addEventListener("DOMContentLoaded", () => {
console.log("login.js loaded");
    const studentForm = document.getElementById("studentForm");

    if (studentForm) {
        studentForm.addEventListener("submit", function (e) {
            e.preventDefault();
    console.log("student form submitted");
            login(
                document.getElementById("studentEmail").value.trim(),
                document.getElementById("studentPassword").value,
                "STUDENT"
            );
        });
    }

    const teacherForm = document.getElementById("teacherForm");

    if (teacherForm) {
        teacherForm.addEventListener("submit", function (e) {
            e.preventDefault();
         console.log("Teacher form submitted");
            login(
                document.getElementById("teacherEmail").value.trim(),
                document.getElementById("teacherPassword").value,
                "TEACHER"
            );
        });
    }

});

async function login(email, password, expectedRole) {
 console.log("login() called");
    try {
console.log("Sending request...");
        const response = await fetch(`${BASE_URL}/auth/login`, {

            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify({
                email: email,
                password: password
            })

        });

        if (!response.ok) {

            throw new Error("Invalid email or password");

        }

        const data = await response.json();
    console.log("Login Response:",data);
        console.log(data);

        if (data.role !== expectedRole) {

            alert("Please use the correct login section.");

            return;

        }

        localStorage.setItem("token", data.token);
        localStorage.setItem("userId", data.id);
        localStorage.setItem("role", data.role);

        if (data.role === "TEACHER") {

            window.location.href = "TeacherHome.html";

        } else {

            window.location.href = "StudentHome.html";

        }

    } catch (error) {

        console.error(error);

        alert(error.message);

    }

}