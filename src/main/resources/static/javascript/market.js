/* @author Fiona Gray */

fetch('/assetoverviewbank',{
        headers: {
                'Authorization': localStorage.getItem("token"),
                'Content-Type': 'application/json'
        },})
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

                // todo: image meegeven aan front?
                //let img = new Image();
                //img.src =  value.image.value
                //td1.appendChild(img);

                let assetCode = value.symbol.toUpperCase();
                let assetName = value.assetName;
                td1.textContent = assetCode + "      " + assetName;

                btn.type = "button";
                btn.className = "buy-button";
                btn.value = "Buy";
                btn.onclick = function(value) {
                        (window.location.href = ("/buy.html?assetCode=" + assetCode));
                }
                td1.appendChild(btn);

                let td2 = document.createElement('td');
                td2.textContent= new Intl.NumberFormat('nl-NL', { style: 'currency', currency: 'EUR' })
                    .format(value.currentPrice);
                tr.appendChild(td1);
                tr.appendChild(td2);
                tableBody.appendChild(tr);
            });
        }).catch(error => {
        console.log(error);
    });

/* * * * * * NAVIGATION * * * * * */
const menuButton = document.getElementsByClassName('menu-button')[0];
menuButton.addEventListener('click',() => {
        menuLinks.classList.toggle('active')
})
