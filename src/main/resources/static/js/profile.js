document.addEventListener("DOMContentLoaded", () => {

    loadStudentProfile();
    loadProfile();

    const changeBtn = document.getElementById("changeimg");
    const fileInput = document.getElementById("proimg");

    if (changeBtn && fileInput) {

        changeBtn.addEventListener("click", () => {
            fileInput.click();
        });

        fileInput.addEventListener("change", uploadProfilePicture);
    }

});

// ==========================
// Load Student Details
// ==========================

async function loadStudentProfile() {

    try {

        const response = await fetch(
            `${BASE_URL}/student/me`,
            {
                method: "GET",
                headers: getHeaders()
            }
        );

        if (!response.ok) {
            throw new Error("Unable to load profile");
        }

        const student = await response.json();

        document.getElementById("studentName").textContent =
            student.firstName + " " + student.lastName;

        const profileImage = document.getElementById("photo");

        if (student.profilePicture && student.profilePicture !== "") {

            profileImage.src =
                `${BASE_URL}/uploads/profile/${student.profilePicture}?t=${Date.now()}`;

        } else {

            profileImage.src = "loo.png";

        }

    }
    catch (error) {

        console.error(error);
        alert("Unable to load profile.");

    }

}

// ==========================
// Upload Profile Picture
// ==========================

async function uploadProfilePicture() {

    const fileInput = document.getElementById("proimg");

    if (fileInput.files.length === 0) {
        return;
    }

    const file = fileInput.files[0];

    const formData = new FormData();

    formData.append(
        "userId",
        localStorage.getItem("userId")
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
                    Authorization: "Bearer " + getToken()
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

        alert("Unable to upload profile picture.");

    }

}

// ==========================
// Load Academic Profile
// ==========================

async function loadProfile() {

    try {

        const response = await fetch(

            `${BASE_URL}/student/profile`,

            {

                method: "GET",

                headers: getHeaders()

            }

        );

        if (!response.ok) {
            throw new Error("Unable to load profile.");
        }

        const student = await response.json();

        loadMarks(
            student.st1Marks,
            "st1Percentage",
            "st1Subjects"
        );

        loadMarks(
            student.st2Marks,
            "st2Percentage",
            "st2Subjects"
        );

        loadMarks(
            student.putMarks,
            "putPercentage",
            "putSubjects"
        );

        loadAttendance(
            student.st1Attendance,
            "attendanceSt1Percentage",
            "attendanceSt1Subjects"
        );

        loadAttendance(
            student.st2Attendance,
            "attendanceSt2Percentage",
            "attendanceSt2Subjects"
        );

        loadAttendance(
            student.overallAttendance,
            "attendanceOverallPercentage",
            "attendanceOverallSubjects"
        );

    }

    catch (error) {

        console.error(error);

        alert("Unable to load academic profile.");

    }

}

// ==========================
// Load Marks
// ==========================

function loadMarks(markList, percentageId, listId) {

    const list = document.getElementById(listId);

    if (!list) {
        return;
    }

    list.innerHTML = "";

    if (!markList || markList.length === 0) {

        document.getElementById(percentageId).textContent = "0%";

        return;

    }

    let obtained = 0;
    let maximum = 0;

    markList.forEach(mark => {
        const courseName = mark.courseName || "Course";
        const marksObtained = Number(mark.marksObtained ?? 0);
        const maximumMarks = Number(mark.maximumMarks ?? 0);

        obtained += marksObtained;
        maximum += maximumMarks;

        const li = document.createElement("li");

        li.textContent =
            `- ${courseName}: ${marksObtained}/${maximumMarks}`;

        list.appendChild(li);

    });

    const percentage = maximum === 0 ? 0 : (obtained / maximum) * 100;

    document.getElementById(percentageId).textContent =
        percentage.toFixed(1) + "%";

}

// ==========================
// Load Attendance
// ==========================

function loadAttendance(attendanceList, percentageId, listId) {

    const list = document.getElementById(listId);

    if (!list) {
        return;
    }

    list.innerHTML = "";

    if (!attendanceList || attendanceList.length === 0) {

        document.getElementById(percentageId).textContent = "0%";

        return;

    }

    let total = 0;

    attendanceList.forEach(item => {
        const courseName = item.courseName || "Course";
        const percentage = Number(item.percentage ?? 0);

        total += percentage;

        const li = document.createElement("li");

        li.textContent =
            `- ${courseName}: ${percentage.toFixed(1)}%`;

        list.appendChild(li);

    });

    const average = total / attendanceList.length;

    document.getElementById(percentageId).textContent =
        average.toFixed(1) + "%";

}
