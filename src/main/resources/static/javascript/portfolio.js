fetch('/portfoliovalueoverview',{
    headers: {
        'Authorization': localStorage.getItem("token"),
        'Content-Type': 'application/json'
    },})
    .then((response) => response.json()).then(data => {
    let tableBody = document.querySelector('#portfoliovalueoverview');

    data.forEach(function (object) {
        let tr = document.createElement('tr');
        let td =document.createElement('td');
        td.style.textAlign = "center"
        td.textContent = object.date;
        let td2 =document.createElement('td');
        td2.style.textAlign = "right"
        td2.textContent = new Intl.NumberFormat('nl-NL', { style: 'currency', currency: 'EUR' }).format
        (object.portfolioValue);
        tr.appendChild(td);
        tr.appendChild(td2);
        tableBody.appendChild(tr);
    });
}).catch(error => {
});

fetch('../portfolioassetoverview',{
    headers: {
        'Authorization': localStorage.getItem("token"),
        'Content-Type': 'application/json'
    },})
    .then((response) => response.json()).then(data => {
    let tr = '';
    let tableBody = document.querySelector('#portfolioassetoverview');

    data.forEach(function (object2) {
        let tr = document.createElement('tr');
        let td =document.createElement('td');
        td.textContent = object2.assetName
        let td2 =document.createElement('td');
        td2.textContent = object2.assetCode;
        let td3 =document.createElement('td');
        td3.style.textAlign = "right"
        td3.textContent = new Intl.NumberFormat('nl-NL', { style: 'currency', currency: 'EUR' })
            .format(object2.currentPrice);
        let td4 =document.createElement('td');
        td4.style.textAlign = "center"
        td4.textContent = object2.amountOfAsset;
        let td5 =document.createElement('td');
        td5.style.textAlign = "right"
        td5.textContent = new Intl.NumberFormat('nl-NL', { style: 'currency', currency: 'EUR' })
            .format(object2.assetTotalValue);
        tr.appendChild(td);
        tr.appendChild(td2);
        tr.appendChild(td3);
        tr.appendChild(td4);
        tr.appendChild(td5);
        tableBody.appendChild(tr);
    });
}).catch(error => {
});

const portfoliovalueField = document.getElementById("portfoliovalue")

fetch('/portfoliovalueoverviewtoday', {
    method: 'GET',
    headers: {
        'Authorization': localStorage.getItem("token"),
        'Content': 'application/json'
    },
})
    .then((response) => response.json())
    .then( data => {
        portfoliovalue.innerHTML = new Intl.NumberFormat('nl-NL', {minimumFractionDigits:2,
            maximumFractionDigits: 2}).format(data.portfolioValue);
        console.log(data);
    }).catch((error) => {
    console.error('Error', error);
});

const balanceField = document.getElementById("balance")

    fetch('/balance', {
        method: 'GET',
        headers: {
            'Authorization': localStorage.getItem("token"),
            'Content': 'application/json'
        },
    })
        .then((response) => response.json())
        .then( data => {
            balanceField.innerHTML = new Intl.NumberFormat('nl-NL', { minimumFractionDigits:2,
                maximumFractionDigits: 2}).format(data);
            console.log(data);
        }).catch((error) => {
        console.error('Error', error);
    });

