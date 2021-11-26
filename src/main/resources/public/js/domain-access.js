let url = contextRoot + "santas";

async function loadSantas() {
    console.log(url);
    const response = await fetch(url, {
        headers: {
            "Accept":"application/json"
        }
    });
    const santas = await response.json();
    console.log(santas);
    /* addToElement(santas); */
};

const addToElement = data => {
    data.forEach(santa => {
        console.log(santa.firstName);//hakee tällä hetkellä ainoastaan etunimen
        const divElement = document.createElement("div");
        divElement.id = "card";
        const headerElement = document.createElement("h3");
        headerElement.innerText = santa.firstName;

        const figureElement = document.createElement("figure");
        figureElement.id = "card-figure";

        divElement.appendChild(figureElement);
        divElement.appendChild(headerElement);

        document.getElementById("santa-cards").appendChild(divElement);
        
    });
};