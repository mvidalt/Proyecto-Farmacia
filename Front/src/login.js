function send() {
  //npm install bcryptjs
  //const bcrypt = require('bcryptjs');
  let http = new XMLHttpRequest();

  let email = document.getElementById("email").value;
  let pass = document.getElementById("pass").value;
  // Generar el hash de la contraseña
  //const hashedPassword = bcrypt.hashSync(pass, 9);

  http.open("GET", "http://localhost:3000/Back-Farmacia/Login?email=" + email + "&pass=" + pass, true); 


  http.onreadystatechange = function () {
    if (http.readyState === XMLHttpRequest.DONE) {
      if (http.status === 200) {
        // Obtener la respuesta del backend
        var response = http.responseText;

        if (response === null) {
          // El login no ha sido correcto
          document.getElementById("resultado").innerHTML = 'Login erroneo';
        } else {   // El login ha sido exitoso, obtener el código de sesión
          var sessionCode = response;

          // Almacenar el código de sesión en sessionStorage
          sessionStorage.setItem('session', sessionCode);
          sessionStorage.setItem('mail', email);

          // Avanzar a la página "Gestio"
          window.location.href = 'Gestio.html';
        }


      } else {
        // Error en la petición al backend
        console.error('Error en la petición al backend:', http.status);
      }
    }
  };

  http.send();
}