document.addEventListener("DOMContentLoaded", () => {

    loadCourses();

    document
        .getElementById("assignmentForm")
        .addEventListener("submit", createAssignment);

});

async function loadCourses() {

    try {

        const teacherId = localStorage.getItem("userId");

        const response = await fetch(
            `${BASE_URL}/courses/teacher/${teacherId}`,
            {
                method: "GET",
                headers: getHeaders()
            }
        );

        if (!response.ok) {
            throw new Error("Unable to load courses");
        }

        const courses = await response.json();

        const select = document.getElementById("assignment");

        select.innerHTML =
            "<option value=''>Select Course</option>";

        courses.forEach(course => {

            select.innerHTML += `
                <option value="${course.id}">
                    ${course.courseName}
                </option>
            `;

        });

    } catch (error) {

        console.error(error);
        alert("Unable to load courses.");

    }

}

async function createAssignment(e) {

    e.preventDefault();

    const assignment = {

        courseId:
            document.getElementById("assignment").value,

        title:
            document.getElementById("assignmenttitleinput").value,

        instructions:
            document.getElementById("assignmentinstructions").value,

        maximumMarks:
            parseInt(
                document.getElementById("marksinput").value
            ),

        submissionType:
            document.getElementById("submissionType").value,

        dueDateTime:
            document.getElementById("dueDateTime").value

    };

    try {

        const response = await fetch(
            `${BASE_URL}/assignments`,
            {
                method: "POST",
                headers: {
                    ...getHeaders(),
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(assignment)
            }
        );

        if (!response.ok) {
            throw new Error("Assignment creation failed");
        }

        alert("Assignment created successfully.");

        document.getElementById("assignmentForm").reset();

    } catch (error) {

        console.error(error);

        alert("Unable to create assignment.");

    }

}