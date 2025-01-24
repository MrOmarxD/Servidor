window.onload
{
    document.getElementById('botComp').addEventListener("click", comprobar);
    var errores = [];
}
function comprobar() {
    let arrayInputs = document.getElementsByTagName('input');
    for(let i = 0; i<arrayInputs.length-1; i++){
        validarCasilla(arrayInputs[i].id, arrayInputs[i].value);
    }
    mostrarErrores();
}
function mostrarErrores() {
    let eleCajaErrores = document.getElementById('cajaErrores');
    eleCajaErrores.innerHTML = "";
    let str = "";
    errores.forEach(error => str = str+"<br>"+error);
    eleCajaErrores.innerHTML = "<p style='color: red;'>"+str+"</p>";
    errores = [];
}

function validarCasilla(id, value) {
    if(value=="")
        return;
    switch (id) {
        case 'dni':
            if (!(/^\d{8}[a-zA-Z]$/.test(value))){
                errores.push("Dni Incorrecto");
                document.getElementById(id).value = "";
            }
            break;
        case 'nombre':
            if (!(/^[a-zA-Z ]*$/.test(value)))
                errores.push("Nombre Incorrecto");
                document.getElementById(id).value = "";
            break;
        case 'email':
            if (!(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(value)))
                errores.push("Email Incorrecto");
                document.getElementById(id).value = "";
            break;
        case 'web':
            if (!(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(value)))
                errores.push("Web Incorrecta");
                document.getElementById(id).value = "";
            break;
        case 'contrasenia':
            if (!(/(^[0-9\s\+\-])+$/.test(value)))
                errores.push("Contraseña Incorrecto");
                document.getElementById(id).value = "";
            break;
    }
}