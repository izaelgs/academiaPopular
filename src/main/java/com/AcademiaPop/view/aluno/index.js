function getSerie(){
    let request = new XMLHttpRequest()
   
    let url = "http://localhost:8080/serie/recentes"
    console.log(url)
    request.open("GET", url, false)
    request.send()
    return request.responseText
}

function getExercicios(){
    let request = new XMLHttpRequest()
   
    let url = "http://localhost:8080/serie/exercicios/2"
    console.log(url)
    request.open("GET", url, false)
    request.send()
    return request.responseText
}

function getDia(id_aluno){
    
    let request = new XMLHttpRequest()
   
    let url = "http://localhost:8080/semana/aluno/"+id_aluno
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

    data = getDia(user.id)
    semana = JSON.parse(data)
    console.log(semana)
    dias = semana.dias
    let d = new Date();
    let weekday = d.getDay();
    let dias_semana = ["Domingo", "Segunda-Feira", "Terça-Feira", "Quarta-Feira", "Quinta-Feira", "Sexta-Feira", "Sábado"];
    let dia_hoje = dias_semana[weekday];
    let dias_c = 0;
    console.log(weekday);


    dias.forEach(dia => {
        
        console.log("dia: "+dia.dia+", dia hoje: "+weekday)
        if(dia.dia == weekday){
            dias_c ++;
            series = dia.series
 
            series.forEach(serie => {
                dia_descricao = serie.descricao
                dia_titulo = serie.titulo
                dia_descricao = serie.descricao
                dia_exercicios = serie.exercicios
                let exercicio = ""

                dia_exercicios.forEach(ex => {
                    titulo_e = ex.titulo
                    exercicio += 
                    "<div class=\"row\" id=\"exercicios\">"+
                        "<div class=\"col-md-6\">"+            
                            "<p class=\"mt-3 mb-0\"><input type=\"checkbox\" name=\"\" id=\"\"> "+titulo_e+"</p>"+                                   
                        "</div>"+               
                    "</div>"
                });
                
                document.getElementById("hoje").innerHTML +=                    
                    "<h3><em>"+dia_hoje+"</em> - "+dia_titulo+"</h3>"+
                    "<p>"+dia_descricao+"</p>"+
                    "<div class=\"progress\">"+
                        "<div class=\"progress-bar bg-dark\" role=\"progressbar\" style=\"width: 70%;\" aria-valuenow=\"70\" aria-valuemin=\"0\" aria-valuemax=\"100\">70%</div>"+
                    "</div>"+
                    exercicio
            });
        }
        
    });

    if(dias_c == 0){
        document.getElementById("hoje").innerHTML +=                    
                    "<h3>Não Foram Encontrados Exercícios Para <em>"+dia_hoje+"</em></h3>"+
                    "<p>faça a inscrição dos exercícios no painel exercícios ou peça para seu professor fazer pra vc seu otario</p>"   
    }
  
    data = getSerie()
    series_recentes = JSON.parse(data)
    series_recentes.forEach(serie => {
        document.getElementById("sugeridos").innerHTML += 
    "<div class=\"col-md-6\"><h3 class=\"cv-title\">"+serie.titulo+"</h3><div class=\"cv-item\"><h4>Professor Corno</h4><h5>2020 - 2022</h5><p><em>Inserir alguma coisa aqui</em></p><p>"+serie.descricao+"</p></div></div>"

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