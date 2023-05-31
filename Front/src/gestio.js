function logOut() {
    // Borrar el email y la sesión del sessionStorage
    sessionStorage.removeItem('email');
    sessionStorage.removeItem('session');
    
    window.location.href = 'Login.html';
}

function getTable() {
    var email = sessionStorage.getItem('email');
    var session = sessionStorage.getItem('session');
    var http = new XMLHttpRequest();
    http.open("GET", "http://localhost:3000/Farmaciaa/ServeXips?email=" + email + "&session=" + session, true);
    http.onreadystatechange = function () {
        if (http.readyState === XMLHttpRequest.DONE) {
            if (http.status === 200) {
                var response = http.responseText;
                document.getElementById('table').innerHTML = response;
            } else {
                console.error('Error en la petición al backend:', http.status);
            }

        }
    }
    http.send();
}