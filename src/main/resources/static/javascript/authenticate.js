

    window.addEventListener('load', (e)=>{
    //voeg click aan listener aan de submit knop toe

            fetch("/authenticate", {
                method: 'GET',
                headers: {
                    'Authorization': localStorage.getItem("token"),
                    'Content-Type': 'application/json'
                },
            })
                .then(r => {
                    if(r.status === 200){
                        return r.json()
                    } else if(r.status === 401){
                        // document.querySelector('#info').classList.add("error")
                        // return { "text": "Je bent niet geauthenticeerd. Haal eerst je token op" }
                        //
                        console.log(r.headers.get('Location'))
                        window.location.href = "/index"

                    }
                })
                .then(data =>
                    document.querySelector('#info').innerHTML = data.text
                )
                .catch()
        }
    )
