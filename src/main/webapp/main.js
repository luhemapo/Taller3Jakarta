var rowId = 0;


document.getElementById("foto-button").onclick = function() {
    rowId += 1;

    let foto = {
        dateInput: document.getElementById("foto-button").value,
    }

    let tr = document.createElement("tr");
    tr.setAttribute("id", "row-" + rowId);

    let tdId = document.createElement("td")
    tdId.innerHTML = rowId;
    tr.appendChild(tdId);

    Object.keys(foto).forEach((key)=> {
        console.log(key);

        let td = document.createElement("td")
        td.innerHTML = foto[key];

        tr.appendChild(td);

    });

    let tdActions = document.createElement("td")

    let input = document.createElement("input");
    input.setAttribute("id", "delete-" + rowId);
    input.setAttribute("type", "button");
    input.value = "Eliminar";
    input.onclick = function(){
        let id = this.getAttribute("id");
        id = +id.replace("delete-", "");

        document.getElementById("row-" + id).remove();



    };

    tdActions.appendChild(input);

    tr.appendChild(tdActions);

    document.getElementById("body-table").appendChild(tr);

};