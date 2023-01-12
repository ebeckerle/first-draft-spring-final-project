function runAddContactFormValidation(){
    //need to validate -  a type is chosen
    // -if there is something in the ZipCode it is 5 digits
    // -

}


function runStateAutoFill(){
//TODO  - send 2 array lists - one full name and one postal codes (or maybe a Hashmap?) from the Java.
//TODO  -Make the select option invisible, but change the selection with the JavaScript?
    let input = document.querySelector("input[name='stateInput']").value;
    console.log(input);
    let states = ["AL", "AK"];
    let autofillState = "";
    for(let i=0; i<states.length; i++){
        if(states[i].charAt(0) == input.charAt(0)){
            console.log("working");
        }
    }
}


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