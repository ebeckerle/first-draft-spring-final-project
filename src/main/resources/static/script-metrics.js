function runCharts(){

    let chartDataElement = document.querySelector("#chart");
    let chartData = chartDataElement.getAttribute("xyValues").split(",");
    return chartData;

}

function viewBarGraph(){

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


function createCSVFromChartData(){

    console.log("let's create a CSV from our chart data")
    let chartData = runCharts();
//    console.log(chartData);
    let chartTitleElement = document.querySelector("#chartTitle");
    let chartTitle = chartTitleElement.getAttribute("chartTitle");
//    console.log(chartTitle);

    let csvHeadersElement = document.querySelector("#csvHeaders");
    let csvHeaders = csvHeadersElement.getAttribute("csvHeaders").replace("[", "").replace("]", "").replace(" ", "");
//    console.log(csvHeaders);
    console.log(typeof csvHeaders);

    //csv rows - we will put the data into this array and then
    let csvRows = [];

    //push headers


    for(let valueSet in chartData){
        let xy = chartData[valueSet].replace("{","").replace("}", "").replace(" ", "");
        let xyArray = xy.replace("=", ",");
        csvRows.push(xyArray);
    }

    console.log(csvRows);

    let csv = csvHeaders+"\n";

    csvRows = csvRows.join("\n");

    csv = csv + csvRows;

    console.log(csv);


}
