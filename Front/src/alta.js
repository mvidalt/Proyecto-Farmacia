function getPatiens() {
    var email = sessionStorage.getItem('email');
    var session = sessionStorage.getItem('session');
    var http = new XMLHttpRequest();
    http.open("GET", "http://localhost:3000/Back/ServePatients?email=" + email + "&session=" + session, true);

    http.onreadystatechange = function () {
        if (http.readyState === XMLHttpRequest.DONE) {
            if (http.status === 200) {
                //Esperando a saber que es la respuesta del servidor
                var response = http.responseText;
            } else {
                console.error('Error en la petición al backend:', http.status);
            }

        }
    }
    http.send();

}

function getMedicines() {
    var email = sessionStorage.getItem('email');
    var session = sessionStorage.getItem('session');

    var http = new XMLHttpRequest();
    http.open("GET", "http://localhost:3000/Back/ServeMedicines?email=" + email + "&session=" + session, true);

    http.onreadystatechange = function () {
        if (http.readyState === XMLHttpRequest.DONE) {
            if (http.status === 200) {
                //Esperando a saber que es la respuesta del servidor
                var response = http.responseText;
            } else {
                console.error('Error en la petició al backend:', http.status);
            }
        }
    };

    http.send();
}

function enviar() {
    var email = sessionStorage.getItem('email');
    var session = sessionStorage.getItem('session');

    var idXip = "...;"; // Valor de la idXip
    var mailP = "...;;" // Valor del mailP
    var idMed = "...;"; // Valor de la idMed
    var dateLimit = "...;"; // Valor de la dateLimit

    var data = {
        email: email,
        session: session,
        idXip: idXip,
        mailP: mailP,
        idMed: idMed,
        dateLimit: dateLimit
    };

    var http = new XMLHttpRequest();
    http.open("POST", "http://localhost:3000/DAMTomcat/Release", true);
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");


    http.onreadystatechange = function () {
        if (http.readyState === XMLHttpRequest.DONE) {
            if (http.status === 200) {
                var response = http.responseText;
            } else {
                console.error('Error en la petició al backend:', http.status);
            }
        }
    };

    var jsonData = JSON.stringify(data);


    http.send(jsonData);


}