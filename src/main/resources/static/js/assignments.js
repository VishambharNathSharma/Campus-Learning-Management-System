document.addEventListener("DOMContentLoaded", () => {
    loadAssignments();
});

async function loadAssignments() {

    const studentId = localStorage.getItem("userId");

    if (!studentId) {
        alert("Student not logged in.");
        return;
    }

    try {

        // Get enrolled courses
        const enrollmentResponse = await fetch(
            `${BASE_URL}/enrollments/student/${studentId}`,
            {
                headers: getHeaders()
            }
        );

        if (!enrollmentResponse.ok) {
            throw new Error("Unable to load enrollments.");
        }

        const enrollments = await enrollmentResponse.json();

        const container = document.getElementById("assignmentContainer");
        container.innerHTML = "";

        if (enrollments.length === 0) {

            container.innerHTML = `
                <div class="no-data">
                    <h2>You are not enrolled in any course.</h2>
                </div>
            `;
            return;
        }

        let allAssignments = [];

        // Fetch assignments for every enrolled course
        for (const enrollment of enrollments) {

            const courseId = enrollment.courseId;

            const assignmentResponse = await fetch(
                `${BASE_URL}/assignments/course/${courseId}`,
                {
                    headers: getHeaders()
                }
            );

            if (assignmentResponse.ok) {

                const assignments = await assignmentResponse.json();

                allAssignments.push(...assignments);

            }

        }

        if (allAssignments.length === 0) {

            container.innerHTML = `
                <div class="no-data">
                    <h2>No assignments available.</h2>
                </div>
            `;
            return;
        }

        allAssignments.forEach(assignment => {

            container.innerHTML += `
                <div class="assignment-card">

                    <h2>${assignment.title}</h2>

                    <p>
                        <strong>Course:</strong>
                        ${assignment.courseName}
                    </p>

                    <p>
                        <strong>Due Date:</strong>
                        ${formatDate(assignment.dueDateTime)}
                    </p>

                    <button
                        class="view-btn"
                        onclick="viewQuestionPaper('${assignment.questionPaperFileName}')">

                        📄 View Question Paper

                    </button>

                    <div class="status pending">
                        🟡 Pending
                    </div>

                </div>
            `;

        });

    } catch (error) {

        console.error(error);

        alert(error.message);

    }

}

function formatDate(dateTime) {

    if (!dateTime) return "-";

    return new Date(dateTime).toLocaleString();

}

function viewQuestionPaper(fileName) {

    if (!fileName) {
        alert("Question paper not available.");
        return;
    }

    window.open(`${BASE_URL.replace("/api", "")}/uploads/${fileName}`, "_blank");

}