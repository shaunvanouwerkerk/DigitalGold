/*
@author David Truijens - scripts voor orderpagina Buy
 */

/* * * * * *  CONSTANTS * * * * * */
const menuButton = document.getElementsByClassName('menu-button')[0];
const menuLinks = document.getElementsByClassName('nav-links')[0];
const dropDownSelector = document.getElementById("coinselector");
const priceField = document.getElementById("price");
const cryptoValue = document.querySelector('#cryptoValue');
const cryptoAmount = document.querySelector('#cryptoAmount');
const buttonPostBuyOrder = document.getElementById("postButton");
const formTitle = document.getElementById("form-title");
const cryptoTitle = document.getElementById("crypto-title");
const orderForm = document.getElementById("order");

/* * * * * * * * * * * * * * * * */


/* * * * * * NAVIGATION * * * * * */
menuButton.addEventListener('click',() => {
    menuLinks.classList.toggle('active')
})
/* * * * * * * * * * * * * * * * */


/* * * * * * CALCULATOR * * * * * */

/* * * * van gekozen crypto prijs ophalen om te kunnen doorgeven aan calculator * * * */
function getAssetPriceByAssetCode () {
    fetch('/assetoverviewbank', {
        method: 'GET',
        headers: {
            'Authorization': localStorage.getItem("token"),
            'Content-Type': 'application/json'
        }})
        .then((response) => response.json()).then(assetData => {
        console.log(assetData);
        const crypto = dropDownSelector.options[dropDownSelector.selectedIndex].text.toLowerCase();
        console.log(crypto);
        assetData.forEach(function (value) {
            if (value.symbol === crypto) {
                console.log("Assetcode gevonden " + value.symbol + " : " + value.current_price)
                priceField.innerHTML = value.current_price;
            }
            })
        })
}

calc('crypto')
cryptoValue.onkeyup = ()=>calc()
cryptoValue.onchange = ()=>calc()
cryptoAmount.onkeyup = ()=>calc('crypto')
cryptoAmount.onchange = ()=>calc('crypto')

/* TODO: Functie moet straks op basis van assetprice data ipv API Coindesk */
function calc(changer){
    const cryptoAmt = Number(cryptoAmount.value);
    const cryptoVal = Number(cryptoValue.value);
    const exchangeRate = parseFloat(document.getElementById("price").innerHTML);

    console.log(exchangeRate)
    if(changer==='crypto'){
        const amount =  cryptoAmt * exchangeRate
        cryptoValue.value = parseFloat(amount).toFixed(2)
    }else{
        cryptoAmount.value = cryptoVal / exchangeRate
    }
}
/* * * * * * * * * * * * * * * */


/* * * * * * POST BUY ORDER * * * * * */
buttonPostBuyOrder.addEventListener("click",() => {
    if(validateOrderInput().valueOf(true)) {
        postRequest();
    } else {
        alert("Form data is missing. \nYour buy order cannot be submitted \nPlease fill in amount or value.")
    }
})

function validateOrderInput() {
    let selectedCryptoSymbol = dropDownSelector.options[dropDownSelector.selectedIndex].text;
    return Number(cryptoValue.value) > 0 && Number(cryptoAmount.value) > 0 && selectedCryptoSymbol !== "";
}

function postRequest() {
    let selectedCryptoSymbol = dropDownSelector.options[dropDownSelector.selectedIndex].text;
    let selectedCryptoAmount = cryptoAmount.value;

    // javascript object Order, matches java Order
    let orderData = {assetCode: selectedCryptoSymbol, amountOfAsset: selectedCryptoAmount , type: "buy", limit: 0};

    fetch('/order', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Authorization': localStorage.getItem("token"),
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(orderData)  // moet worden omgezet naar een string
    })
        .then(response => {
            console.log(response.status);
            if(response.ok) {
                alert("Order processed successfully")
            } else {
                alert("Order processing failed")
            }
        })
        .catch((error) => {
            console.log("Order niet verwerkt. Fout: ", error)
        });
    document.getElementById("order").reset();
}
/* * * * * * * * * * * * * * * * * * * * */


