document.addEventListener("DOMContentLoaded", () => {
    loadDashboard();
});

async function loadDashboard() {

    try {

        const response = await fetch(
            `${BASE_URL}/student/dashboard`,
            {
                method: "GET",
                headers: getHeaders()
            }
        );

        if (!response.ok) {
            throw new Error("Unable to load dashboard");
        }

        const data = await response.json();

        // Welcome
        document.getElementById("welcomeName").textContent =
            data.studentName;

        // Email
        document.getElementById("email").textContent =
            data.email;

        // Roll Number
        document.getElementById("rollNo").textContent =
            data.rollno;

        // Total Courses (Enrollment Card)
        document.getElementById("totalCourses").textContent =
            data.totalCourses;

        // Assignment Card
        document.getElementById("assignmentTotal").textContent =
            data.totalAssignments;

        // Total Exams
        document.getElementById("totalExams").textContent =
            data.totalExams;

        // Attendance
        document.getElementById("presentAttendance").textContent =
            data.presentAttendance;

        document.getElementById("totalAttendance").textContent =
            data.totalAttendance + "%";

        // Bottom Big Card
        document.getElementById("dashboardTotalExams").textContent =
            data.totalExams;

        document.getElementById("dashboardTotalCourses").textContent =
            data.totalCourses;

    }
    catch (error) {

        console.error(error);

        alert("Unable to load dashboard.");

    }
}