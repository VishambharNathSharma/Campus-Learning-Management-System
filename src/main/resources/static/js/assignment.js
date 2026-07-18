document.addEventListener("DOMContentLoaded", () => {

    loadAssignments();

});

async function loadAssignments() {

    try {

        const teacherId = localStorage.getItem("userId");

        const response = await fetch(

            `${BASE_URL}/assignments/teacher/${teacherId}`,

            {

                method: "GET",

                headers: getHeaders()

            }

        );

        if (!response.ok) {

            throw new Error("Unable to load assignments");

        }

        const assignments = await response.json();

        const tbody = document.getElementById("assignmentTableBody");

        tbody.innerHTML = "";

        assignments.forEach((assignment, index) => {

            tbody.innerHTML += `

            <tr>

                <td>${index + 1}</td>

                <td>${assignment.courseName}</td>

                <td>${assignment.title}</td>

                <td>${assignment.maximumMarks}</td>

                <td>${formatSubmissionType(assignment.submissionType)}</td>

                <td>${formatDate(assignment.dueDateTime)}</td>

                <td>

                    <button onclick="viewSubmissions(${assignment.id})">

                        View

                    </button>

                    <button onclick="deleteAssignment(${assignment.id})">

                        Delete

                    </button>

                </td>

            </tr>

            `;

        });

    }

    catch (error) {

        console.error(error);

        alert("Unable to load assignments.");

    }

}

function formatDate(dateTime) {

    if (!dateTime) return "";

    return new Date(dateTime).toLocaleDateString();

}

function formatSubmissionType(type) {

    if (type === "FILE_UPLOAD")
        return "File Upload";

    if (type === "TEXT_ENTRY")
        return "Text Entry";

    return type;

}

function viewSubmissions(assignmentId) {

      localStorage.setItem("assignmentId", assignmentId);

        document.getElementById("assignmentListSection").style.display = "none";

        document.getElementById("submissionsSection").style.display = "block";

}

async function deleteAssignment(id) {

    if (!confirm("Delete this assignment?")) {

        return;

    }

    try {

        const response = await fetch(

            `${BASE_URL}/assignments/${id}`,

            {

                method: "DELETE",

                headers: getHeaders()

            }

        );

        if (!response.ok) {

            throw new Error();

        }

        alert("Assignment deleted successfully.");

        loadAssignments();

    }

    catch (error) {

        console.error(error);

        alert("Unable to delete assignment.");

    }

}