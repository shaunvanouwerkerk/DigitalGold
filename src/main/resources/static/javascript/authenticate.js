//Author Shaun van Ouwerkerk

    window.addEventListener('load', (e)=>{
    //voeg click aan listener aan de submit knop toe

            fetch("../authenticate", {
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
                        console.log(r.headers.get('Location'))
                        window.location.href = "../index.html"
                        alert("Your not authorized to view this page." + "\n\nPlease login with your username and password to get access.")


                    }
                })
                .then(data =>
                    document.querySelector('#info').innerHTML = data.text
                )
                .catch()
        }
    )

document.querySelector('#btnLogout').addEventListener('click', (e)=> {
    // window.confirm("Are you sure you want to log out?");
    if(confirm("Are you sure you want to log out?")){
        localStorage.clear()
        window.location.href = "../index.html"
    }else{
        event.preventDefault()
        return false;
    }
    }
)





