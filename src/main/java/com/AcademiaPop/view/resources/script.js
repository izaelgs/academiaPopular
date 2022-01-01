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

function register(nome,user,senha,email,telefone,cpf){
    let request = new XMLHttpRequest()
    let url = "http://localhost:8080/user/insert"
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

/*function login(id){
    data = getUser("http://localhost:8080/user/"+id)
    user = JSON.parse(data)
    return user
}*/

//console.log(login('user'))

//console.log(register("professor"))

function myScript() {
    let login_str = document.getElementById("login").value;
    let senha_str = document.getElementById("senha").value;

    data = login(login_str, senha_str)
    user = JSON.parse(data)
    console.log("status: "+user.status)
    
    if(user.status == 1){

    }else if(user.status == 2){

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

    data = register(nome,user,senha,email,telefone,cpf)
    if(data == "usuario inserido com sucesso"){
        alert("Cadastro Concluído com Sucesso!")
        window.location.href = '/login.html';
    }else if(data == "erro ao inserir corno"){
        alert("Esse corno já está cadastrado!")
        window.location.href = '/register.html';
    }else {
        alert("Erro ao tentar realizar cadastro!")
    }    
}