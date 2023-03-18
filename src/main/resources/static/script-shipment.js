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

function toggleViewListOrCalendar(){
    console.log("jin the toggle view list or calendar function");

    if(document.getElementById("viewList").checked){
        console.log("in the view list selected");
        document.getElementById("event-data-list").style.display = "block";
        document.getElementById("calendar").style.display = "none";
    }
    if(document.getElementById("viewCalendar").checked){
        document.getElementById("event-data-list").style.display = "none";
        document.getElementById("calendar").style.display = "block";
    }


}