function getPatiens() {
    var email = sessionStorage.getItem('email');
    var session = sessionStorage.getItem('session');
    var http = new XMLHttpRequest();
    http.open("GET", "http://localhost:3000/Farmaciaa/ServePatients?email=" + email + "&session=" + session, true);

    http.onreadystatechange = function () {
        if (http.readyState === XMLHttpRequest.DONE) {
            if (http.status === 200) {
                var responseData = http.responseText;

               // Parse the response as JSON
                var parsedResponse;
                try {
                parsedResponse = JSON.parse(responseData);
                } catch (error) {
                console.error("Error parsing JSON response:", error);
                }

                // Get the select element by its ID
                var selectElement = document.getElementById("patientsSelect");

                // Clear any existing options
                selectElement.innerHTML = '';

                // Check if the parsed response is an array
                if (Array.isArray(parsedResponse)) {
                // Iterate over the JSON data and create options
                parsedResponse.forEach(function(item) {
                    // Create an option element
                    var option = document.createElement("option");
                    
                    // Set the value and text content of the option using the email
                    option.value = item.mail;
                    option.textContent = item.mail;
                    
                    // Append the option to the select element
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
                // Parse the response as JSON
                var parsedResponse;
                try {
                parsedResponse = JSON.parse(responseData);
                } catch (error) {
                console.error("Error parsing JSON response:", error);
                }

                // Get the select element by its ID
                var selectElement = document.getElementById("medicinesSelect");

                // Clear any existing options
                selectElement.innerHTML = '';

                // Check if the parsed response is an array
                if (Array.isArray(parsedResponse)) {
                // Iterate over the JSON data and create options
                parsedResponse.forEach(function(item) {
                    // Create an option element
                    var option = document.createElement("option");
                    
                    // Set the value and text content of the option using the medicine name
                    option.value = item.id;
                    option.textContent = item.name;
                    
                    // Append the option to the select element
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
    http.open("POST", "http://localhost:3000/Farmaciaa/Release", true);
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