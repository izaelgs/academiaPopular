function getUser(url){
    let request = new XMLHttpRequest()
    request.open("GET", url, false)
    request.send()
    return request.responseText
}

function login(login_str, senha_str){
    let request = new XMLHttpRequest()
    let url = "http://localhost:8080/user/login"
    request.open("POST", url, false)
    request.setRequestHeader('Content-Type', 'application/json')
    request.send(JSON.stringify({
        "login": login_str,
        "senha": senha_str               
    }))
    return request.responseText
}

function register(nome,user,senha,email,telefone,cpf,tipo){
    let request = new XMLHttpRequest()
   
    let url = "http://localhost:8080/"+tipo+"/insert"
    console.log(url)
    request.open("POST", url, false)
    request.setRequestHeader('Content-Type', 'application/json')
    request.send(JSON.stringify({
        "nome": nome,
        "login": user,
        "senha": senha,
        "email": email,
        "telefone": telefone,        
        "cpf": cpf,
    }))
    return request.responseText
}

function login_btn() {
    let login_str = document.getElementById("login").value;
    let senha_str = document.getElementById("senha").value;

    let modulo = "";

    data = login(login_str, senha_str)
    user = JSON.parse(data)

    switch(user.modulo){
        case 1:
            modulo = "aluno";
            break;
        case 2:
            modulo = "professor";
            break;
        case 3:
            modulo = "admin";
            break;
    }

    console.log("modulo: "+modulo+", status: "+user.status)
    
    if(user.status == 1 || user.status == 3){
        alert("login bem Sucedido!")
        sessionStorage.setItem("user", JSON.stringify(user))

        window.location.href = "../"+modulo
    }else if(user.status == 2){
        alert("login bem Sucedido!")

        sessionStorage.setItem("user", JSON.stringify(user))

        window.location.href = "../cadastro.html"
    }else{
        alert("login ou usuário incorretos!")
    }
    //alert("login: "+login+", senha: "+senha);
}

function register_btn(){
    let nome = document.getElementById("nome").value;
    let user = document.getElementById("user").value;
    let senha = document.getElementById("senha").value;
    let email = document.getElementById("email").value;
    let telefone = document.getElementById("telefone").value;
    let cpf = document.getElementById("cpf").value;
    let tipo = 0;


    var tipo_arr = document.getElementsByName("tipo");
    for (var i = 0; i < tipo_arr.length; i++) {
        if (tipo_arr[i].checked) {
            tipo = tipo_arr[i].value;
        }
    }

    console.log("Escolheu: " + tipo);
    
    data = register(nome,user,senha,email,telefone,cpf,tipo)
    if(data == "usuario inserido com sucesso"){
        alert("Cadastro Concluído com Sucesso!")
        window.location.href = '/login.html';
    }if(data == "Aluno inserido com sucesso"){
        alert("Cadastro Concluído com Sucesso!")
        window.location.href = '/login.html';
    }else if(data == "Aluno inserido com sucesso"){
        alert("Cadastro Concluído com Sucesso!")
        window.location.href = '/login.html';
    }else if(data == "erro ao inserir corno"){
        alert("Esse corno já está cadastrado!")
        window.location.href = '/register.html';
    }else {
        alert("Erro ao tentar realizar cadastro!")
        console.log(data)
    }    
}