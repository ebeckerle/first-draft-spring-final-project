window.addEventListener('load', function(){
    console.log("inside window event listener, on load");
    if(document.title == "Supervisor Portal") {
        runDashboardFunction();
    }
    if(document.title == "Metrics"){
        viewBarGraph();
    }
});


//function runFormValidation(){
//    let userSelectedProject = document.querySelector("#jsLineEntry");
//    userSelectedProject.innerHTML = userSelectedProject.getAttribute("jsProject")
//}



//window.addEventListener("load", function()){
//    let form = document.querySelector("form");
//    form.addEventListener("submit", function(event){
//        let hours = document.querySelector("input[name=hours]");
//        if (hours.value >= 24 || hours.value <= 0){
//            alert("you can only work between 1 to 24 hours");
//            event.preventDefault();
//        }
//    })
//}