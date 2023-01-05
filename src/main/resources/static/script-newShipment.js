function viewSubOptionsForShipmentType(){

        if (document.getElementById("INCOMING").selected){
            document.getElementById("subOptionsIncoming").style.display = "block";
            document.getElementById("subOptionsOutgoing").style.display = "none";

        } else if (document.getElementById("OUTGOING").selected){
            document.getElementById("subOptionsIncoming").style.display = "none";
            document.getElementById("subOptionsOutgoing").style.display = "block";

        }


}