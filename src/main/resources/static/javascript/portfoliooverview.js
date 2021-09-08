fetch('../portfoliovalueoverview')
    .then((response) => response.json()).then(data => {
    let tr = '';
    let tableBody = document.querySelector('#portfoliovalueoverview');
    data.forEach(function (value) {
        let tr = document.createElement('tr');
        let td =document.createElement('td');
        td.textContent = value.date;
        let td2 =document.createElement('td');
        td2.textContent = new Intl.NumberFormat('nl-NL', { style: 'currency', currency: 'EUR' }).format( value.portfolioValue);
        tr.appendChild(td);
        tr.appendChild(td2);
        tableBody.appendChild(tr);
    });

}).catch(error => {
});

fetch('../portfolioassetoverview')
    .then((response) => response.json()).then(data => {
    let tr = '';
    let tableBody = document.querySelector('#portfolioassetoverview');
    data.forEach(function (value) {
        console.log(value);
        let tr = document.createElement('tr');
        let td =document.createElement('td');
        td.textContent = value.assetName;
        let td2 =document.createElement('td');
        td2.textContent = value.assetCode;
        let td3 =document.createElement('td');
        td3.textContent = new Intl.NumberFormat('nl-NL', { style: 'currency', currency: 'EUR' }).format(value.currentPrice);
        let td4 =document.createElement('td');
        td4.textContent = value.amountOfAsset;
        let td5 =document.createElement('td');
        td5.textContent = new Intl.NumberFormat('nl-NL', { style: 'currency', currency: 'EUR' }).format(value.assetTotalValue);
        tr.appendChild(td);
        tr.appendChild(td2);
        tr.appendChild(td3);
        tr.appendChild(td4);
        tr.appendChild(td5);
        tableBody.appendChild(tr);
    });

}).catch(error => {
});

fetch('../portfoliovalueoverviewtoday')
    .then((response) => response.json()).then(data => {
    let tableBody = document.querySelector('#portfoliovalueoverviewtoday');
    let tr = document.createElement('tr');
    let td = document.createElement('td');
    td.textContent =  "Portfolio Value";
    let td2 = document.createElement('td');
    td2.textContent = new Intl.NumberFormat('nl-NL', { style: 'currency', currency: 'EUR' }).format(data.portfolioValue);
    tr.appendChild(td);
    tr.appendChild(td2);
    tableBody.appendChild(tr);
}).catch(error => {
});