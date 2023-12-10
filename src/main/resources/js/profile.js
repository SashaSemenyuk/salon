window.onscroll = fuction(); {scrollFunction()};

function scrollFunction(){
    let scrollPos = 200;
    let header = document.getElementsByClassName('header');

    if(document.body.scrollTop > scrollPos || document.documentElement.scrollTop > scrollPos){
        header.classList.add('active');
    } else{
        header.classList.remove('active');
    }
}

var count = 1
setTimeout(demo, 500)
setTimeout(demo, 700)
setTimeout(demo, 900)
setTimeout(reset, 2000)

setTimeout(demo, 2500)
setTimeout(demo, 2750)
setTimeout(demo, 3050)


var mousein = false
function demo() {
    if(mousein) return
    document.getElementById('demo' + count++)
        .classList.toggle('hover')

}

function demo2() {
    if(mousein) return
    document.getElementById('demo2')
        .classList.toggle('hover')
}

function reset() {
    count = 1
    var hovers = document.querySelectorAll('.hover')
    for(var i = 0; i < hovers.length; i++ ) {
        hovers[i].classList.remove('hover')
    }
}

document.addEventListener('mouseover', function() {
    mousein = true
    reset()
})