document.addEventListener("DOMContentLoaded", () => {
    loadSubmissions();
});

async function loadSubmissions() {

    try {

        const response = await fetch(
            `${BASE_URL}/submissions/submissions`,
            {
                method: "GET",
                headers: getHeaders()
            }
        );

        if (!response.ok) {
            throw new Error("Failed to load submissions");
        }

        const submissions = await response.json();

        renderTable(submissions);

    } catch (error) {
        console.error(error);
        alert("Unable to load submissions");
    }

}

function renderTable(submissions) {

    const tbody = document.getElementById("submissionTableBody");

    tbody.innerHTML = "";

    submissions.forEach((submission, index) => {

        tbody.innerHTML += `

        <tr>

            <td>${index + 1}</td>

            <td>${submission.studentName}</td>

            <td>${submission.status}</td>

            <td>${submission.assignmentName}</td>

            <td>${formatDate(submission.submittedAt)}</td>

            <td>

                <button onclick="viewSubmission('${submission.submissionFile}')">

                    View

                </button>

            </td>

        </tr>

        `;

    });

}

function viewSubmission(fileUrl) {

    window.open(`${BASE_URL}/${fileUrl}`, "_blank");

}

function formatDate(dateString) {

    if (!dateString) return "";

    const date = new Date(dateString);

    return date.toLocaleDateString();

}