document.addEventListener("DOMContentLoaded", () => {
    loadEnrollments();
});

async function loadEnrollments() {

    try {

        const teacherId = localStorage.getItem("userId");

        const response = await fetch(
            `${BASE_URL}/enrollments/teacher/${teacherId}`,
            {
                method: "GET",
                headers: getHeaders()
            }
        );

        if (!response.ok) {
            throw new Error("Unable to load enrollments");
        }

        const enrollments = await response.json();

        const tbody = document.getElementById("enrollmentTableBody");

        tbody.innerHTML = "";

        if (enrollments.length === 0) {

            tbody.innerHTML = `
                <tr>
                    <td colspan="5">No enrollments found.</td>
                </tr>
            `;

            return;
        }

        enrollments.forEach(enrollment => {

            tbody.innerHTML += `
                <tr>
                    <td>${enrollment.studentId}</td>
                    <td>${enrollment.studentName}</td>
                    <td>${enrollment.courseId}</td>
                    <td>${enrollment.courseTitle}</td>
                    <td>${formatDate(enrollment.enrollmentDate)}</td>
                </tr>
            `;

        });

    }
    catch (error) {

        console.error(error);
        alert("Unable to load enrollments.");

    }

}

function formatDate(date) {

    if (!date) return "-";

    return new Date(date).toLocaleDateString("en-IN");

}