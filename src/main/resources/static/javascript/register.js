    let houseNumber;
    let streetName;
    let zipCode;
    let city;
    let dateOfBirth;
    let bsn;
    let iban;
    let emailaddress;
    let firstName;
    let prefix;
    let lastName;
    let password;
    let username;


    document.querySelector('#sbRegister').addEventListener('click',
        function (event){
        if(formValidation().valueOf(true)){
            event.preventDefault() // anders wordt de gebruiker naar andere pagina geleid
            // // selecteer inputs en zet waarden omde normale submit functie uitgevoerd
            username = String(document.querySelector('#username').value)
            password = String(document.querySelector('#password').value)
            firstName = String(document.querySelector('#firstname').value)
            prefix = String(document.querySelector('#prefix').value)
            lastName = String(document.querySelector('#lastname').value)
            houseNumber = String(document.querySelector('#housenumber').value)
            streetName = String(document.querySelector('#streetname').value)
            zipCode= String(document.querySelector('#zipcode').value)
            city = String(document.querySelector('#city').value)
            dateOfBirth = String(document.querySelector('#dateofbirth').value)
            bsn = String(document.querySelector('#bsn').value)
            emailaddress = String(document.querySelector('#emailaddress').value)


            let data = {username: username,password: password ,fullName: {firstName: firstName, prefix: prefix,lastName: lastName},
                address: {houseNumber: houseNumber,streetName: streetName,zipCode: zipCode,city: city},customerDetails: {dateOfBirth:
                    dateOfBirth, bsn: bsn, iban: "12345678910", emailaddress: emailaddress}}


            const url = '../register'
            const options = {
                method: 'POST',
                body: JSON.stringify(data),
                headers:{
                    'Content-Type': 'application/json',
                    'Accept' : 'application/json'
                }}

            fetch(url,options)
                .then(response => {
                console.log(response)
                        if(response.ok) {
                            alert("Registration successful")
                            window.location.href = "../index.html"
                        } else {
                            alert("Register unsuccessful")
                        }
                return response.json()}
                )
                .catch((error) => {
                    console.error ('Error' , error);
                });
        }})



    //Checkt formulier op correctheid
    function formValidation() {

        let isEmailValid= checkEmail(document.getElementById('emailaddress'))
        let isBsnValid = checkLenght(document.getElementById('bsn'),9,9)
        let isZipCodeValid = checkPostcode(document.getElementById('zipcode'))
        let isPasswordValid = checkLenght(document.getElementById('password'),8,50)
        let isDateOfBirthValid = checkDateOfBirth(document.getElementById('dateofbirth'))
        // let isNotEmpty = checkRequired([document.getElementById('username'),
        //     document.getElementById('firstname'),document.getElementById('lastname'),
        //         document.getElementById('housenumber'),document.getElementById('streetname'),
        //             document.getElementById('city')]);


        // Check of alle velden op true staan
        var validForm = isEmailValid && isBsnValid && isZipCodeValid && isDateOfBirthValid && isPasswordValid
        return validForm

    }


    function checkEmail(input) {
        const char = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        if (char.test(input.value.trim())) {
            ShowSuccess(input);
            return true;
        }else {
            ShowError(input, "Email is not valid");
            return false;
        }
    }


    // Checkt op lengte van een veld
    function checkLenght(input, min, max) {
        if (input.value.length < min) {
            ShowError(input, `${getFieldName(input)} must be minimal ${min} characters`);
            return false;
        }else if(input.value.length > max){
            ShowError(input, `${getFieldName(input)} must be maximal ${max} characters`);
            return false;
        }else {
            ShowSuccess(input,"");
            return true;
        }
    }
    //Checkt of datum in format yyyy-mm-dd is ingevuld
    function checkDateOfBirth(input) {
        var date_regex = /(\d{4})-(\d{2})-(\d{2})/;
        if (date_regex.test(input.value)) {
            ShowSuccess(input, "");
            return true;
        } else {
            ShowError(input, "Date not in format yyyy-mm-dd");
            return false;
        }
    }

    // Checkt postcode op format 1000 aa
    function checkPostcode(input) {
        var date_regex = /^[1-9][0-9]{3}[\s]?[A-Za-z]{2}$/i;
        if (date_regex.test(input.value)) {
            ShowSuccess(input, "");
            return true;
        } else {
            ShowError(input, "Zipcode not in format (1234 AB)");
            return false;
        }
    }


    function getFieldName(input) {
        return input.id.charAt(0).toUpperCase() + input.id.slice(1)
    }

    function checkRequired(inputErr) {
        let check = false;
        inputErr.forEach(function(input){
            if (input.value.trim() === "") {
                ShowError(input, `${getFieldName(input)} is required`);
            }else {
                ShowSuccess(input);
               check = true;
            }
        });
        return check
    }


    //Toont error bericht
    function ShowError(input, message) {
        const formControl = input.parentElement;
        formControl.className = "form-control error";
        const small = formControl.querySelector('small');
        small.innerText = message;
    }

    //Toont succes bericht
    function ShowSuccess(input) {
        const formControl = input.parentElement;
        formControl.className = "form-control success";
    }








