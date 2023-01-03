function viewSubOptionsForShipmentType(){

       if (document.getElementById("Employee").selected){
                   document.getElementById("subOptionsEmployee").style.display = "block";
                   document.getElementById("subOptionsProject").style.display = "none";
                   document.getElementById("subOptionsWorkType").style.display = "none";
                   document.getElementById("subOptionsPayRate").style.display = "none";
               } else if (document.getElementById("Project").selected){
                   document.getElementById("subOptionsProject").style.display = "block";
                   document.getElementById("subOptionsEmployee").style.display = "none";
                   document.getElementById("subOptionsWorkType").style.display = "none";
                   document.getElementById("subOptionsPayRate").style.display = "none";
               } else if (document.getElementById("WorkType").selected){
                   document.getElementById("subOptionsWorkType").style.display = "block";
                   document.getElementById("subOptionsEmployee").style.display = "none";
                   document.getElementById("subOptionsProject").style.display = "none";
                   document.getElementById("subOptionsPayRate").style.display = "none";
               }else if (document.getElementById("PayRate").selected){
                   document.getElementById("subOptionsEmployee").style.display = "none";
                   document.getElementById("subOptionsProject").style.display = "none";
                   document.getElementById("subOptionsWorkType").style.display = "none";
                   document.getElementById("subOptionsPayRate").style.display = "block";
               }
}