function send() {
  let http = new XMLHttpRequest();

  let email = document.getElementById("email").value;
  let pass = document.getElementById("pass").value;

  http.open("GET", "http://localhost:3000/Farmaciaa/Login?email=" + email + "&pass=" + pass, true); 


  http.onreadystatechange = function () {
    if (http.readyState === XMLHttpRequest.DONE) {
      if (http.status === 200) {
        var response = http.responseText;

        if (response === "") {

          document.getElementById("resultado").innerHTML = 'Login erroneo';
        } else {  
          var sessionCode = response;

          sessionStorage.setItem('session', sessionCode);
          sessionStorage.setItem('email', email);
          
          window.location.href = 'Gestio.html';
        }


      } else {
        console.error('Error en la petición al backend:', http.status);
      }
    }
  };

  http.send();
}