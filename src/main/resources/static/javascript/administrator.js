let currentTransactionFee;
let adjustedTransactionFee;
let currentStartingCapital;
let adjustedStartingCapital;


fetch('../currenttransactionfee',{
    headers: {
        'Authorization': localStorage.getItem("token"),
        'Content-Type': 'application/json'

    },})
    .then((response) => response.json())
    .then( data => {
        currentTransactionFee = data;
        document.getElementById("transactionsfees").value = currentTransactionFee;

    }).catch((error) => {
    console.error('Error', error);
});

fetch('../currentstartingcapital',{
    headers: {
        'Authorization': localStorage.getItem("token"),
        'Content-Type': 'application/json'

    },})
    .then((response) => response.json())
    .then( data => {
        currentStartingCapital = data;
        document.getElementById("startupcapital").value = currentStartingCapital

}).catch((error) => {
    console.error('Error', error);
});


    document.querySelector('#btnAdjustCapital').addEventListener('click',
        function (event) {
            event.preventDefault() // anders wordt de gebruiker naar andere pagina geleid
            adjustedStartingCapital = document.getElementById("newstartupcapital").value;


            let data = '?startingBudget=' + adjustedStartingCapital
            const url = `../adjuststartingcapital` + data;
            const options = {
                method: `POST`,
                headers: {
                    'Authorization': localStorage.getItem("token"),
                    'Content-Type': 'application/json'
                }

            }
            fetch(url, options)
                .then(response => {
                        console.log(response)
                        if (response.ok) {
                            alert("Startup capital adjusted to € " + adjustedStartingCapital)
                        } else {
                            alert("Changes unsuccessful")
                        }
                        return response.json()
                    }
                )
                .catch((error) => {
                    console.error('Error', error);
                });
        })


    document.querySelector('#btnAdjustFee').addEventListener('click',
        function (event) {
            event.preventDefault() // anders wordt de gebruiker naar andere pagina geleid
            adjustedTransactionFee = document.getElementById("newtransactionfee").value;

            let data = '?transactionFee=' + adjustedTransactionFee
            const url = `../adjusttransactionfee` + data;
            const options = {
                method: `POST`,
                headers: {
                    'Authorization': localStorage.getItem("token"),
                    'Content-Type': 'application/json'
                }

            }
            fetch(url, options)
                .then(response => {
                        console.log(response)
                        if (response.ok) {
                            alert("Transactionfee percentage changed to " + adjustedTransactionFee + " %")
                        } else {
                            alert("Changes unsuccessful")
                        }
                        return response.json()
                    }
                )
                .catch((error) => {
                    console.error('Error', error);
                });
        })


