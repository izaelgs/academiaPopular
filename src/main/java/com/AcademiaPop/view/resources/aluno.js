if(sessionStorage.user != undefined){
    user = JSON.parse(sessionStorage.user)

    if(user.modulo == 1 && user.status == 1){
        
    }else if(user.status == 0){
        alert("tua conta ta desativada seu corno")
    }else{
        alert("Sessão Expirada")
        window.location.href = "../"
    }
}else{
    alert("Sessão Expirada")
    window.location.href = "../"
}