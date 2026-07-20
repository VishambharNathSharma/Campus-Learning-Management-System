document.addEventListener("DOMContentLoaded", () => {

    loadStudentAssignments();

    document
        .getElementById("assignmentsubmitbutton")
        .addEventListener("click", submitAssignment);

});

// ===========================
// Load Assignments
// ===========================

async function loadStudentAssignments() {

    const studentId = localStorage.getItem("userId");

    if (!studentId) {
        alert("Student not logged in");
        return;
    }

    try {

        // Get enrolled courses
        const enrollmentResponse = await fetch(
            `${BASE_URL}/enrollments/student/${studentId}`,
            {
                method: "GET",
                headers: getHeaders()
            }
        );

        if (!enrollmentResponse.ok) {
            throw new Error("Unable to load enrollments");
        }

        const enrollments = await enrollmentResponse.json();

        const select = document.getElementById("assignmentcourse");

        select.innerHTML = "";

        if (enrollments.length === 0) {

            const option = document.createElement("option");
            option.textContent = "No enrolled courses";
            option.disabled = true;
            option.selected = true;

            select.appendChild(option);

            return;
        }

        let totalAssignments = 0;

        for (const enrollment of enrollments) {

            const courseId = enrollment.courseId;

            const assignmentResponse = await fetch(
                `${BASE_URL}/assignments/course/${courseId}`,
                {
                    method: "GET",
                    headers: getHeaders()
                }
            );

            if (!assignmentResponse.ok) {
                continue;
            }

            const assignments = await assignmentResponse.json();

            assignments.forEach(assignment => {

                const option = document.createElement("option");

                option.value = assignment.id;

                option.textContent =
                    `${assignment.courseName} - ${assignment.title}`;

                select.appendChild(option);

                totalAssignments++;

            });

        }

        if (totalAssignments === 0) {

            const option = document.createElement("option");

            option.textContent = "No assignments available";

            option.disabled = true;
            option.selected = true;

            select.appendChild(option);

        }

    }
    catch (error) {

        console.error(error);

        alert("Failed to load assignments");

    }

}

// ===========================
// Submit Assignment
// ===========================

async function submitAssignment(event) {

    event.preventDefault();

    const assignmentId =
        document.getElementById("assignmentcourse").value;

    const file =
        document.getElementById("assignfile").files[0];

    const notes =
        document.getElementById("notes").value;

    const studentId =
        localStorage.getItem("userId");

    if (!assignmentId || assignmentId === "No assignments available") {

        alert("Please select an assignment");

        return;

    }

    if (!file) {

        alert("Please attach your assignment PDF");

        return;

    }

    if (file.type !== "application/pdf") {

        alert("Only PDF files are allowed");

        return;

    }

    const formData = new FormData();

    formData.append("assignmentId", assignmentId);

    formData.append("studentId", studentId);

    formData.append("notes", notes);

    formData.append("file", file);

    try {

        const response = await fetch(
            `${BASE_URL}/submissions`,
            {
                method: "POST",

                headers: {
                    "Authorization": "Bearer " + getToken()
                },

                body: formData
            }
        );

        if (response.ok) {

            alert("Assignment submitted successfully");

            document.getElementById("assignfile").value = "";

            document.getElementById("notes").value = "";

        } else {

            const message = await response.text();

            alert(message);

        }

    }
    catch (error) {

        console.error(error);

        alert("Submission failed");

    }

}