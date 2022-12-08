import dayjs from "dayjs";

function viewCalendar(){
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
              July 2020
            </div>
            <!-- Pagination -->
            <div class="calendar-month-header-selectors">
              <span id="previous-month-selector"><</span>
              <span id="present-month-selector">Today</span>
              <span id="next-month-selector">></span>
            </div>
          </section>

          <!-- Calendar grid header -->
          <ol
            id="days-of-week"
            class="day-of-week"
          >
            <li>Sun</li>
            <li>Mon</li>
            <li>Tues</li>
            <li>Wed</li>
            <li>Thur</li>
            <li>Fri</li>
            <li>Sat</li>
          </ol>
          <!-- Calendar grid -->
          <ol
            id="calendar-days"
            class="date-grid"
          >
            <li class="calendar-day">
              <span>
                1
              </span>
              ...
              <span>
                29
              </span>
            </li>
          </ol>
        </div>
        `;
}
