document.addEventListener("DOMContentLoaded", () => {

    loadTeacherDashboard();

});

async function loadTeacherDashboard() {

    try {

        const response = await fetch(

            `${BASE_URL}/dashboard/teacher`,

            {
                method: "GET",
                headers: getHeaders()
            }

        );

        if (!response.ok) {
            throw new Error("Unable to load dashboard");
        }

        const data = await response.json();

        //----------------------------
        // Welcome
        //----------------------------

        document.getElementById("teacherName").textContent =
            data.teacherName;

        //----------------------------
        // Cards
        //----------------------------

        document.getElementById("totalCourses").textContent =
            data.totalCourses;

        document.getElementById("totalStudents").textContent =
            data.totalStudents;

        document.getElementById("totalExams").textContent =
            data.totalExams;

        document.getElementById("totalAssignments").textContent =
            data.totalAssignments;

        //----------------------------
        // Course List
        //----------------------------

        const container =
            document.getElementById("courseContainer");

        container.innerHTML = "";

        data.courses.forEach(course => {

            container.innerHTML += `

            <div class="course-item">

                <img src="book.png" class="course-icon">

                <div>

                    <h3>${course.courseName}</h3>

                    <p>${course.studentCount} Students</p>

                </div>

            </div>

            `;

        });

        //----------------------------
        // Pie Chart
        //----------------------------

        drawEnrollmentChart(
            data.totalStudentsEnrolled,
            data.totalStudents
        );
        document.getElementById("enrolled").textContent=data.totalStudentsEnrolled;
        document.getElementById("Total").textContent=data.totalStudents;


    }

    catch(error){

        console.error(error);

    }

}

function drawEnrollmentChart(enrolled, totalStudents){

    const ctx =
        document.getElementById("attendanceChart");

    new Chart(ctx,{

        type:"pie",

        data:{

            labels:[
                "Enrolled",
                "Total Students"
            ],

            datasets:[{

                data:[
                    enrolled,
                    totalStudents
                ],

                backgroundColor:[
                    "#2ecc71",
                    "#e74c3c"
                ]

            }]

        },

        options:{
            responsive:true
        }

    });

}