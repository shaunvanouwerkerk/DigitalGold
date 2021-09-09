

    // let Address = new Object();
    let houseNumber;
    let streetName;
    let zipCode;
    let city;

    function Address(houseNumber,streetName,zipCode,city){
        this.houseNumber = houseNumber;
        this.streetName = streetName;
        this.zipCode = zipCode;
        this.city = city;
    }

    // let CustomerDetails = new Object();
    let dateOfBirth;
    let bsn;
    let iban;
    let emailaddress;


    function CustomerDetails(dateOfBirth,bsn,iban,emailaddress){
        this.dateOfBirth = dateOfBirth;
        this.bsn = bsn;
        this.iban = iban;
        this.emailaddress = emailaddress;
    }
    // let FullName = new Object();
    let firstName;
    let prefix;
    let lastName;

    function FullName(firstName,prefix,lastName){
        this.firstName = firstName;
        this.prefix = prefix;
        this.lastName = lastName
    }
    // let Customer = new Object();
    let username;
    let password;

    function Customer(username,password,Fullname,Address,CustomerDetails){
        this.username = username;
        this.password = password;
        this.Fullname = Fullname;
        this.Address = Address;
        this.CustomerDetails = CustomerDetails
    }

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

            let address = Address(houseNumber, streetName, zipCode, city);
            let customerDetails = CustomerDetails(dateOfBirth,bsn,iban,emailaddress)
            let fullName = FullName(firstName,prefix,lastName)



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










