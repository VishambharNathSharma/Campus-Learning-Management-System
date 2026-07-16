document.addEventListener("DOMContentLoaded", function () {

    loadStudentAssignments();

    document
    .getElementById("assignmentsubmitbutton")
    .addEventListener("click", submitAssignment);

});


// Load assignments in dropdown

async function loadStudentAssignments(){

    const studentId = localStorage.getItem("userId");


    if(!studentId){

        alert("Student not logged in");
        return;

    }


    try{


        const response = await fetch(

            `${BASE_URL}/assignments/student/${studentId}`,

            {

                method:"GET",

                headers:getHeaders()

            }

        );


        if(!response.ok){

            throw new Error("Unable to load assignments");

        }


        const assignments = await response.json();



        const select =
        document.getElementById("assignmentcourse");



        select.innerHTML="";



        assignments.forEach(assignment=>{


            const option =
            document.createElement("option");



            option.value =
            assignment.id;



            option.textContent =
            `${assignment.courseName} - ${assignment.title}`;



            select.appendChild(option);


        });



    }
    catch(error){

        console.error(error);

        alert("Failed to load assignments");

    }

}




// Submit Assignment

async function submitAssignment(){


    const assignmentId =
    document.getElementById("assignmentcourse").value;



    const file =
    document.getElementById("assignfile").files[0];



    const notes =
    document.getElementById("notes").value;



    const studentId =
    localStorage.getItem("userId");



    if(!assignmentId){

        alert("Please select assignment");

        return;

    }



    if(!file){

        alert("Please attach your assignment PDF");

        return;

    }



    if(file.type !== "application/pdf"){


        alert("Only PDF files are allowed");

        return;

    }



    const formData = new FormData();



    formData.append(
        "assignmentId",
        assignmentId
    );



    formData.append(
        "studentId",
        studentId
    );



    formData.append(
        "notes",
        notes
    );



    formData.append(
        "file",
        file
    );



    try{


        const response = await fetch(

            `${BASE_URL}/submissions`,

            {

                method:"POST",

                headers:{

                    "Authorization":
                    "Bearer " + getToken()

                },


                body:formData

            }

        );



        if(response.ok){


            alert(
                "Assignment submitted successfully"
            );



            document.getElementById("assignfile").value="";

            document.getElementById("notes").value="";


        }
        else{


            const message =
            await response.text();


            alert(message);


        }


    }
    catch(error){


        console.error(error);

        alert(
            "Submission failed"
        );


    }


}