

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
    let username;
    let password;


    document.querySelector('#sbRegister').addEventListener('click',
        function (event){
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
                            alert("Registration successfull")
                            window.location.href = "../index.html"
                        } else {
                            alert("Register unsuccessfull")
                        }
                return response.json()}
                )
                .catch((error) => {
                    console.error ('Error' , error);
                });
        })










