
function createCalendar(today, month, year, eventData){
  let firstDay = (new Date(year, month)).getDay();
  let daysInMonth = 32 - new Date(year, month, 32).getDate();

  let tbl = document.getElementById("calendar-body"); // body of the calendar
  console.log(tbl);
  // let tbl = document.createElement("tbody"); // body of the calendar
  // console.log(tbl);

  // clearing all previous cells
  // tbl.innerHTML = "";

  // filing data about month and in the page via DOM.
  // monthAndYear.innerHTML = months[month] + " " + year;
  // selectYear.value = year;
  // selectMonth.value = month;

  // creating all cells
  let date = 1;
  for (let i = 0; i < 6; i++) {
      // creates a table row
      let row = document.createElement("tr");

      //creating individual cells, filing them up with data.
      for (let j = 0; j < 7; j++) {
          if (i === 0 && j < firstDay) {
              let cell = document.createElement("td");
              let cellText = document.createTextNode("");
              cell.appendChild(cellText);
              row.appendChild(cell);
          }
          else if (date > daysInMonth) {
              break;
          }

          else {
              let cell = document.createElement("td");
              let cellText = document.createTextNode(date);
              if (date === today.getDate() && year === today.getFullYear() && month === today.getMonth()) {
                  cell.classList.add("bg-info");
                  console.log("bg-info");
                  console.log("bg-info");
              } // color today's date
              cell.appendChild(cellText);
              cell.setAttribute('id', 'date-'+date);
              row.appendChild(cell);
              date++;
          }


      }

      tbl.appendChild(row); // appending each row into calendar body.
  }

  let displayMonth = new Intl.DateTimeFormat("en-US", { month: "long" }).format(today);
  document.getElementById("selected-month").innerHTML = displayMonth;

}

function loadEventData(){
    let eventCount = document.getElementById("eventTotal").innerHTML;

        class Event {
            constructor(name, startDate, endDate) {
                this.name = name;
                this.startDate = startDate;
                this.endDate = endDate;
            }
        }

        let arrayOfEvents = [];

        for(let i = 1; i <= eventCount; i++){
            let eventNameElement = document.querySelector("#eventName"+i);
            let eventName = eventNameElement.getAttribute("eventName");

            let eventStartDateElement = document.querySelector("#startDate"+i);
            let eventStart = eventStartDateElement.getAttribute("eventStart");

            let yearStart = Number(eventStart.slice((eventStart.indexOf(",YEAR=")+6),(eventStart.indexOf(",YEAR=")+10)));
            let monthStart = Number(eventStart.slice((eventStart.indexOf(",MONTH=")+7),eventStart.indexOf(",WEEK_OF_YEAR=")));
            let dateStart = Number(eventStart.slice((eventStart.indexOf(",DAY_OF_MONTH=")+14),(eventStart.indexOf(",DAY_OF_YEAR="))));
            let eventStartDate = new Date(yearStart, monthStart, dateStart);

            let eventEndDateElement = document.querySelector("#endDate"+i);
            let eventEnd = eventEndDateElement.getAttribute("eventEnd");
            let year = Number(eventEnd.slice((eventEnd.indexOf(",YEAR=")+6),(eventEnd.indexOf(",YEAR=")+10)));
            let month = Number(eventEnd.slice((eventEnd.indexOf(",MONTH=")+7),eventEnd.indexOf(",WEEK_OF_YEAR=")));
            let date = Number(eventEnd.slice((eventEnd.indexOf(",DAY_OF_MONTH=")+14),(eventEnd.indexOf(",DAY_OF_YEAR="))));

            let eventEndDate = new Date(year, month, date);

            let event = new Event(eventName, eventStartDate, eventEndDate);
            arrayOfEvents.push(event);
        }

    return arrayOfEvents;
}


function populateCalendarWithEvents(todayDate, eventData){

    for(let i = 0; i < eventData.length; i++){
        //if current month is the same month as the date's start date / end date
        let startDateDayOfMonth = eventData[i].startDate.getDate();
        let eventName = eventData[i].name;
        let day = document.getElementById('date-'+startDateDayOfMonth);
        console.log(day);
        day.innerHTML=startDateDayOfMonth + "<div>"+eventName+"</div>";
    }
}