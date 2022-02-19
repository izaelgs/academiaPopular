let dias_semana = ["Domingo", "Segunda-Feira", "Terça-Feira", "Quarta-Feira", "Quinta-Feira", "Sexta-Feira", "Sábado"];

function getSemanas(id_professor){
    
    let request = new XMLHttpRequest()
   
    let url = "http://localhost:8080/semana/professor/"+id_professor
    request.open("GET", url, false)
    request.send()
    return request.responseText
}

function getAluno(id_aluno){
    
    let request = new XMLHttpRequest()
   
    let url = "http://localhost:8080/aluno/get/"+id_aluno
    request.open("GET", url, false)
    request.send()
    return request.responseText
}

function createSerie(id_semana,dia){
    let url = "http://localhost:8080/serie/insert/dia/"
    let request = new XMLHttpRequest()
    console.log(url)
    request.open("POST", url, false)
    request.setRequestHeader('Content-Type', 'application/json')
    request.send(JSON.stringify({
        "id_semana": id_semana,
        "dia": dia
    }))
    return request.responseText
}

function submitExercicio(id_dia,id_semana){
    let str_form = "form_exercicio_"+id_dia
    let str_exercicio = "exercicio_"+id_dia
    let formulario = document.getElementById(str_form);

    let request = new XMLHttpRequest()

    let titulo = formulario.titulo.value
    let descricao = formulario.desc.value
    let serie = formulario.serie.value
    if(serie == 0){
        serie = createSerie(id_semana,id_dia)
    }
    
    let url = "http://localhost:8080/serie/exercicio/insere/"+serie
    console.log(url)
    request.open("POST", url, false)
    request.setRequestHeader('Content-Type', 'application/json')
    request.send(JSON.stringify({
        "titulo": titulo,
        "descricao": descricao
    }))

    formulario.titulo.value = '';
    formulario.desc.value = '';
    formulario.serie.value = 0;
    document.getElementById(str_exercicio).innerHTML += "<hr><b>"+titulo+"</b><br> "+descricao
    if(request.responseText){
        alert("adicionei essa merda")
    }else{
        alert("vo adicionar essa merda não, ta maluko")

    }
}

function submitDia(aluno,id_semana){
    let str_form = "form_dia_"+aluno
    let formulario = document.getElementById(str_form);

    let request = new XMLHttpRequest()
    let dia = formulario.dia.value
    
    let url = "http://localhost:8080/dia/insert/serie"
    console.log(url)
    request.open("POST", url, false)
    request.setRequestHeader('Content-Type', 'application/json')
    request.send(JSON.stringify({
        "id_semana": id_semana,
        "dia": dia
    }))

    if(request.responseText && (request.responseText > 0)){
        id_dia = request.responseText
        let dia_ref = dias_semana[dia];    
        formulario.dia = dia+1;
        document.getElementById(aluno).innerHTML = document.getElementById(aluno).innerHTML += 
        "<div class=\"container shadow-lg\">"+
            "<div class=\"modal-heade bg-secondary rounded-top p-2 text-white\">"+
                "<h3 class=\"modal-title\">"+dia_ref+"</h3>"+                            
            "</div>"+
            "<div class=\"modal-body bg-light rounded-bottom\">"+
                "<h4>Exercícios</h4>"+
                "<p id=\"exercicio_"+id_dia+"\"></p>"+
                "<div id=\"add_exercicio_"+id_dia+"\" style=\"display:none\">"+
                    "<form id=\"form_exercicio_"+id_dia+"\" name=\"novo_exercicio\" action=\"\">"+
                        "<div class=\"form-row\">"+
                            "<div class=\"form-group col-md-7\">"+
                                "<label for=\"titulo\">Titulo</label>"+
                                "<input type=\"text\" class=\"form-control\" id=\"titulo\" name=\"titulo\" value=\"titulo\">"+
                            "</div>"+
                            "<div class=\"form-group col-md-5\">"+
                                "<label for=\"serie\">Serie</label>"+
                                "<select id=\"serie\" class=\"form-control\">"+
                                    "<option value=\"0\" selected>Criar Nova Série</option>"+
                                "</select>"+
                            "</div>"+
                            "<div class=\"form-group col-md-12\">"+
                                "<label for=\"desc\">Descrição</label>"+
                                "<input type=\"text\" class=\"form-control\" id=\"desc\" placeholder=\"descreva aqui essa merda de exercicio\">"+
                            "</div>"+
                            "<div class=\"form-group col-md-12\">"+
                                "<button class=\"btn btn-sm btn-primary w-100\" onclick=\"submitExercicio("+id_dia+""+id_semana+")\" type=\"button\">Salvar</button>"+
                            "</div>"+                                        
                        "</div>"+
                    "</form>"+
                "</div>"+
                "<button class=\"btn btn-sm btn-outline-primary w-100\"onclick=\"toogle_form_dia("+id_dia+")\">Adicionar</button>"+
            "</div>"+                        
        "</div>" 
    
        alert("adicionei essa merda")
    }else{
        alert("vo adicionar essa merda não, ta maluko")

    }
}

