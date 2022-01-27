function getProfessor(id_aluno) {

    let request = new XMLHttpRequest()

    let url = "http://localhost:8080/professor/aluno/" + id_aluno
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

    console.log(user.id)
    data = getProfessor(user.id)
    professor = JSON.parse(data)

    if (professor) {
        if (professor.img) {
            img_prof = professor.img;
        } else {
            img_prof = "https://i.pinimg.com/originals/ff/a0/9a/ffa09aec412db3f54deadf1b3781de2a.png"
        }
        document.getElementById("professor").innerHTML += "<div class=\"container card p-3\">" +
                                                                "<div class=\"d-flex align-items-center row\">" +
                                                                    "<div class=\"image col-md-2 text-center\">"+
                                                                        "<img src=" + img_prof + " class=\"rounded\" width=\"155\"> </div>" +
                                                                        "<h4 class=\"cmb-0 mt-0\">" + professor.nome + "</h4> <span>Resumo</span>" +
                                                                    "<div class=\"col-md-10\">" +                                                                    
                                                                        "<div class=\"p-2 mt-2 bg-primary d-flex justify-content-between rounded text-white stats\">" +
                                                                        "<div class=\"d-flex flex-column\"> <span class=\"articles\">Mensagens Pendentes</span> <span class=\"number1\">38</span> </div>" +
                                                                        "<div class=\"d-flex flex-column\"> <span class=\"Alunos\">Alunos</span> <span class=\"number2\">980</span> </div>" +
                                                                        "<div class=\"d-flex flex-column\"> <span class=\"rating\">Avaliação</span> <span class=\"number3\">8.9</span> </div>" +
                                                                        "</div>" +
                                                                        "<div class=\"button mt-2 d-flex flex-row align-items-center\"> <button class=\"btn btn-sm btn-outline-primary w-100\">Chat</button> <button class=\"btn btn-sm btn-primary w-100 ml-2\">Mete o Prego</button> </div>" +
                                                                    "</div>" +
                                                                "</div>" +
                                                            "</div>";
        document.getElementById("modais").innerHTML += "";
        
    } else {
        document.getElementById("professor").innerHTML +=
            "<h3>Não Foram Encontrados Exercícios Para <em>" + dia_hoje + "</em></h3>" +
            "<p>faça a inscrição dos exercícios no painel exercícios ou peça para seu professor fazer pra vc seu otario</p>"
    }

    if (user.img) {
        document.getElementById("img_perfil").src = user.img
    } else {
        document.getElementById("img_perfil").src = "https://i.pinimg.com/originals/ff/a0/9a/ffa09aec412db3f54deadf1b3781de2a.png"
    }

    menuMobile.addEventListener('click', () => {
        menuMobile.classList.contains("bi-list")
            ? menuMobile.classList.replace("bi-list", "bi-x")
            : menuMobile.classList.replace("bi-x", "bi-list");
        body.classList.toggle("menu-nav-active");
    });
}