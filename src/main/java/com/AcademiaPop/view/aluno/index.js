function getSerie(){
    let request = new XMLHttpRequest()
   
    let url = "http://localhost:8080/serie/recentes"
    console.log(url)
    request.open("GET", url, false)
    request.send()
    return request.responseText
}

window.onload = function up() { // Could be anonymous, of course, but should not
    
    const menuMobile = document.querySelector('.menu-mobile');
    const body = document.querySelector('body');
    const nav_item = document.querySelectorAll('.nav-item');
    console.log(nav_item)

    user = JSON.parse(sessionStorage.user)
    console.log(user)
    console.log(user.img)

    data = getSerie()
    series_recentes = JSON.parse(data)
    series_recentes.forEach(serie => {
        document.getElementById("sugeridos").innerHTML += 
    "<div class=\"col-md-6\"><h3 class=\"cv-title\">"+serie.titulo+"</h3><div class=\"cv-item\"><h4>Professor Corno</h4><h5>2020 - 2022</h5><p><em>Inserir alguma coisa aqui</em></p><p>"+serie.descricao+"</p></div></div>"

        console.log(serie)

    });

    document.getElementById("user").innerHTML = user.nome

    if(user.img != ""){
        document.getElementById("img_perfil").src = user.img
    }
    
    menuMobile.addEventListener('click', ()=>{
        menuMobile.classList.contains("bi-list") 
            ? menuMobile.classList.replace("bi-list", "bi-x")
            : menuMobile.classList.replace("bi-x", "bi-list");  
            body.classList.toggle("menu-nav-active");  
    });
}