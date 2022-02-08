if(sessionStorage.user != undefined){
    user = JSON.parse(sessionStorage.user)

    if(user.status == 3){
        alert("deu certo garaio")
    }else{
        alert("TU N ME ENGANA N MALUCO Q EU SEI Q TU N É ADM")
        window.location.href = "../"
    }
}else{
    alert("Sessão Expirada")
    window.location.href = "../"
}