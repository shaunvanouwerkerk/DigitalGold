/*
@author David Truijens - scripts voor orderpagina Buy
 */

/* * * * * * NAVIGATION * * * * * */
const menuButton = document.getElementsByClassName('menu-button')[0];
const menuLinks = document.getElementsByClassName('nav-links')[0];

menuButton.addEventListener('click',() => {
    menuLinks.classList.toggle('active')
})
/* * * * * *  END NAVIGATION * * * * * */

const selector = document.getElementById("coinselector");
const priceField = document.getElementById("price");

/* * * * * * CALCULATOR * * * * * */

/* * * * van gekozen crypto prijs ophalen en doorgeven aan calculator * * * */


function getAssetPriceByAssetCode () {
    fetch('/assetoverviewbank', {
        method: 'GET',
        headers: {
            'Authorization': localStorage.getItem("token"),
            'Content-Type': 'application/json'
        }})
        .then((response) => response.json()).then(assetData => {
        console.log(assetData);
        const crypto = selector.options[selector.selectedIndex].text.toLowerCase();
        console.log(crypto);
        assetData.forEach(function (value) {
            if (value.symbol === crypto) {
                console.log("Assetcode gevonden " + value.symbol + " : " + value.current_price)
                priceField.innerHTML = value.current_price;
            }
            })
        })
}

const cryptoValue = document.querySelector('#cryptoValue');
const cryptoAmount = document.querySelector('#cryptoAmount');

calc('crypto')
cryptoValue.onkeyup = ()=>calc()
cryptoValue.onchange = ()=>calc()
cryptoAmount.onkeyup = ()=>calc('crypto')
cryptoAmount.onchange = ()=>calc('crypto')

/* TODO: Functie moet straks op basis van assetprice data ipv API Coindesk */
function calc(changer){
    const currency = "USD";
    const cryptoAmt = Number(cryptoAmount.value);
    const cryptoVal = Number(cryptoValue.value);
    const exchangeRate = parseFloat(document.getElementById("price").innerHTML);

    console.log(exchangeRate)
    if(changer==='crypto'){
        const amount =  cryptoAmt * exchangeRate
        cryptoValue.value = parseFloat(amount).toFixed(2)
    }else{
        const amount =  cryptoVal / exchangeRate
        cryptoAmount.value = amount
    }
}
/* * * * * * END CALCULATOR * * * * * */


/* * * * * * POST ORDER * * * * * */

const buttonPostBuyOrder = document.getElementById("postButton");
buttonPostBuyOrder.addEventListener("click",() => {
    postRequest();
})

function postRequest() {
    let selectedCryptoSymbol = selector.options[selector.selectedIndex].text;
    let selectedCryptoAmount = document.getElementById("cryptoAmount").value;

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
}
/* * * * * * END POST ORDER * * * * * */


/* * * * * * ASSETLIST DROPDOWN * * * * * */

/* wordt aangeroepen bij body.onload */
function initialize() {
    document.getElementById("crypto-title").innerHTML = "";
    document.getElementById("form-title").innerHTML = "";
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

            /* pararmeter gevonden dus teruggeven */
            return passedAssetCode;
        }
    }
    /* geen parameter of geen geldige parameter dan 0 teruggeven */
    return 0;
}




selector.addEventListener('change', () => {
    executeDropDownEvents();
})

function executeDropDownEvents() {
    let selectedCryptoSymbol = selector.options[selector.selectedIndex].text;
    let selectedCryptoPrice= selector.options[selector.selectedIndex].value;
    console.log(selectedCryptoSymbol + " : " + selectedCryptoPrice);
    document.getElementById("crypto-title").innerHTML = "";
    document.getElementById("form-title").innerHTML = "";
    document.getElementById("crypto-title").innerHTML = "Buy " + selectedCryptoSymbol;
    document.getElementById("form-title").innerHTML = selectedCryptoSymbol;
    document.getElementById("cryptoAmount").placeholder = "Enter " + selectedCryptoSymbol + "..."
    /*document.getElementById("price").innerHTML = selectedCryptoPrice;*/
    getAssetPriceByAssetCode();
    document.getElementById("order").reset();
}


/*TODO: Help schermpje met info tonen. */
const buttonHelp = document.getElementById("help");
buttonHelp.addEventListener("click",() => {
    /* showHelp(); */
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
            let dropdown = document.getElementById("coinselector");
            dropdown.length = 0;
            dropdown.selectedIndex = 0;
            let option;

            assetList.forEach(function (value) {
                option = document.createElement('option');
                option.text = value.symbol.toUpperCase();
                option.value = value.current_price;
                /*TODO: Asset name helaas niet beschikbaar via assetoverviewbank. assetprice wordt gebruikt.
                   Hoe nu de crypto naam tonen hier?? */
                dropdown.add(option);
                dropdown.selectedIndex = 0;
                let firstCryptoInList = dropdown.options[dropdown.selectedIndex].text;
                let firstCryptoPriceInList = dropdown.options[dropdown.selectedIndex].value;
                document.getElementById("crypto-title").innerHTML = "Buy " + firstCryptoInList;
                document.getElementById("form-title").innerHTML = firstCryptoInList;
                document.getElementById("cryptoAmount").placeholder = "Enter " + firstCryptoInList + "..."
                document.getElementById("price").innerHTML = firstCryptoPriceInList;
            })
        })
    } else {
        /*  passedParameter is niet 0 dus dropdown alleen vullen met de assetCode uit de parameter */
        let dropdown = document.getElementById("coinselector");
        dropdown.length = 0;
        dropdown.selectedIndex = 0;
        let option;
        option = document.createElement('option');
        option.text = passedParameter.toUpperCase();
        dropdown.add(option);
        dropdown.selectedIndex = 0;
        executeDropDownEvents();
    }
}
/* * * * * * END ASSETLIST DROPDOWN * * * * * */



