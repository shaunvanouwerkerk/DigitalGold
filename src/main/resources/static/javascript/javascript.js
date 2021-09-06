const menuButton = document.getElementsByClassName('menu-button')[0];
const menuLinks = document.getElementsByClassName('nav-links')[0];

menuButton.addEventListener('click',() => {
    menuLinks.classList.toggle('active')
})


let i = 0;
let txt = 'Welcome to your gateway to crypto.';
let speed = 50;
		
function typeWriter() {
    if (i < txt.length) {
        document.getElementById("tagline").innerHTML += txt.charAt(i);
        i++; setTimeout(typeWriter, speed);
    }
}
