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