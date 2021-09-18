fetch('../portfoliovalueoverview',{
    headers: {
        'Authorization': localStorage.getItem("token"),
        'Content-Type': 'application/json'
    },})
    .then((response) => response.json()).then(data => {
    let tr = '';
    let tableBody = document.querySelector('#portfoliovalueoverview');

    // table headers
    let trh = document.createElement('tr');
    let th1 = document.createElement('th');
    th1.textContent = "Date";
    let th2 = document.createElement('th');
    th2.textContent = "Portfolio value";
    trh.appendChild(th1);
    trh.appendChild(th2);
    tableBody.appendChild(trh);

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

fetch('../portfolioassetoverview',{
    headers: {
        'Authorization': localStorage.getItem("token"),
        'Content-Type': 'application/json'
    },})
    .then((response) => response.json()).then(data => {
    let tr = '';
    let tableBody = document.querySelector('#portfolioassetoverview');

    // table headers
    let trh = document.createElement('tr');
    let th1 = document.createElement('th');
    th1.textContent = "Name";
    let th2 = document.createElement('th');
    th2.textContent = " ";
    let th3 = document.createElement('th');
    th3.textContent = "Price";
    let th4 = document.createElement('th');
    th4.textContent = "Amount";
    let th5 = document.createElement('th');
    th5.textContent = "Total Value";
    trh.appendChild(th1);
    trh.appendChild(th2);
    trh.appendChild(th3);
    trh.appendChild(th4);
    trh.appendChild(th5);
    tableBody.appendChild(trh);

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

fetch('../portfoliovalueoverviewtoday',{
    headers: {
        'Authorization': localStorage.getItem("token"),
        'Content-Type': 'application/json'
    },})
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
