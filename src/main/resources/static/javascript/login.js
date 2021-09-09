

    let username
    let password

    window.addEventListener('load', (e)=>{
    //voeg click aan listener aan de submit knop toe

    document.querySelector('#sbSubmit').addEventListener('click',
        function (event){
            event.preventDefault() // anders wordt de gebruiker naar andere pagina geleidt
            // // selecteer inputs en zet waarden omde normale submit functie uitgevoerd
            username = String(document.querySelector('#username').value)
            password = String(document.querySelector('#password').value)

            // Stuurt username en password naar de server
            let data = '?username=' + username + '&password=' + password
            const url = `http://localhost:8080/login` + data;
            const options = {
                method: `POST`,
                headers:{
                    'Content-Type': 'application/json'
                }
            }
            // Token komt terug als username & password erkent wordt
            fetch(url,options)
                .then(response => response.text())
                .then(json=>{
                    console.log(json);
                    localStorage.setItem("token", json)})
                .catch((error) => {
                    console.error('Error',error);
                })
        })
})