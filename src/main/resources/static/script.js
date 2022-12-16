window.addEventListener('load', function(){
    console.log("inside window event listener, on load");

    //
    if(document.title == "Supervisor Portal") {
        runDashboardFunction();
    }
    if(document.title == "Metrics"){
        viewBarGraph();
    }
    if(document.title == "Timesheet"){
        let form = document.getElementById("create-line-entry");
        form.addEventListener("submit", function(event){
        let hours = document.querySelector("input[name=hours]");
            if (hours.value >= 18 || hours.value <= 0){
                alert("You can only work between 1 to 18 hours");
                event.preventDefault();
            }
        });
    }
    if(document.title == "Manage Shipments"){
        let today = new Date();
        let currentMonth = today.getMonth();
        let currentYear = today.getFullYear();
        let selectYear = document.getElementById("year");
        let selectMonth = document.getElementById("month");

        let months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
        let monthAndYear = document.getElementById("monthAndYear");

        console.log(today);
        createCalendar(today, currentMonth, currentYear);
    }

    //allows for header when employee portal logged in to mobile friendly
    let toggleButton = document.getElementsByClassName('toggle-button')[0]
    let navbarLinks = document.getElementsByClassName('nav-bar-links')[0]
    toggleButton.addEventListener('click', () => {
        navbarLinks.classList.toggle('active')
    })




});



