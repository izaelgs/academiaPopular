function getSerie(){
    let request = new XMLHttpRequest()
   
    let url = "http://localhost:8080/serie/recentes"
    request.open("GET", url, false)
    request.send()
    return request.responseText
}

function getExercicios(){
    let request = new XMLHttpRequest()
   
    let url = "http://localhost:8080/semana/professor/2"
    request.open("GET", url, false)
    request.send()
    return request.responseText
}

function getDia(id_aluno){
    
    let request = new XMLHttpRequest()
   
    let url = "http://localhost:8080/semana/aluno/"+id_aluno
    request.open("GET", url, false)
    request.send()
    return request.responseText
}

window.onload = function up() { 
    
    const menuMobile = document.querySelector('.menu-mobile');
    const body = document.querySelector('body');
    const nav_item = document.querySelectorAll('.nav-item');

    user = JSON.parse(sessionStorage.user)

    data = getDia(user.id)
    semana = JSON.parse(data)
    dias = semana.dias
    let d = new Date();
    let weekday = d.getDay();
    let dias_semana = ["Domingo", "Segunda-Feira", "Terça-Feira", "Quarta-Feira", "Quinta-Feira", "Sexta-Feira", "Sábado"];
    let dia_hoje = dias_semana[weekday];
    let dias_c = 0;


    dias.forEach(dia => {
        
        series = dia.series
        let dia_ref = dias_semana[dia.dia];

        if(dia.dia == weekday){
            dias_c ++;            
 
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

        let serie_str = "";

        series.forEach(serie =>{
            dia_descricao = serie.descricao
            dia_titulo = serie.titulo
            dia_descricao = serie.descricao
            dia_exercicios = serie.exercicios           
            
            serie_str += "<p class=\"card-text\"><b><em>"+dia_titulo+"</em></b><br>"+dia_descricao+"</p>"
        });

        document.getElementById("semana").innerHTML +=                    
            "<div class=\"card col-md-4\" style=\"width: 18rem;\">"+
            "<div class=\"card-body\">"+
                "<h5 class=\"card-title\">"+dia_ref+"</h5>"+
                serie_str+
                "<a href=\"#\" class=\"btn btn-primary\">Go somewhere</a>"+
            "</div>"+
            "</div>"
        
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