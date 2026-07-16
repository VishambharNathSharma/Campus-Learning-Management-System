

document.addEventListener('DOMContentLoaded', function() {

    var usernameInput = document.getElementById('username');
    if (usernameInput) {
        usernameInput.addEventListener('input', function() {
            let trimmedText = usernameInput.value.trim();
            const regex = /^[A-Za-z]+(?:\s+[A-Za-z]+){0,2}$/;
            let errorMsg = document.getElementById('usernameError');
            
            if (!regex.test(trimmedText) && trimmedText.length > 0) {
                usernameInput.classList.add('error');
                if (errorMsg) {
                    errorMsg.textContent = 'Name can include first, middle and last name (up to 3 words)';
                }
            } else {
                usernameInput.classList.remove('error');
                if (errorMsg) {
                    errorMsg.textContent = '';
                }
            }
        });
    }

    var emailtxt = document.getElementById('email');
    if (emailtxt) {
        emailtxt.addEventListener('input', function() {
            const regex = /^[a-zA-Z]+_[a-zA-Z]+_[0-9]{4}@[a-zA-Z]+\.ac\.in$/;
            let errormsg = document.getElementById('emailerror');
            let text = emailtxt.value;
            if (!regex.test(text) && text.length > 0) {
                emailtxt.classList.add('error');
                if (errormsg) {
                    errormsg.textContent = 'Email is not in required format';
                }
            } else {
                emailtxt.classList.remove('error');
                if (errormsg) {
                    errormsg.textContent = '';
                }
            }
        });
    }
    var rollno = document.getElementById('rollno');
    if (rollno) {
        rollno.addEventListener('input', function() {
            const regex = /^[0-9]{0,13}$/; // require exactly 6 digits (adjust if needed)
            let rollnoval = rollno.value;
            let errormg = document.getElementById('rollnoerror');
            if (!regex.test(rollnoval) && rollnoval.length > 0) {
                rollno.classList.add('error');
                if (errormg) {
                    errormg.textContent = 'Roll number must be less than 14 digits';
                }
            } else {
                rollno.classList.remove('error');
                if (errormg) {
                    errormg.textContent = '';
                }
            }
        });
    }
    var password = document.getElementById('password');
    if (password) {
        password.addEventListener('input', function() {
            const regex = /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)[A-Za-z\d@$!%*#?&]{6,}$/; // at least one lower, one upper, one digit, min 6 chars
            let passwordval = password.value;
            let errormg = document.getElementById('passworderror');
            if (!regex.test(passwordval) && passwordval.length > 0) {
                password.classList.add('error');
                if (errormg) {
                    errormg.textContent = 'Password must include upper and lower case letters and be at least 6 characters';
                }
            } else {
                password.classList.remove('error');
                if (errormg) {
                    errormg.textContent = '';
                }
            }
        });
    }

    // Form submit: validate each form separately and prevent submit if any invalid
    var forms = document.querySelectorAll('form');
    forms.forEach(function(form) {
        form.addEventListener('submit', function(e) {
            function validateAll() {
                let valid = true;

                const usernameRegex = /^[A-Za-z]+(?:\s+[A-Za-z]+){0,2}$/;
                const emailRegex = /^[a-zA-Z]+_[a-zA-Z]+_[0-9]{4}@[a-zA-Z]+\.ac\.in$/;
                const rollRegex = /^[0-9]{0,13}$/;
                const pwdRegex = /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)[A-Za-z\d@$!%*#?&]{6,}$/;

                const u = form.querySelector('[name="username"]');
                const uErr = form.querySelector('.usernameError');
                if (u) {
                    const usernameText = u.value.trim();
                    if (!usernameRegex.test(usernameText) || usernameText.length === 0) {
                        u.classList.add('error');
                        if (uErr) uErr.textContent = 'Username must contain 1 to 3 words of letters only';
                        valid = false;
                    } else {
                        u.classList.remove('error');
                        if (uErr) uErr.textContent = '';
                    }
                }

                const eInput = form.querySelector('[name="email"]');
                const eErr = form.querySelector('.emailError');
                if (eInput) {
                    if (!emailRegex.test(eInput.value) || eInput.value.length === 0) {
                        eInput.classList.add('error');
                        if (eErr) eErr.textContent = 'Email is not in required format';
                        valid = false;
                    } else {
                        eInput.classList.remove('error');
                        if (eErr) eErr.textContent = '';
                    }
                }

                const r = form.querySelector('[name="rollno"]');
                const rErr = form.querySelector('.rollnoError');
                if (r) {
                    if (!rollRegex.test(r.value) || r.value.length === 0) {
                        r.classList.add('error');
                        if (rErr) rErr.textContent = 'Roll number must be exactly 6 digits';
                        valid = false;
                    } else {
                        r.classList.remove('error');
                        if (rErr) rErr.textContent = '';
                    }
                }

                const p = form.querySelector('[name="password"]');
                const pErr = form.querySelector('.passwordError');
                if (p) {
                    if (!pwdRegex.test(p.value) || p.value.length === 0) {
                        p.classList.add('error');
                        if (pErr) pErr.textContent = 'Password must include upper and lower case letters and be at least 6 characters';
                        valid = false;
                    } else {
                        p.classList.remove('error');
                        if (pErr) pErr.textContent = '';
                    }
                }

                return valid;
            }

            e.preventDefault();
            if (validateAll()) {
                const role = form.id === 'studentForm' ? 'student' : form.id === 'teacherForm' ? 'teacher' : (document.getElementById('role')?.value || 'student');
                localStorage.setItem('role', role);
                if (role === 'teacher') {
                    window.location.href = 'TeacherHome.html';
                } else if (role === 'student') {
                    window.location.href = 'StudentHome.html';
                }
             else {
                var first = form.querySelector('.error');
                if (first) first.focus();
            }
            }
        });
    });
    const darkModeKey = 'darkMode';

    function setDarkMode(isDark) {
        document.body.classList.toggle('darkmode', isDark);
        localStorage.setItem(darkModeKey, isDark ? 'enabled' : 'disabled');
    }

    const savedDarkMode = localStorage.getItem(darkModeKey);
    if (savedDarkMode === 'enabled') {
        setDarkMode(true);
    } else if (savedDarkMode === 'disabled') {
        setDarkMode(false);
    }

    var darkToggles = document.querySelectorAll('.dark');
    if (darkToggles.length === 0) {
        console.warn('No elements found with class "dark". Toggle will not work.');
    }
    darkToggles.forEach(function(el) {
        el.addEventListener('click', function() {
            const isDark = !document.body.classList.contains('darkmode');
            setDarkMode(isDark);
        });
    });
 document.querySelectorAll('a').forEach(link=>{
    link.addEventListener('click',function(e){
        if(link.target!=='_blank'&& link.href.startsWith(window.location.origin)){
            e.preventDefault();
            document.body.classList.add('fadeout');
            setTimeout(()=>{
                window.location.href=link.href;
            },300);
        }
    });
 });

 document.body.classList.remove('fadeout');

 const input = document.getElementById('proimg');
 const button = document.getElementById('changeimg');
 const picture = document.getElementById('photo');
 if (button && input && picture) {
     button.addEventListener('click', ()=>{
         input.click();
     });
     input.addEventListener('change', ()=>{
         const img = input.files[0];
         if (!img) return;
         const url = URL.createObjectURL(img);
         picture.src = url;
     });
 }

 window.toggleRole = function toggleRole() {
     const roleElement = document.getElementById('role');
     if (!roleElement) return;
     const role = roleElement.value;
     const studentPanel = document.getElementById('studentPanel');
     const teacherPanel = document.getElementById('teacherPanel');
     if (studentPanel) {
         studentPanel.style.display = (role === 'student') ? 'block' : 'none';
     }
     if (teacherPanel) {
         teacherPanel.style.display = (role === 'teacher') ? 'block' : 'none';
     }
 };
 toggleRole();
 window.handleLogin = function handleLogin(event) {
    if (event) {
        event.preventDefault();
    }

    const role = document.getElementById('role')?.value || 'student';
    localStorage.setItem('role', role);
    if(role === 'teacher'){
    window.location.href = 'TeacherHome.html';
    }
    else{
    window.location.href = 'StudentHome.html';
    }
};
 const role = localStorage.getItem('role');

// set courses link based on role
const coursesLink = document.getElementById('coursesLink');
if (role === 'teacher') {
  coursesLink.href = 'course.html';
} else{
  coursesLink.href = 'studentcourse.html';
}
  
 
});