/* * * * * * ASSETLIST DROPDOWN * * * * * */

/* wordt aangeroepen bij body.onload */
function initialize() {
    cryptoTitle.innerHTML = "";
    formTitle.innerHTML = "";
    const assetCodeParam = processUrlParam();
    getAssetList(assetCodeParam);
}

/* controleren en afhandelen URL parameter
*  functie geeft de parameter terug als die is meegegeven
*  of 0 (nul) als er geen of geen geldige parameter is gevonden*/
function processUrlParam () {
    const paramsData = window.location.search;
    const urlParameters = new URLSearchParams(paramsData);
    if(urlParameters.has('assetCode')) {
        let assetCodeParam = urlParameters.get('assetCode');
        if (typeof assetCodeParam === 'string') {
            const passedAssetCode = assetCodeParam.toLowerCase();
            console.log("uit URL als parameter gehaald: " + passedAssetCode);
            return passedAssetCode;
        }
    }
    return 0;
}

dropDownSelector.addEventListener('change', () => {
    executeDropDownEvents();
})

function executeDropDownEvents() {
    let selectedCryptoSymbol = dropDownSelector.options[dropDownSelector.selectedIndex].text;
    let selectedCryptoPrice= dropDownSelector.options[dropDownSelector.selectedIndex].value;
    console.log(selectedCryptoSymbol + " : " + selectedCryptoPrice);
    cryptoTitle.innerHTML = "";
    formTitle.innerHTML = "";
    cryptoTitle.innerHTML = "Buy " + selectedCryptoSymbol;
    formTitle.innerHTML = selectedCryptoSymbol;
    cryptoAmount.placeholder = "Enter " + selectedCryptoSymbol + "..."
    getAssetPriceByAssetCode();
    orderForm.reset();
}

function getAssetList(passedParameter) {
    /*  passedParameter checken: indien 0 dan als gewoonlijk drop down opbouwen */
    if (passedParameter === 0) {
        fetch('/assetoverviewbank', {
            method: 'GET',
            headers: {
                'Authorization': localStorage.getItem("token"),
                'Content-Type': 'application/json'
            }})
            .then((response) => response.json()).then(assetList => {
            console.log(assetList);
            dropDownSelector.length = 0;
            dropDownSelector.selectedIndex = 0;
            let option;

            assetList.forEach(function (value) {
                option = document.createElement('option');
                option.text = value.symbol.toUpperCase();
                option.value = value.current_price;
                /*TODO: Asset name helaas niet beschikbaar via assetoverviewbank. assetprice wordt gebruikt.
                   Hoe nu de crypto naam tonen hier?? */
                dropDownSelector.add(option);
                dropDownSelector.selectedIndex = 0;
                let firstCryptoInList = dropDownSelector.options[dropDownSelector.selectedIndex].text;
                let firstCryptoPriceInList = dropDownSelector.options[dropDownSelector.selectedIndex].value;
                cryptoTitle.innerHTML = "Buy " + firstCryptoInList;
                formTitle.innerHTML = firstCryptoInList;
                cryptoAmount.placeholder = "Enter " + firstCryptoInList + "..."
                priceField.innerHTML = firstCryptoPriceInList;
            })
        })
    } else {
        /*  passedParameter is niet 0 dus dropdown alleen vullen met de assetCode uit de parameter */
        dropDownSelector.length = 0;
        dropDownSelector.selectedIndex = 0;
        let option;
        option = document.createElement('option');
        option.text = passedParameter.toUpperCase();
        dropDownSelector.add(option);
        dropDownSelector.selectedIndex = 0;
        executeDropDownEvents();
    }
}
/* * * * * * END ASSETLIST DROPDOWN * * * * * */

/*TODO: Help schermpje met info tonen? */
const buttonHelp = document.getElementById("help");
buttonHelp.addEventListener("click",() => {
    /* showHelp(); */
})


