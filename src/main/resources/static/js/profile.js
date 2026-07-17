document.addEventListener("DOMContentLoaded",()=>{
loadStudentProfile();
loadProfile();
const changeBtn=document.getElementById("changeimg");
const fileInput=document.getElementById("proimg");
if(changeBtn&&fileInput){
changeBtn.addEventListener("click",()=>{
    fileInput.click();
});
fileInput.addEventListener("change",uploadProfilePicture);
}

});
async function loadStudentProfile(){
try{
const response= await fetch(`${BASE_URL}/student/me`,{
    method: "GET",
    headers: getHeaders()
    }
});
if(!response.ok){
throw new Error("Unable to load profile");
}

const student = await response.json();
//Name
if(document.getElementById("studentName")){
document.getElementById("studentName").textContent=student.firstName+" "+student.lastName;
}
//profile picture
const profileImage = document.getElementById("photo");
if(profileImage){
if(student.profilePicture&&student.profilePicture!==""){
profileImage.src = `${BASE_URL}/uploads/profile/${student.profilePicture}?t=${Date.now()}`;
}
else{
profileImage.src="/static/loo.png";
}
}
}
catch(error){
console.error(error);
alert("Unable to load profile");
}
}
async function uploadProfilePicture(){
const fileInput=document.getElementById("proimg");
if(fileInput.files.length===0){
return;
}
const file = fileInput.files[0];
const formData=new FormData();
formData.append(
"userId",localStorage.getItem("userId")
);
    formData.append(
        "file",
        file
    );

    try {

        const response = await fetch(

            `${BASE_URL}/users/profile-picture`,

            {

                method: "POST",

                headers: {

                    "Authorization":
                        "Bearer " + getToken()

                },

                body: formData

            }

        );

        if (!response.ok) {

            throw new Error("Upload failed");

        }

        alert("Profile picture updated successfully.");

        loadStudentProfile();

    }

    catch (error) {

        console.error(error);

        alert("Unable to upload image.");

    }

}
}
async function loadProfile() {

    try {

        const response = await fetch(`${BASE_URL}/student/profile`, {
            method: "GET",
            headers: getHeaders()
        });

        if (!response.ok) {
            throw new Error("Unable to load academic profile");
        }

        const student = await response.json();

        loadMarks(student.st1Marks, "st1Percentage", "st1Subjects");
        loadMarks(student.st2Marks, "st2Percentage", "st2Subjects");
        loadMarks(student.putMarks, "putPercentage", "putSubjects");

        loadAttendance(student.st1Attendance,
            "attendanceSt1Percentage",
            "attendanceSt1Subjects");

        loadAttendance(student.st2Attendance,
            "attendanceSt2Percentage",
            "attendanceSt2Subjects");

        loadAttendance(student.overallAttendance,
            "attendanceOverallPercentage",
            "attendanceOverallSubjects");

    } catch (error) {

        console.error(error);

    }

}
function loadMarks(markList, percentageId, listId) {

    const list = document.getElementById(listId);

    if (!list) return;

    list.innerHTML = "";

    let obtained = 0;
    let maximum = 0;

    markList.forEach(mark => {

        obtained += mark.obtainedMarks;
        maximum += mark.maximumMarks;

        const li = document.createElement("li");

        li.textContent =
            `${mark.subjectName}: ${mark.obtainedMarks}/${mark.maximumMarks}`;

        list.appendChild(li);

    });

    const percentage =
        maximum === 0 ? 0 : (obtained / maximum) * 100;

    document.getElementById(percentageId).textContent =
        percentage.toFixed(1) + "%";

}

function loadAttendance(attendanceList, percentageId, listId) {

    const list = document.getElementById(listId);

    if (!list) return;

    list.innerHTML = "";

    let total = 0;

    attendanceList.forEach(item => {

        total += item.percentage;

        const li = document.createElement("li");

        li.textContent =
            `${item.subjectName}: ${item.percentage.toFixed(1)}%`;

        list.appendChild(li);

    });

    const average =
        attendanceList.length === 0
            ? 0
            : total / attendanceList.length;

    document.getElementById(percentageId).textContent =
        average.toFixed(1) + "%";

}
