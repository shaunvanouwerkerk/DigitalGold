const menuButton = document.getElementsByClassName('menu-button')[0];
const menuLinks = document.getElementsByClassName('nav-links')[0];

menuButton.addEventListener('click',() => {
    menuLinks.classList.toggle('active')
})

function initialize() {
    document.getElementById("crypto-title").innerHTML += ("Buy Bitcoin (BTC)");
    document.getElementById("form-title").innerHTML = "Bitcoin";
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

const cryptoValue = document.querySelector('#cryptoValue');
const cryptoAmount = document.querySelector('#cryptoAmount');


calc('crypto')
cryptoValue.onkeyup = ()=>calc()
cryptoValue.onchange = ()=>calc()
cryptoAmount.onkeyup = ()=>calc('crypto')
cryptoAmount.onchange = ()=>calc('crypto')

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

const buttonLoadCrypto = document.getElementById("loadcrypto");
buttonLoadCrypto.addEventListener("click",() => {
    getCryptoListFromApi();
})

async function getCryptoListFromApi() {
    const response = await fetch('https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=20&page=1&sparkline=false',
        {
            headers: {
                'accept':'application/json'
            }
        });
    let cryptoList = await response.json();
    console.info(cryptoList);

    let dropdown = document.getElementById("coinselector");
    dropdown.length = 0;
    dropdown.selectedIndex = 0;

    let option;
    for (let i = 0; i < cryptoList.length; i++) {
        option = document.createElement('option');
        option.text = cryptoList[i].name;
        option.value = cryptoList[i].symbol;
        dropdown.add(option);
    }
}

const buttonPostBuyOrder = document.getElementById("postButton");
buttonPostBuyOrder.addEventListener("click",() => {
    postRequest();
})



function postRequest() {
    let selectedCryptoSymbol = selector.options[selector.selectedIndex].value;
    let selectedCryptoAmount = document.getElementById("cryptoAmount").value;

    let orderData = {assetCode: selectedCryptoSymbol, amountOfAsset: selectedCryptoAmount , type: "buy", limit: 0}; // javascript object Order, matches java Order

    fetch('http://localhost:8080/order', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(orderData)  // moet worden omgezet naar een string
    })
        .then(response => {
            console.log(response)
            return response.json() }
        )
        .then(json => {
            console.log(json);
        })
        .catch((error) => {
            console.error('Foutje', error);
        });
}



