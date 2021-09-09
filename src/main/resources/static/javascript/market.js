fetch('../assetOverviewBank')
    .then((response) => response.json()).then(data =>
        {console.log(data);
        let tr = '';
        let tableBody = document.querySelector('#AssetOverview');

        // table headers
        let trh = document.createElement('tr');
        let th1 = document.createElement('th');
        th1.textContent = "Asset";
        let th2 = document.createElement('th');
        th2.textContent = "Price";
        trh.appendChild(th1);
        trh.appendChild(th2);
        tableBody.appendChild(trh);

        // tabel data
        data.forEach(function (value) {
                let tr = document.createElement('tr');
                let td1 = document.createElement('td');
                let btn = document.createElement('input');

                btn.type = "button";
                btn.className = "buy-button";
                btn.value =  "Buy"
                btn.onclick = function() {
                        (window.location.href = "../order.html");
                }
                td1.textContent = value.assetCode;
                td1.appendChild(btn);

                let td2 = document.createElement('td');
                td2.textContent= new Intl.NumberFormat('nl-NL', { style: 'currency', currency: 'EUR' }).format(value.price);
                tr.appendChild(td1);
                tr.appendChild(td2);
                tableBody.appendChild(tr);
            });

                console.log(new Intl.NumberFormat('de-DE', { style: 'currency', currency: 'EUR' }).format(number));

        }).catch(error => {
        console.log(error);
    });

function redirectBuyPage() {
        document.location.href = "../portfolio"
}
/*
var btn = document.createElement('input');
btn.type = "button";
btn.className = "btn";
btn.value = entry.email;
btn.onclick = (function(entry) {return function() {chooseUser(entry);}})(entry);
td.appendChild(btn);*/
