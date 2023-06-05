function getPatiens() {
    var email = sessionStorage.getItem('email');
    var session = sessionStorage.getItem('session');
    var http = new XMLHttpRequest();
    http.open("GET", "http://localhost:3000/Farmaciaa/ServePatients?email=" + email + "&session=" + session, true);

    http.onreadystatechange = function () {
        if (http.readyState === XMLHttpRequest.DONE) {
            if (http.status === 200) {
                var responseData = http.responseText;

                var parsedResponse;
                try {
                parsedResponse = JSON.parse(responseData);
                } catch (error) {
                console.error("Error parsing JSON response:", error);
                }

                var selectElement = document.getElementById("patientsSelect");

                selectElement.innerHTML = '';

                if (Array.isArray(parsedResponse)) {

                    parsedResponse.forEach(function(item) {

                    var option = document.createElement("option");
                    
                    option.value = item.mail;
                    option.textContent = item.mail;
                    
                    selectElement.appendChild(option);
                });
                } else {
                console.error("Invalid response format. Expected an array.");
                }

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
    http.open("GET", "http://localhost:3000/Farmaciaa/ServeMedicines?email=" + email + "&session=" + session, true);

    http.onreadystatechange = function () {
        if (http.readyState === XMLHttpRequest.DONE) {
            if (http.status === 200) {
                
                var responseData = http.responseText;
                var parsedResponse;
                try {
                parsedResponse = JSON.parse(responseData);
                } catch (error) {
                console.error("Error parsing JSON response:", error);
                }

                var selectElement = document.getElementById("medicinesSelect");

                selectElement.innerHTML = '';

                if (Array.isArray(parsedResponse)) {
                parsedResponse.forEach(function(item) {
                    var option = document.createElement("option");
                    
                    option.value = item.id;
                    option.textContent = item.name;
                    
                    selectElement.appendChild(option);
                });
                } else {
                console.error("Invalid response format. Expected an array.");
                }
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
    let mailP = document.getElementById("patientsSelect").value;
    let idXip = document.getElementById("xipIdInput").value;
    let idMed = document.getElementById("medicinesSelect").value;
    let dateLimit = document.getElementById("dateInput").value;

    var fechaActual = new Date();

    // Obtener los componentes de la fecha
    var anio = fechaActual.getFullYear();
    var mes = ('0' + (fechaActual.getMonth() + 1)).slice(-2);
    var dia = ('0' + fechaActual.getDate()).slice(-2); 

    var fechaFormateada = anio + '-' + mes + '-' + dia;

    if (dateLimit < fechaFormateada){
        document.getElementById('error').innerHTML = 'La fecha introducida no es correcta';
    }
    else{
        var http = new XMLHttpRequest();
        http.open("POST", "http://localhost:3000/Farmaciaa/Release", true);
        http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    
    
        http.onreadystatechange = function () {
            if (http.readyState === XMLHttpRequest.DONE) {
                if (http.status === 200) {
                    response = http.responseText;
                    console.log(response);
                    if (response === "error"){
                        document.getElementById('error').innerHTML = 'ID repetido';
                    }else{
                        document.getElementById('error').innerHTML = 'Xip introducido correctamente'
                        document.getElementById('error').style.color = 'black';
                        document.getElementById("xipIdInput").value = '';
                        document.getElementById("dateInput").value = '';
                    }
                    
                } else {
                    console.error('Error en la petició al backend:', http.status);
                }
            }
        };
    
        http.send("email=" + email + "&session=" + session + "&mailP=" + mailP + "&idXip=" + idXip + "&idMed=" + idMed + "&dateLimit=" + dateLimit);
    

        
    }

 


}