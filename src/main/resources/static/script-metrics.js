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
    let chartTitleElement = document.querySelector("#chartTitle");
         let chartData = chartDataElement.getAttribute("xyValues").split(",");
         let chartTitle = chartTitleElement.getAttribute("chartTitle");

        let xValues = [];
        let yValues = [];
        let barColors = ["#245761", "#3B90A1","#52C8E0","#4AB2C7", "#5233BD", "#58D4ED", "#2B31A1", "#4D80F0","#2D73A6", "#4B7EB8", "#384DA6", "#6A5DF0", "#5D37A1", "#9646C2", "#75D8F5", "#7AE0F5", "#1C52BD"];

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
              text: chartTitle
            }
          }
        });
}

function viewScatterPlotGraph(){
    let chartDataElement = document.querySelector("#chart");
    let chartTitleElement = document.querySelector("#chartTitle");
         let chartData = chartDataElement.getAttribute("xyValues").split(",");
         let chartTitle = chartTitleElement.getAttribute("chartTitle");

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
                       text: chartTitle
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

function viewPieGraph(){
    let chartDataElement = document.querySelector("#chart");
    let chartTitleElement = document.querySelector("#chartTitle");
    let chartData = chartDataElement.getAttribute("xyValues").split(",");
    let chartTitle = chartTitleElement.getAttribute("chartTitle");

    let xValues = [];
    let yValues = [];
    let colors = ["#245761", "#3B90A1","#52C8E0","#4AB2C7", "#5233BD", "#58D4ED", "#2B31A1", "#4D80F0","#2D73A6", "#4B7EB8", "#384DA6", "#6A5DF0", "#5D37A1", "#9646C2", "#75D8F5", "#7AE0F5", "#1C52BD"];

    for(let valueSet in chartData){
        let xy = chartData[valueSet].replace("{","").replace("}", "");
        let xyArray = xy.split("=");
        let xValue = xyArray[0];
        let yValue = xyArray[1];
        xValues.push(xValue);
        yValues.push(yValue);
    }

    new Chart("myChart", {
              type: "pie",
              data: {
                labels: xValues,
                datasets: [{
                  backgroundColor: colors,
                  data: yValues
                }]
              },
              options: {
                legend: {display: true},
                title: {
                  display: true,
                  text: chartTitle
                }
              }
            });

}

function viewPolarAreaGraph(){
    let chartDataElement = document.querySelector("#chart");
    let chartTitleElement = document.querySelector("#chartTitle");
    let chartData = chartDataElement.getAttribute("xyValues").split(",");
    let chartTitle = chartTitleElement.getAttribute("chartTitle");

    let xValues = [];
    let yValues = [];
    let colors = ["#245761", "#3B90A1","#52C8E0","#4AB2C7", "#5233BD", "#58D4ED", "#2B31A1", "#4D80F0","#2D73A6", "#4B7EB8", "#384DA6", "#6A5DF0", "#5D37A1", "#9646C2", "#75D8F5", "#7AE0F5", "#1C52BD"];

    for(let valueSet in chartData){
        let xy = chartData[valueSet].replace("{","").replace("}", "");
        let xyArray = xy.split("=");
        let xValue = xyArray[0];
        let yValue = xyArray[1];
        xValues.push(xValue);
        yValues.push(yValue);
    }

    new Chart("myChart", {
              type: "polarArea",
              data: {
                labels: xValues,
                datasets: [{
                  backgroundColor: colors,
                  data: yValues
                }]
              },
              options: {
                legend: {display: true},
                title: {
                  display: true,
                  text: chartTitle
                }
              }
            });

            //TODO: figure out how to increase width of axes
            //                options: {
            //                    scales: {
            //                        yAxes: [{
            //                            ticks: {
            //                                beginAtZero: true
            //                            }
            //                        }]
            //                    }
            //                }
            //            });

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