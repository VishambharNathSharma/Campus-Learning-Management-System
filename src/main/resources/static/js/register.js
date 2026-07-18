const BASE_URL = "http://localhost:8080/api";

document.getElementById("registerForm").addEventListener("submit", async function (e) {

    e.preventDefault();

    const fullName = document.getElementById("username").value.trim();

    const names = fullName.split(" ");

    const firstName = names[0];

    const lastName = names.slice(1).join(" ");

    const user = {
        firstName: firstName,
        lastName: lastName,
        email: document.getElementById("email").value.trim(),
        rollNo: document.getElementById("rollno").value.trim(),
        password: document.getElementById("password").value,
        role: "STUDENT"
    };

    try {

        const response = await fetch(`${BASE_URL}/auth/register`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(user)
        });

        const message = await response.text();

        if (!response.ok) {
            throw new Error(message);
        }

        alert(message);

        document.getElementById("registerForm").reset();

        window.location.href = "login.html";

    } catch (error) {

        alert(error.message);

    }

});