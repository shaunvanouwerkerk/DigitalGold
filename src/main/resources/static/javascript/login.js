

    let username
    let password

    document.querySelector('#sbSubmit').addEventListener('click',
        function (event) {
            event.preventDefault() // anders wordt de gebruiker naar andere pagina geleidt
            // // selecteer inputs en zet waarden omde normale submit functie uitgevoerd
            username = String(document.querySelector('#username').value)
            password = String(document.querySelector('#password').value)

            // Stuurt username en password naar de server
            let data = '?username=' + username + '&password=' + password
            const url = `../login` + data;
            const options = {
                method: `POST`,
                headers: {
                    'Content-Type': 'application/json'
                }
            }
            // Token komt terug als username & password erkend wordt
            fetch(url, options)
                .then(response => response.text()).then(token => {
                localStorage.setItem("token", token)
                console.log(token);
                })
                .then(response =>  {
                    if (response.json().ok) {
                        alert("Login successfull")
                        window.location.href = "../portfolio.html"

                    } else {
                        alert("Login unsuccessfull")
                    }
                })


    })












