document.addEventListener('DomContentLoaded',()=>{
loadCourses();
})

async function loadCourses(){
try{
const response= await fetch(`${BASE_URL}/courses`,{
headers:getHeaders()
}
);
if(!response.ok()){
throw new Error("Unable to load courses");
}
const courses=await response.json();

const container=document.getElementById("courseContainer");
container.innerHtml="";
courses.forEach(courses=>{
  container.innerHTML += `
                <div class="course-card">

                    <p><strong>${course.courseCode}</strong></p>

                    <h2>${course.courseName}</h2>

                    <p>${course.description}</p>

                    <p>
                        <strong>Faculty:</strong>
                        ${course.teacherName}
                    </p>

                    <button onclick="enroll(${course.id})">
                        Enroll
                    </button>

                </div>
            `;

        });

    }
    catch(error){
        console.error(error);
    }

}

async function enroll(courseId){

    const studentId = localStorage.getItem("userId");

    const response = await fetch(
        `${BASE_URL}/enrollments`,
        {
            method:"POST",

            headers:getHeaders(),

            body:JSON.stringify({
                studentId:studentId,
                courseId:courseId
            })
        }
    );

    if(response.ok){

        alert("Enrollment Successful");

    }else{

        alert("Enrollment Failed");

    }

}