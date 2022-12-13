
function viewCalendar(calendarBody, currentMonth){

  document.getElementById("calendar").innerHTML = `
  <!-- Parent container for the calendar month -->
  <div class="calendar-month">
      <!-- The calendar header -->
      <section class="calendar-month-header">
        <!-- Month name -->
        <div
          id="selected-month"
          class="calendar-month-header-selected-month"
        >
          ${currentMonth}
        </div>
        <!-- Pagination -->
        <div class="calendar-month-header-selectors">
          <span id="previous-month-selector"> </span>
          <span id="present-month-selector">${currentMonth}</span>
          <span id="next-month-selector"> </span>
        </div>
      </section>

      <table>
          <!-- Calendar grid header -->
          <thead id="days-of-week" class="day-of-week">
              <tr>
                  <th>Sun</th>
                  <th>Mon</th>
                  <th>Tue</th>
                  <th>Wed</th>
                  <th>Thu</th>
                  <th>Fri</th>
                  <th>Sat</th>
              </tr>
          </thead>
          <!-- Calendar grid -->
          <!--<tbody id="calendar-body">-->
            ${calendarBody}
            <!--</tbody>-->
      </table>

      </div>
      `;
}

function createCalendar(month, year){
  let firstDay = (new Date(year, month)).getDay();
  let daysInMonth = 32 - new Date(year, month, 32).getDate();


  // let tbl = document.getElementById("calendar-body"); // body of the calendar
  // console.log(tbl);
  let tbl = document.createElement("tbody"); // body of the calendar
  console.log(tbl);



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
              } // color today's date
              cell.appendChild(cellText);
              row.appendChild(cell);
              date++;
          }


      }

      tbl.appendChild(row); // appending each row into calendar body.
  }

  return tbl;

}