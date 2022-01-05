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

    document.getElementById("user").innerHTML = user.nome

    
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
                
                /*document.getElementById("hoje").innerHTML +=                    
                    "<h3><em>"+dia_hoje+"</em> - "+dia_titulo+"</h3>"+
                    "<p>"+dia_descricao+"</p>"+
                    "<div class=\"progress\">"+
                        "<div class=\"progress-bar bg-dark\" role=\"progressbar\" style=\"width: 70%;\" aria-valuenow=\"70\" aria-valuemin=\"0\" aria-valuemax=\"100\">70%</div>"+
                    "</div>"+
                    exercicio*/

                
            });
        }

        let serie_str = "";
        let exercicio_str = "";

        series.forEach(serie =>{
            dia_descricao = serie.descricao
            dia_titulo = serie.titulo
            dia_descricao = serie.descricao
            dia_exercicios = serie.exercicios           
            
            serie_str += "<p class=\"card-text\"><b><em>"+dia_titulo+"</em></b><br>"+dia_descricao+"</p>"
            console.log(dia_exercicios)
            dia_exercicios.forEach(exercicio_o=>{
                exercicio_str += "<b>"+exercicio_o.titulo+"</b><br> "+exercicio_o.descricao+"<hr>"
            })
        });

        document.getElementById("semana").innerHTML +=                    
            "<div class=\"card col-md-4\" style=\"width: 18rem;\">"+
            "<div class=\"card-body\">"+
                "<h5 class=\"card-title\">"+dia_ref+"</h5>"+
                serie_str+
                "<a href=\"#\" class=\"btn btn-primary open-moral\" data-toggle=\"modal\" data-target=\"#ex_modal_"+dia.id+"\">Vizualizar</a>"+
            "</div>"+
            "</div>"

        document.getElementById("modais").innerHTML +=                    
            "<div class=\"modal fade\" id=\"ex_modal_"+dia.id+"\" tabindex=\"-1\" role=\"dialog\">"+
            "<div class=\"modal-dialog modal-lg\" role=\"document\">"+
                "<div class=\"modal-content\">"+
                    "<div class=\"modal-header\">"+
                        "<h3 class=\"modal-title\">"+dia_ref+"</h3>"+
                        "<button type=\"button\" class=\"close\" data-dismiss=\"modal\">"+
                            "<span>&times;</span>"+
                        "</button>"+
                    "</div>"+
                    "<div class=\"modal-body\">"+
                        "<h4>Exercícios</h4>"+
                        "<p>"+exercicio_str+"</p>"+
                    "</div>"+
                    "<div class=\"modal-footer\">"+
                        "<button type=\"button\" class=\"btn btn-danger\" data-dismiss=\"modal\">Fechar</button>"+
                    "</div>"+
                "</div>"+
            "</div> "+  
            "</div>"
        
    });

    if(dias_c == 0){
        /*document.getElementById("hoje").innerHTML +=                    
                    "<h3>Não Foram Encontrados Exercícios Para <em>"+dia_hoje+"</em></h3>"+
                    "<p>faça a inscrição dos exercícios no painel exercícios ou peça para seu professor fazer pra vc seu otario</p>" */  
    }

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