let username
let password
const administrator = "admin01"

document.querySelector('#sbSubmit').addEventListener('click',
    function (event) {
        event.preventDefault() // anders wordt de gebruiker naar andere pagina geleidt
        // // selecteer inputs en zet waarden omde normale submit functie uitgevoerd
        username = String(document.querySelector('#username').value)
        password = String(document.querySelector('#password').value)

        if(username !== administrator) {

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
                .then(response => {
                    if (!response.ok) {
                        alert("Login unsuccessful " + "\n\nCombination of username and password are incorrect.");
                        console.log(response.status)
                        return Promise.reject(new Error(response.statusText))
                    }
                    alert("Login successful" + "\n\nWelcome to your Digital Gold portfolio")
                    console.log(response.status)
                    return response.text()
                })
                .then(response => {
                    window.localStorage.setItem("token", response);
                    window.location.replace("/portfolio.html")
                })
                .catch(error => {
                    console.error("Issue fetching data: ", error)
                })
        }else{

            // Stuurt username en password naar de server
            let data = '?username=' + username + '&password=' + password
            const url = `../login` + data;
            const options = {
                method: `POST`,
                headers: {
                    'Content-Type': 'application/json'
                }
            }
            // Administrator wordt naar een andere pagina gestuurd
            fetch(url, options)
                .then(response => {
                    if (!response.ok) {
                        alert("Login unsuccessful " + "\n\nCombination of username and password are incorrect.");
                        console.log(response.status)
                        return Promise.reject(new Error(response.statusText))
                    }
                    alert("Login successful" + "\n\nWelcome to the administrator page")
                    console.log(response.status)
                    return response.text()
                })
                .then(response => {
                    window.localStorage.setItem("token", response);
                    window.location.replace("/administrator.html")
                })
                .catch(error => {
                    console.error("Issue fetching data: ", error)
                })

        }
    })
