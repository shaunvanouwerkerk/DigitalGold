/*
@author David Truijens - scripts voor orderpagina
 */

/* * * * * * NAVIGATION * * * * * */
const menuButton = document.getElementsByClassName('menu-button')[0];
const menuLinks = document.getElementsByClassName('nav-links')[0];

menuButton.addEventListener('click',() => {
    menuLinks.classList.toggle('active')
})
/* * * * * *  END NAVIGATION * * * * * */


/* * * * * * CALCULATOR * * * * * */
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
    const res = await fetch(`https://api.coindesk.com/v1/bpi/currentprice/${currency.toLowerCase()}.json`) //bpi = bitcoin price index dus BTC only!

    const json = await res.json()
    const exchangeRate = parseInt(json.bpi[currency].rate_float)
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

/* TODO: moet worden aangeroepen bij onload */
function initialize() {
    let previousPage = document.referrer;
    console.log(previousPage);
    document.getElementById("crypto-title").innerHTML += ("Buy Bitcoin (BTC)");
    document.getElementById("form-title").innerHTML = "Bitcoin";
    getAssetList();
}

const selector = document.getElementById("coinselector");

selector.addEventListener('change', () => {
    let selectedCryptoName = selector.options[selector.selectedIndex].text;
    let selectedCryptoSymbol = selector.options[selector.selectedIndex].value.toUpperCase();
    console.log(selectedCryptoName + selectedCryptoSymbol);
    document.getElementById("crypto-title").innerHTML = "";
    document.getElementById("form-title").innerHTML = "";
    document.getElementById("crypto-title").innerHTML = "Buy " + selectedCryptoName + "( " + selectedCryptoSymbol + " )";
    document.getElementById("form-title").innerHTML = selectedCryptoName;
})

/*TODO: Tijdelijk dropdown laden via button --> moet onload worden. */
const buttonLoadCrypto = document.getElementById("loadcrypto");
buttonLoadCrypto.addEventListener("click",() => {
    getAssetList();
})

function getAssetList() {
    const response = fetch('/assetoverviewbank')
        let assetList = response.json();
    let dropdown = document.getElementById("coinselector");
    dropdown.length = 0;
    dropdown.selectedIndex = 0;

    let option;
    for (let i = 0; i < assetList.length; i++) {
        option = document.createElement('option');
        option.text = assetList.assetCode;
        /*TODO: Asset name helaas niet beschikbaar via assetoverviewbank. assetprice wordt gebruikt.
           Hoe nu de crypto naam tonen hier?? */
        dropdown.add(option);
    }
}
/* * * * * * END ASSETLIST DROPDOWN * * * * * */



