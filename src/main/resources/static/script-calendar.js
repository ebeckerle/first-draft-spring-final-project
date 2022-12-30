
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
              if(date === 1 || date === 10 || date === 15){
                let cellText = document.createTextNode(date + "event");
                cell.appendChild(cellText);
                row.appendChild(cell);
                date++;
              }else{
                 let cellText = document.createTextNode(date);
                 cell.appendChild(cellText);
                 row.appendChild(cell);
                 date++;
              }

//              if (date === today.getDate() && year === today.getFullYear() && month === today.getMonth()) {
//                  cell.classList.add("bg-info");
//              } // color today's date
//              cell.appendChild(cellText);
//              row.appendChild(cell);
//              date++;
          }


      }

      tbl.appendChild(row); // appending each row into calendar body.
  }

  console.log(tbl);

  let displayMonth = new Intl.DateTimeFormat("en-US", { month: "long" }).format(month);

  document.getElementById("selected-month").innerHTML = displayMonth;

}

function loadEventData(){
    let eventCount = document.getElementById("eventTotal").innerHTML;
        console.log(eventCount);

        class Event {
            constructor(name, startDate, endDate) {
                this.name = name;
                this.startDate = startDate;
                this.endDate = endDate;
            }
        }

        let arrayOfEvents = [];

        for(let i = 1; i <= eventCount; i++){
            console.log("#eventName"+i)
            let eventNameElement = document.querySelector("#eventName"+i);
            let eventName = eventNameElement.getAttribute("eventName");
            console.log(eventName);
            let eventStartDateElement = document.querySelector("#startDate"+i);
            let eventStart = eventStartDateElement.getAttribute("eventStart");
            console.log(eventStart);
            let eventEndDateElement = document.querySelector("#endDate"+i);
            let eventEnd = eventEndDateElement.getAttribute("eventEnd");
            console.log(eventEnd);
            let event = new Event(eventName, eventStart, eventEnd);
            arrayOfEvents.push(event);
        }
        console.log(arrayOfEvents);

    return arrayOfEvents;
}