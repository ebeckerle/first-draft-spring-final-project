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

        let eventData = loadEventData();
        createCalendar(today, currentMonth, currentYear, eventData);
        populateCalendarWithEvents(today, eventData);
    }

//TODO - this is not working - trying to front end validate so that the user cannot
//TODO - submit a new shipment incoming with a new carrier WITHOUT a company name
    if(document.title == "Add Shipment"){
            console.log("in add shipment load")
            let form = document.getElementById("addShipmentForm");
            console.log(form);
            form.addEventListener("submit", function(event){

//                let carrierCompanyNameField = document.getElementById("companyName");
//                let carrierCompanyNameField = document.querySelector("input[name=companyName]").value;

                    if (document.getElementById("INCOMING").selected  && document.getElementById("newCarrier").selected){
                                     alert("A company name for your new carrier is required.");
                                     event.preventDefault();
                                }
//                if (document.getElementById("INCOMING").selected &&
//                document.getElementById("newCarrier").selected
//                && carrierCompanyNameField == null){
//                     alert("A company name for your new carrier is required.");
//                     event.preventDefault();
//                }
            });
        }

    //allows for header when employee portal logged in to mobile friendly
    let toggleButton = document.getElementsByClassName('toggle-button')[0]
    let navbarLinks = document.getElementsByClassName('nav-bar-links')[0]
    toggleButton.addEventListener('click', () => {
        navbarLinks.classList.toggle('active')
    })

});



