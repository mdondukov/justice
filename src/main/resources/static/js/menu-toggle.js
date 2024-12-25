document.addEventListener('DOMContentLoaded', () => {
    const toggleButton = document.querySelector('[data-collapse-toggle="navbar-default"]');
    const menu = document.getElementById('navbar-default');

    if (toggleButton && menu) {
        toggleButton.addEventListener('click', () => {
            menu.classList.toggle('hidden');
        });
    }
});
