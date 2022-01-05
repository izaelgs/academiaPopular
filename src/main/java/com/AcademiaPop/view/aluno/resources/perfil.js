function update(id,nome,user,senha,email,telefone,cpf){
    let request = new XMLHttpRequest()
   
    let url = "http://localhost:8080/user/update"
    request.open("PUT", url, false)
    request.setRequestHeader('Content-Type', 'application/json')
    request.send(JSON.stringify({
        "login": user,
        "senha": senha,
        "email": email,
        "telefone": telefone,
        "nome": nome,
        "cpf": cpf,
        "id": id
    }))
    return request.responseText
}

function update_btn(){
    user_s = JSON.parse(sessionStorage.user)

    let nome = document.getElementById("nome").value;
    let user = document.getElementById("usuario").value;
    let senha = document.getElementById("senha").value;
    let email = document.getElementById("email").value;
    let telefone = document.getElementById("telefone").value;
    let cpf = document.getElementById("cpf").value;
    let id_user = user_s.id_user;
    
    tipo = "aluno";
   
    data = update(id_user,nome,user,senha,email,telefone,cpf,tipo)
    if(data != "" || data != undefined){

        user_d = JSON.parse(data)

        if(user_d.id && user_d.nome && user_d.login && user_d.senha && user_d.email && user_d.telefone && user_d.cpf){
            alert("Perfil Atualizado com Sucesso!")
            user_s.nome = user_d.nome
            user_s.login = user_d.login
            user_s.senha = user_d.senha
            user_s.email = user_d.email
            user_s.telefone = user_d.telefone
            user_s.cpf = user_d.cpf

            sessionStorage.setItem("user", JSON.stringify(user_s))



            window.location.href = "#";
        }

    }else {
        alert("Erro ao tentar salvar alterações!")
        console.log(data)
    } 
}

window.onload = function up() { // Could be anonymous, of course, but should not
    
    const menuMobile = document.querySelector('.menu-mobile');
    const body = document.querySelector('body');
    const nav_item = document.querySelectorAll('.nav-item');

    user = JSON.parse(sessionStorage.user)


    document.getElementById("user").innerHTML = user.nome

    document.getElementById("nome").value = user.nome
    document.getElementById("usuario").value = user.login
    document.getElementById("senha").value = user.senha
    document.getElementById("email").value = user.email
    document.getElementById("telefone").value = user.telefone
    document.getElementById("cpf").value = user.cpf


    if(user.img){
        document.getElementById("img_perfil").src = user.img
    }else{
        document.getElementById("img_perfil").src = "https://i.pinimg.com/originals/ff/a0/9a/ffa09aec412db3f54deadf1b3781de2a.png"
    }
    
    menuMobile.addEventListener('click', ()=>{
        menuMobile.classList.contains("bi-list") 
            ? menuMobile.classList.replace("bi-list", "bi-x")
            : menuMobile.classList.replace("bi-x", "bi-list");  
            body.classList.toggle("menu-nav-active");  
    });
}