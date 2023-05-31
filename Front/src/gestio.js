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
                var response = http.responseText.trim(); // Eliminar espacios en blanco al inicio y al final
                if (response.toLowerCase() === 'access denied') { // Comparar sin importar mayúsculas y minúsculas
                    document.getElementById('table').innerHTML = response;
                    document.getElementById('logout').innerText = 'Volver a login'; // Cambiar el texto del botón
                    document.getElementById('alta').style.display = 'none';
                  setTimeout(function() {
                    window.location.href = 'login.html';
                  }, 10000); // 10 segundos (10000 milisegundos)
                  
                } else {
                  document.getElementById('table').innerHTML = response;
                }
            } else {
                console.error('Error en la petición al backend:', http.status);
            }

        }
    }
    http.send();
}