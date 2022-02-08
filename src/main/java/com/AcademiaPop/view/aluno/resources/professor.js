function getProfessor(id_aluno) {

    let request = new XMLHttpRequest()

    let url = "http://localhost:8080/professor/aluno/" + id_aluno
    request.open("GET", url, false)
    request.send()
    return request.responseText
}

function getProfessores() {

    let request = new XMLHttpRequest()

    let url = "http://localhost:8080/professor/recomendados/"
    request.open("GET", url, false)
    request.send()
    return request.responseText
}

function updateProfessor(id_p, id_a) {
    let request = new XMLHttpRequest()

    let url = "http://localhost:8080/semana/update/professor"
    request.open("PUT", url, false)
    request.setRequestHeader('Content-Type', 'application/json')
    request.send(JSON.stringify({
        "id_p": id_p,
        "id_a": id_a
    }))
    return request.responseText
}

function seguir(id_p, id_a) {
    console.log("id_p: " + id_p + ", id_a: " + id_a)
    resultado = window.confirm("realmente deseja seguir este vagabundo?");
    if (resultado) {
        updateProfessor(id_p, id_a)
        window.location.reload("")
    }
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

    data2 = getProfessores()
    professores = JSON.parse(data2)

    if (professor.nome) {
        if (professor.img) {
            img_prof = professor.img;
        } else {
            img_prof = "https://i.pinimg.com/originals/ff/a0/9a/ffa09aec412db3f54deadf1b3781de2a.png"
        }
        document.getElementById("professor").innerHTML += "<div class=\"container card p-3\">" +
            "<div class=\"d-flex align-items-center row\">" +
            "<div class=\"image col-md-2 text-center\">" +
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
            "<h3>Não Foi Encontrado Nenhum Professor Que te Maceta</h3>" +
            "<p>Segue um desses corno abaixo ae pra eles te manda o papo das correria</p>"
    }

    if (professores) {
        console.log(professores)

        professores.forEach(professor => {
            console.log("fodase: " + professor.nome)
            if (professor.img) {
                img_prof = professor.img;
            } else {
                img_prof = "https://i.pinimg.com/originals/ff/a0/9a/ffa09aec412db3f54deadf1b3781de2a.png"
            }
            document.getElementById("sugeridos").innerHTML += `<div class="col-lg-4">
            <div class="text-center card-box">
                    <div class="thumb-lg member-thumb mx-auto">
                    <img src="${img_prof}"
                        class="rounded-circle img-thumbnail" style="width: 300px;
                        height: 300px;">
                    </div>
                    <h4 class="mb-0 mt-0">${professor.nome}</h4>
                    <h5>Apresentação</h5>
                <div class="member-card pt-2 pb-2">
                    <div
                        class="p-2 mt-2 bg-primary d-flex justify-content-between rounded text-white stats">
                        <div class="d-flex flex-column"> <span class="articles">Articles</span> <span
                                class="number1">38</span> </div>
                        <div class="d-flex flex-column"> <span class="followers">Followers</span> <span
                                class="number2">980</span> </div>
                        <div class="d-flex flex-column"> <span class="rating">Avaliação</span> <span
                                class="number3">8.9</span> </div>
                    </div>
                    <div class="button mt-2 d-flex flex-row align-items-center"> <button
                            class="btn btn-sm btn-outline-primary w-100">Chat</button> <button
                            class="btn btn-sm btn-primary w-100 ml-2" onclick="seguir(${professor.id},${user.id})">Seguir</button> </div>
                </div>
            </div>
        </div>`;
            document.getElementById("modais").innerHTML += "";
        });
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