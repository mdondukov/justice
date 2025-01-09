document.addEventListener('htmx:afterSwap', () => {
    console.log('HTMX swap complete. Reinitializing Flowbite components...');

    // Инициализация выпадающих списков
    document.querySelectorAll('[data-dropdown-toggle]').forEach((toggle) => {
        const dropdownId = toggle.getAttribute('data-dropdown-toggle');
        const dropdownElement = document.getElementById(dropdownId);

        if (dropdownElement && !toggle.dataset.dropdownInitialized) {
            new Dropdown(dropdownElement, toggle);
            toggle.dataset.dropdownInitialized = true;
        }
    });

    // Инициализация модальных окон
    document.querySelectorAll('[data-modal-toggle]').forEach((toggle) => {
        const modalId = toggle.getAttribute('data-modal-toggle');
        const modalElement = document.getElementById(modalId);

        if (modalElement && !toggle.dataset.modalInitialized) {
            new Modal(modalElement);
            toggle.dataset.modalInitialized = true;
        }
    });

    // Инициализация тултипов
    document.querySelectorAll('[data-tooltip-target]').forEach((tooltip) => {
        const tooltipId = tooltip.getAttribute('data-tooltip-target');
        const tooltipElement = document.getElementById(tooltipId);

        if (tooltipElement && !tooltip.dataset.tooltipInitialized) {
            new Tooltip(tooltipElement, tooltip);
            tooltip.dataset.tooltipInitialized = true;
        }
    });

    console.log('Flowbite components re-initialized');
});
