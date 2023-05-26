function logOut() {
    // Borrar el email y la sesión del sessionStorage
    sessionStorage.removeItem('email');
    sessionStorage.removeItem('session');
    
    window.location.href = 'Login.html';
}

function getTable() {
    var email = sessionStorage.getItem('email');
    var session = sessionStorage.getItem('session');
    console.log(sessionStorage);
    var http = new XMLHttpRequest();
    http.open("GET", "http://localhost:3000/DAMTomcat/ServeXips?email=" + email + "&session=" + session, true);
    http.onreadystatechange = function () {
        if (http.readyState === XMLHttpRequest.DONE) {
            if (http.status === 200) {
                var response = http.responseText;

                /* Insertar el HTML de la tabla en un elemento en la página
                var tableElement = document.getElementById('table');
                tableElement.innerHTML = response;*/
                document.getElementById('table').innerHTML = response;
            } else {
                console.error('Error en la petición al backend:', http.status);
            }

        }
    }
    http.send();
}