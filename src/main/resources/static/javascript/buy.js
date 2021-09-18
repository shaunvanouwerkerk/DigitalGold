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
const balanceField = document.getElementById("balance")

/* * * * * * * * * * * * * * * * */


/* * * * * * BALANCE * * * * * * */

function showAccountBalance () {

    fetch('/balance', {
        method: 'GET',
        headers: {
            'Authorization': localStorage.getItem("token"),
            'Content': 'application/json'
        },
    })
        .then((response) => response.json())
        .then( data => {
            balanceField.innerHTML = data;
            console.log(data);
        }).catch((error) => {
        console.error('Error', error);
    });
}
/* * * * * * * * * * * * * * * * */



/* * * * * * NAVIGATION * * * * * */
menuButton.addEventListener('click',() => {
    menuLinks.classList.toggle('active')
})
/* * * * * * * * * * * * * * * * */


/* * * * * * CALCULATOR * * * * * */

/* * * * van gekozen crypto de info ophalen om te kunnen doorgeven aan calculator en aan de DOM * * * */
function getAssetDetailsByAssetCode () {
    fetch('/assetoverviewbank', {
        method: 'GET',
        headers: {
            'Authorization': localStorage.getItem("token"),
            'Content-Type': 'application/json'
        }
    })
        .then((response) => response.json()).then(assetData => {
            console.log(assetData);
            const crypto = dropDownSelector.options[dropDownSelector.selectedIndex].value;
            console.log(crypto);
            assetData.every(function (value) {
                if (value.symbol === crypto) {
                    console.log("Assetcode gevonden " + value.symbol + "  :  " + value.currentPrice);
                    priceField.textContent = value.currentPrice;
                    formTitle.textContent = value.assetName;
                    cryptoTitle.textContent = "Buy " + value.assetName + " (" + value.symbol + ")";
                    cryptoAmount.placeholder = "Enter " + value.symbol + "...";
                    orderForm.reset();
                    return false;
                }
                return true;
            })
        })
}

calc('crypto')
cryptoValue.onkeyup = ()=>calc()
cryptoValue.onchange = ()=>calc()
cryptoAmount.onkeyup = ()=>calc('crypto')
cryptoAmount.onchange = ()=>calc('crypto')

function calc(changer){
    const cryptoAmt = Number(cryptoAmount.value);
    const cryptoVal = Number(cryptoValue.value);
    let exchangeRate = parseFloat(document.getElementById("price").textContent);
    if(changer==='crypto'){
        const amount =  cryptoAmt * exchangeRate
        cryptoValue.value = parseFloat(amount).toFixed(2)
    }else{
        /*cryptoAmount.value = cryptoVal / exchangeRate*/
        cryptoAmount.value = Number(cryptoVal / exchangeRate).toFixed(3)
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
    orderForm.reset();
})

function validateOrderInput() {
    let selectedCryptoSymbol = dropDownSelector.options[dropDownSelector.selectedIndex].text;
    return Number(cryptoValue.value) > 0 && Number(cryptoAmount.value) > 0 && selectedCryptoSymbol !== "";
}

function postRequest() {
    let selectedCryptoSymbol = dropDownSelector.options[dropDownSelector.selectedIndex].value;
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
                showAccountBalance();
            } else {
                alert("Order processing failed")
            }
        })
        .catch((error) => {
            console.log("Order niet verwerkt. Fout: ", error)
        });
}
/* * * * * * * * * * * * * * * * * * * * */


/* * * * * * ASSETLIST DROPDOWN * * * * * */

/* wordt aangeroepen bij body.onload */
function initialize() {
    showAccountBalance();
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
            const passedAssetCode = assetCodeParam.toUpperCase();
            console.log("uit URL als parameter gehaald: " + passedAssetCode);
            return passedAssetCode;
        }
    }
    return 0;
}

dropDownSelector.addEventListener('change', () => {
    getAssetDetailsByAssetCode();
})

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
                option.text = value.symbol + "  -  " + value.assetName /* bitcoin - btc*/
                option.value = value.symbol; /* btc */
                dropDownSelector.add(option);
            })
                dropDownSelector.selectedIndex = 0;
                getAssetDetailsByAssetCode();
            })
    } else {
        /*  passedParameter is niet 0 dus dropdown alleen vullen met de assetCode uit de parameter */
        dropDownSelector.length = 0;
        dropDownSelector.selectedIndex = 0;
        let option;
        option = document.createElement('option');
        option.value = passedParameter;
        option.text = passedParameter;
        dropDownSelector.add(option);
        dropDownSelector.selectedIndex = 0;
        getAssetDetailsByAssetCode();
    }
}
/* * * * * * END ASSETLIST DROPDOWN * * * * * */



