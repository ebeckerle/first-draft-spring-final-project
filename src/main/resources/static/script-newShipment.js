function viewSubOptionsForShipmentType(){

        if (document.getElementById("INCOMING").selected){
            document.getElementById("subOptionsIncoming").style.display = "block";
            document.getElementById("subOptionsOutgoing").style.display = "none";

        } else if (document.getElementById("OUTGOING").selected){
            document.getElementById("subOptionsIncoming").style.display = "none";
            document.getElementById("subOptionsOutgoing").style.display = "block";

        }

}

function addANewContactForCarrier(){

    if (document.getElementById("newCarrier").selected){
        let a = document.createElement("a");
        a.href = "/supervisor/managecontacts";
        let event = document.createEvent("MouseEvents");
        event.initMouseEvent("click", true, true, window, 0, 0, 0, 0, 0, true, false, false, false, 0, null);
        a.dispatchEvent(event);
    }
}