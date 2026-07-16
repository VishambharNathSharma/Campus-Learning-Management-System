async function loadAssignments(courseId) {

    try {

        const response = await fetch(
            `${BASE_URL}/assignments/course/${courseId}`,
            {
                method: "GET",
                headers: getHeaders()
            }
        );

        if (!response.ok) {
            throw new Error("Failed to load assignments.");
        }

        const assignments = await response.json();

        const container = document.getElementById("assignmentContainer");

        container.innerHTML = "";

        if (assignments.length === 0) {

            container.innerHTML = `
                <div class="no-data">
                    <h2>No Assignments Available</h2>
                </div>
            `;
            return;
        }

        document.getElementById("courseTitle").textContent =
            assignments[0].courseName + " Assignments";

        assignments.forEach(assignment => {

            const card = document.createElement("div");

            card.className = "assignment-card";

            card.innerHTML = `

                <h2>${assignment.title}</h2>

                <p>
                    <strong>Course :</strong>
                    ${assignment.courseName}
                </p>

                <p>
                    <strong>Due Date :</strong>
                    ${formatDate(assignment.dueDateTime)}
                </p>

                <div class="card-buttons">

                    <button
                        class="view-btn"
                        onclick="viewQuestionPaper('${assignment.questionPaperFileName}')">

                        📄 View Question Paper

                    </button>

                </div>

                <div class="status pending">

                    🟡 Pending

                </div>

            `;

            container.appendChild(card);

        });

    } catch (error) {

        console.error(error);
        alert("Unable to load assignments.");

    }

}