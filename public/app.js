

window.onload = async function() {
    const table = document.querySelector("table");
    const tBody = table.querySelector("tbody");
    const timeSeries = await fetch("stocks/intraday").then((response) => response.json());

    timeSeries.forEach((stockPriceInfo) => {
        const tr = createTableEntry(stockPriceInfo);
        tBody.appendChild(tr);
    });

    const webSocket = new WebSocket("ws://localhost:9090/stocks/updates");

    webSocket.onmessage = function(message) {
        const stockPriceInfo = JSON.parse(message.data);
        console.log("Received update", stockPriceInfo);
        const tr = createTableEntry(stockPriceInfo);
        tBody.appendChild(tr);
    }

    webSocket.onclose = function () {
        console.log("Connection closed");
    }
}

function createTableEntry({updateDate, open, close, high, low, volume}) {
    const tr = document.createElement("tr");
    tr.appendChild(createTd(updateDate));
    tr.appendChild(createTd(open));
    tr.appendChild(createTd(close));
    tr.appendChild(createTd(high));
    tr.appendChild(createTd(low));
    tr.appendChild(createTd(volume));

    return tr;
}

function createTd(text) {
    const td = document.createElement("td");
    td.innerText = text;
    return td;
}

