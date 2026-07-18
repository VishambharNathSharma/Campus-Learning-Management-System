// ================================
// Course Module
// ================================

document.addEventListener("DOMContentLoaded", () => {

    // Redirect if not logged in
    if (!localStorage.getItem("token")) {
        window.location.href = "index.html";
        return;
    }

    // Load course table if present
    if (document.getElementById("courseTableBody")) {
        loadCourses();
    }

    // Register create course form
    const courseForm = document.getElementById("courseForm");

    if (courseForm) {
        courseForm.addEventListener("submit", createCourse);
    }

});

// ===================================
// Load Teacher Courses
// ===================================

async function loadCourses() {

    const teacherId = localStorage.getItem("userId");

    try {

        const response = await fetch(
            `${BASE_URL}/courses/teacher/${teacherId}`,
            {
                headers: getHeaders()
            }
        );

        if (!response.ok) {
            throw new Error("Unable to load courses");
        }

        const courses = await response.json();

        const tableBody = document.getElementById("courseTableBody");

        tableBody.innerHTML = "";

        if (courses.length === 0) {

            tableBody.innerHTML = `
                <tr>
                    <td colspan="7" style="text-align:center">
                        No courses found.
                    </td>
                </tr>
            `;

            return;
        }

        courses.forEach((course, index) => {

            tableBody.innerHTML += `
                <tr>

                    <td>${index + 1}</td>

                    <td>${course.courseName}</td>

                    <td>${course.courseCode}</td>

                    <td>${course.courseCredits}</td>

                    <td>${course.startDate}</td>

                    <td>${course.endDate}</td>

                    <td>
                        <button
                            class="delete-btn"
                            onclick="deleteCourse(${course.id})">
                            Delete
                        </button>
                    </td>

                </tr>
            `;

        });

    } catch (error) {

        console.error(error);

        alert("Unable to load courses.");

    }

}

// ===================================
// Delete Course
// ===================================

async function deleteCourse(courseId) {

    if (!confirm("Delete this course?")) {
        return;
    }

    try {

        const response = await fetch(
            `${BASE_URL}/courses/${courseId}`,
            {
                method: "DELETE",
                headers: getHeaders()
            }
        );

        if (!response.ok) {
            throw new Error("Delete failed");
        }

        alert("Course deleted successfully.");

        loadCourses();

    } catch (error) {

        console.error(error);

        alert("Unable to delete course.");

    }

}

// ===================================
// Create Course
// ===================================

async function createCourse(event) {

    event.preventDefault();

    const teacherId = localStorage.getItem("userId");

    const objectives = [];

    document.querySelectorAll(".objective-input").forEach(input => {

        if (input.value.trim() !== "") {
            objectives.push(input.value.trim());
        }

    });

    const course = {

        courseName: document.getElementById("courseTitle").value,

        courseCode: document.getElementById("courseCode").value,

        description: document.getElementById("courseDescription").value,

        courseCredits: parseInt(
            document.getElementById("credits").value
        ),

        startDate: document.getElementById("startDate").value,

        endDate: document.getElementById("endDate").value,

        learningObjectives: objectives,

        teacherId: teacherId

    };

    try {

        const response = await fetch(
            `${BASE_URL}/courses`,
            {
                method: "POST",
                headers: getHeaders(),
                body: JSON.stringify(course)
            }
        );

        if (!response.ok) {
            throw new Error("Unable to create course");
        }

        alert("Course created successfully.");

        document.getElementById("courseForm").reset();

        const objectiveContainer =
            document.getElementById("learningObjectives");

        if (objectiveContainer) {
            objectiveContainer.innerHTML = "";
            addObjective();
        }

        loadCourses();

    } catch (error) {

        console.error(error);

        alert("Failed to create course.");

    }

}

// ===================================
// Add Learning Objective
// ===================================

function addObjective() {

    const container =
        document.getElementById("learningObjectives");

    if (!container) return;

    const div = document.createElement("div");

    div.style.marginBottom = "10px";

    div.innerHTML = `
        <input
            type="text"
            class="objective-input"
            placeholder="Learning Objective">

        <button
            type="button"
            onclick="removeObjective(this)">
            Remove
        </button>
    `;

    container.appendChild(div);

}

// ===================================
// Remove Learning Objective
// ===================================

function removeObjective(button) {

    button.parentElement.remove();

}