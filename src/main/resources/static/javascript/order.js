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


/* * * * * * CALCULATOR * * * * * */

/* * * * van gekozen crypto prijs ophalen en doorgeven aan calculator * * * */


function getAssetPriceByAssetCode (crypto) {
    fetch('/assetoverviewbank')
        .then((response) => response.json()).then(assetData => {
        console.log(assetData);
        console.log(crypto);

        assetData.forEach(function (value) {
            if (value.symbol === crypto) {
                console.log("Assetcode gevonden " + value.symbol + " : " + value.current_price)
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
async function calc(changer){
    const currency = "USD"
    const cryptoAmt = Number(cryptoAmount.value)
    const cryptoVal = Number(cryptoValue.value)
    let exchangeRate = executeDropDownEvents();

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
    let selectedCryptoSymbol = selector.options[selector.selectedIndex].value;
    let selectedCryptoAmount = document.getElementById("cryptoAmount").value;

    let orderData = {assetCode: selectedCryptoSymbol, amountOfAsset: selectedCryptoAmount , type: "buy", limit: 0}; // javascript object Order, matches java Order

    fetch('/order', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
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
    let previousPage = document.referrer;
    console.log(previousPage);
    document.getElementById("crypto-title").innerHTML = "";
    document.getElementById("form-title").innerHTML = "";
    getAssetList();
}


const selector = document.getElementById("coinselector");

selector.addEventListener('change', () => {
    executeDropDownEvents();
})

function executeDropDownEvents() {
    let selectedCryptoName = selector.options[selector.selectedIndex].text;
    let selectedCryptoSymbol = selector.options[selector.selectedIndex].value.toUpperCase();
    console.log(selectedCryptoName + selectedCryptoSymbol);
    document.getElementById("crypto-title").innerHTML = "";
    document.getElementById("form-title").innerHTML = "";
    document.getElementById("crypto-title").innerHTML = "Buy " + selectedCryptoName;
    document.getElementById("form-title").innerHTML = selectedCryptoName;
    return getAssetPriceByAssetCode(selectedCryptoSymbol);
}


/*TODO: Help schermpje met info tonen. */
const buttonHelp = document.getElementById("help");
buttonHelp.addEventListener("click",() => {
    getAssetPriceByAssetCode("xrp");
    /* showHelp(); */
})

function getAssetList() {
    fetch('/assetoverviewbank')
        .then((response) => response.json()).then(assetList => {
            console.log(assetList);
            let dropdown = document.getElementById("coinselector");
            dropdown.length = 0;
            dropdown.selectedIndex = 0;
            let option;

            assetList.forEach(function (value) {
                option = document.createElement('option');
                option.text = value.symbol.toUpperCase();
                /*TODO: Asset name helaas niet beschikbaar via assetoverviewbank. assetprice wordt gebruikt.
                   Hoe nu de crypto naam tonen hier?? */
                dropdown.add(option);
                dropdown.selectedIndex = 0;
                let firstCryptoInList = dropdown.options[selector.selectedIndex].text;
                document.getElementById("crypto-title").innerHTML = "Buy " + firstCryptoInList;
                document.getElementById("form-title").innerHTML = firstCryptoInList;
            })
        })
}
/* * * * * * END ASSETLIST DROPDOWN * * * * * */



