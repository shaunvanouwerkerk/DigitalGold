fetch('/assetoverviewbank')
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

        // table data
        data.forEach(function (value) {
                let tr = document.createElement('tr');
                let td1 = document.createElement('td');
                let btn = document.createElement('input');

                td1.textContent = value.symbol.toUpperCase();

                btn.type = "button";
                btn.className = "buy-button";
                btn.value = "Buy";
                btn.onclick = function() {
                        (window.location.href = ("/buy.html?assetCode=" + td1.textContent));
                }
                td1.appendChild(btn);

                let td2 = document.createElement('td');
                td2.textContent= new Intl.NumberFormat('nl-NL', { style: 'currency', currency: 'EUR' })
                    .format(value.current_price);
                tr.appendChild(td1);
                tr.appendChild(td2);
                tableBody.appendChild(tr);
            });
        }).catch(error => {
        console.log(error);
    });
