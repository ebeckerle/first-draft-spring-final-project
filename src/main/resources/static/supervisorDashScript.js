runFunction();
function runFunction(){
    let textElementArray = document.querySelectorAll('.trial');
    console.log("hey yo!");
    textElementArray.forEach(element =>{
        element.style = `color: #245761`;
    });

}