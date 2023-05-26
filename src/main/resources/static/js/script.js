// var nam = window.prompt("Name?")
// window.alert(`Hello ${nam}`)

hello()

function hello(){
    console.log("hello")
}

window.addEventListener('click',process)
document.getElementById('button').addEventListener('click',process2)

function process(evt){
    console.log(evt.clientX + " " +evt.clientY)
}

function process2(evt){
    document.getElementsByTagName("h1")[0].innerHTML = 
        document.getElementById("uname").value
}