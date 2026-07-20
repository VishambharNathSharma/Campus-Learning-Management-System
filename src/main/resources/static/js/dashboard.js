document.addEventListener("DOMContentLoaded", () => {
    loadStudentDashboard();
});

async function loadStudentDashboard() {

    const studentId = localStorage.getItem("userId");

    if (!studentId) {
        alert("Student not logged in");
        return;
    }

    try {

        const response = await fetch(
            `${BASE_URL}/dashboard/student/${studentId}`,
            {
                method: "GET",
                headers: getHeaders()
            }
        );

        if (!response.ok) {
            throw new Error("Failed to load dashboard");
        }

        const data = await response.json();

        console.log(data);

        // ==========================
        // Student Details
        // ==========================

        document.getElementById("studentName").textContent =
            `Welcome Back, ${data.studentName}!`;

        document.getElementById("rollNo").textContent =
            `(${data.rollno})`;

        // ==========================
        // Dashboard Cards
        // ==========================

        document.getElementById("totalCourses").textContent =
            data.totalCourses;

        document.getElementById("totalAssignments").textContent =
            data.totalAssignments;

        document.getElementById("totalExams").textContent =
            data.totalExams;

        document.getElementById("averagePercentage").textContent =
            data.averagePercentage.toFixed(2) + "%";

        document.getElementById("presentAttendance").textContent =
            "Present : " + data.presentAttendance;

        document.getElementById("absentAttendance").textContent =
            "Absent : " + data.absentAttendance;

        // ==========================
        // Attendance Pie Chart
        // ==========================

        const ctx = document.getElementById("attendanceChart");

        new Chart(ctx, {
            type: "pie",
            data: {
                labels: ["Present", "Absent"],
                datasets: [{
                    data: [
                        data.presentAttendance,
                        data.absentAttendance
                    ],
                    backgroundColor: [
                        "#22c55e",
                        "#ef4444"
                    ]
                }]
            },
            options: {
                responsive: true,
                plugins: {
                    legend: {
                        position: "bottom"
                    }
                }
            }
        });

        // ==========================
        // My Courses
        // ==========================

        const courseList = document.querySelector(".course-list");

        courseList.innerHTML = "";

        if (!data.enrolledCourses || data.enrolledCourses.length === 0) {

            courseList.innerHTML =
                "<p>No courses enrolled.</p>";

        } else {

            data.enrolledCourses.forEach(course => {

                const courseCard = document.createElement("div");

                courseCard.className = "course-item";

                courseCard.innerHTML = `
                    <img src="school.png" alt="Course">

                    <h3>${course.courseName}</h3>

                    <p><strong>Course Code:</strong> ${course.courseCode}</p>

                    <p><strong>Teacher:</strong> ${course.teacherName}</p>

                    <p><strong>Credits:</strong> ${course.courseCredits}</p>
                `;

                courseList.appendChild(courseCard);

            });

        }

    } catch (error) {

        console.error(error);

        alert("Unable to load dashboard.");

    }

}