function runDemo(){
//    let outputDataListElement = document.querySelector("#jsReturnList");
//        let list = outputDataListElement.getAttribute("data").split(",");
//        for(let item in list){
//            outputDataListElement.innerHTML += `<li>${list[item].replace("L", "9").replace("[", "").replace("]", "")}</li>`;
//        }

    let chartDataElement = document.querySelector("#chart");
         let chartData = chartDataElement.getAttribute("xyValues").split(",");


// CODE TO CONVERT A HASH MAP TO CREATE AN OBJECT FOR A SCATTER PLOT
//        let xyValues = [];
//         for(let valueSet in chartData){
//            let xy = chartData[valueSet].replace("{","").replace("}", "");
//            let xyArray = xy.split("=");
//            let xValue = xyArray[0];
//            console.log(xValue);
//            let yValue = xyArray[1];
//            console.log(yValue);
//            let xyPair = {x:xValue, y:yValue};
//            xyValues.push(xyPair);
//         }

//        new Chart("myChart", {
//          type: "scatter",
//          data: {
//            datasets: [{
//              pointRadius: 4,
//              pointBackgroundColor: "rgb(0,0,255)",
//              data: xyValues
//            }]
//          },
//          options: {
//            legend: {display: false},
//            scales: {
//              xAxes: [{ticks: {min: 40, max:160}}],
//              yAxes: [{ticks: {min: 6, max:16}}],
//            }
//          }
//        });

        let xValues = [];
        let yValues = [];
        let barColors = ["#245761", "#3B90A1","#52C8E0","#58D4ED","#4AB2C7"];

        for(let valueSet in chartData){
           let xy = chartData[valueSet].replace("{","").replace("}", "");
           let xyArray = xy.split("=");
           let xValue = xyArray[0];
           let yValue = xyArray[1];
           xValues.push(xValue);
           yValues.push(yValue);
        }

        new Chart("myChart", {
          type: "bar",
          data: {
            labels: xValues,
            datasets: [{
              backgroundColor: barColors,
              data: yValues
            }]
          },
          options: {
            legend: {display: false},
            title: {
              display: true,
              text: "Will need to pass this text thru the Controller"
            }
          }
        });
}




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