function runCharts(){

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

}

function viewBarGraph(){

    console.log("viewBarGraph")

    let chartDataElement = document.querySelector("#chart");
         let chartData = chartDataElement.getAttribute("xyValues").split(",");

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

function viewScatterPlotGraph(){


    let chartDataElement = document.querySelector("#chart");
         let chartData = chartDataElement.getAttribute("xyValues").split(",");

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
                   type: "scatter",
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

}

function viewSubOptions(){
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