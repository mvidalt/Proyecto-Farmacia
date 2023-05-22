function logOut() {
    // Borrar el email y la sesión del sessionStorage
    sessionStorage.removeItem('mail');
    sessionStorage.removeItem('session');

    window.location.href = 'Login.html';
}

function getTable() {
    var email = sessionStorage.getItem('mail');
    var session = sessionStorage.getItem('session');

    var http = new XMLHttpRequest();
    http.open("GET", "http://localhost:3000/DAMTomcat/ServXips?mail=" + email + "&session=" + session, true);
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

        }lil_sekai
    }
    http.send();
}