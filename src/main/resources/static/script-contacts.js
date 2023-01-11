function viewSecondEmailForm(){
console.log("in the view second email form")
    if (document.getElementById("addEmail").checked){
        document.getElementById("secondEmail").style.display = "block";
    }

}


function viewSecondPhoneNumberForm(){

    if (document.getElementById("addPhoneNumber").checked){
        document.getElementById("secondPhoneNumber").style.display = "block";
    }

}