function toogle_form_dia(dia){
    let str_exercicio = "add_exercicio_"+dia

    var x = document.getElementById(str_exercicio);
    if (x.style.display === "none") {
      x.style.display = "block";
    } else {
      x.style.display = "none";
    }
}

function toogle_add_dia(aluno){
    let str_dia = "add_dia_"+aluno

    var x = document.getElementById(str_dia);
    if (x.style.display === "none") {
      x.style.display = "block";
    } else {
      x.style.display = "none";
    }
}


window.onload = function up() { 

    const menuMobile = document.querySelector('.menu-mobile');
    const body = document.querySelector('body');
    const nav_item = document.querySelectorAll('.nav-item');

    user = JSON.parse(sessionStorage.user)

    document.getElementById("user").innerHTML = user.nome

    
    data = getSemanas(user.id)
    semanas = JSON.parse(data)
    let semanas_c = 0;
    let d = new Date();
    let weekday = d.getDay();    
    let dia_hoje = dias_semana[weekday];

    let id_aluno_bkp = 0;

    semanas.forEach(semana => { 
        semanas_c++
        dias = semana.dias
        data_aluno = getAluno(semana.id_aluno)
        aluno = JSON.parse(data_aluno)
        if(aluno.img){
            img_aluno = aluno.img
        }else{
            img_aluno = "https://i.pinimg.com/originals/ff/a0/9a/ffa09aec412db3f54deadf1b3781de2a.png"
        }

        if(id_aluno_bkp != aluno.id){

            document.getElementById("alunos").innerHTML +=        
            "<div class=\"container mt-5 d-flex justify-content-center card col-md-6\">"+
                "<div class=\"card p-3\">"+
                    "<div class=\"d-flex align-items-center\">"+
                        "<div class=\"image \"> <img src=\""+img_aluno+"\" class=\"rounded\" width=\"155\"> </div>"+
                        "<div class=\"ml-3 w-100\">"+
                            "<h4 class=\"mb-0 mt-0\">"+aluno.nome+"</h4> <span>Senior Journalist</span>"+
                            "<div class=\"p-2 mt-2 bg-primary d-flex justify-content-between rounded text-white stats\">"+
                                "<div class=\"d-flex flex-column\"> <span class=\"articles\">Nível</span> <span class=\"number1\">38</span> </div>"+
                                "<div class=\"d-flex flex-column\"> <span class=\"followers\">Followers</span> <span class=\"number2\">980</span> </div>"+
                                "<div class=\"d-flex flex-column\"> <span class=\"rating\">Rating</span> <span class=\"number3\">8.9</span> </div>"+
                            "</div>"+
                            "<div class=\"button mt-2 d-flex flex-row align-items-center\"> <button class=\"btn btn-sm btn-outline-primary w-100\"data-toggle=\"modal\" data-target=\"#aluno_modal_"+aluno.id+"\">Chat</button> <button class=\"btn btn-sm btn-primary w-100 ml-2\"data-toggle=\"modal\" data-target=\"#aluno_modal_"+aluno.id+"\">Vizualizar</button> </div>"+                    
                        "</div>"+
                    "</div>"+
                "</div>"+
            "</div>"

            select_dias = ''

            for(i = 0;i <= 6; i++){            
                select_dias += `<option value=\"${i}\" id="${aluno.id}_${i}">${dias_semana[i]}</option>`            
            }
            
            document.getElementById("modais").innerHTML +=                    
            "<div class=\"modal fade\" id=\"aluno_modal_"+aluno.id+"\" tabindex=\"-1\" role=\"dialog\">"+
            "<div class=\"modal-dialog modal-lg\" role=\"document\">"+
                "<div class=\"modal-content\">"+
                    "<div class=\"modal-header\">"+
                        "<h3 class=\"modal-title\">"+aluno.nome+"</h3>"+
                        "<button type=\"button\" class=\"close\" data-dismiss=\"modal\">"+
                            "<span>&times;</span>"+
                        "</button>"+
                    "</div>"+
                    "<div class=\"modal-body\">"+
                        "<h4>Dias</h4>"+
                        "<div id=\""+aluno.id+"\"></div>"+
                        "<br><button class=\"btn btn-sm btn-outline-primary w-100\" onclick=\"toogle_add_dia("+aluno.id+")\">Adicionar Dia</button>"+
                        "<div id=\"add_dia_"+aluno.id+"\" style=\"display:none\">"+
                                    "<form id=\"form_dia_"+aluno.id+"\" name=\"novo_dia\" action=\"\">"+
                                        "<div class=\"form-row\">"+
                                            "<div class=\"form-group col-md-12\">"+
                                                "<label for=\"dia\">Dia da Semana</label>"+
                                                "<select id=\"dia\" name=\"dia\" class=\"form-control\">"+
                                                    select_dias+
                                                "</select>"+
                                            "</div>"+
                                            "<div class=\"form-group col-md-12\">"+
                                                "<button id=\"submit_dia_"+aluno.id+"\" class=\"btn btn-sm btn-primary w-100\" onclick=\"submitDia("+aluno.id+","+semana.id+")\" type=\"button\">Salvar</button>"+
                                            "</div>"+                                        
                                        "</div>"+
                                    "</form>"+
                                "</div>"+
                    "</div>"+
                    "<div class=\"modal-footer\">"+
                        "<button type=\"button\" class=\"btn btn-danger\" data-dismiss=\"modal\">Fechar</button>"+
                    "</div>"+
                "</div>"+
            "</div> "+  
            "</div>"

            document.getElementById("chat").innerHTML +=                    
                    "<div class=\"modal fade\" id=\"chat_modal_"+aluno.id+"\" tabindex=\"-1\" role=\"dialog\">"+
                    "<div class=\"modal-dialog modal-lg\" role=\"document\">"+
                        "<div class=\"modal-content\">"+
                            "<div class=\"modal-header\">"+
                                "<h3 class=\"modal-title\">"+aluno.nome+"</h3>"+
                                "<button type=\"button\" class=\"close\" data-dismiss=\"modal\">"+
                                    "<span>&times;</span>"+
                                "</button>"+
                            "</div>"+
                            "<div class=\"modal-body\">"+
                                "<h4>Exercícios</h4>"+
                                "<p>"+aluno+"</p>"+
                            "</div>"+
                            "<div class=\"modal-footer\">"+
                                "<button type=\"button\" class=\"btn btn-danger\" data-dismiss=\"modal\">Fechar</button>"+
                            "</div>"+
                        "</div>"+
                    "</div> "+  
                    "</div>"
            
            id_aluno_bkp = aluno.id
        }

        console.log("dias: "+dias[0].id)
        if(dias[0].id != 0){
            dias.forEach(dia => {
            document.getElementById(`${aluno.id}_${dia.dia}`).disabled = true
            if(dia.dia+1 <= 6){
                document.getElementById(`${aluno.id}_${dia.dia+1}`).selected = true
            }else{
                document.getElementById(`submit_dia_${aluno.id}`).disabled = true
            }
            


            series = dia.series
            let dia_ref = dias_semana[dia.dia];

            if(dia.dia == weekday){
    
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
                    
                });
            }

            let serie_str = "";
            let exercicio_str = "";
            let select_series = "";

            series.forEach(serie =>{
                dia_descricao = serie.descricao
                dia_titulo = serie.titulo
                dia_descricao = serie.descricao
                dia_exercicios = serie.exercicios           
                
                serie_str += "<p class=\"card-text\"><b><em>"+dia_titulo+"</em></b><br>"+dia_descricao+"</p>"
                dia_exercicios.forEach(exercicio_o=>{
                    exercicio_str += "<b>"+exercicio_o.titulo+"</b><br> "+exercicio_o.descricao+"<hr>"
                })

                select_series += "<option value=\""+serie.id+"\">"+dia_titulo+"</option>"
                
            });
    
            document.getElementById(aluno.id).innerHTML += 
                    "<div class=\"container shadow-lg\">"+
                        "<div class=\"modal-heade bg-secondary rounded-top p-2 text-white\">"+
                            "<h3 class=\"modal-title\">"+dia_ref+"</h3>"+                            
                        "</div>"+
                        "<div class=\"modal-body bg-light rounded-bottom\">"+
                            "<h4>Exercícios</h4>"+
                            "<p id=\"exercicio_"+dia.id+"\">"+exercicio_str+"</p>"+
                            "<div id=\"add_exercicio_"+dia.id+"\" style=\"display:none\">"+
                                "<form id=\"form_exercicio_"+dia.id+"\" name=\"novo_exercicio\" action=\"\">"+
                                    "<div class=\"form-row\">"+
                                        "<div class=\"form-group col-md-7\">"+
                                            "<label for=\"titulo\">Titulo</label>"+
                                            "<input type=\"text\" class=\"form-control\" id=\"titulo\" name=\"titulo\" value=\"titulo\">"+
                                        "</div>"+
                                        "<div class=\"form-group col-md-5\">"+
                                            "<label for=\"serie\">Serie</label>"+
                                            "<select id=\"serie\" class=\"form-control\">"+
                                                "<option value=\"0\" selected>Criar Nova Série</option>"+
                                                select_series+
                                            "</select>"+
                                        "</div>"+
                                        "<div class=\"form-group col-md-12\">"+
                                            "<label for=\"desc\">Descrição</label>"+
                                            "<input type=\"text\" class=\"form-control\" id=\"desc\" placeholder=\"descreva aqui essa merda de exercicio\">"+
                                        "</div>"+
                                        "<div class=\"form-group col-md-12\">"+
                                            "<button class=\"btn btn-sm btn-primary w-100\" onclick=\"submitExercicio("+dia.id+","+semana.id+")\" type=\"button\">Salvar</button>"+
                                        "</div>"+                                        
                                    "</div>"+
                                "</form>"+
                            "</div>"+
                            "<button class=\"btn btn-sm btn-outline-primary w-100\"onclick=\"toogle_form_dia("+dia.id+")\">Adicionar</button>"+
                        "</div>"+                        
                    "</div>"                           
        });
        }
                 
    });

    if(semanas_c == 0){
        document.getElementById("alunos").innerHTML +=                    
                    "<h3>Não Foram Encontrados Alunos Para <em>"+dia_hoje+"</em></h3>"+
                    "<p>faça a inscrição dos exercícios no painel do seu aluno ou peça para ele mesmo fazer pra vc seu otario</p>"  
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