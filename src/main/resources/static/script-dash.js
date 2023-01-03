function runDashboardFunction(){

//TODO - use for loop to make this code more sustainable -
    let project1 = document.getElementById("1");
    project1.style.backgroundColor = "#245761";
    let project2 = document.getElementById("2");
    project2.style.backgroundColor = "#3B90A1";
    let project3 = document.getElementById("3");
    project3.style.backgroundColor = "#52C8E0";
    let project4 = document.getElementById("4");
    project4.style.backgroundColor = "#4AB2C7";
}

function displaySubMenu(menu){
    console.log(menu);
    if(menu == "employees"){
        let subMenu = document.getElementById("subMenu-employees");
        subMenu.style.display = "inline-block";
    }
    console.log("fox");

}

function hideSubMenu(menu){
    if(menu == "employees"){
        let subMenu = document.getElementById("subMenu-employees");
        subMenu.style.display = "inline-block";
    }
}