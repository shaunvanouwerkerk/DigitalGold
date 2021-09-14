
    let form = document.getElementById("register");
    let password = document.getElementById("password");
    let emailaddress = document.getElementById("emailaddress")
    let bsn = document.getElementById("bsn")
    let city = document.getElementById("city")
    let zipCode= document.getElementById("zipcode")
    let username= document.getElementById("username")


    //Show input error message
    function ShowError(input, message) {
        const formControl = input.parentElement;
        formControl.className = "form-control error";
        const small = formControl.querySelector('small');
        small.innerText = message;
    }

    //Show input success
    function ShowSuccess(input) {
        const formControl = input.parentElement;
        formControl.className = "form-control success";
    }

    //Check email
    function CheckEmail(input) {
        const char = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        if (char.test(input.value.trim())) {
            ShowSuccess(input);
        }else {
            ShowError(input, "Email is not valid");
        }
    }
    function CheckRequired(input) {
        input.forEach(function(input){
            if (input.value.trim() === "") {
                ShowError(input, `${getFieldName(input)} is required`);
            }else {
                ShowSuccess(input);
            }
        });
    }

    function CheckLenght(input, min, max) {
        if (input.value.length < min) {
            ShowError(input, `${getFieldName(input)} must be at least ${min} characters`);
        }else if(input.value.length > max){
            ShowError(input, `${getFieldName(input)} must be less then ${max} characters`);
        }else {
            ShowSuccess(input);
        }
    }



    function getFieldName(input) {
        return input.id.charAt(0).toUpperCase() + input.id.slice(1)
    }

    form.addEventListener('submit', function(e){
        e.preventDefault();

        CheckLenght(password, 8, 50);
        CheckLenght(bsn, 8, 25);
        CheckEmail(emailaddress);
        CheckRequired([username]);

    });






















