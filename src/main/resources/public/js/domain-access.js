let url = contextRoot;

async function loadSantas(path) {
    /* console.log(url+"santa-users"); */
    let response = await fetch(url+path, {
        headers: {
            "Accept":"application/json"
        }
    });
    let santas = await response.json();
    /* console.log(santas); */
    addToElement(santas);
};

const addToElement = data => {
    data.forEach(santa => {
        //console.log(santa.firstName);//hakee tällä hetkellä ainoastaan etunimen
        const divElement = document.createElement("div");
        divElement.id = "card";
        const headerElement = document.createElement("h3");
        headerElement.innerText = santa.santaProfileName;//hakee santa-profilen kautta profiilin nimen

        const figureElement = document.createElement("figure");
        figureElement.id = "card-figure";

        divElement.appendChild(figureElement);
        divElement.appendChild(headerElement);

        document.getElementById("santa-cards").appendChild(divElement);
        
    });
};

async function loadTest() {
    /* console.log(url+"testi"); */
    let response = await fetch(url+"santas/available", {
        headers: {
            "Accept":"application/json"
        }
    });
    let santas = await response.json();
    console.log(santas);
};