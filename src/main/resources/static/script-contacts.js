function runAddContactFormValidation(){
    //TODO - Front End need to validate
    //-  a contact type is chosen
    let contactType = document.querySelector("select[name='contactType']").value;
    console.log(contactType);
    if(contactType == "" || contactType == null){
        console.log("this works");
    }
    // - a company name is chosen
    // -if there is something in the ZipCode it is 5 digits
    // -if there is something in the state is two alpha characters
    // -if there is something in the email it is in the following format myemail@email.com
    // - if there is something in the phone number it is 10 digits

}

//  ---- SOLVED THIS a Different way, keeping code around for reference -- 1/13 ----------
//function runStateAutoFill(){
//    let input = document.querySelector("input[name='stateInput']").value;
//    console.log(input);
//    let states = ["AL", "AK", "AR", "AZ"];
//    let autofillState = "";
//    console.log(typeof input);
//    console.log(input.length);
//    console.log(typeof input.length);
//    if(input.length == 1){
//        for(let i=0; i<states.length; i++){
//            if(states[i].charAt(0) == input.charAt(0)){
//                console.log("working");
//                document.getElementById("stateInput").value = states[i];
//                break;
//            }
//        }
//    }else if (input.length == 2){
//        console.log("input greater than one character")
//        for(let i=0; i<states.length; i++){
//            if(states[i].charAt(0) == input.charAt(0) && states[i].charAt(1) == input.charAt(1)){
//            document.getElementById("stateInput").value = states[i];
//            break;
//            }
//        }
//    }
//}



//TODO - DRY out - these 6 functions could maybe be 3?? - click and unclick radio button to show a second form input
let radioStateEmail = false;
let radioStatePhoneNumber = false;
function viewSecondEmailForm(){

    if(radioStateEmail == false) {
        checkRadioButtonEmail();
        radioStateEmail = true;
        document.getElementById("secondEmail").style.display = "block";
    }else{
        uncheckRadioButtonEmail();
        radioStateEmail = false;
        document.getElementById("secondEmail").style.display = "none";
        //TODO - if button is filled with data when the radio button is unchecked then the data disappears
    }
}


function viewSecondPhoneNumberForm(){

        if(radioStatePhoneNumber == false) {
            checkRadioButtonPhoneNumber();
            radioStatePhoneNumber = true;
            document.getElementById("secondPhoneNumber").style.display = "block";
        }else{
            uncheckRadioButtonPhoneNumber();
            radioStatePhoneNumber = false;
            document.getElementById("secondPhoneNumber").style.display = "none";
            //TODO - if button is filled with data when the radio button is unchecked then the data disappears
        }
    if (document.getElementById("addPhoneNumber").checked){
        document.getElementById("secondPhoneNumber").style.display = "block";
    }

}

function checkRadioButtonEmail(){
    document.getElementById("addEmail").checked = true;
}

function uncheckRadioButtonEmail(){
    document.getElementById("addEmail").checked = false;
}

function checkRadioButtonPhoneNumber(){
    document.getElementById("addPhoneNumber").checked = true;
}

function uncheckRadioButtonPhoneNumber(){
    document.getElementById("addPhoneNumber").checked = false;
}