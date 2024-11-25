document.addEventListener('DOMContentLoaded', () => {
    // Найти все кнопки табов
    const tabs = document.querySelectorAll('[data-tabs-target]');
    const tabContents = document.querySelectorAll('[id^="documents-tab-content"] [role="tabpanel"]');

    tabs.forEach(tab => {
        tab.addEventListener('click', () => {
            // Снять активность со всех вкладок и скрыть все панели
            tabs.forEach(t => {
                t.classList.remove('text-deep-sky-900', 'border-deep-sky-800');
                t.classList.add('text-gray-500', 'hover:text-gray-400', 'hover:border-gray-300');
                t.setAttribute('aria-selected', 'false');
            });

            tabContents.forEach(content => {
                content.classList.add('hidden');
            });

            // Активировать выбранную вкладку и её содержимое
            tab.classList.add('text-deep-sky-900', 'border-deep-sky-800');
            tab.classList.remove('text-gray-500', 'hover:text-gray-400', 'hover:border-gray-300');
            tab.setAttribute('aria-selected', 'true');

            const target = document.querySelector(tab.getAttribute('data-tabs-target'));
            if (target) {
                target.classList.remove('hidden');
            }
        });
    });
});
