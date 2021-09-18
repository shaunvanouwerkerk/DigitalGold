/*
@author David Truijens, Sandra Turina
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

let balanceField = getCurrentBalance();


/* * * * * * BALANCE * * * * * * */
function getCurrentBalance () {
    fetch('/balance', {
        method: 'GET',
        headers: {
            'Authorization': localStorage.getItem("token"),
            'Content': 'application/json'
        },
    })
        .then((response) => response.json())
        .then( data => {
            balanceField = data;
            document.getElementById("balance").innerHTML = balanceField;
            console.log(balanceField);
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

/* * * * van gekozen crypto prijs ophalen om te kunnen doorgeven aan calculator * * * */
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
                cryptoTitle.textContent = "Sell " + value.assetName + " (" + value.symbol + ")";
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
    const exchangeRate = parseFloat(document.getElementById("price").textContent);

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
        orderForm.reset();
    }
})

function validateOrderInput() {
    let selectedCryptoSymbol = dropDownSelector.options[dropDownSelector.selectedIndex].text;
    return Number(cryptoValue.value) > 0 && Number(cryptoAmount.value) > 0 && selectedCryptoSymbol !== "";
}

function postRequest() {
    let selectedCryptoSymbol = dropDownSelector.options[dropDownSelector.selectedIndex].value;
    let selectedCryptoAmount = cryptoAmount.value;

    // javascript object Order, matches java Order
    let orderData = {assetCode: selectedCryptoSymbol, amountOfAsset: selectedCryptoAmount , type: "sell", limit: 0};

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
                getCurrentBalance();
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
    getAssetListPortfolio();
}


dropDownSelector.addEventListener('change', () => {
    getAssetDetailsByAssetCode();
})

/*laden van list van assets die in portfolio zitten */
function getAssetListPortfolio() {
        fetch('/portfolioassetoverview', {
            headers: {
                'Authorization': localStorage.getItem("token"),
                'Content-Type': 'application/json'
        }})
        .then((response) => response.json()).then(assetListPortfolio => {
            console.log(assetListPortfolio);
            dropDownSelector.length = 0;
            dropDownSelector.selectedIndex = 0;
            let option;

            assetListPortfolio.forEach(function (value) {
                option = document.createElement('option');
                option.text = value.assetCode + "  -  " + value.assetName;
                option.value = value.assetCode;
                dropDownSelector.add(option);
            })
            dropDownSelector.selectedIndex = 0;
            getAssetDetailsByAssetCode();
        })
}
/* * * * * * END ASSETLIST DROPDOWN * * * * * */

