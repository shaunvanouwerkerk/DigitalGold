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
            if(formValidationSC().valueOf(true)){
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
                            alert("Startup capital adjusted to: € " + adjustedStartingCapital)
                            window.location.href = "../administrator.html"
                        } else {
                            alert("Changes unsuccessful")
                        }
                        return response.json()
                    }
                )
                .catch((error) => {
                    console.error('Error', error);
                });
        }})


    document.querySelector('#btnAdjustFee').addEventListener('click',
        function (event) {
        if(formValidationTF().valueOf(true)){
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
                            alert("Transactionfee percentage changed to: " + (adjustedTransactionFee))
                            window.location.href = "../administrator.html"
                        } else {
                            alert("Changes unsuccessful")
                        }
                        return response.json()
                    }
                )
                .catch((error) => {
                    console.error('Error', error);
                });
        }})

function formValidationTF() {

    let isTransactionFeeValid = checkTransactionFee(document.getElementById('newtransactionfee'))

    // Check of alle velden op true staan
    var validForm = isTransactionFeeValid
    return validForm
}

function formValidationSC() {

    let isStartingCapitalValid = checkStartingCapital(document.getElementById('newstartupcapital'))

    // Check of alle velden op true staan
    var validForm = isStartingCapitalValid
    return validForm
}


function checkTransactionFee(input){
        console.log(input.value)
    var date_regex = /^[0]+\.[0-9]+$/;
    if (date_regex.test(input.value)) {
        ShowSuccess(input, "");
        return true;
    } else {
        ShowError(input, "Transaction fee not in format 0.05");
        return false;
    }
}

function checkStartingCapital(input){
    console.log(input.value)
    var date_regex = /^[0-9]{1,5}$/;
    if (date_regex.test(input.value)) {
        ShowSuccess(input, "");
        return true;
    } else {
        ShowError(input, "Not in format: max € 99.999,-");
        return false;
    }
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
